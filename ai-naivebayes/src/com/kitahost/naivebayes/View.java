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
	private JButton btnTest;
	private JLabel label;
	
	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
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
		this.contentPane.setLayout(new BoxLayout(this.contentPane, BoxLayout.Y_AXIS));
		
		this.panelOpsi = new JPanel();
		this.panelOpsi.setAlignmentY(Component.TOP_ALIGNMENT);
		this.panelOpsi.setBorder(new TitledBorder(null, "Classification Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.contentPane.add(this.panelOpsi);
		GridBagLayout gbl_panelOpsi = new GridBagLayout();
		gbl_panelOpsi.columnWidths = new int[] {172, 0, 0, 0};
		gbl_panelOpsi.rowHeights = new int[] {5, 0, 0, 0};
		gbl_panelOpsi.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
		gbl_panelOpsi.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		this.panelOpsi.setLayout(gbl_panelOpsi);
		
		this.lblDataTraining = new JLabel("Banyak Data Training");
		GridBagConstraints gbc_lblDataTraining = new GridBagConstraints();
		gbc_lblDataTraining.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataTraining.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDataTraining.gridx = 0;
		gbc_lblDataTraining.gridy = 0;
		this.panelOpsi.add(this.lblDataTraining, gbc_lblDataTraining);
		
		this.sliderDataTesting = new JSlider();
		this.sliderDataTesting.setMajorTickSpacing(50);
		this.sliderDataTesting.setMinorTickSpacing(20);
		this.sliderDataTesting.setValue(0);
		this.sliderDataTesting.setPaintTicks(true);
		this.sliderDataTesting.setPaintLabels(true);
		GridBagConstraints gbc_sliderDataTesting = new GridBagConstraints();
		gbc_sliderDataTesting.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderDataTesting.anchor = GridBagConstraints.NORTH;
		gbc_sliderDataTesting.insets = new Insets(0, 0, 5, 5);
		gbc_sliderDataTesting.gridx = 0;
		gbc_sliderDataTesting.gridy = 1;
		this.panelOpsi.add(this.sliderDataTesting, gbc_sliderDataTesting);
		
		this.label = new JLabel("0");
		this.label.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		this.panelOpsi.add(this.label, gbc_label);
		
		this.btnReloadTable = new JButton("Reload Table");
		GridBagConstraints gbc_btnReloadTable = new GridBagConstraints();
		gbc_btnReloadTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnReloadTable.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReloadTable.anchor = GridBagConstraints.NORTH;
		gbc_btnReloadTable.gridx = 2;
		gbc_btnReloadTable.gridy = 1;
		this.panelOpsi.add(this.btnReloadTable, gbc_btnReloadTable);
		this.btnReloadTable.addActionListener(this);
		
		this.btnRandomizeData = new JButton("Randomize Data");
		GridBagConstraints gbc_btnRandomizeData = new GridBagConstraints();
		gbc_btnRandomizeData.insets = new Insets(0, 0, 5, 0);
		gbc_btnRandomizeData.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRandomizeData.anchor = GridBagConstraints.NORTH;
		gbc_btnRandomizeData.gridx = 3;
		gbc_btnRandomizeData.gridy = 1;
		this.panelOpsi.add(this.btnRandomizeData, gbc_btnRandomizeData);
		this.btnRandomizeData.addActionListener(this);
		
		this.btnClassificateRecord = new JButton("Classificate 1 Record");
		GridBagConstraints gbc_btnClassificateRecord = new GridBagConstraints();
		gbc_btnClassificateRecord.insets = new Insets(0, 0, 5, 5);
		gbc_btnClassificateRecord.gridx = 2;
		gbc_btnClassificateRecord.gridy = 2;
		this.panelOpsi.add(this.btnClassificateRecord, gbc_btnClassificateRecord);
		
		this.btnClassificate = new JButton("Classificate 100%");
		GridBagConstraints gbc_btnClassificate = new GridBagConstraints();
		gbc_btnClassificate.insets = new Insets(0, 0, 5, 0);
		gbc_btnClassificate.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnClassificate.anchor = GridBagConstraints.NORTH;
		gbc_btnClassificate.gridx = 3;
		gbc_btnClassificate.gridy = 2;
		this.panelOpsi.add(this.btnClassificate, gbc_btnClassificate);
		
		this.btnTest = new JButton("Test");
		GridBagConstraints gbc_btnTest = new GridBagConstraints();
		gbc_btnTest.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTest.insets = new Insets(0, 0, 0, 5);
		gbc_btnTest.gridx = 2;
		gbc_btnTest.gridy = 3;
		this.panelOpsi.add(this.btnTest, gbc_btnTest);
		
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
		this.btnClassificate.addActionListener(this);
		this.btnClassificateRecord.addActionListener(this);
		this.btnTest.addActionListener(this);
		this.sliderDataTesting.addChangeListener(this);
		this.setVisible(true);
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
				this.controller.testingClassification();
			}
		}
		else if (source == this.btnTest){
			if (this.tableTraining.getModel()!=null && this.tableTesting.getModel()!=null){
				this.controller.test();
			}
		}
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
