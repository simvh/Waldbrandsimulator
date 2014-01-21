package tests;

import java.io.FileNotFoundException;

import wald.Helfer;
import wald.wald;
import control.Computer;
import control.Modus;
import java.util.logging.Level;
import java.util.logging.Logger;

public class demo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int h=15;
		Helfer helfer[]=new Helfer[h*4];
		for(int i=0;i<h;i++){
			helfer[i*4+0]=new Helfer(500,0);
		helfer[i*4+1]=new Helfer(0,500);
		helfer[i*4+2]=new Helfer(500,999);
		helfer[i*4+3]=new Helfer(999,500);
		}
		try {
			wald Wald=new wald(control.pfad.src+"data/demowald2");
			Computer computer=new Computer(Wald,helfer);
			computer.setOutstr(control.pfad.src+"data/demo2out");
			computer.setModus(Modus.preventievmod);
			computer.setModus(Modus.ernstfallmod);
			computer.berechnen();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
