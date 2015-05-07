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
	public Model() {
		this.attributes = new HashMap<String, String[]>();
		this.header = new ArrayList<String>();
		this.data = new ArrayList<String[]>();
	}
	public Map<String, String[]> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String[]> attributes) {
		this.attributes = attributes;
	}
	public ArrayList<String> getHeader() {
		return header;
	}
	public void setHeader(ArrayList<String> header) {
		this.header = header;
	}
	public ArrayList<String[]> getData() {
		return data;
	}
	public void setData(ArrayList<String[]> data) {
		this.data = data;
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
}
