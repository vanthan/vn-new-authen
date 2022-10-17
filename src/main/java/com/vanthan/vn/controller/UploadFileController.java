package com.vanthan.vn.controller;

import com.vanthan.vn.dto.BaseResponse;
import com.vanthan.vn.model.entity.UploadFile;
import com.vanthan.vn.service.IFileStorageService;
import com.vanthan.vn.util.MultipartFileSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@RequestMapping(value = "/api")
public class UploadFileController extends BaseController {
    @Autowired

    private IFileStorageService fileStorageService;

    @PostMapping("upload-file")
    public ResponseEntity<?> uploadFile(HttpServletRequest httpServletRequest, @RequestParam("file") final MultipartFile file) {
        try {
            if (file == null || httpServletRequest == null) {
                throw new Exception("required_fields");
            }
            if (file.getSize() > 1024 * 1024 * 20) {
                throw new Exception("Dung lượng file quá lớn, vui lòng chọn file nhỏ hơn 20MB");
            }
            UploadFile uploadFile = fileStorageService.storeImage(httpServletRequest, file);
            return ResponseEntity.ok(new BaseResponse(uploadFile, "succecss"));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BaseResponse(ex.getMessage(), null));
        }
    }

    @PostMapping("upload-video")
    public ResponseEntity<?> uploadVideo(HttpServletRequest httpServletRequest,
                                         @RequestParam("thumbFile") final MultipartFile thumbFile,
                                         @RequestParam("videoFile") final MultipartFile videoFile) {
        try {
            if (thumbFile == null || videoFile == null || httpServletRequest == null) {
                throw new Exception("required_fields");
            }
            if (videoFile.getSize() > 1024 * 1024 * 20 || thumbFile.getSize() > 1024 * 1024 * 20) {
                throw new Exception("Dung lượng file quá lớn, vui lòng chọn file nhỏ hơn 20MB");
            }
            UploadFile uploadFile = fileStorageService.storeVideo(httpServletRequest, thumbFile, videoFile);
            return ResponseEntity.ok(new BaseResponse(uploadFile));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BaseResponse(ex.getMessage(), null));
        }
    }

    @GetMapping("images/{fileName:.+}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable final String fileName) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(resource.getInputStream()));
    }

    @GetMapping("download/{fileName:.+}")
    public ResponseEntity<?> getVideo(@PathVariable final String fileName) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(new InputStreamResource(resource.getInputStream()));
    }

    @GetMapping("video/{fileName:.+}")
    public void streamVideo(HttpServletResponse response, HttpServletRequest request, @PathVariable final String fileName) throws Exception {
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        String path = resource.getFile().getPath();
        try {
            MultipartFileSender.fromFile(new File(path))
                    .with(request)
                    .with(response)
                    .serveResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
