package Logic_Project;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.AbstractDocument.BranchElement;

public class LogicMatrix {

	private int [][] current_matrix;
	private int [][] undo_matrix;
	private ArrayList<int[][]> future_direzioni = new ArrayList<int[][]>();
//	TODO private String decisione
	
	
	public ArrayList<int[][]> getFuture_direzioni() {
		return future_direzioni;
	}

	public void setFuture_direzioni(ArrayList<int[][]> future_direzioni) {
		this.future_direzioni = future_direzioni;
	}

	public final int size=4;
	public enum Direction
	{
		RIGHT, // ->
		LEFT,  // <-
		UP,    
		DOWN 
	}

	public LogicMatrix()
	{
       
		current_matrix = new int [size][size];
		undo_matrix = new int [size][size];
		for (int i = 0; i < current_matrix.length; i++) {
			for (int j = 0; j < current_matrix.length; j++) {
				current_matrix[i][j] = 0;
				undo_matrix[i][j] = 0;
			}
		}
	   	
		generateValue();
		generateValue();//inserisce i primi 2 numeri a caso..
		int [][]copy= new int[size][size];
		for (int i = 0; i < copy.length; i++) {
			for (int j = 0; j < copy.length; j++) {
				copy[i][j]=current_matrix[i][j];
			}
		}
		
		future_direzioni.add(direzioneVersoDestraMatrice(copy)); 
		future_direzioni.add(direzioneVersoSUGeneraMatrice(copy));
		future_direzioni.add(direzioneVersoSinistra(copy));
		future_direzioni.add(direzioneVersoDestraMatrice(copy));
		future_direzioni.add(copy); //inserisco la matrice corrente in ultima posizione quindi posizione4 
		
		for (int i = 0; i < current_matrix.length; i++) {
			for (int j = 0; j < current_matrix.length; j++) {
				undo_matrix[i][j] = current_matrix[i][j] ;
			}
		}

		print();//TODO DA ELIMINARE
	}

private boolean any_sum;
	public int[][] sumMatrix(Direction d)
	{
	    any_sum=false;
		saveMatrix();
		
		if(d==Direction.RIGHT)
		{
   		  direzioneVersoDestra();
		}
		else if(d==Direction.LEFT)
		{
			direzioneVersoSinistra();
		}
		else if (d==Direction.UP)
		{
			direzioneVersoSU();
			
		}
		else if(d==Direction.DOWN)
		{
		  direzioneVersoIlbasso();	
		}
		if(any_sum!=false) {
		generateValue();
		}
		print();//TODO DA ELIMINARE
		System.out.println();
	 return current_matrix;
	}
	
	

//TODO da eliminare...
public void print() {

	for (int i = 0; i < current_matrix.length; i++) {
		for (int j = 0; j < current_matrix.length; j++) {
	      System.out.print(current_matrix[i][j]+" ");		
		}
		System.out.println();
	}
	
	System.out.println();
	System.out.println("prova stampa:");
	System.out.println();
	
	int [][]prova_stampa;
	ArrayList<int[][]> da_stampare = new ArrayList<int[][]>(4);
    prova_stampa=generaMatrici(Direction.DOWN);
    da_stampare.add(prova_stampa);
    
    
    prova_stampa=generaMatrici(Direction.UP);
    da_stampare.add(prova_stampa);
    
    prova_stampa=generaMatrici(Direction.LEFT);  
    da_stampare.add(prova_stampa);

    prova_stampa=generaMatrici(Direction.RIGHT);
    da_stampare.add(prova_stampa);
    
    
    da_stampare.add(current_matrix);// TODO potenziali problemi...
    
    
future_direzioni.set(0, da_stampare.get(0)); //0 giu
future_direzioni.set(1, da_stampare.get(1)); //1 su
future_direzioni.set(2, da_stampare.get(2)); //2 left
future_direzioni.set(3, da_stampare.get(3)); //3 right
future_direzioni.set(4, da_stampare.get(4)); //precedente



   for (int i = 0; i < prova_stampa.length; i++) {
   	
       for (int j = 0; j < da_stampare.size(); j++) {
		for (int j2 = 0; j2 < prova_stampa.length; j2++) {
			System.out.print(future_direzioni.get(j)[i][j2]+" ");
		   }
		System.out.print("     ");
	   }
       System.out.println(); 
     }
   
}
	
private void generateValue()
{
   	Random gen1 =new Random();
   	int a,b;
   	do
    {
   		a=gen1.nextInt(size);
   		b=gen1.nextInt(size);
    }   	
    while(current_matrix[a][b]!=0);
   	
    current_matrix[a][b]=2;
    
}

public void setValue(int i , int j, int value)
{
	
	assert value >=0  : "value deve essere maggiore di 0 "+value;
	assert value%2==0 : "value deve essere divisibile per 2 "+value;
	
	current_matrix[i][j]=value;
}


public int getValue(int i, int j)
{
   return current_matrix[i][j];	
}

	

private void saveMatrix()
{
  for (int i = 0; i < current_matrix.length; i++) 
  {
	 for (int j = 0; j < current_matrix.length; j++)
	 {
	     	undo_matrix[i][j]=current_matrix[i][j];
	 }
	  
   }
     
}

public void undo()
{
  for (int i = 0; i < current_matrix.length; i++) 
  {
	 for (int j = 0; j < current_matrix.length; j++) 
	 {
		 current_matrix[i][j]=undo_matrix[i][j];
  	 }
		  
  }
}




//funzioni private 	


//********************** direzione verso il basso ***************************	
private boolean any_move=false;

private void direzioneVersoIlbasso() 	
{
	any_move=false;
	for (int j = 0; j <current_matrix.length ; j++) 
	{
		int indice_inserimento=current_matrix.length-1;
		int daSommare=0;
		
		for (int i = current_matrix.length-1; i>=0;  i--) 
		{
			
			 if(current_matrix[i][j]!=0)
			 {
				 if(daSommare==0) {
					 daSommare=current_matrix[i][j];
						 if(indice_inserimento !=i)
						 {  
							 any_move=true;
							 current_matrix[indice_inserimento][j]=current_matrix[i][j];
							 current_matrix[i][j]=0;
					     }
				 }else if(current_matrix[i][j] == daSommare && daSommare!=0)
				 {
					 any_sum=true;
                     current_matrix[indice_inserimento][j]+=daSommare;
                     current_matrix[i][j]=0;
                     daSommare=0;
                     indice_inserimento--;
				 }
				 else if(current_matrix[i][j] != daSommare && daSommare!=0)
				 {
					 indice_inserimento--;
					 if(indice_inserimento !=i)
				     { 
						 any_move=true;
                       current_matrix[indice_inserimento][j]=current_matrix[i][j];
                       current_matrix[i][j]=0;
				     }
                     daSommare=current_matrix[indice_inserimento][j];
                  }	 
				  

			 }//if!=0
		}//for j
	}//for i
	if(any_move== true && any_sum ==false)
	{
		any_sum=true;
	}
}



//********************** direzione verso L'alto ***************************	

private void direzioneVersoSU() {
	any_move=false;
	for (int j = 0; j < current_matrix.length; j++) 
	{
		int indice_inserimento=0;
		int daSommare=0;
		for (int i = 0; i < current_matrix.length; i++) 
		{
			
			 if(current_matrix[i][j]!=0)
			 {
				 if(daSommare==0) {
					 daSommare=current_matrix[i][j];
					 if(indice_inserimento !=i)
					 {  
						 any_move=true;
						 current_matrix[indice_inserimento][j]=current_matrix[i][j];
						 current_matrix[i][j]=0;
				     }
				 }else if(current_matrix[i][j] == daSommare && daSommare!=0)
				   {
					 any_sum=true;
                     current_matrix[indice_inserimento][j]+=daSommare;
                     current_matrix[i][j]=0;
                     daSommare=0;
                     indice_inserimento++;
				   }
				 else if(current_matrix[i][j] != daSommare && daSommare!=0)
				 {
					 indice_inserimento++;
					 if(indice_inserimento !=i)
				     { 
						 any_move=true;
					   current_matrix[indice_inserimento][j]=current_matrix[i][j];
                       current_matrix[i][j]=0;
				     } 
                     daSommare=current_matrix[indice_inserimento][j];
                  }	 

			 }//if!=0
		}//for j
	}//for i
	if(any_move== true && any_sum ==false)
	{
		any_sum=true;
	}
}


//********************** direzione verso Sinistra ***************************	

private void direzioneVersoSinistra() 
{
	any_move=false;
	for (int i = 0; i < current_matrix.length; i++) 
	{
		int indice_inserimento=0;
		int daSommare=0;
		for (int j = 0; j < current_matrix.length; j++) 
		{
			 if(current_matrix[i][j]!=0)
			 {
				 if(daSommare==0) {
					 daSommare=current_matrix[i][j];
					 if(indice_inserimento !=j)
					 {  
						 any_move=true;
						 current_matrix[i][indice_inserimento]=current_matrix[i][j];
						 current_matrix[i][j]=0;
				     }
				 }else if(current_matrix[i][j] == daSommare && daSommare!=0)
				   {
					 any_sum=true;
                     current_matrix[i][indice_inserimento]+=daSommare;
                     current_matrix[i][j]=0;
                     daSommare=0;
                     indice_inserimento++;
				   }
				 else if(current_matrix[i][j] != daSommare && daSommare!=0)
				 {
					 indice_inserimento++;
					 if(indice_inserimento !=j)
				     { 
						 any_move=true;
                       current_matrix[i][indice_inserimento]=current_matrix[i][j];
                       current_matrix[i][j]=0;
				     }
                     daSommare=current_matrix[i][indice_inserimento];
                  }	 

			 }//if!=0
		}//for j
	}//for i
	
	if(any_move== true && any_sum ==false)
	{
		any_sum=true;
	}
}


//********************** direzione verso destra ***************************	
private void direzioneVersoDestra()
	{
	any_move=false;	
		for (int i = 0; i < current_matrix.length; i++) 
		{
			int indice_inserimento=current_matrix.length-1;
			int daSommare=0;
			for (int j = current_matrix.length-1; j >=0 ; j--) {
				
				
				
				 if(current_matrix[i][j]!=0)
				 {
					 if(daSommare==0) {
						 daSommare=current_matrix[i][j];
  						 if(indice_inserimento !=j)
  						 {  
  							 any_move=true;
  							 current_matrix[i][indice_inserimento]=current_matrix[i][j];
  							 current_matrix[i][j]=0;
  					     }
					 }else if(current_matrix[i][j] == daSommare && daSommare!=0)
					 {
						 any_sum=true;
                         current_matrix[i][indice_inserimento]+=daSommare;
                         current_matrix[i][j]=0;
                         daSommare=0;
                         indice_inserimento--;
					 }
					 else if(current_matrix[i][j] != daSommare && daSommare!=0)
					 {
						 indice_inserimento--;
						 if(indice_inserimento !=j)
  						 {
						 any_move=true;
                         current_matrix[i][indice_inserimento]=current_matrix[i][j];
                         current_matrix[i][j]=0;
  						 }
                         daSommare=current_matrix[i][indice_inserimento];
                      }	 
					  
					 
	
				 }//if!=0
			}//for j
		}//for i
		if(any_move== true && any_sum ==false)
		{
			any_sum=true;
		}

	}
   



public int[][] generaMatrici(Direction d)
{
  int [][] pino_return =new int[size][size];
  int [][] pino_retur =new int[size][size];

  //copia della matrice corrente
	  for (int i = 0; i < current_matrix.length; i++) 
	  {
		 for (int j = 0; j < current_matrix.length; j++)
		 {
		     	pino_return[i][j]=current_matrix[i][j];
		 }
	   }	 
		 switch(d) 
		 {
		 case  RIGHT: // ->
			 return direzioneVersoDestraMatrice(pino_return);
		 case  	LEFT:  // <-
	     	 return direzioneVersoSinistra(pino_return);
		 case  	UP: 
			  return direzioneVersoSUGeneraMatrice(pino_return);
	     case  DOWN :
 	  	    	return direzioneVersoIlbassoGeneraMatrice(pino_return);

 		
		 }
		 

//	 	  System.out.println("pino_return dentro: genera matrice");
//		    for (int i = 0; i < current_matrix.length; i++) {
//				for (int j = 0; j < current_matrix.length; j++) {
//			        
//					System.out.print(pino_retur[i][j]+" ");		
//				}
//				System.out.println();
//			} 
//		    System.out.println();
    return pino_retur;
}


//TODO .................... genera 4 matrici.................................


private int[][] direzioneVersoIlbassoGeneraMatrice(int [][] pino_return) 	
{
	any_move=false;
	for (int j = 0; j <pino_return.length ; j++) 
	{
		int indice_inserimento=pino_return.length-1;
		int daSommare=0;
		
		for (int i = pino_return.length-1; i>=0;  i--) 
		{
			
			 if(pino_return[i][j]!=0)
			 {
				 if(daSommare==0) {
					 daSommare=pino_return[i][j];
						 if(indice_inserimento !=i)
						 {  
							 any_move=true;
							 pino_return[indice_inserimento][j]=pino_return[i][j];
							 pino_return[i][j]=0;
					     }
				 }else if(pino_return[i][j] == daSommare && daSommare!=0)
				 {
					 any_sum=true;
                     pino_return[indice_inserimento][j]+=daSommare;
                     pino_return[i][j]=0;
                     daSommare=0;
                     indice_inserimento--;
				 }
				 else if(pino_return[i][j] != daSommare && daSommare!=0)
				 {
					 indice_inserimento--;
					 if(indice_inserimento !=i)
				     { 
						 any_move=true;
                       pino_return[indice_inserimento][j]=pino_return[i][j];
                       pino_return[i][j]=0;
				     }
                     daSommare=pino_return[indice_inserimento][j];
                  }	 
				  

			 }//if!=0
		}//for j
	}//for i
	if(any_move== true && any_sum ==false)
	{
		any_sum=true;
	}


	
	return pino_return;
}


//********************** direzione verso L'alto ***************************	

private int[][] direzioneVersoSUGeneraMatrice(int [][] pino_return) {
	any_move=false;
	for (int j = 0; j < pino_return.length; j++) 
	{
		int indice_inserimento=0;
		int daSommare=0;
		for (int i = 0; i < pino_return.length; i++) 
		{
			
			 if(pino_return[i][j]!=0)
			 {
				 if(daSommare==0) {
					 daSommare=pino_return[i][j];
					 if(indice_inserimento !=i)
					 {  
						 any_move=true;
						 pino_return[indice_inserimento][j]=pino_return[i][j];
						 pino_return[i][j]=0;
				     }
				 }else if(pino_return[i][j] == daSommare && daSommare!=0)
				   {
					 any_sum=true;
                     pino_return[indice_inserimento][j]+=daSommare;
                     pino_return[i][j]=0;
                     daSommare=0;
                     indice_inserimento++;
				   }
				 else if(pino_return[i][j] != daSommare && daSommare!=0)
				 {
					 indice_inserimento++;
					 if(indice_inserimento !=i)
				     { 
						 any_move=true;
					   pino_return[indice_inserimento][j]=pino_return[i][j];
                       pino_return[i][j]=0;
				     } 
                     daSommare=pino_return[indice_inserimento][j];
                  }	 

			 }//if!=0
		}//for j
	}//for i
	if(any_move== true && any_sum ==false)
	{
		any_sum=true;
	}
	return pino_return;
}


//********************** direzione verso Sinistra ***************************	

private int[][] direzioneVersoSinistra(int [][] pino_return) 
{
	any_move=false;
	for (int i = 0; i < pino_return.length; i++) 
	{
		int indice_inserimento=0;
		int daSommare=0;
		for (int j = 0; j < pino_return.length; j++) 
		{
			 if(pino_return[i][j]!=0)
			 {
				 if(daSommare==0) {
					 daSommare=pino_return[i][j];
					 if(indice_inserimento !=j)
					 {  
						 any_move=true;
						 pino_return[i][indice_inserimento]=pino_return[i][j];
						 pino_return[i][j]=0;
				     }
				 }else if(pino_return[i][j] == daSommare && daSommare!=0)
				   {
					 any_sum=true;
                     pino_return[i][indice_inserimento]+=daSommare;
                     pino_return[i][j]=0;
                     daSommare=0;
                     indice_inserimento++;
				   }
				 else if(pino_return[i][j] != daSommare && daSommare!=0)
				 {
					 indice_inserimento++;
					 if(indice_inserimento !=j)
				     { 
						 any_move=true;
                       pino_return[i][indice_inserimento]=pino_return[i][j];
                       pino_return[i][j]=0;
				     }
                     daSommare=pino_return[i][indice_inserimento];
                  }	 

			 }//if!=0
		}//for j
	}//for i
	
	if(any_move== true && any_sum ==false)
	{
		any_sum=true;
	}

return pino_return;
}



//********************** direzione verso destra ***************************	
private int[][] direzioneVersoDestraMatrice(int[][] pino_return)
	{
	any_move=false;	
		for (int i = 0; i < pino_return.length; i++) 
		{
			int indice_inserimento=pino_return.length-1;
			int daSommare=0;
			for (int j = pino_return.length-1; j >=0 ; j--) {
				
				
				
				 if(pino_return[i][j]!=0)
				 {
					 if(daSommare==0) {
						 daSommare=pino_return[i][j];
  						 if(indice_inserimento !=j)
  						 {  
  							 any_move=true;
  							 pino_return[i][indice_inserimento]=pino_return[i][j];
  							 pino_return[i][j]=0;
  					     }
					 }else if(pino_return[i][j] == daSommare && daSommare!=0)
					 {
						 any_sum=true;
                         pino_return[i][indice_inserimento]+=daSommare;
                         pino_return[i][j]=0;
                         daSommare=0;
                         indice_inserimento--;
					 }
					 else if(pino_return[i][j] != daSommare && daSommare!=0)
					 {
						 indice_inserimento--;
						 if(indice_inserimento !=j)
  						 {
						 any_move=true;
                         pino_return[i][indice_inserimento]=pino_return[i][j];
                         pino_return[i][j]=0;
  						 }
                         daSommare=pino_return[i][indice_inserimento];
                      }	 
					  
					 
	
				 }//if!=0
			}//for j
		}//for i
		if(any_move== true && any_sum ==false)
		{
			any_sum=true;
		}
     return pino_return;
	}






	



}
	