package wald;
import java.util.Scanner;
import java.io.*;
public class wald {
	/**
	 * gibt an in welcher Runde der Wald ist
	 */
	public int runde=0;
	/**
	 * Die Flaeche des Waldes
	 */
	public Waldflaeche flaeche[][];
	public int Bäume,Waldbestand;
	/**
	 * gibt an ob der Wald noch brennt
	 */
	public boolean brenntnoch;
        
        public wald(char[][] w){
            int i,j;
		this.brenntnoch=false;
		i=w.length;
		j=w[0].length;
		this.Bäume=i*j;
		this.flaeche=new Waldflaeche[i][j];
		for(int x=0;x<i;x++){
			for(int y=0;y<j;y++){
                                Waldflaeche.wald=this;
				switch(w[x][y]){
				case 'N': flaeche[x][y]= new Nadelbaum(x,y);break;
				case 'L': flaeche[x][y]= new Laubbaum(x,y);break;
				case '-': flaeche[x][y]= new Busch(x,y);this.Bäume--;break;
				case 'B': flaeche[x][y]= new Brand(x,y);this.Bäume--;break;
				case 'A': flaeche[x][y]=new Asche(x,y,0);this.Bäume--;break;
				case'a': flaeche[x][y]=new abgeholzt(x,y);break;
				}
			}
		}
		this.Waldbestand=this.Bäume;
        }
        
	public wald(String filename) throws FileNotFoundException{
		File f=new File(filename);
		Scanner sc=new Scanner(f);
                String line;
		int i,j;
		this.brenntnoch=false;
		i=sc.nextInt();
		j=sc.nextInt();
		sc.nextLine();
		this.Bäume=i*j;
		this.flaeche=new Waldflaeche[i][j];
		for(int x=0;x<i;x++){
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
				case 'A': flaeche[x][y]=new Asche(x,y,0);this.Bäume--;break;
				case'a': flaeche[x][y]=new abgeholzt(x,y);break;
				}
			}
		}
		sc.close();
		this.Waldbestand=this.Bäume;
	}
        
	public wald(wald w){
		int i,j;
		this.brenntnoch=w.brenntnoch;
		i=w.flaeche.length;
		j=w.flaeche[0].length;
		this.flaeche=new Waldflaeche[i][j];
		for(int x=0;x<i;x++){
			for(int y=0;y<j;y++){
				switch(w.flaeche[x][y].toChar()){
				case 'N': flaeche[x][y]= new Nadelbaum(x,y);flaeche[x][y].brennen=w.flaeche[x][y].brennen;flaeche[x][y].brennzeit=w.flaeche[x][y].brennzeit;flaeche[x][y].zuendcounter=w.flaeche[x][y].zuendcounter;break;
				case 'L': flaeche[x][y]= new Laubbaum(x,y);flaeche[x][y].brennen=w.flaeche[x][y].brennen;flaeche[x][y].brennzeit=w.flaeche[x][y].brennzeit;flaeche[x][y].zuendcounter=w.flaeche[x][y].zuendcounter;break;
				case '-': flaeche[x][y]= new Busch(x,y);flaeche[x][y].brennen=w.flaeche[x][y].brennen;flaeche[x][y].brennzeit=w.flaeche[x][y].brennzeit;flaeche[x][y].zuendcounter=w.flaeche[x][y].zuendcounter;break;
				case 'B': flaeche[x][y]= new Brand(x,y);flaeche[x][y].brennen=w.flaeche[x][y].brennen;flaeche[x][y].brennzeit=w.flaeche[x][y].brennzeit;flaeche[x][y].zuendcounter=w.flaeche[x][y].zuendcounter;break;
				case 'A': flaeche[x][y]=new Asche(x,y,0);flaeche[x][y].brennen=w.flaeche[x][y].brennen;flaeche[x][y].brennzeit=w.flaeche[x][y].brennzeit;flaeche[x][y].zuendcounter=w.flaeche[x][y].zuendcounter;break;
				case 'a': flaeche[x][y]=new abgeholzt(x,y);break;
				}
				}
			}
		this.Bäume=w.Bäume;
		this.Waldbestand=w.Waldbestand;
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
		
//		System.out.println("runde "+this.runde);
//		System.out.println(this.brenntnoch);
		
	}
        
        public void runde(char[][] wood){
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
                                wood[x][y]=Waldflaeche.wald.flaeche[x][y].toChar();
			}
		}
		
//		System.out.println("runde "+this.runde);
//		System.out.println(this.brenntnoch);
		
	}
//	public void end(){
//		int i=flaeche.length;
//		int j=flaeche[0].length;
//		for(int x=0;x<i;x++){
//			for(int y=0;y<j;y++){
//				if(Waldflaeche.wald.flaeche[x][y].toString().equals("B")){
//				Waldflaeche.wald.flaeche[x][y].bst();
////				System.out.println(Waldflaeche.wald.flaeche[x][y].inbrantgesteckt);
//				return;
//				}
//			}
//		}
//	}
	
public String toString(){
	String out="";
	out+=flaeche.length+" "+flaeche[0].length+"\n";
	for (int x=0;x<flaeche.length;x++){
		for(int y=0;y<flaeche[0].length;y++){
			out+=""+flaeche[x][y].toString();
			}out+='\n';
		}
	
	return out;
	
}
	
	
	
	
}
