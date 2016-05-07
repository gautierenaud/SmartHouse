package frameself.gui;

import frameself.main.Admin;
import frameself.monitor.EventCollector;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.RowSorter;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;







public class GuiAdmin
{
	Admin admin;
	private static JFrame frmFrameselfMapekLoop;
	static JTable receivedEvents;
	static JTable formattedEvents;
	static JTable filtredEvents;
	static JTable agregatedEvents;
	static JTable inferedSymptoms;
	static JTable eventsTable;
	static JTable symptomsTable;
	static JTable rfcsTable;
	static JTable actionsTable;
	static JTable actionsResultTable;
	static JTable kbTable;
	static JTable receivedSymptoms;
	static JTable formattedSymptoms;
	static JTable filtredSymptoms;
	static JTable agregatedSymptoms;
	static JTable receivedRfcs;
	static JTable inferedPlans;
	static JTable actions;
	static JTable actionsResult;
	static JTable inferedRfcs;
	static JTable sensors;
	static JTable effectors;
	static JTable policies;
	private JPanel panel;
	private static JSlider slider;
	private JLabel lblSpeed;
	private static JSpinner spinner;
	private static JToggleButton startButton;
	private static JLabel counter;
	private static JRadioButton fixLoop;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private JButton resetButton;
	private JPanel panel_12;
	private JLabel lblNewLabel_11;
	private JPanel panel_13;
	private JLabel lblNewLabel_12;
	private JPanel panel_14;
	private JLabel lblNewLabel_13;

	public void start()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAdmin window = new GuiAdmin(GuiAdmin.this.admin);
					GuiAdmin.frmFrameselfMapekLoop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public GuiAdmin(Admin admin)
	{
		this.admin = admin;
		initialize();
	}



	private void initialize()
	{
		frmFrameselfMapekLoop = new JFrame();
		frmFrameselfMapekLoop.setTitle("FRAMESELF Administration");

		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = (int)tk.getScreenSize().getWidth();
		int ySize = (int)tk.getScreenSize().getHeight();

		JMenuBar menubar = new JMenuBar();
		menubar.setEnabled(false);

		frmFrameselfMapekLoop.setJMenuBar(menubar);
		this.panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout)this.panel.getLayout();
		flowLayout.setAlignment(0);
		menubar.add(this.panel);

