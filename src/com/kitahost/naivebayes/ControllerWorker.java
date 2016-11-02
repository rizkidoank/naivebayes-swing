package com.kitahost.naivebayes;

import javax.swing.SwingWorker;

public class ControllerWorker extends SwingWorker<Boolean, Void>{
	private Controller controller;
	
	@Override
	protected Boolean doInBackground() throws Exception {
		this.controller.testingClassification();
		return null;
	}

	public ControllerWorker(Controller controller) {
		this.controller = controller;
	}
}
