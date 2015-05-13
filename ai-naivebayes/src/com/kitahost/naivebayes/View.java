package com.kitahost.naivebayes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JSplitPane;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class View extends JFrame implements ActionListener,ChangeListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnProgram;
	private JMenu mnDataset;
	private JMenu mnOptions;
	private JMenuItem mntmExit;
	private JMenuItem mntmOpenFile;
	private JMenuItem mntmReadFromDatabase;
	private JMenuItem mntmConfigureDatabase;
	private Controller controller;
	private JScrollPane scrollPaneTraining;
	private JScrollPane scrollPaneTesting;
	private JTable tableTraining;
	private JTable tableTesting;
	private JPanel panelTesting;
	private JPanel panelTraining;
	private JSplitPane splitPane;
	private JPanel panelOpsi;
	private JLabel lblDataTraining;
	private JSlider sliderDataTesting;
	private JButton btnReloadTable;
	private JButton btnClassificate;
	private JButton btnRandomizeData;
	private JButton btnClassificateRecord;
	private JLabel lblTotalTraining;
	private JLabel lblTotalTesting;
	private JLabel label;
	private JPanel panelRecord;
	private JComboBox cbBuying;
	private JComboBox cbMaint;
	private JComboBox cbDoors;
	private JComboBox cbPersons;
	private JComboBox cbLugBoot;
	private JComboBox cbSafety;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea textAreaOutput;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		this.menuBar = new JMenuBar();
		setJMenuBar(this.menuBar);
		
		this.mnProgram = new JMenu("Program");
		this.menuBar.add(this.mnProgram);
		
		this.mntmExit = new JMenuItem("Exit");
		this.mnProgram.add(this.mntmExit);
		
		this.mnDataset = new JMenu("Dataset");
		this.menuBar.add(this.mnDataset);
		
		this.mntmOpenFile = new JMenuItem("Open file...");
		this.mnDataset.add(this.mntmOpenFile);
		
		this.mntmReadFromDatabase = new JMenuItem("Read from database...");
		this.mnDataset.add(this.mntmReadFromDatabase);
		
		this.mnOptions = new JMenu("Options");
		this.menuBar.add(this.mnOptions);
		
		this.mntmConfigureDatabase = new JMenuItem("Configure database");
		this.mnOptions.add(this.mntmConfigureDatabase);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panelOpsi = new JPanel();
		this.panelOpsi.setAlignmentY(Component.TOP_ALIGNMENT);
		this.panelOpsi.setBorder(new TitledBorder(null, "Classification Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.contentPane.add(this.panelOpsi, BorderLayout.NORTH);
		GridBagLayout gbl_panelOpsi = new GridBagLayout();
		gbl_panelOpsi.columnWidths = new int[] {172, 0, 0, 0};
		gbl_panelOpsi.rowHeights = new int[] {5, 0, 0, 0};
		gbl_panelOpsi.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
		gbl_panelOpsi.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		this.panelOpsi.setLayout(gbl_panelOpsi);
		
		this.lblDataTraining = new JLabel("Data Training Percentage");
		GridBagConstraints gbc_lblDataTraining = new GridBagConstraints();
		gbc_lblDataTraining.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataTraining.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDataTraining.gridx = 0;
		gbc_lblDataTraining.gridy = 0;
		this.panelOpsi.add(this.lblDataTraining, gbc_lblDataTraining);
		
		this.sliderDataTesting = new JSlider();
		this.sliderDataTesting.setMajorTickSpacing(50);
		this.sliderDataTesting.setMinorTickSpacing(5);
		this.sliderDataTesting.setValue(100);
		this.sliderDataTesting.setPaintTicks(true);
		this.sliderDataTesting.setPaintLabels(true);
		GridBagConstraints gbc_sliderDataTesting = new GridBagConstraints();
		gbc_sliderDataTesting.anchor = GridBagConstraints.NORTH;
		gbc_sliderDataTesting.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderDataTesting.insets = new Insets(0, 0, 5, 5);
		gbc_sliderDataTesting.gridx = 0;
		gbc_sliderDataTesting.gridy = 1;
		this.panelOpsi.add(this.sliderDataTesting, gbc_sliderDataTesting);
		
		this.btnReloadTable = new JButton("Reload Table");
		GridBagConstraints gbc_btnReloadTable = new GridBagConstraints();
		gbc_btnReloadTable.anchor = GridBagConstraints.NORTH;
		gbc_btnReloadTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnReloadTable.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReloadTable.gridx = 2;
		gbc_btnReloadTable.gridy = 1;
		this.panelOpsi.add(this.btnReloadTable, gbc_btnReloadTable);
		this.btnReloadTable.addActionListener(this);
		
		this.btnRandomizeData = new JButton("Randomize Data");
		GridBagConstraints gbc_btnRandomizeData = new GridBagConstraints();
		gbc_btnRandomizeData.anchor = GridBagConstraints.NORTH;
		gbc_btnRandomizeData.insets = new Insets(0, 0, 5, 0);
		gbc_btnRandomizeData.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRandomizeData.gridx = 3;
		gbc_btnRandomizeData.gridy = 1;
		this.panelOpsi.add(this.btnRandomizeData, gbc_btnRandomizeData);
		this.btnRandomizeData.addActionListener(this);
		
		this.label = new JLabel("0%");
		this.label.setLabelFor(this.sliderDataTesting);
		this.label.setVerticalAlignment(SwingConstants.TOP);
		this.label.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		this.panelOpsi.add(this.label, gbc_label);
		
		this.btnClassificateRecord = new JButton("Classificate Record");
		GridBagConstraints gbc_btnClassificateRecord = new GridBagConstraints();
		gbc_btnClassificateRecord.anchor = GridBagConstraints.NORTH;
		gbc_btnClassificateRecord.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClassificateRecord.insets = new Insets(0, 0, 5, 5);
		gbc_btnClassificateRecord.gridx = 2;
		gbc_btnClassificateRecord.gridy = 2;
		this.panelOpsi.add(this.btnClassificateRecord, gbc_btnClassificateRecord);
		this.btnClassificateRecord.addActionListener(this);
		
		this.btnClassificate = new JButton("Classificate Testing");
		GridBagConstraints gbc_btnClassificate = new GridBagConstraints();
		gbc_btnClassificate.anchor = GridBagConstraints.NORTH;
		gbc_btnClassificate.insets = new Insets(0, 0, 5, 0);
		gbc_btnClassificate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClassificate.gridx = 3;
		gbc_btnClassificate.gridy = 2;
		this.panelOpsi.add(this.btnClassificate, gbc_btnClassificate);
		this.btnClassificate.addActionListener(this);
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		this.panelOpsi.add(this.panel, gbc_panel);
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.X_AXIS));
		
		this.scrollPane = new JScrollPane();
		this.panel.add(this.scrollPane);
		
		this.textAreaOutput = new JTextArea();
		this.textAreaOutput.setTabSize(4);
		this.textAreaOutput.setRows(5);
		this.scrollPane.setViewportView(this.textAreaOutput);
		this.textAreaOutput.setForeground(Color.GREEN);
		this.textAreaOutput.setEditable(false);
		this.textAreaOutput.setBackground(Color.BLACK);
		
		this.panelRecord = new JPanel();
		GridBagConstraints gbc_panelRecord = new GridBagConstraints();
		gbc_panelRecord.fill = GridBagConstraints.BOTH;
		gbc_panelRecord.gridx = 3;
		gbc_panelRecord.gridy = 3;
		this.panelOpsi.add(this.panelRecord, gbc_panelRecord);
		this.panelRecord.setLayout(new BoxLayout(this.panelRecord, BoxLayout.Y_AXIS));
		
		this.cbBuying = new JComboBox();
		this.cbBuying.setModel(new DefaultComboBoxModel(new String[] {"vhigh", "high", "med", "low"}));
		this.panelRecord.add(this.cbBuying);
		
		this.cbMaint = new JComboBox();
		this.cbMaint.setModel(new DefaultComboBoxModel(new String[] {"vhigh", "high", "med", "low"}));
		this.panelRecord.add(this.cbMaint);
		
		this.cbDoors = new JComboBox();
		this.cbDoors.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5more"}));
		this.panelRecord.add(this.cbDoors);
		
		this.cbPersons = new JComboBox();
		this.cbPersons.setModel(new DefaultComboBoxModel(new String[] {"2", "4", "more"}));
		this.panelRecord.add(this.cbPersons);
		
		this.cbLugBoot = new JComboBox();
		this.cbLugBoot.setModel(new DefaultComboBoxModel(new String[] {"small", "med", "big"}));
		this.panelRecord.add(this.cbLugBoot);
		
		this.cbSafety = new JComboBox();
		this.cbSafety.setModel(new DefaultComboBoxModel(new String[] {"low", "med", "high"}));
		this.panelRecord.add(this.cbSafety);
		
		this.splitPane = new JSplitPane();
		this.splitPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		this.splitPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.splitPane.setResizeWeight(0.5);
		this.contentPane.add(this.splitPane);
		
		this.panelTraining = new JPanel();
		this.panelTraining.setBorder(new TitledBorder(null, "Data Training", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.splitPane.setLeftComponent(this.panelTraining);
		this.panelTraining.setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneTraining = new JScrollPane();
		this.panelTraining.add(this.scrollPaneTraining);
		this.scrollPaneTraining.setViewportBorder(null);
		
		this.tableTraining = new JTable();
		this.scrollPaneTraining.setViewportView(this.tableTraining);
		
		this.lblTotalTraining = new JLabel("Total :");
		this.panelTraining.add(this.lblTotalTraining, BorderLayout.SOUTH);
		
		this.panelTesting = new JPanel();
		this.panelTesting.setBorder(new TitledBorder(null, "Data Testing", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.splitPane.setRightComponent(this.panelTesting);
		this.panelTesting.setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneTesting = new JScrollPane();
		this.panelTesting.add(this.scrollPaneTesting);
		this.scrollPaneTesting.setViewportBorder(null);
		
		this.tableTesting = new JTable();
		this.scrollPaneTesting.setViewportView(this.tableTesting);
		
		this.lblTotalTesting = new JLabel("Total : ");
		this.panelTesting.add(this.lblTotalTesting, BorderLayout.SOUTH);
		
		this.mntmExit.addActionListener(this);
		this.mntmOpenFile.addActionListener(this);
		this.sliderDataTesting.addChangeListener(this);
	}
	
	public JTextArea getTextAreaOutput() {
		return textAreaOutput;
	}

	public void setTextAreaOutput(JTextArea textAreaOutput) {
		this.textAreaOutput = textAreaOutput;
	}

	public void registerObserver(Controller controller){
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source==this.mntmExit) {
			 int input = JOptionPane.showConfirmDialog(
					 this,
					 "Do you want to exit program?",
					 "Exit",
					 JOptionPane.YES_NO_OPTION,
					 JOptionPane.QUESTION_MESSAGE);
			 if (input == JOptionPane.YES_OPTION) {
				System.exit(1);
			 }
		}
		else if (source == this.mntmOpenFile) {
			JFileChooser filechooser = new JFileChooser();
			int status = filechooser.showOpenDialog(this);
			if (status == JFileChooser.APPROVE_OPTION) {
				this.controller.loadFileToTable(filechooser.getSelectedFile());
			}
		}
		else if (source == this.btnReloadTable) {
			if (this.tableTraining.getModel()!=null && this.tableTesting.getModel()!=null) {
				this.controller.loadTable(this.sliderDataTesting.getValue());
			}
		}
		else if (source == this.btnRandomizeData) {
			if (this.tableTraining.getModel()!=null && this.tableTesting.getModel()!=null) {
				this.controller.randomizeData(this.sliderDataTesting.getValue());
			}
		}
		else if (source == this.btnClassificate) {
			if (this.tableTraining.getModel()!=null && this.tableTesting.getModel()!=null) {
				this.textAreaOutput.setText("");
				ControllerWorker cworker = new ControllerWorker(this.controller);
				cworker.execute();
			}
		}
		else if (source == this.btnClassificateRecord) {
			if (this.tableTraining.getModel()!=null && this.tableTesting.getModel()!=null) {
				this.textAreaOutput.setText("");
				String[] data = new String[6];
				data[0]=this.cbBuying.getSelectedItem().toString();
				data[1]=this.cbMaint.getSelectedItem().toString();
				data[2]=this.cbDoors.getSelectedItem().toString();
				data[3]=this.cbPersons.getSelectedItem().toString();
				data[4]=this.cbLugBoot.getSelectedItem().toString();
				data[5]=this.cbSafety.getSelectedItem().toString();
				this.textAreaOutput.append("Record Classification\n");
				this.textAreaOutput.append("{buying,maint,doors,persons,lug_boot,safety} : " + data[0] +" , ");
				this.textAreaOutput.append(data[1] +" , ");
				this.textAreaOutput.append(data[2] +" , ");
				this.textAreaOutput.append(data[3] +" , ");
				this.textAreaOutput.append(data[4] +" , ");
				this.textAreaOutput.append(data[5] +"\n\n");
				this.textAreaOutput.append("Independent Probability\n");
				this.textAreaOutput.append("acc      : " + this.controller.calcProbIndependent("acc") +"\n");
				this.textAreaOutput.append("unacc    : " + this.controller.calcProbIndependent("unacc") +"\n");
				this.textAreaOutput.append("good     : " + this.controller.calcProbIndependent("good") +"\n");
				this.textAreaOutput.append("vgood    : " + this.controller.calcProbIndependent("vgood") +"\n\n");
				this.textAreaOutput.append("Classification : "+this.controller.classification(data)+"\n\n");
			}
		}
	}
	public JSlider getSliderDataTesting() {
		return sliderDataTesting;
	}

	public void setSliderDataTesting(JSlider sliderDataTesting) {
		this.sliderDataTesting = sliderDataTesting;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object source = e.getSource();
		if (source==this.sliderDataTesting) {
			this.label.setText(this.sliderDataTesting.getValue() + "%");
		}
	}

	public JTable getTableTraining() {
		return tableTraining;
	}

	public void setTableTraining(JTable tableTraining) {
		this.tableTraining = tableTraining;
	}

	public JTable getTableTesting() {
		return tableTesting;
	}

	public void setTableTesting(JTable tableTesting) {
		this.tableTesting = tableTesting;
	}

	public JLabel getLblTotalTraining() {
		return lblTotalTraining;
	}

	public void setLblTotalTraining(JLabel lblTotalTraining) {
		this.lblTotalTraining = lblTotalTraining;
	}

	public JLabel getLblTotalTesting() {
		return lblTotalTesting;
	}

	public void setLblTotalTesting(JLabel lblTotalTesting) {
		this.lblTotalTesting = lblTotalTesting;
	}
}
