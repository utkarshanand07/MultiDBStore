package com.marvel.multidbstore.repository;

import com.marvel.multidbstore.entity.DynamoDBProduct;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;


import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DynamoDBProductRepository {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<DynamoDBProduct> productTable;

    @PostConstruct
    public void init() {
        productTable = dynamoDbEnhancedClient.table("inventory", TableSchema.fromBean(DynamoDBProduct.class));
    }

    public DynamoDBProduct save(DynamoDBProduct product) {
        productTable.putItem(product);
        return product;
    }

    public DynamoDBProduct findById(String id) {
        return productTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public List<DynamoDBProduct> findAll() {
        List<DynamoDBProduct> products = new ArrayList<>();
        productTable.scan().items().forEach(products::add);
        return products;
    }

    public void deleteById(String id) {
        productTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public void deleteAll() {
        productTable.scan().items().forEach(item ->
                productTable.deleteItem(r -> r.key(k -> k.partitionValue(item.getId())))
        );
    }
}
