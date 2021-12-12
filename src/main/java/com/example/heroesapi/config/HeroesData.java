package com.example.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import static com.example.heroesapi.constans.HeroesConstant.DYNAMO_ENDPOINT;
import static com.example.heroesapi.constans.HeroesConstant.DYNAMO_REGION;

public class HeroesData {

    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(DYNAMO_REGION, DYNAMO_ENDPOINT))
                .build();
        DynamoDB dynamoDB = new DynamoDB(client);
        Table table = dynamoDB.getTable("heroes");
        Item hero = new Item()
                .withPrimaryKey("id", 1)
                .withString("name", "Spider Man")
                .withString("universe", "marvel")
                .withString("movies", "9");

        PutItemOutcome outcome = table.putItem(hero);

    }

}
