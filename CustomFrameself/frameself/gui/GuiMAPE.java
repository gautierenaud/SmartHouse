package frameself.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

















public class GuiMAPE
{
	public static JFrame frame;
	public static JPanel actuatorsJPanel;
	public static JPanel sensorsJPanel;
	public static JPanel eventsJPane;
	public static JPanel symptomsJPane;
	public static JLabel monitorModel;
	public static JLabel monitorArrow;
	public static JLabel analyzerArrow;
	public static JLabel analyzerModel;
	public static JPanel requestsJPane;
	public static JLabel plannerArrow;
	public static JLabel plannerModel;
	public static JPanel plansJPane;
	public static JLabel executorArrow;
	public static JLabel executorModel;
	public static JPanel actionsJPane;
	public static double sWidth;
	public static double sHeight;
	public static double pWidth = 1564.0D;
	public static double pHeight = 874.0D;

	public static int xMargin;

	public static int yMargin;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiMAPE window = new GuiMAPE();
					GuiMAPE.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}




	public GuiMAPE()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		sWidth = screenSize.getWidth();
		sHeight = screenSize.getHeight();
		xMargin = (int)(sWidth - pWidth) / 2;
		yMargin = (int)(sHeight - pHeight) / 2;


		initialize();
	}




	private void initialize()
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(0, 0, (int)sWidth, (int)sHeight);
		frame.setDefaultCloseOperation(3);
		frame.getContentPane().setLayout(null);

		executorArrow = new JLabel("");
		executorArrow.setVisible(false);
		executorArrow.setBounds(xMargin + 1027, yMargin + 419, 122, 76);
		executorArrow.setIcon(new ImageIcon("images/fe.png"));
		frame.getContentPane().add(executorArrow);

		plannerArrow = new JLabel("");
		plannerArrow.setVisible(false);
		plannerArrow.setBounds(xMargin + 989, yMargin + 166, 90, 100);
		plannerArrow.setIcon(new ImageIcon("images/fp.png"));
		frame.getContentPane().add(plannerArrow);

		analyzerArrow = new JLabel("");
		analyzerArrow.setVisible(false);
		analyzerArrow.setBounds(xMargin + 477, yMargin + 169, 89, 97);
		analyzerArrow.setIcon(new ImageIcon("images/fa.png"));
		frame.getContentPane().add(analyzerArrow);

		monitorArrow = new JLabel("");
		monitorArrow.setVisible(false);
		monitorArrow.setBounds(xMargin + 407, yMargin + 419, 148, 76);
		monitorArrow.setIcon(new ImageIcon("images/fm.png"));
		frame.getContentPane().add(monitorArrow);

		JLabel lblBase = new JLabel("Base");
		lblBase.setBounds(xMargin + 743, yMargin + 453, 81, 37);
		lblBase.setForeground(Color.WHITE);
		lblBase.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblBase);

		JLabel lblKnowledge = new JLabel("Knowledge");
		lblKnowledge.setBounds(xMargin + 706, yMargin + 419, 148, 37);
		lblKnowledge.setForeground(Color.WHITE);
		lblKnowledge.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblKnowledge);

		executorModel = new JLabel("");
		executorModel.setEnabled(false);
		executorModel.setBounds(xMargin + 849, yMargin + 397, 229, 166);
		executorModel.setIcon(new ImageIcon("images/model4.png"));
		frame.getContentPane().add(executorModel);

		plannerModel = new JLabel("");
		plannerModel.setEnabled(false);
		plannerModel.setBounds(xMargin + 829, yMargin + 236, 197, 173);
		plannerModel.setIcon(new ImageIcon("images/model3.png"));
		frame.getContentPane().add(plannerModel);

		monitorModel = new JLabel("");
		monitorModel.setEnabled(false);
		monitorModel.setBounds(xMargin + 522, yMargin + 397, 186, 166);
		monitorModel.setIcon(new ImageIcon("images/model2.png"));
		frame.getContentPane().add(monitorModel);

		analyzerModel = new JLabel("");
		analyzerModel.setEnabled(false);
		analyzerModel.setBounds(xMargin + 549, yMargin + 222, 230, 180);
		analyzerModel.setIcon(new ImageIcon("images/model1.png"));
		frame.getContentPane().add(analyzerModel);

		JLabel lblExecutor = new JLabel("Executor");
		lblExecutor.setBounds(xMargin + 1239, yMargin + 349, 123, 37);
		lblExecutor.setForeground(Color.WHITE);
		lblExecutor.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblExecutor);

		JLabel lblMonitor = new JLabel("Monitor");
		lblMonitor.setBounds(xMargin + 173, yMargin + 349, 148, 37);
		lblMonitor.setForeground(Color.WHITE);
		lblMonitor.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblMonitor);

		JLabel lblPlanner_1 = new JLabel("Analyzer");
		lblPlanner_1.setBounds(xMargin + 385, yMargin + 11, 148, 37);
		lblPlanner_1.setForeground(Color.WHITE);
		lblPlanner_1.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblPlanner_1);

		JLabel lblPlanner = new JLabel("Planner");
		lblPlanner.setBounds(xMargin + 1011, yMargin + 11, 148, 37);
		lblPlanner.setForeground(Color.WHITE);
		lblPlanner.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblPlanner);

		symptomsJPane = new JPanel();
		symptomsJPane.setBounds(xMargin + 194, yMargin + 45, 203, 304);
		symptomsJPane.setBackground(new Color(250, 250, 210));
		frame.getContentPane().add(symptomsJPane);
		symptomsJPane.setLayout(new FlowLayout(1, 5, 5));

		actionsJPane = new JPanel();
		actionsJPane.setBounds(xMargin + 1150, yMargin + 385, 186, 368);
		actionsJPane.setBackground(new Color(250, 250, 210));
		frame.getContentPane().add(actionsJPane);
		actionsJPane.setLayout(new FlowLayout(1, 5, 5));

		requestsJPane = new JPanel();
		requestsJPane.setBounds(xMargin + 527, yMargin + 45, 491, 110);
		requestsJPane.setBackground(new Color(250, 250, 210));
		frame.getContentPane().add(requestsJPane);
		requestsJPane.setLayout(new FlowLayout(1, 5, 5));

		plansJPane = new JPanel();
		plansJPane.setBounds(xMargin + 1150, yMargin + 45, 186, 304);
		plansJPane.setBackground(new Color(250, 250, 210));
		frame.getContentPane().add(plansJPane);
		plansJPane.setLayout(new FlowLayout(1, 5, 5));

		eventsJPane = new JPanel();
		eventsJPane.setBounds(xMargin + 200, yMargin + 385, 197, 368);
		eventsJPane.setBackground(new Color(250, 250, 210));
		frame.getContentPane().add(eventsJPane);
		eventsJPane.setLayout(new FlowLayout(1, 5, 5));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(xMargin + 408, yMargin + 218, 793, 350);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("images/k3.png"));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(xMargin + 158, yMargin + 177, 461, 391);
		lblNewLabel.setIcon(new ImageIcon("images/M.png"));
		frame.getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setBounds(xMargin + 227, yMargin + 11, 461, 391);
		label.setIcon(new ImageIcon("images/A.png"));
		frame.getContentPane().add(label);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(xMargin + 888, yMargin + 11, 441, 411);
		lblNewLabel_2.setIcon(new ImageIcon("images/P.png"));
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(xMargin + 983, yMargin + 157, 441, 411);
		lblNewLabel_3.setIcon(new ImageIcon("images/E.png"));
		frame.getContentPane().add(lblNewLabel_3);

		sensorsJPanel = new JPanel();
		sensorsJPanel.setBounds(xMargin + 109, yMargin + 759, 688, 104);
		sensorsJPanel.setBackground(new Color(255, 218, 185));
		frame.getContentPane().add(sensorsJPanel);
		FlowLayout fl_sensorsJPanel = new FlowLayout(1, 5, 5);
		sensorsJPanel.setLayout(fl_sensorsJPanel);

		JLabel lblSensors = new JLabel("Sensors");
		lblSensors.setBounds(xMargin + 0, yMargin + 791, 117, 37);
		lblSensors.setFont(new Font("Tahoma", 0, 30));
		lblSensors.setForeground(new Color(255, 140, 0));
		frame.getContentPane().add(lblSensors);

		JLabel lblEvents = new JLabel("Events");
		lblEvents.setBounds(xMargin + 91, yMargin + 652, 96, 37);
		lblEvents.setForeground(new Color(204, 204, 0));
		lblEvents.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblEvents);

		JLabel lblActions = new JLabel("Actions");
		lblActions.setBounds(xMargin + 1355, yMargin + 637, 96, 37);
		lblActions.setForeground(new Color(204, 204, 0));
		lblActions.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblActions);

		JLabel lblActuators = new JLabel("Actuators");
		lblActuators.setBounds(xMargin + 1430, yMargin + 791, 148, 37);
		lblActuators.setForeground(new Color(255, 140, 0));
		lblActuators.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblActuators);

		JLabel lblSymptoms = new JLabel("Symptoms");
		lblSymptoms.setBounds(xMargin + 36, yMargin + 70, 148, 37);
		lblSymptoms.setForeground(new Color(204, 204, 0));
		lblSymptoms.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblSymptoms);

		JLabel lblRequests = new JLabel("Change Requests");
		lblRequests.setBounds(xMargin + 649, yMargin + 0, 238, 37);
		lblRequests.setForeground(new Color(204, 204, 0));
		lblRequests.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblRequests);

		actuatorsJPanel = new JPanel();
		actuatorsJPanel.setBounds(xMargin + 807, yMargin + 759, 620, 104);
		actuatorsJPanel.setBackground(new Color(255, 218, 185));
		frame.getContentPane().add(actuatorsJPanel);
		actuatorsJPanel.setLayout(new FlowLayout(1, 5, 5));

		JLabel lblMapekLoop = new JLabel("AUTONOMIC COMPUTING");
		lblMapekLoop.setBounds(xMargin + 450, yMargin + 606, 685, 77);
		lblMapekLoop.setForeground(Color.DARK_GRAY);
		lblMapekLoop.setFont(new Font("Tahoma", 0, 57));
		frame.getContentPane().add(lblMapekLoop);

		JLabel lblMapekLoop_1 = new JLabel("(MAPE-K LOOP)");
		lblMapekLoop_1.setBounds(xMargin + 623, yMargin + 671, 307, 77);
		lblMapekLoop_1.setForeground(Color.DARK_GRAY);
		lblMapekLoop_1.setFont(new Font("Tahoma", 0, 43));
		frame.getContentPane().add(lblMapekLoop_1);

		JLabel lblPlans_1 = new JLabel("Plans");
		lblPlans_1.setBounds(xMargin + 1346, yMargin + 53, 94, 70);
		lblPlans_1.setForeground(new Color(204, 204, 0));
		lblPlans_1.setFont(new Font("Tahoma", 0, 30));
		frame.getContentPane().add(lblPlans_1);
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/gui/GuiMAPE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */