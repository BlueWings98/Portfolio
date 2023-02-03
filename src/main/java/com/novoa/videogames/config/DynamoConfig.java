package com.novoa.videogames.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.novoa.videogames.repository")
public class DynamoConfig {
    @Value("${amazon.dynamodb.endpoint}")
    private String endpoint;
    @Value("${amazon.aws.region}")
    private String region;
    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(this.endpoint,this.region))
                .withCredentials(awsCredentialsProvider()).build();
        return amazonDynamoDB;
    }
    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(){
        String accessKey = System.getenv("amazon_aws_accesskey");
        String secretKey = System.getenv("amazon_aws_secretkey");
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey,secretKey));
    }
}