		fixLoop = new JRadioButton("Max loop number:");
		fixLoop.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (ev.getStateChange() == 1) {
					GuiAdmin.spinner.setEnabled(true);
				} else if (ev.getStateChange() == 2) {
					GuiAdmin.spinner.setEnabled(false);
				}

			}
		});
		this.panel.add(fixLoop);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setEnabled(false);
		this.panel.add(spinner);

		this.horizontalStrut = Box.createHorizontalStrut(20);
		this.panel.add(this.horizontalStrut);

		this.lblSpeed = new JLabel("Waiting time (s)");
		this.panel.add(this.lblSpeed);

		slider = new JSlider();
		slider.setValue(1);
		slider.setMinimum(1);
		slider.setMaximum(10);
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		this.panel.add(slider);

		startButton = new JToggleButton("");

		startButton.setContentAreaFilled(false);
		startButton.setBorder(null);
		startButton.setBackground(SystemColor.menu);
		startButton.setIcon(new ImageIcon("images/start.png"));
		startButton.setSelectedIcon(new ImageIcon("images/stop.png"));
		startButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (ev.getStateChange() == 1) {
					System.out.println("MAPE-K loop started");
					GuiAdmin.this.admin.start();
				} else if (ev.getStateChange() == 2) {
					System.out.println("MAPE-K loop stopped");
					GuiAdmin.this.admin.stop();
				}

			}
		});
		this.horizontalStrut_1 = Box.createHorizontalStrut(20);
		this.panel.add(this.horizontalStrut_1);

		this.resetButton = new JButton("");
		this.resetButton.setContentAreaFilled(false);
		this.resetButton.setBackground(SystemColor.menu);
		this.resetButton.setBorder(null);
		this.resetButton.setIcon(new ImageIcon("images/reset.png"));
		this.resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin.setLoopCounter(0);
				GuiAdmin.this.clear(GuiAdmin.receivedEvents);
				GuiAdmin.this.clear(GuiAdmin.formattedEvents);
				GuiAdmin.this.clear(GuiAdmin.filtredEvents);
				GuiAdmin.this.clear(GuiAdmin.agregatedEvents);
				GuiAdmin.this.clear(GuiAdmin.inferedSymptoms);

				GuiAdmin.this.clear(GuiAdmin.receivedSymptoms);
				GuiAdmin.this.clear(GuiAdmin.inferedRfcs);

				GuiAdmin.this.clear(GuiAdmin.receivedRfcs);
				GuiAdmin.this.clear(GuiAdmin.inferedPlans);

				GuiAdmin.this.clear(GuiAdmin.actions);
				GuiAdmin.this.clear(GuiAdmin.actionsResult);

				GuiAdmin.this.clear(GuiAdmin.eventsTable);
				GuiAdmin.this.clear(GuiAdmin.symptomsTable);
				GuiAdmin.this.clear(GuiAdmin.rfcsTable);
				GuiAdmin.this.clear(GuiAdmin.actionsTable);
				GuiAdmin.this.clear(GuiAdmin.actionsResultTable);

				GuiAdmin.counter.setText("0 loops");
				EventCollector.getEvents().clear();

			}



		});
		this.panel.add(this.resetButton);
		this.panel.add(startButton);

		counter = new JLabel("0 loops");
		counter.setFont(new Font("Segoe UI", 1, 20));
		this.panel.add(counter);
		counter.setHorizontalAlignment(0);
		frmFrameselfMapekLoop.setSize(846, 581);
		frmFrameselfMapekLoop.setDefaultCloseOperation(3);
		frmFrameselfMapekLoop.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JTabbedPane tabMAPEK = new JTabbedPane(1);
		tabMAPEK.setTabLayoutPolicy(1);
		frmFrameselfMapekLoop.getContentPane().add(tabMAPEK);



		JPanel monitor = initializeMonitorTab(tabMAPEK);
		tabMAPEK.addTab("Monitor", null, monitor, null);

		JPanel analyzer = initializeAnalyzerTab(tabMAPEK);
		tabMAPEK.addTab("Analyzer", null, analyzer, null);

		JPanel planner = initializePlannerTab(tabMAPEK);
		tabMAPEK.addTab("Planner", null, planner, null);

		JPanel executer = initializeExecuterTab(tabMAPEK);
		tabMAPEK.addTab("Executer", null, executer, null);

		JPanel knowledge = initializeknowledgeTab(tabMAPEK);
		tabMAPEK.addTab("Knowledge", null, knowledge, null);

		JPanel mape = initializeMapekTab(tabMAPEK);
		tabMAPEK.addTab("MAPE-K", null, mape, null);
	}

	public JTable creatEventJTable()
	{
		TableModel model = new DefaultTableModel(new Object[] { "Loop", "Id", "Category", "Value", "Sensor", "Location", "Priority", "Severity", "Description", "Timestamp", "Expiry" }, 0) {
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:  return Class.class;
				case 1:  return Class.class;
				case 2:  return Class.class;
				case 3:  return Class.class;
				case 4:  return Class.class;
				case 5:  return Class.class;
				case 6:  return Class.class;
				case 7:  return Class.class;
				case 8:  return Class.class;
				case 9:  return Class.class;
				case 10:  return Class.class; }
				return null;
			}


		};
		JTable table = new JTable(model);

		RowSorter<TableModel> sorter = new TableRowSorter(model);

		table.setRowSorter(sorter);
		table.getRowSorter().toggleSortOrder(9);
		table.getRowSorter().toggleSortOrder(9);
		TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer()
		{
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				if ((value instanceof Date)) {
					value = this.f.format(value);
				}
				return super.getTableCellRendererComponent(table, value, isSelected, 
						hasFocus, row, column);
			}

		};
		table.getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);
		table.getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);



		return table;
	}

	public JTable creatSymptomJTable()
	{
		TableModel model = new DefaultTableModel(new Object[] { "Loop", "Id", "Category", "Value", "Location", "Priority", "Severity", "Description", "Timestamp", "Expiry" }, 0) {
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:  return Class.class;
				case 1:  return Class.class;
				case 2:  return Class.class;
				case 3:  return Class.class;
				case 4:  return Class.class;
				case 5:  return Class.class;
				case 6:  return Class.class;
				case 7:  return Class.class;
				case 8:  return Class.class;
				case 9:  return Class.class; }
				return null;
			}


		};
		JTable table = new JTable(model);

		RowSorter<TableModel> sorter = new TableRowSorter(model);

		table.setRowSorter(sorter);
		table.getRowSorter().toggleSortOrder(8);
		table.getRowSorter().toggleSortOrder(8);
		TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer()
		{
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				if ((value instanceof Date)) {
					value = this.f.format(value);
				}
				return super.getTableCellRendererComponent(table, value, isSelected, 
						hasFocus, row, column);
			}

		};
		table.getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		table.getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);


		return table;
	}

	public JTable creatRfcJTable() {
		TableModel model = new DefaultTableModel(new Object[] { "Loop", "Id", "Category", "Value", "Location", "Priority", "Severity", "Description", "Timestamp", "Expiry" }, 0) {
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:  return Class.class;
				case 1:  return Class.class;
				case 2:  return Class.class;
				case 3:  return Class.class;
				case 4:  return Class.class;
				case 5:  return Class.class;
				case 6:  return Class.class;
				case 7:  return Class.class;
				case 8:  return Class.class;
				case 9:  return Class.class; }
				return null;
			}


		};
		JTable table = new JTable(model);

		RowSorter<TableModel> sorter = new TableRowSorter(model);

		table.setRowSorter(sorter);
		table.getRowSorter().toggleSortOrder(8);
		table.getRowSorter().toggleSortOrder(8);
		TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer()
		{
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				if ((value instanceof Date)) {
					value = this.f.format(value);
				}
				return super.getTableCellRendererComponent(table, value, isSelected, 
						hasFocus, row, column);
			}

		};
		table.getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);
		table.getColumnModel().getColumn(9).setCellRenderer(tableCellRenderer);


		return table;
	}

	public JTable creatActionJTable() {
		TableModel model = new DefaultTableModel(new Object[] { "Loop", "Id", "Category", "Name", "Parameters", "Priority", "Description", "Effector", "Timestamp" }, 0) {
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:  return Class.class;
				case 1:  return Class.class;
				case 2:  return Class.class;
				case 3:  return Class.class;
				case 4:  return Class.class;
				case 5:  return Class.class;
				case 6:  return Class.class;
				case 7:  return Class.class;
				case 8:  return Class.class;
				}
				return null;
			}


		};
		JTable table = new JTable(model);

		RowSorter<TableModel> sorter = new TableRowSorter(model);

		table.setRowSorter(sorter);
		table.getRowSorter().toggleSortOrder(8);
		table.getRowSorter().toggleSortOrder(8);
		TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer()
		{
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				if ((value instanceof Date)) {
					value = this.f.format(value);
				}
				return super.getTableCellRendererComponent(table, value, isSelected, 
						hasFocus, row, column);
			}

		};
		table.getColumnModel().getColumn(8).setCellRenderer(tableCellRenderer);


		return table;
	}

	public JTable creatActionResultJTable() {
		TableModel model = new DefaultTableModel(new Object[] { "Loop", "Id", "Category", "Name", "Parameters", "Priority", "Description", "Effector", "result", "Error", "Timestamp" }, 0) {
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:  return Class.class;
				case 1:  return Class.class;
				case 2:  return Class.class;
				case 3:  return Class.class;
				case 4:  return Class.class;
				case 5:  return Class.class;
				case 6:  return Class.class;
				case 7:  return Class.class;
				case 8:  return Class.class;
				case 9:  return Class.class;
				case 10:  return Class.class; }
				return null;
			}


		};
		JTable table = new JTable(model);

		RowSorter<TableModel> sorter = new TableRowSorter(model);

		table.setRowSorter(sorter);
		table.getRowSorter().toggleSortOrder(10);
		table.getRowSorter().toggleSortOrder(10);
		TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer()
		{
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
			{
				if ((value instanceof Date)) {
					value = this.f.format(value);
				}
				return super.getTableCellRendererComponent(table, value, isSelected, 
						hasFocus, row, column);
			}

		};
		table.getColumnModel().getColumn(10).setCellRenderer(tableCellRenderer);


		return table;
	}

	public JTable creatKnowledgeJTable() {
		TableModel model = new DefaultTableModel(new Object[] { "Name" }, 0) {
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:  return Class.class; }
				return null;
			}


		};
		JTable table = new JTable(model);

		RowSorter<TableModel> sorter = new TableRowSorter(model);
		table.setRowSorter(sorter);

		return table;
	}

	public void bindTableSize(JTable table, final JLabel label) {
		table.getModel().addTableModelListener(new TableModelListener()
		{
			public void tableChanged(TableModelEvent e) {
				label.setText(String.valueOf(e.getLastRow() + 1));
			}
		});
	}

	public JPanel createTablePanel(JPanel tab, String title, JTable table, String iconFile)
	{
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setLayout(new BorderLayout(0, 0));
		tab.add(panel);

		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, "Center");
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(153, 204, 255));
		panel.add(titlePanel, "North");
		titlePanel.setLayout(new GridLayout(0, 3, 0, 0));
		JLabel iconLabel = new JLabel("");
		iconLabel.setHorizontalAlignment(4);
		iconLabel.setIcon(new ImageIcon(iconFile));
		titlePanel.add(iconLabel);
		JLabel titleLabel = new JLabel(title);
		titleLabel.setForeground(Color.BLACK);
		titlePanel.add(titleLabel);
		titleLabel.setHorizontalAlignment(0);
		JLabel sizeLabel = new JLabel("0");
		sizeLabel.setForeground(Color.BLACK);
		titlePanel.add(sizeLabel);
		sizeLabel.setHorizontalAlignment(2);
		bindTableSize(table, sizeLabel);
		return panel;
	}

	public JPanel initializeMapekTab(JTabbedPane tabbedPane_1) { JPanel mapek = new JPanel();
	mapek.setLayout(new GridLayout(5, 1, 0, 0));

	eventsTable = creatEventJTable();
	createTablePanel(mapek, "Events", eventsTable, "images/arrow2.png");

	symptomsTable = creatSymptomJTable();
	createTablePanel(mapek, "Symptoms", symptomsTable, "images/arrow2.png");

	rfcsTable = creatRfcJTable();
	createTablePanel(mapek, "RFCs", rfcsTable, "images/arrow2.png");

	actionsTable = creatActionJTable();
	createTablePanel(mapek, "Actions", actionsTable, "images/arrow2.png");

	actionsResultTable = creatActionResultJTable();
	createTablePanel(mapek, "Results", actionsResultTable, "images/arrow2.png");


	return mapek;
	}

	private JPanel initializeMonitorTab(JTabbedPane tabbedPane_1) { JPanel monitor_1 = new JPanel();

	monitor_1.setLayout(new GridLayout(0, 1, 0, 0));

	receivedEvents = creatEventJTable();
	createTablePanel(monitor_1, "Events Collector", receivedEvents, "images/arrow2.png");



	formattedEvents = creatEventJTable();
	createTablePanel(monitor_1, "Events Formatter", formattedEvents, "images/arrow2.png");

	filtredEvents = creatEventJTable();
	createTablePanel(monitor_1, "Events Filter", filtredEvents, "images/arrow2.png");

	agregatedEvents = creatEventJTable();
	createTablePanel(monitor_1, "Events Agregator", agregatedEvents, "images/arrow2.png");


	inferedSymptoms = creatSymptomJTable();
	createTablePanel(monitor_1, "Symptoms Inference", inferedSymptoms, "images/arrow2.png");

	return monitor_1;
	}


	private JPanel initializeAnalyzerTab(JTabbedPane tabbedPane_1)
	{
		JPanel analyzer_1 = new JPanel();
		analyzer_1.setLayout(new GridLayout(2, 1, 0, 0));

		receivedSymptoms = creatSymptomJTable();
		createTablePanel(analyzer_1, "Symptoms Reader", receivedSymptoms, "images/arrow2.png");

		inferedRfcs = creatRfcJTable();
		createTablePanel(analyzer_1, "RFCs Inference", inferedRfcs, "images/arrow2.png");

		return analyzer_1;
	}

	private JPanel initializePlannerTab(JTabbedPane tabbedPane_1) {
		JPanel planner_1 = new JPanel();
		planner_1.setLayout(new GridLayout(2, 1, 0, 0));

		receivedRfcs = creatRfcJTable();
		createTablePanel(planner_1, "RFCs Reader", receivedRfcs, "images/arrow2.png");

		inferedPlans = creatActionJTable();
		createTablePanel(planner_1, "Plans Inference", inferedPlans, "images/arrow2.png");

		return planner_1;
	}

	public void clear(JTable table) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setRowCount(0);
	}

	private JPanel initializeExecuterTab(JTabbedPane tabbedPane_1) {
		JPanel executer_1 = new JPanel();
		executer_1.setLayout(new GridLayout(2, 1, 0, 0));

		actions = creatActionJTable();
		createTablePanel(executer_1, "Received Actions", actions, "images/arrow2.png");

		actionsResult = creatActionResultJTable();
		createTablePanel(executer_1, "Actions Results", actionsResult, "images/arrow2.png");

		return executer_1;
	}

	private JPanel initializeknowledgeTab(JTabbedPane tabbedPane_1)
	{
		JPanel knowledge = new JPanel();
		knowledge.setLayout(new GridLayout(3, 1, 0, 0));

		sensors = creatKnowledgeJTable();
		createTablePanel(knowledge, "Sensors", sensors, "images/info2.png");

		effectors = creatKnowledgeJTable();
		createTablePanel(knowledge, "Effectors", effectors, "images/info2.png");

		policies = creatKnowledgeJTable();
		createTablePanel(knowledge, "Policies", policies, "images/info2.png");

		return knowledge;
	}

	public static JTable getReceivedEvents() {
		return receivedEvents;
	}

	public static void setReceivedEvents(JTable receivedEvents) {
		receivedEvents = receivedEvents;
	}

	public static JTable getNormalizedEvents() {
		return formattedEvents;
	}

	public static void setNormalizedEvents(JTable formattedEvents) {
		formattedEvents = formattedEvents;
	}

	public static JTable getFiltredEvents()
	{
		return filtredEvents;
	}

	public static void setFiltredEvents(JTable filtredEvents) {
		filtredEvents = filtredEvents;
	}

	public static JTable getAgregatedEvents() {
		return agregatedEvents;
	}

	public static void setAgregatedEvents(JTable agregatedEvents) {
		agregatedEvents = agregatedEvents;
	}

	public static JTable getInferedSymptoms() {
		return inferedSymptoms;
	}

	public static void setInferedSymptoms(JTable inferedSymptoms) {
		inferedSymptoms = inferedSymptoms;
	}


	public JFrame getFrmFrameselfMapekLoop()
	{
		return frmFrameselfMapekLoop;
	}



	public static JTable getSensors()
	{
		return sensors;
	}

	public static void setSensors(JTable sensors) {
		sensors = sensors;
	}

	public static JTable getEffectors() {
		return effectors;
	}

	public static void setEffectors(JTable effectors) {
		effectors = effectors;
	}

	public static JTable getPolicies() {
		return policies;
	}

	public static void setPolicies(JTable policies) {
		policies = policies;
	}

	public static JTable getReceivedSymptoms()
	{
		return receivedSymptoms;
	}

	public static void setReceivedSymptoms(JTable receivedSymptoms) {
		receivedSymptoms = receivedSymptoms;
	}

	public static JTable getInferedRfcs()
	{
		return inferedRfcs;
	}

	public static void setInferedRfcs(JTable inferedRfcs) {
		inferedRfcs = inferedRfcs;
	}



	public static JTable getInferedPlans()
	{
		return inferedPlans;
	}

	public static void setInferedPlans(JTable inferedPlans) {
		inferedPlans = inferedPlans;
	}

	public static JTable getReceivedRfcs() {
		return receivedRfcs;
	}

	public static void setReceivedRfcs(JTable receivedRfcs) {
		receivedRfcs = receivedRfcs;
	}






	public static JTable getActionsResult()
	{
		return actionsResult;
	}

	public static void setActionsResult(JTable actionsResult) {
		actionsResult = actionsResult;
	}

	public static JSlider getSlider()
	{
		return slider;
	}

	public static void setSlider(JSlider slider) {
		slider = slider;
	}

	public static JSpinner getSpinner() {
		return spinner;
	}

	public static void setSpinner(JSpinner spinner) {
		spinner = spinner;
	}

	public static JToggleButton getStartButton() {
		return startButton;
	}

	public static void setStartButton(JToggleButton startButton) {
		startButton = startButton;
	}

	public static JLabel getCounter() {
		return counter;
	}

	public static void setCounter(JLabel counter) {
		counter = counter;
	}

	public static JRadioButton getFixLoop() {
		return fixLoop;
	}

	public static void setFixLoop(JRadioButton fixLoop) {
		fixLoop = fixLoop;
	}

	public static JTable getEventsTable() {
		return eventsTable;
	}

	public static void setEventsTable(JTable eventsTable) {
		eventsTable = eventsTable;
	}

	public static JTable getSymptomsTable() {
		return symptomsTable;
	}

	public static void setSymptomsTable(JTable symptomsTable) {
		symptomsTable = symptomsTable;
	}

	public static JTable getRfcsTable() {
		return rfcsTable;
	}

	public static void setRfcsTable(JTable rfcsTable) {
		rfcsTable = rfcsTable;
	}

	public static JTable getActionsTable() {
		return actionsTable;
	}

	public static void setActionsTable(JTable actionsTable) {
		actionsTable = actionsTable;
	}

	public static JTable getActionsResultTable() {
		return actionsResultTable;
	}

	public static void setActionsResultTable(JTable actionsResultTable) {
		actionsResultTable = actionsResultTable;
	}

	public static JTable getKbTable() {
		return kbTable;
	}

	public static void setKbTable(JTable kbTable) {
		kbTable = kbTable;
	}

	public static JTable getActions() {
		return actions;
	}

	public static void setActions(JTable actions) {
		actions = actions;
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/gui/GuiAdmin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */