package control;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.math.*;
import wald.*;

public class Computer {
private wald wald,brenn;
private Helfer helfer[];
private List<Asche> asche;
private Asche ziele[];
private Weg weg[];
public Computer(wald wald ,Helfer helfer[] ) throws FileNotFoundException{
	this.wald=wald;
	this.brenn=new wald("wald");
	this.helfer=helfer;
	this.asche= new ArrayList<Asche>();
}
private void abbrennen(){
	this.brenn.runde();
	this.brenn.runde();
	this.brenn.runde();
	this.brenn.runde();
	this.brenn.runde();
	this.brenn.runde();
	while (((this.brenn.Bäume/(double)(this.brenn.Waldbestand))>0.5)&&(this.brenn.brenntnoch)){
		this.brenn.runde();
	}
	this.brenn.end();
	this.out(this.brenn);
	System.out.println((this.brenn.Bäume/(double)(this.brenn.Waldbestand)));
}

public void berechnen(){
	Asche a[];
	this.abbrennen();
	for(int i=0;i<this.brenn.flaeche.length;i++){
		for(int j=0;j<this.brenn.flaeche[0].length;j++){
			if(this.brenn.flaeche[i][j].toString().equals("A")){
				System.out.println(this.brenn.flaeche[i][j]);
				this.asche.add((Asche) this.brenn.flaeche[i][j]);
			}
		}
	}
	a=this.asche.toArray(new Asche[0]);
	Arrays.sort(a);
	this.asche=Arrays.asList(a);
	a=null;
	
}


