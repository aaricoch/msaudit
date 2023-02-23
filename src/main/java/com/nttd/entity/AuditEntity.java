package com.nttd.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MongoEntity(collection = "BQHAUDIT")
public class AuditEntity extends ReactivePanacheMongoEntity {
    private String application;
    private String applicationUser;
    private String sessionUser;
    private String transactionCode;
    private String transactionDate;
    private String message;
    private String request;
    private String response;
}
