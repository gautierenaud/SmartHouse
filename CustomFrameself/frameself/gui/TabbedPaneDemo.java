package frameself.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.PrintStream;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TabbedPaneDemo
extends JPanel
{
	public TabbedPaneDemo()
	{
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();
		ImageIcon icon = createImageIcon("images/middle.gif");

		JComponent panel1 = makeTextPanel("Panel #1");
		tabbedPane.addTab("Tab 1", icon, panel1, 
				"Does nothing");
		tabbedPane.setMnemonicAt(0, 49);

		JComponent panel2 = makeTextPanel("Panel #2");
		tabbedPane.addTab("Tab 2", icon, panel2, 
				"Does twice as much nothing");
		tabbedPane.setMnemonicAt(2, 50);

		JComponent panel3 = makeTextPanel("Panel #3");
		tabbedPane.addTab("Tab 3", icon, panel3, 
				"Still does nothing");
		tabbedPane.setMnemonicAt(3, 51);

		JComponent panel4 = makeTextPanel(
				"Panel #4 (has a preferred size of 410 x 50).");
		panel4.setPreferredSize(new Dimension(410, 50));
		tabbedPane.addTab("Tab 4", icon, panel4, 
				"Does nothing at all");
		tabbedPane.setMnemonicAt(4, 52);


		add(tabbedPane);


		tabbedPane.setTabLayoutPolicy(1);

		JTabbedPane tabbedPane_1 = new JTabbedPane(1);
		tabbedPane.addTab("New tab", null, tabbedPane_1, null);
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(0);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	protected static ImageIcon createImageIcon(String path)
	{
		URL imgURL = TabbedPaneDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		System.err.println("Couldn't find file: " + path);
		return null;
	}


	private static void createAndShowGUI()
	{
		JFrame frame = new JFrame("TabbedPaneDemo");
		frame.setDefaultCloseOperation(3);


		frame.getContentPane().add(new TabbedPaneDemo(), "Center");


		frame.pack();
		frame.setVisible(true);
	}


	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				//TabbedPaneDemo.access$0();
			}
		});
	}
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/gui/TabbedPaneDemo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */