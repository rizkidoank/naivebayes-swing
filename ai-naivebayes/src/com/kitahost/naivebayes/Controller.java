package com.kitahost.naivebayes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Controller {
	private final View view;
	private Model model;
	
	public Controller(View view, Model model){
		this.view = view;
		this.model = model;
	}

	public void loadFileToTable(File file) {
		try {
			this.model = new Model();
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((line=reader.readLine())!=null) {
				if (line.charAt(0)!='@'){
					model.getData().add(line.toLowerCase().split(","));
				}
				else{
					if(
						line.toLowerCase().contains("@data")==false &&
						!line.toLowerCase().startsWith("@relation") &&
						!line.toLowerCase().startsWith("@input") &&
						!line.toLowerCase().startsWith("@output")
					){
						this.model.getHeader().add(line.toLowerCase().split(" ")[1]);
						this.model.getAttributes().put(
							model.getHeader().get(model.getHeader().size()-1),
							line.substring(line.indexOf("{")+1,line.indexOf("}")).toLowerCase().split(",")
						);
					}
					}
				}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadTable(int percent){
		DefaultTableModel tableModelTraining = new DefaultTableModel(this.model.getHeader().toArray(),0);
		DefaultTableModel tableModelTesting = new DefaultTableModel(this.model.getHeader().toArray(),0);
		
		this.view.getTableTraining().setModel(tableModelTraining);
		this.view.getTableTesting().setModel(tableModelTesting);
		
		int nData = this.model.getData().size()*percent/100;
		
		this.model.setNumTraining(nData);
		this.model.setNumTesting(this.model.getData().size()-nData);
		
		for (int i = 0; i < this.model.getNumTraining(); i++) {
			tableModelTraining.addRow(this.model.getData().get(i));
		}
		for (int i = this.model.getNumTraining(); i < this.model.getData().size(); i++) {
			tableModelTesting.addRow(this.model.getData().get(i));
		}
		this.view.getLblTotalTraining().setText("Total : " + this.model.getNumTraining());
		this.view.getLblTotalTesting().setText("Total : " + this.model.getNumTesting());
	}

	public void randomizeData(int percent) {
		int index = 0;
		String[] tmp;
		for (int i = 0; i < this.model.getData().size(); i++) {
			index = ((index+776) % this.model.getData().size());
			tmp=this.model.getData().get(i);
			this.model.getData().set(i, this.model.getData().get(index));
			this.model.getData().set(index, tmp);
		}
		loadTable(percent);
	}
	
	public double calcProbIndependent(String value){
		double nElm = 0;
		for (String[] row : this.model.getDataTraining()){
			if (row[row.length-1].equals(value))
				nElm++;
		}
		return (nElm/this.model.getDataTraining().size());
	}
	
	public double calcTotalDependent(String header,String value,String valkelas){
		double sum = 0;
		for (String[] data : this.model.getDataTraining()){
			int index = this.model.getHeader().indexOf(header);
			int kelas = this.model.getHeader().size()-1;
			if (data[index].equals(value) && data[kelas].equals(valkelas)) {
				sum++;
			}
		}
		return sum;
	}

	public double[] calcDependentOne(String[] data){
		String[] attributes = this.model.getAttributes().get("acceptability");
		double[] hasil = new double[4];
		for (int i = 0; i < hasil.length; i++)
			hasil[i]=1;
		for (int i=0; i< data.length;i++) {
			String header = this.model.getHeader().get(i);
			for (int j = 0; j < hasil.length; j++)
				hasil[j]=hasil[j]*calcDependentProb(header,data[i],attributes[j]);
		}
		return hasil;
	}

	private double calcDependentProb(String header, String value, String valueKelas) {
		return calcTotalDependent(header, value, valueKelas)/calcRecordTotal("acceptability",valueKelas);
	}

	private double calcRecordTotal(String header, String value) {
		double n = 0;
		int index = this.model.getHeader().indexOf(header);
		for (String[] data : this.model.getDataTraining())
			if (data[index].equals(value))
				n++;
		return n;
	}
	
	public void testingClassification() {
		ArrayList<String> hasil = new ArrayList<String>();
		for (String[] data : this.model.getDataTesting()) {
			hasil.add(classification(data));
		}
		double akurasi = calcAcurracy(hasil);
		System.out.println(akurasi);
	}

	private double calcAcurracy(ArrayList<String> hasil) {
		double n=0;
		for (int i = 0; i < this.model.getDataTesting().size(); i++)
			if (this.model.getDataTesting().get(i)[6].equals(hasil.get(i)))
				n++;				

		return (n/this.model.getDataTesting().size()*100);
	}

	private String classification(String[] data) {
		double[] hasil = calcDependentOne(data);
		String[] attributesClass = this.model.getAttributes().get("acceptability");
		
		for (int i = 0; i < hasil.length; i++) {
			hasil[i] = hasil[i] * (calcRecordTotal("acceptability",attributesClass[i])/this.model.getDataTraining().size());
		}
		
		if (hasil[0] > hasil[1] && hasil[0] > hasil[2] && hasil[0] > hasil[3]) {
			return "unacc";
		}
		else if (hasil[1] > hasil[0] && hasil[1] > hasil[2] && hasil[1] > hasil[3]) {
			return "acc";
		}
		else if (hasil[2] > hasil[0] && hasil[2] > hasil[1] && hasil[2] > hasil[3]) {
			return "vgood";
		}
		else
			return "good";
	}

	public void test() {
		for (String str : this.model.getAttributes().get("acceptability")) {
			System.out.println(str);
		}
		
	}
}
