package com.browserblend.fileservice.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.browserblend.fileservice.aws.AWSCredentialsProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {
    @Bean
    public AWSCredentialsProvider awsCredentials() {
        return new AWSCredentialsProviderImpl(
                
        );
    }
    @Bean
    public AmazonS3 amazonS3(AWSCredentialsProvider awsCredentialsProvider) {
        return AmazonS3Client.builder()
                .withRegion("us-east-2")
                .withCredentials(awsCredentialsProvider)
                .build();
    }
}
