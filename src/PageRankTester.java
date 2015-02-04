import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;

public class PageRankTester
{
	public static void main (String[] args)
	{
		JFrame frame = new JFrame();

		//Create Panels
		final MatrixPanel m1 = new MatrixPanel(9,false);
		final MatrixPanel m2 = new MatrixPanel(9, true);
		OptionsPanel opPanel = new OptionsPanel(m1,m2);
		
		//Constraints for GridBagLayout
		GridBagConstraints m1Constraints = new GridBagConstraints();
		GridBagConstraints m2Constraints = new GridBagConstraints();
		GridBagConstraints opPanelConstraints = new GridBagConstraints();
		m1Constraints.gridx = 0;
		m1Constraints.gridy = 0;
		m1Constraints.insets = new Insets(8,8,8,8);
		m2Constraints.gridx = 1;
		m2Constraints.gridy = 0;
		m2Constraints.insets = new Insets(8,8,8,8);
		opPanelConstraints.gridx=0;
		opPanelConstraints.gridy=1;
		opPanelConstraints.gridwidth=2;

		//Add panels to and alter the main frame
		frame.setLayout(new GridBagLayout());
		frame.setBackground(Color.red.darker().darker());
		frame.add(m1,m1Constraints);
		frame.add(m2,m2Constraints);
		frame.add(opPanel, opPanelConstraints);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
