package wald;
import java.util.Scanner;
import java.io.*;
public class wald {
	public int runde=0;
	public Waldflaeche flaeche[][];
	public int Bäume,Waldbestand;
	public boolean brenntnoch;
	public wald(String filename) throws FileNotFoundException{
		File f=new File(filename);
		Scanner sc=new Scanner(f);
		int i,j;
		this.brenntnoch=false;
		i=sc.nextInt();
		j=sc.nextInt();
		sc.nextLine();
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
				case '-': flaeche[x][y]= new Busch(x,y);this.Bäume--;break;
				case 'B': flaeche[x][y]= new Brand(x,y);this.Bäume--;break;
				}
			}
		}
		this.Waldbestand=this.Bäume;
	}
	
	public void runde(){
		runde++;
		int i=flaeche.length;
		int j=flaeche[0].length;
		this.brenntnoch=false;
		for(int x=0;x<i;x++){
			for(int y=0;y<j;y++){
				Waldflaeche.wald.flaeche[x][y].st(true);
			}
		}
		for(int x=0;x<i;x++){
			for(int y=0;y<j;y++){
				Waldflaeche.wald.flaeche[x][y].st(false);
			}
		}
		
		System.out.println("runde "+this.runde);
		System.out.println(this.brenntnoch);
		
	}
	public void end(){
		int i=flaeche.length;
		int j=flaeche[0].length;
		for(int x=0;x<i;x++){
			for(int y=0;y<j;y++){
				if(Waldflaeche.wald.flaeche[x][y].toString().equals("B")){
				Waldflaeche.wald.flaeche[x][y].bst();
				System.out.println(Waldflaeche.wald.flaeche[x][y].inbrantgesteckt);
				return;
				}
			}
		}
	}
}
