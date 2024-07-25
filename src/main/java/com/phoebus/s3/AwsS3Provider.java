package com.phoebus.s3;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.objectstorage.ObjectStorageOperations;
import io.micronaut.objectstorage.aws.AwsS3Operations;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
@Requires(env = Environment.AMAZON_EC2)
public class AwsS3Provider extends S3Provider{

    @Inject
    private AwsS3Operations operations;

    @Override
    public ObjectStorageOperations getOperations() {
        return operations;
    }
}
