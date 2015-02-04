import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class MatrixPanel extends JPanel
{
	public MatrixPanel(int mSize, boolean random)
	{
		matrixSize=mSize;
		@SuppressWarnings({ "unchecked", "unused" })
		ArrayList<ArrayList<JTextField>> originalMatrix = (ArrayList<ArrayList<JTextField>>) matrix.clone();
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
		this.setBorder(raisedBevel);

		GridBagConstraints leftSideBorderConstraints = new GridBagConstraints();
		GridBagConstraints topBorderConstraints = new GridBagConstraints();
		GridBagConstraints matrixAConstraints = new GridBagConstraints();


		JPanel leftSideBorder = makeSideBorder();
		JPanel topBorder = makeTopBorder();
		JPanel matrixA = makeMatrix();

		//fill matrix randomly if parameter random=true
		if(random)
		{
			fillRandomly(matrix);
		}

		//set constraints for GridBagLayout
		leftSideBorderConstraints.fill = GridBagConstraints.HORIZONTAL;
		leftSideBorderConstraints.gridx = 0;
		leftSideBorderConstraints.gridy = 1;
		leftSideBorder.setBackground(Color.white);
		topBorderConstraints.fill = GridBagConstraints.HORIZONTAL;
		topBorderConstraints.gridx = 1;
		topBorderConstraints.gridy = 0;
		topBorder.setBackground(Color.white);
		matrixAConstraints.fill = GridBagConstraints.HORIZONTAL;
		matrixAConstraints.gridx = 1;
		matrixAConstraints.gridy = 1;
		matrixA.setBackground(Color.white);

		this.add(leftSideBorder, leftSideBorderConstraints);
		this.add(topBorder, topBorderConstraints);
		this.add(matrixA, matrixAConstraints);
	}
	

	public void fillRandomly(ArrayList<ArrayList<JTextField>> m)
	{
		for(int k=0;k<matrixSize;k++)
		{
			double pagesWithHyperlinks = rg.nextInt(8)+1;
			double pagesWithoutHyperlinks = 8-pagesWithHyperlinks;
			double probClicked = (1.0/(pagesWithHyperlinks+1))
					- (pagesWithoutHyperlinks*magicProbability/(pagesWithHyperlinks+1)); //+1 for staying on same page
			ArrayList<Integer> linkedPages = getRandomWebPages(pagesWithHyperlinks); //pages that are hyperlinked to by the kth site
			m.get(k).get(0).setText(Double.toString(probClicked)); //likelihood of staying on same website (no hyperlink clicked)
			for (int n = 1; n < matrixSize; n++) 
			{
				Integer test = n;
				if(linkedPages.contains(test))
				{
					m.get(k).get(n).setText(Double.toString(probClicked));
				}
				else
				{
					m.get(k).get(n).setText(Double.toString(magicProbability));
				}
			}
		}
		originalMatrix = m;
	}

	private ArrayList<Integer> getRandomWebPages(double numHyperlinks)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int k = 0; k<numHyperlinks; k++)
			list.add(getRandomWebPages(numHyperlinks,list));
		return list;
	}


	private Integer getRandomWebPages(double numHypers1, ArrayList<Integer> list1)
	{
		int temp = rg.nextInt(matrixSize-1)+1;
		if(!list1.contains(temp))
		{
			return temp;
		}
		else
		{
			return getRandomWebPages(numHypers1,list1); //recursion!!
		}

	}

	public ArrayList<ArrayList<JTextField>> getMatrix()
	{
		return matrix;
	}

	private JPanel makeSideBorder()
	{
		JPanel matrixSideBorder = new JPanel();
		matrixSideBorder.setLayout(new BoxLayout(matrixSideBorder, BoxLayout.Y_AXIS));
		for(int r=1; r<=matrixSize;r++)
		{
			String page = ""+(char)('A'+r-1);
			JLabel matrixBorderLabel = new JLabel(page);
			matrixSideBorder.add(matrixBorderLabel);
			if(r!=matrixSize)
			{
				matrixSideBorder.add(Box.createRigidArea(new Dimension(0,12)));
			}
		}
		return matrixSideBorder;
	}

	private JPanel makeTopBorder()
	{
		JPanel matrixTopBorder = new JPanel();
		matrixTopBorder.setLayout(new FlowLayout());
		for(int c=1; c<=matrixSize;c++)
		{
			String page = ""+(char)('A'+c-1);
			JLabel matrixBorderLabel = new JLabel(page);
			matrixTopBorder.add(matrixBorderLabel);
			if(c!=matrixSize)
			{
				matrixTopBorder.add(Box.createRigidArea(new Dimension(55,0)));
			}
		}
		return matrixTopBorder;
	}

	private JPanel makeMatrix()
	{
		JPanel matrixA = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		for(int c=1; c<=matrixSize;c++)
		{
			ArrayList<JTextField> columnOfMatrix = new ArrayList<JTextField>();
			for(int r=1;r<=matrixSize;r++)
			{
				final JTextField matrixField = new JTextField(5);
				String col = ""+(char)('A'+c-1);
				String row = ""+(char)('A'+r-1);
				matrixField.setText(row+col);
				constraints.fill = GridBagConstraints.HORIZONTAL;
				constraints.gridx = c;
				constraints.gridy = r;
				columnOfMatrix.add(matrixField);
				matrixA.add(matrixField, constraints);	
			}
			matrix.add(columnOfMatrix);
		}
		return matrixA;
	}
	
	public void square() 
	{
		@SuppressWarnings("unchecked")
		ArrayList<ArrayList<JTextField>> squaredMatrix = (ArrayList<ArrayList<JTextField>>) originalMatrix.clone();
		double temp = 0;
		
		for(int c=0;c<matrixSize;c++)
		{
			for(int r=0;r<matrixSize;r++)
			{
				temp = 0;
				for(int k=0;k<matrixSize;k++)
					{
						temp += Double.parseDouble(matrix.get(k).get(r).getText())*Double.parseDouble(matrix.get(c).get(k).getText());
						debug = "k&r&c:"+k+""+r+""+c+" "+matrix.get(k).get(r).getText()+" * "+matrix.get(c).get(k).getText();
						System.out.println(debug+"+");
					}
				System.out.println("temp "+temp);
				squaredMatrix.get(c).get(r).setText(Double.toString(temp));
				
			}
		}
		
		matrix= squaredMatrix;
	}
	
	

	private static final long serialVersionUID = 1L;
	Random rg = new Random();
	protected int matrixSize; //matrixSize=rows=columns because matrix always square
	protected double magicProbability = 0.005;
	private ArrayList<ArrayList<JTextField>> matrix = new ArrayList<ArrayList<JTextField>>();
	ArrayList<ArrayList<JTextField>> originalMatrix = new ArrayList<ArrayList<JTextField>>();
	static String debug ="";
}
