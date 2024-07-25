package com.phoebus.s3;

import br.com.phoebus.object.storage.minio.MinIOStorageOperations;
import io.micronaut.context.annotation.Requires;
import io.micronaut.objectstorage.ObjectStorageOperations;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
@Requires(env = "minio")
public class MinIOProvider extends S3Provider{

    @Inject
    MinIOStorageOperations operations;

    @Override
    public ObjectStorageOperations getOperations() {
        return operations;
    }
}

