package com.example.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.heroesapi.constans.HeroesConstant.DYNAMO_ENDPOINT;
import static com.example.heroesapi.constans.HeroesConstant.DYNAMO_REGION;

@Configuration
@EnableDynamoDBRepositories
public class HeroesTable {

    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(DYNAMO_REGION, DYNAMO_ENDPOINT))
                .build();
        DynamoDB dynamoDB = new DynamoDB(client);
        String tableName = "heroes";

        try {
            Table table = dynamoDB.createTable(tableName,
                    List.of(new KeySchemaElement("id", KeyType.HASH)),
                    List.of(new AttributeDefinition("id", ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L, 5L)
            );

            table.waitForActive();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
