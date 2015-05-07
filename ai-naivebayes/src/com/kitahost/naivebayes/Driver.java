package com.kitahost.naivebayes;

import javax.swing.JFrame;


public class Driver {
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(view, model);
		view.registerObserver(controller);
		view.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
