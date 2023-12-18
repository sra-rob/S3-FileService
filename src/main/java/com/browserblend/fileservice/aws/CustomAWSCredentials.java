package com.browserblend.fileservice.aws;

import com.amazonaws.auth.AWSCredentials;

public class CustomAWSCredentials implements AWSCredentials {
    private final String awsAccessKeyId;
    private final String awsSecretKey;
    public CustomAWSCredentials(String awsAccessKeyId, String awsSecretKey) {
        this.awsAccessKeyId = awsAccessKeyId;
        this.awsSecretKey = awsSecretKey;
    }
    @Override
    public String getAWSAccessKeyId() {
        return awsAccessKeyId;
    }

    @Override
    public String getAWSSecretKey() {
        return awsSecretKey;
    }
}