	private Weg ereicht(Helfer h,Asche w ){
	point helfer=new point(h.x,h.y);
	point ziel=new point(w.x,w.y);
	int rund=h.wald.runde;
	int c=0;
	
	for(Weg weg : Weg.values()){
		boolean broken=false;
	while(Math.abs(helfer.x-ziel.x)+Math.abs(helfer.y-ziel.y)<1){// noch nicht am Ziel
		if(c==2){
			rund++;
			c=0;
		}
		if(this.brenn.flaeche[h.x][h.y].toString().equals("A")){
			if(((Asche)this.brenn.flaeche[h.x][h.y]).runde>=rund){
				switch(this.wald.flaeche[h.x][h.y].toString()){
				case "L":if(rund>=((Asche)this.brenn.flaeche[h.x][h.y]).runde-4){broken=true;}break;
				case "N":if(rund>=((Asche)this.brenn.flaeche[h.x][h.y]).runde-2){broken=true;}break;
				case "-":if(rund>=((Asche)this.brenn.flaeche[h.x][h.y]).runde-1){broken=true;}break;
				case "B":broken=true;break;
				}
				if(broken){
					break;
				}
			}
		}
		if(helfer.x-ziel.x<0){
			if(helfer.y-ziel.y<0){
				switch(weg){
				case wagsenk: helfer.x++ ;c++;break;
				case senkwag: helfer.y++ ;c++;break;
				case wagdia: if(Math.abs(helfer.x-ziel.x)<Math.abs(helfer.y-ziel.y)){
					helfer.y++ ;c++;}else{helfer.x++;c++;}break;
				case senkdia: if(Math.abs(helfer.x-ziel.x)<=Math.abs(helfer.y-ziel.y)){
					helfer.y++ ;c++;}else{helfer.x++;c++;}break;
				}
			}else{if(helfer.y-ziel.y>0){
				switch(weg){
				case wagsenk: helfer.x++ ;c++;break;
				case senkwag: helfer.y-- ;c++;break;
				case wagdia: if(Math.abs(helfer.x-ziel.x)<Math.abs(helfer.y-ziel.y)){
					helfer.y-- ;c++;}else{helfer.x++;c++;}break;
				case senkdia: if(Math.abs(helfer.x-ziel.x)<=Math.abs(helfer.y-ziel.y)){
					helfer.y-- ;c++;}else{helfer.x++;c++;}break;
				}
			}else{
				helfer.x++;c++;
				continue;
			}
				
			}
		}else{
			if(helfer.x-ziel.x>0){

				if(helfer.y-ziel.y<0){
					switch(weg){
					case wagsenk: helfer.x-- ;c++;break;
					case senkwag: helfer.y++ ;c++;break;
					case wagdia: if(Math.abs(helfer.x-ziel.x)<Math.abs(helfer.y-ziel.y)){
											helfer.y++ ;c++;}else{helfer.x--;c++;}break;
					case senkdia: if(Math.abs(helfer.x-ziel.x)<=Math.abs(helfer.y-ziel.y)){
						helfer.y++ ;c++;}else{helfer.x--;c++;}break;
					}
				}else{if(helfer.y-ziel.y>0){
					switch(weg){
					case wagsenk: helfer.x-- ;c++;break;
					case senkwag: helfer.y-- ;c++;break;
					case wagdia: if(Math.abs(helfer.x-ziel.x)<Math.abs(helfer.y-ziel.y)){
						helfer.y-- ;c++;}else{helfer.x--;c++;}break;
					case senkdia: if(Math.abs(helfer.x-ziel.x)<=Math.abs(helfer.y-ziel.y)){
						helfer.y-- ;c++;}else{helfer.x--;c++;}break;
					}
				}else{
					helfer.x--;c++;
					continue;
				}
					
				}
			}else{//helfer vertikal zum ziel verschoben
				if(helfer.y-ziel.y<0){
					helfer.y++;c++;
					continue;
				}else{
					helfer.y--;c++;
					continue;
				}
				
			}
		}
	
	}
	
	if(broken==false){
		return weg;
	}
	
	}return null;
}

void weglaufen(Helfer helfer, Asche ziel,Weg weg){
	while(helfer.getbewegungsmarken()<0){
		if(Math.abs(helfer.x-ziel.x)+Math.abs(helfer.y-ziel.y)<1){// noch nicht am Ziel
			if(helfer.x-ziel.x<0){
				if(helfer.y-ziel.y<0){
					switch(weg){
					case wagsenk: helfer.gehenrechts() ;break;
					case senkwag: helfer.gehenrunter() ;break;
					case wagdia: if(Math.abs(helfer.x-ziel.x)<Math.abs(helfer.y-ziel.y)){
						helfer.gehenrunter() ;}else{helfer.gehenrechts();}break;
					case senkdia: if(Math.abs(helfer.x-ziel.x)<=Math.abs(helfer.y-ziel.y)){
						helfer.gehenrunter() ;}else{helfer.gehenrechts();};break;
					}
				}else{if(helfer.y-ziel.y>0){
					switch(weg){
					case wagsenk: helfer.gehenrechts() ;break;
					case senkwag: helfer.gehenhoch() ;break;
					case wagdia: if(Math.abs(helfer.x-ziel.x)<Math.abs(helfer.y-ziel.y)){
						helfer.gehenhoch() ;}else{helfer.gehenrechts();}break;
					case senkdia: if(Math.abs(helfer.x-ziel.x)<=Math.abs(helfer.y-ziel.y)){
						helfer.gehenhoch() ;}else{helfer.gehenrechts();};break;
					}
				}else{
					helfer.gehenrechts();
					continue;
				}
					
				}
			}else{
				if(helfer.x-ziel.x>0){

					if(helfer.y-ziel.y<0){
						switch(weg){
						case wagsenk: helfer.gehenlinks() ;break;
						case senkwag: helfer.gehenrunter() ;break;
						case wagdia: if(Math.abs(helfer.x-ziel.x)<Math.abs(helfer.y-ziel.y)){
							helfer.gehenrunter() ;}else{helfer.gehenlinks();}break;
						case senkdia: if(Math.abs(helfer.x-ziel.x)<=Math.abs(helfer.y-ziel.y)){
							helfer.gehenrunter() ;}else{helfer.gehenlinks();};break;
						}
					}else{if(helfer.y-ziel.y>0){
						switch(weg){
						case wagsenk: helfer.gehenlinks() ;break;
						case senkwag: helfer.gehenhoch() ;break;
						case wagdia: if(Math.abs(helfer.x-ziel.x)<Math.abs(helfer.y-ziel.y)){
							helfer.gehenhoch() ;}else{helfer.gehenlinks();}break;
						case senkdia: if(Math.abs(helfer.x-ziel.x)<=Math.abs(helfer.y-ziel.y)){
							helfer.gehenhoch() ;}else{helfer.gehenlinks();};break;
						}
					}else{
						helfer.gehenlinks();
						continue;
					}
						
					}
				}else{//helfer vertikal zum ziel verschoben
					if(helfer.y-ziel.y<0){
						helfer.gehenrunter();
						continue;
					}else{
						helfer.gehenhoch();
						continue;
					}
					
				}
			}
		}else{// schon am Ziel
			if(! helfer.isFällen()){
				helfer.baumfällen(ziel.x-helfer.x, ziel.y-helfer.y);
				break;
			}
		}
	}
}



private void out(wald wa){
	// TODO Auto-generated method stub
	FileWriter out = null;
	try {
		out = new FileWriter("out",false);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	int i;try{
		out.append(""+wa.flaeche.length+" "+wa.flaeche[0].length+"\n");
	for (int x=0;x<wa.flaeche.length;x++){
		for(int y=0;y<wa.flaeche[0].length;y++){
			out.append(wa.flaeche[x][y].toString());
			}out.append('\n');
		}
	
	}catch (IOException e){
		e.printStackTrace();
	}
	try {
		out.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

}
