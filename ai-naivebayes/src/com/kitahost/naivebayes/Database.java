package com.kitahost.naivebayes;

import javax.swing.JOptionPane;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database{
	private MongoClient client;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	
	public Database(String host, String database,String collection) {
		this.client = new MongoClient(host,27017);
		this.database = this.client.getDatabase(database);
		this.collection = this.database.getCollection(collection);
	}

	public MongoClient getClient() {
		return client;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public MongoCollection<Document> getCollection() {
		return collection;
	}
	
}
