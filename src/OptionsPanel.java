import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class OptionsPanel extends JPanel
{
	public OptionsPanel(final MatrixPanel mat1, final MatrixPanel mat2)
	{
		JButton randomizeM1 = new JButton("Randomize");
		JButton randomizeM2 = new JButton("Randomize");

		randomizeM1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						mat1.fillRandomly(mat1.getMatrix());
					}
				}).start();
			}	  
		});
		randomizeM2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				new Thread(new Runnable() {
//					public void run() {
						mat2.fillRandomly(mat2.getMatrix());
//					}
//				}).start();
			}	  
		});

		JButton cloneM1 = new JButton("Clone Other");
		JButton cloneM2 = new JButton("Clone Other");

		cloneM1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						for(int c=0;c<mat1.matrixSize;c++)
						{
							for(int r=0;r<mat1.matrixSize;r++)
							{
								mat1.getMatrix().get(c).get(r).setText(mat2.getMatrix().get(c).get(r).getText());
							}
						}
					}
				}).start();
			}	  
		});
		cloneM2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						for(int c=0;c<mat1.matrixSize;c++)
						{
							for(int r=0;r<mat1.matrixSize;r++)
							{
								mat2.getMatrix().get(c).get(r).setText(mat1.getMatrix().get(c).get(r).getText());
							}
						}
					}
				}).start();
			}	  
		});

		JButton compute1 = new JButton("Square");
		JButton compute2 = new JButton("Square");
		
		compute1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run()
					{
						mat1.square();
					}
				}).start();
			}	  
		});
		compute2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run()
					{
						mat2.square();
					}
				}).start();
			}	  
		});

		GridBagConstraints cloneM1Constraints = new GridBagConstraints();
		GridBagConstraints cloneM2Constraints = new GridBagConstraints();
		GridBagConstraints randomizeM1Constraints = new GridBagConstraints();
		GridBagConstraints randomizeM2Constraints = new GridBagConstraints();
		GridBagConstraints compute1Constraints = new GridBagConstraints();
		GridBagConstraints compute2Constraints = new GridBagConstraints();
		randomizeM1Constraints.gridx = 0;
		randomizeM1Constraints.gridy = 0;
		randomizeM2Constraints.gridx = 3;
		randomizeM2Constraints.gridy = 0;
		cloneM1Constraints.gridx = 1;
		cloneM1Constraints.gridy = 0;
		cloneM2Constraints.gridx = 4;
		cloneM2Constraints.gridy = 0;
		compute1Constraints.insets = new Insets(0,0,0,375);
		compute1Constraints.gridx = 2;
		compute1Constraints.gridy = 0;
		compute2Constraints.insets = new Insets(0,0,0,345);
		compute2Constraints.gridx = 5;
		compute2Constraints.gridy = 0;

		this.setLayout(new GridBagLayout());
		this.add(cloneM1,cloneM1Constraints);
		this.add(cloneM2,cloneM2Constraints);
		this.add(randomizeM1,randomizeM1Constraints);
		this.add(randomizeM2,randomizeM2Constraints);
		this.add(compute1, compute1Constraints);
		this.add(compute2, compute2Constraints);

	}

	private static final long serialVersionUID = 1L;
}
