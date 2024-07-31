package com.phoebus.s3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.phoebus.entites.Produto;
import io.micronaut.objectstorage.request.UploadRequest;
import io.micronaut.objectstorage.response.UploadResponse;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class S3ServiceImpl implements S3Service{

    @Inject
    private S3Provider provider;

    @Inject
    private ObjectMapper mapper;

    @PostConstruct
    void init() {
        mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
    }

    @Override
    public UploadResponse put(String key, Object to) {
        try {
            byte[] bytes = mapper.writeValueAsBytes(to);
            UploadRequest req = UploadRequest.fromBytes(bytes, key);
            return this.provider.getOperations().upload(req);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String key) {
        try {
            this.provider.getOperations().delete(key);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar"+e);
        }
    }
}
