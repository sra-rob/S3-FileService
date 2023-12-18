package com.browserblend.fileservice.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class AWSCredentialsProviderImpl implements AWSCredentialsProvider {
    private final String awsAccessKeyId;
    private final String awsSecretKey;
    public AWSCredentialsProviderImpl(String awsAccessKeyId, String awsSecretKey) {
        this.awsAccessKeyId = awsAccessKeyId;
        this.awsSecretKey = awsSecretKey;
    }
    @Override
    public AWSCredentials getCredentials() {
        return new CustomAWSCredentials(awsAccessKeyId, awsSecretKey);
    }

    @Override
    public void refresh() {

    }
}
