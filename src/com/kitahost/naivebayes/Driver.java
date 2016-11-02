package com.kitahost.naivebayes;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
public class Driver {
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(view, model);
		view.registerObserver(controller);
		view.setExtendedState(JFrame.MAXIMIZED_BOTH);
		view.setVisible(true);
	}
}
