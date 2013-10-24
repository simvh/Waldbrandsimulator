package control;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import wald.*;

public class Computer {
private wald wald,brenn;
private Helfer helfer[];

public Computer(wald wald ,Helfer helfer[] ) throws FileNotFoundException{
	this.wald=wald;
	this.brenn=new wald("wald");
	this.helfer=helfer;
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
	this.abbrennen();
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
