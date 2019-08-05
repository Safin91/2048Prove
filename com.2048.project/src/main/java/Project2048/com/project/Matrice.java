package Project2048.com.project;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Matrice extends JPanel {

	private JLabel text1; 

	public Matrice() {

		text1 = new JLabel("1",JLabel.CENTER);
		text1.setPreferredSize(new Dimension(100,100));
        text1.setFont(new Font(text1.getFont().getName(), text1.getFont().getStyle(), 20));
        //text1.setHorizontalTextPosition(55);  
		text1.setBackground(Color.BLUE);
		text1.setBorder(new LineBorder(Color.BLACK));

		//	text1.setText("Ciao");// ok modifica il testo 

		add(text1);
		repaint();
		updateUI();
	}
   


}
