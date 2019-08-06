package Project2048.com.project;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;

public class Matrice extends JPanel {

	private JLabel [][]text1;
	private JLabel [][]text1_back_copy;
	private JLabel score;
	private final JLabel text_score;
	private JButton undo;
	private GridLayout odiolaguijava;


	public Matrice() {


		text_score=new JLabel("<html><font color='red'>SCORE:</font></html>",JLabel.CENTER);
		text_score.setFont(new Font(text_score.getFont().getName(), text_score.getFont().getStyle(), 25));

		odiolaguijava =new GridLayout(0,4); //setLayout
		score = new JLabel("12488",JLabel.CENTER);  
		undo = new JButton("Undo!");
		setLayout(odiolaguijava);

		text1 = new JLabel[4][4];
		text1_back_copy = new JLabel[4][4]; //TODO da inizializzare vedi text1!! 
		//questa matrice mi serve per undo!

		for (int i = 0; i < text1.length; i++) {

			for (int j = 0; j < text1.length; j++) {
				text1[i][j] = new JLabel("1",JLabel.CENTER);
				text1_back_copy[i][j] = new JLabel("1",JLabel.CENTER);

				text1[i][j].setPreferredSize(new Dimension(100,100));
				text1[i][j].setFont(new Font(text1[i][j].getFont().getName(), text1[i][j].getFont().getStyle(), 20));
				text1[i][j].setBackground(Color.BLUE);
				text1[i][j].setBorder(new LineBorder(Color.BLACK));
				text1[i][j].setVisible(true);
				add(text1[i][j]);
	
			}

		}


		score.setFont(new Font(score.getFont().getName(), score.getFont().getStyle(), 25));

		undo.setBackground(Color.GREEN);

		text1[2][2].setText("4"); //daEliminare
		text1[3][3].setText("4"); //daEliminare
		text1[2][1].setText("4"); //daEliminare


		addComponentPanel();
		add(text_score);
		add(score);
		add(undo);
		repaint();

		updateUI();
	}


	//	public Matrice() {
	//		  
	//		
	//		text1 = new JLabel("1",JLabel.CENTER);
	//		text1.setPreferredSize(new Dimension(100,100));
	//        text1.setFont(new Font(text1.getFont().getName(), text1.getFont().getStyle(), 20));
	//		text1.setBackground(Color.BLUE);
	//		text1.setBorder(new LineBorder(Color.BLACK));
	//
	//
	//		add(text1);
	//		repaint();
	//		updateUI();
	//	}

	public void addComponentPanel() 
	{
		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
 				
				for (int i = 0; i < text1.length; i++) {
					for (int j = 0; j < text1.length; j++) {

						text1[i][j].setText(text1_back_copy[i][j].getText());
					}
				}
		
			repaint();
			updateUI();
		}
	});
}


}
