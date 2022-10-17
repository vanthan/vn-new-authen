package com.vanthan.vn.model.entity;

import com.vanthan.vn.model.entity.constants.UploadFileType;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "vn_upload_files")
@Data
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String originUrl;

    private String thumbUrl;

    private UploadFileType type;

    private Integer width;

    private Integer height;

    private Integer duration;
}
