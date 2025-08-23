package com.marvel.multidbstore.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DynamoDBProduct {
    private String id;
    private String name;
    private double price;

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
}
