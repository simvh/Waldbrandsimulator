package wald;
import java.util.Scanner;
import java.io.*;
public class wald {
	
	public Waldflaeche flaeche[][];
	protected int Bäume,Waldbestand;
	public wald(String filename) throws FileNotFoundException{
		Scanner sc=new Scanner(new File(filename));
		int i,j;
		i=sc.nextInt();
		j=sc.nextInt();
		this.Bäume=i*j;
		this.flaeche=new Waldflaeche[i][j];
		for(int x=0;x<i;x++){
			String line;
			line=sc.nextLine();
			for(int y=0;y<j;y++){
				char c;
				c=line.charAt(y);
				Waldflaeche.wald=this;
				switch(c){
				case 'N': flaeche[x][y]= new Nadelbaum(x,y);break;
				case 'L': flaeche[x][y]= new Laubbaum(x,y);break;
				case '-': flaeche[x][y]= new Busch(x,y);break;
				case 'B': flaeche[x][y]= new Brand(x,y);this.Bäume--;break;
				}
			}
		}
		this.Waldbestand=this.Bäume;
	}
	
	public void runde(){
		int i=flaeche.length;
		int j=flaeche[0].length;
		for(int s=0;s<2;s++){
			for(int x=0;x<i;x++){
				for(int y=0;y<j;y++){
					this.flaeche[x][y].st(s==0);
				}
			}
		}
		
		
	}
}
