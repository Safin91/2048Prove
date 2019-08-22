package Logic_Project;

import java.util.Random;

public class LogicMatrix {

	private int [][] current_matrix;
	private int [][] undo_matrix;	
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

		for (int i = 0; i < current_matrix.length; i++) {
			for (int j = 0; j < current_matrix.length; j++) {
				undo_matrix[i][j] = current_matrix[i][j] ;
			}
		}

		
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
   
	
	
	
}
	