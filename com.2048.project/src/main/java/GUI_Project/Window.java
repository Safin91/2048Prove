package GUI_Project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {

	private Matrix pannel;
	
 public Window( ) {
	 super("2048_IA_Edition");
	 pannel= new Matrix();
	  setSize(530,650);
	  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  setVisible(true);
	  setLocationRelativeTo(null);
	  setResizable(false);
	  add(pannel);
	  addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyPressed(KeyEvent e) {
			    if(e.getKeyCode() == KeyEvent.VK_UP){
//                  System.out.println("SU");
			    	pannel.evento('u');
                  }
                  else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                  {
                	  pannel.evento('d');
//                	  System.out.println("GIU'");
                  }
                  else if(e.getKeyCode() == KeyEvent.VK_LEFT)
                  {
                	  pannel.evento('l');
//                	  System.out.println("SINISTRA");
                  }
                  else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                  {
//                	  System.out.println("DESTRA");
                	  pannel.evento('r');
                  }
			    
//			   pannel.repaint();  
              }
			
		});

	
    repaint();
 }	

}
