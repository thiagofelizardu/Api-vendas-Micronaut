package com.phoebus.s3;


import io.micronaut.objectstorage.response.UploadResponse;

public interface S3Service {

    UploadResponse put(String key, Object to);

    void delete(String key);

}
