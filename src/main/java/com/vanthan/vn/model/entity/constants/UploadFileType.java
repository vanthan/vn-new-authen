package com.vanthan.vn.model.entity.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UploadFileType {
    VIDEO,
    IMAGE;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
