package Project2048.com.project;



/*
 *  input deve prendere la matrice da DL
 * */
public class App 
{
    public static void main( String[] args )
    {        
    	Finestra finestra = new Finestra();
     	Matrice matrice =new Matrice();
    	
        finestra.add(matrice);
        finestra.repaint();
    }
}
