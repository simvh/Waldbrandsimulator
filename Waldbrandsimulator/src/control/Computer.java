package control;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import wald.*;

public class Computer {
private wald wald,brenn, test;
private Helfer helfer[];

public Computer(wald wald ,Helfer helfer[] ) throws FileNotFoundException{
	this.wald=wald;
	this.brenn=new wald("wald");
	this.test=new wald("wald");
	this.helfer=helfer;
}
private void abbrennen(){
	while ((this.brenn.Bäume/(double)(this.brenn.Waldbestand))>0.5){
		this.brenn.runde();
	}this.out(this.brenn);
}



private void out(wald wa){
	// TODO Auto-generated method stub
	FileWriter out = null;
	try {
		out = new FileWriter("wald",false);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	int i;try{
		out.append("100 100\n");
	for (int x=0;x<100;x++){
		for(int y=0;y<100;y++){
			out.append(wa.flaeche[x][y].toString());break;
			}
		}out.append('\n');
	
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
