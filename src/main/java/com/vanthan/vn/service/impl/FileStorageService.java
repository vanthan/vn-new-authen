package com.vanthan.vn.service.impl;


import com.vanthan.vn.config.FileStorageProperties;
import com.vanthan.vn.model.entity.UploadFile;
import com.vanthan.vn.model.entity.constants.UploadFileType;
import com.vanthan.vn.repository.IImageRepository;
import com.vanthan.vn.service.IFileStorageService;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileStorageService implements IFileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    public FileStorageService(final FileStorageProperties fileStorageProperties) throws Exception {
        try {
            fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private static ByteArrayOutputStream createThumbnail(final MultipartFile orginalFile, final int width) {
        try {
            ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
            BufferedImage img = ImageIO.read(orginalFile.getInputStream());
            BufferedImage thumbImg = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, Math.min(img.getWidth(), width), Scalr.OP_ANTIALIAS);
            ImageIO.write(thumbImg, "jpeg", thumbOutput);
            return thumbOutput;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UploadFile storeImage(HttpServletRequest request, final MultipartFile file) throws Exception {
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String orginalName = timeStamp + "_" + StringUtils.cleanPath(file.getOriginalFilename().toLowerCase());
        String thumbName = timeStamp + "_thumb_" + StringUtils.cleanPath(file.getOriginalFilename().toLowerCase());
        if (orginalName.contains("..")) {
            throw new Exception("Sorry! Filename contains invalid path sequence " + orginalName);
        }
        String type = file.getContentType();
        if ((type == null || !type.toLowerCase().startsWith("image")) && !orginalName.endsWith("jpg") && !orginalName.endsWith("jpeg") && !orginalName.endsWith("png")) {
            throw new Exception("Lỗi định dạng file");
        }
        Path orginalLocation = fileStorageLocation.resolve(orginalName);
        Path thumbLocation = fileStorageLocation.resolve(thumbName);
        Files.copy(new ByteArrayInputStream(file.getBytes()), orginalLocation, StandardCopyOption.REPLACE_EXISTING);
        ByteArrayOutputStream thumbOutputStream = createThumbnail(file, 1000);
        if (thumbOutputStream != null) {
            Files.copy(new ByteArrayInputStream(thumbOutputStream.toByteArray()), thumbLocation, StandardCopyOption.REPLACE_EXISTING);
        }
        String url = getURLBase(request);
        url = url.concat("/api/images/");
        BufferedImage bimg = ImageIO.read(file.getInputStream());
        UploadFile image = new UploadFile();
        image.setType(UploadFileType.IMAGE);
        image.setWidth(bimg.getWidth());
        image.setHeight(bimg.getHeight());
        image.setOriginUrl(url.concat(orginalName));
        image.setThumbUrl(url.concat(thumbName));
        image = imageRepository.save(image);
        return image;
    }

    public UploadFile storeVideo(HttpServletRequest request, final MultipartFile thumbFile, final MultipartFile videoFile) throws Exception {
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String orginalName = timeStamp + "_" + StringUtils.cleanPath(videoFile.getOriginalFilename().toLowerCase());
        String thumbName = timeStamp + "_thumb_" + StringUtils.cleanPath(thumbFile.getOriginalFilename().toLowerCase());
        if (orginalName.contains("..")) {
            throw new Exception("Sorry! Filename contains invalid path sequence " + orginalName);
        }
        if (thumbName.contains("..")) {
            throw new Exception("Sorry! Filename contains invalid path sequence " + orginalName);
        }
        String videoType = videoFile.getContentType();
        if ((videoType == null || !videoType.toLowerCase().startsWith("video")) && !orginalName.endsWith("mov") && !orginalName.endsWith("mp4")) {
            throw new Exception("Lỗi định dạng file");
        }
        String imageType = thumbFile.getContentType();
        if ((imageType == null || !imageType.toLowerCase().startsWith("image")) && !thumbName.endsWith("jpg") && !thumbName.endsWith("jpeg") && !thumbName.endsWith("png")) {
            throw new Exception("Lỗi định dạng file");
        }
        Path orginalLocation = fileStorageLocation.resolve(orginalName);
        Files.copy(new ByteArrayInputStream(videoFile.getBytes()), orginalLocation, StandardCopyOption.REPLACE_EXISTING);

        Path thumbLocation = fileStorageLocation.resolve(thumbName);
        Files.copy(new ByteArrayInputStream(thumbFile.getBytes()), thumbLocation, StandardCopyOption.REPLACE_EXISTING);

        String url = getURLBase(request);
        String thumbUrl = url;
        url = url.concat("/api/video/");
        thumbUrl = thumbUrl.concat("/api/images/");
        BufferedImage bimg = ImageIO.read(thumbFile.getInputStream());
        UploadFile image = new UploadFile();
        image.setType(UploadFileType.VIDEO);
        image.setWidth(bimg.getWidth());
        image.setHeight(bimg.getHeight());
        image.setOriginUrl(url.concat(orginalName));
        image.setThumbUrl(thumbUrl.concat(thumbName));
        image = imageRepository.save(image);
        return image;
    }

    @Override
    public UploadFile getMedia(UploadFileType type, String mediaUrl, String thumbUrl) throws Exception {
        UploadFile uploadFile = imageRepository.findUploadFileByOriginUrl(mediaUrl);
        if (uploadFile == null) {
            uploadFile = new UploadFile();
            BufferedImage bimg = ImageIO.read(new URL(thumbUrl));
            uploadFile.setWidth(bimg.getWidth());
            uploadFile.setHeight(bimg.getHeight());
            uploadFile.setOriginUrl(mediaUrl);
            uploadFile.setThumbUrl(thumbUrl);
            uploadFile.setType(type);
            uploadFile = imageRepository.save(uploadFile);
        }
        return uploadFile;
    }

    public Resource loadFileAsResource(final String fileName) throws Exception {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;
        }
        throw new Exception("File not found " + fileName);
    }

    private String getURLBase(HttpServletRequest request) throws MalformedURLException {
        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port;

    }

//    private ByteArrayOutputStream randomGrabberFFmpegImage(String filePath) throws Exception {
//        ByteArrayOutputStream thumbOutput = null;
//        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
//        ff.start();
//        thumbOutput = doExecuteFrame(ff.grabImage());
//        ff.stop();
//        return thumbOutput;
//    }
//
//    private ByteArrayOutputStream doExecuteFrame(Frame f) throws Exception {
//        if (null == f || null == f.image) {
//            return null;
//        }
//        Java2DFrameConverter converter = new Java2DFrameConverter();
//        BufferedImage bi = converter.getBufferedImage(f);
//        ByteArrayOutputStream thumbOutput = new ByteArrayOutputStream();
//        ImageIO.write(bi, "jpg", thumbOutput);
//        return thumbOutput;
//    }
}