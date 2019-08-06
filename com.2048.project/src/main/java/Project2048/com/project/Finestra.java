package Project2048.com.project;

import javax.swing.JFrame;

public class Finestra extends JFrame {

	public Finestra() {
	  super("2048_IA_Edition");
	  setSize(530,650);
	  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  setVisible(true);
	  setLocationRelativeTo(null);
	  setResizable(false);
//      setLayout(null);
     repaint();
    
	}
	
	
}
