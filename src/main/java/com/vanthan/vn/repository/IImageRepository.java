package com.vanthan.vn.repository;

import com.vanthan.vn.model.entity.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<UploadFile, Long> {
    UploadFile findUploadFileByOriginUrl(String url);

}
