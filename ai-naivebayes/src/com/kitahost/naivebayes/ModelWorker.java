package com.kitahost.naivebayes;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class ModelWorker extends SwingWorker<Boolean, Void>{
	private Model model;
	
	@Override
	protected Boolean doInBackground() throws Exception {
		this.model.getDb().createTable();
		try {
			for (String header : this.model.getHeader()) {
				String attrval = "";
				for (String value : this.model.getAttributes().get(header)) {
					attrval = attrval + value +",";
				}
				attrval = attrval.substring(0,attrval.length()-1);
				this.model.getDb().getStatement().execute("INSERT INTO `car`.`header` (`header`,`value`) values('"+header+"','"+attrval+"')");
			}
			for (int i = 0; i < this.model.getData().size(); i++) {
				this.model.getDb().getStatement().execute(
						"INSERT INTO `car`.`data` (`buying`,`maint`,`doors`,`persons`,`lug_boot`,`safety`,`acceptability`) values('"+ this.model.getData().get(i)[0] +"','"+ this.model.getData().get(i)[1] +"','"+ this.model.getData().get(i)[2] +"','"+ this.model.getData().get(i)[3] +"','"+ this.model.getData().get(i)[4] +"','"+ this.model.getData().get(i)[5] +"','"+ this.model.getData().get(i)[6] +"')"
				);
			}
			JOptionPane.showMessageDialog(null,"Insert to database complete!","Success",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ModelWorker(Model model){
		this.model = model;
	}
}
