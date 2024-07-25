package com.phoebus.s3;

import io.micronaut.objectstorage.ObjectStorageOperations;

public abstract class S3Provider {
    public abstract ObjectStorageOperations getOperations();
}
