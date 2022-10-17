package com.vanthan.vn.service;

import com.vanthan.vn.model.entity.UploadFile;
import com.vanthan.vn.model.entity.constants.UploadFileType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IFileStorageService {

    UploadFile storeImage(HttpServletRequest request, final MultipartFile file) throws Exception;

    UploadFile storeVideo(HttpServletRequest request, final MultipartFile thumbFile, final MultipartFile videoFile) throws Exception;

    UploadFile getMedia(UploadFileType type, String mediaUrl, String thumbUrl) throws Exception;

    Resource loadFileAsResource(final String fileName) throws Exception;
}
