package com.kitahost.naivebayes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model {
	private Map<String, String[]> attributes;
	private ArrayList<String> header;
	private ArrayList<String[]> data;
	private int numTraining;
	private int numTesting;
	private Database db;
	
	public Model() {
		this.attributes = new HashMap<String, String[]>();
		this.header = new ArrayList<String>();
		this.data = new ArrayList<String[]>();
		this.db = new Database("localhost", "root", "rizkidoank", "car");
	}
	public void clearModel(){
		this.attributes = new HashMap<String, String[]>();
		this.header = new ArrayList<String>();
		this.data = new ArrayList<String[]>();
	}
	
	public int getNumTraining() {
		return numTraining;
	}
	
	public void setNumTraining(int numTraining) {
		this.numTraining = numTraining;
	}
	
	public int getNumTesting() {
		return numTesting;
	}
	
	public void setNumTesting(int numTesting) {
		this.numTesting = numTesting;
	}
	
	public Map<String, String[]> getAttributes() {
		return attributes;
	}
	
	public ArrayList<String> getHeader() {
		return header;
	}
	
	public ArrayList<String[]> getData() {
		return data;
	}
	
	public ArrayList<String[]> getDataTraining(){
		ArrayList<String[]> dataTraining = new ArrayList<String[]>();
		for (int i = 0; i < this.getNumTraining(); i++) {
			dataTraining.add(this.getData().get(i));
		}
		return dataTraining;
	}
	
	public ArrayList<String[]> getDataTesting(){
		ArrayList<String[]> dataTesting = new ArrayList<String[]>();
		for (int i = this.getNumTraining(); i < this.getData().size(); i++) {
			dataTesting.add(this.getData().get(i));
		}
		return dataTesting;
	}

	public Database getDb() {
		return db;
	}

	
}
