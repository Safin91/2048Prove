package GUI_Project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import IAProject.IA;
import Logic_Project.LogicMatrix;
import Logic_Project.LogicMatrix.Direction;

public class Matrix extends JPanel {

	private final int SIZE;

	private JLabel[][] matrix;
	private JLabel score;
	private final JLabel text_score;
	private JButton undo;
	private GridLayout i_hate_gui_java;
	private LogicMatrix logic_matrix;
    private IA ia;
    
    
    
	public LogicMatrix getLogic_matrix() {
		return logic_matrix;
	}

	public void setLogic_matrix(LogicMatrix logic_matrix) {
		this.logic_matrix = logic_matrix;
	}

	public Matrix() {
		ia = new IA(this);
		logic_matrix = new LogicMatrix();
		SIZE = logic_matrix.size;

		text_score = new JLabel("<html><font color='red'>SCORE:</font></html>", JLabel.CENTER);
		text_score.setFont(new Font(text_score.getFont().getName(), text_score.getFont().getStyle(), 25));

		i_hate_gui_java = new GridLayout(0, SIZE);
		score = new JLabel("12488", JLabel.CENTER);
		undo = new JButton("Undo!");
		setLayout(i_hate_gui_java);
		matrix = new JLabel[SIZE][SIZE];
		int value;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {

				value = logic_matrix.getValue(i, j);
				if (value == 0) {
					matrix[i][j] = new JLabel("", JLabel.CENTER);
				} else {
					matrix[i][j] = new JLabel("" + value, JLabel.CENTER);
				}

				matrix[i][j].setOpaque(true);
				matrix[i][j].setPreferredSize(new Dimension(100, 100));
				matrix[i][j].setFont(new Font(matrix[i][j].getFont().getName(), matrix[i][j].getFont().getStyle(), 30));
				matrix[i][j].setBackground(Color.WHITE);
				matrix[i][j].setBorder(new LineBorder(Color.BLACK));
				matrix[i][j].setVisible(true);
				add(matrix[i][j]);
			}
		}

		score.setFont(new Font(score.getFont().getName(), score.getFont().getStyle(), 25));

		undo.setBackground(Color.GREEN);

		addComponentPanel();
		add(text_score);
		add(score);
		add(undo);

		repaint();

		updateUI();
	}

	public void addComponentPanel() 
	{
		undo.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				int value;
				logic_matrix.undo();
                
				for (int i = 0; i < matrix.length; i++) 
				{
					for (int j = 0; j < matrix.length; j++) 
					{
						value=logic_matrix.getValue(i, j);
						  if(value==0) 
						   {
							  matrix[i][j].setText("");
							 
						   }
						  else 
						  {
							  matrix[i][j].setText(""+value);
						  }
						  repaint();
					}
				}
		    
			updateUI();
			}
		}); 
	}

	public void evento(char c) {
		if (c == 'u') {
			logic_matrix.sumMatrix(Direction.UP);
		} else if (c == 'd') {
			logic_matrix.sumMatrix(Direction.DOWN);
		} else if (c == 'l') {
			logic_matrix.sumMatrix(Direction.LEFT);
		} else if (c == 'r') {
			logic_matrix.sumMatrix(Direction.RIGHT);
		}
        
		//TODO DA ELIMINARE
		ia.start();
        ia.execute();
        
        
		scriviMarice();
		repaint();
		updateUI();
	}

	private void scriviMarice() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {

				int value = logic_matrix.getValue(i, j);

				matrix[i][j].setText("" + value);

				if (logic_matrix.getValue(i, j) < 8) {
					matrix[i][j].setBackground(Color.WHITE);
					if (value == 0) {
						matrix[i][j].setText("");
					} // if value!=0

				} else if (value >= 8 && value <= 64) {
					matrix[i][j].setBackground(Color.CYAN);
				} else if (logic_matrix.getValue(i, j) > 64) {
					matrix[i][j].setBackground(Color.YELLOW);
				}
			}
		}
	}
}
