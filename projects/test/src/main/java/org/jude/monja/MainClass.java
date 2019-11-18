package org.jude.monja;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.*;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class MainClass {
    MongoDatabase database;
    MongoCollection<Document> collection;

    public MainClass(){
        initConnection();
        testQuery();
        update();
    }

    private void testQuery(){
        collection.find().forEach((Consumer<Document>) document -> System.out.println(document.toJson()));
    }

    private void update(){
        UpdateResult updateResult = collection.findone(lt("i", 100), new Document("$set"));
        System.out.println(updateResult.getModifiedCount());
    }
    private void initConnection(){

        MongoClientURI connectionString = new MongoClientURI("mongodb://127.0.0.1:27017/gssapiServiceName=mongodb");
        MongoClient mongoClient = new MongoClient(connectionString);
        database = mongoClient.getDatabase("mycustomers");

        collection = database.getCollection("customers");

    }

    public static void main(String... args){
        new MainClass();
    }

}
