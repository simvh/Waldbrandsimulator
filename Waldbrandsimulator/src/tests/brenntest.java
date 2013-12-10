package tests;

import java.io.FileNotFoundException;

import control.Computer;

import wald.Helfer;
import wald.wald;

public class brenntest {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		wald w=new wald("src/data/demowald1");
		Helfer hel[]=new Helfer[10];
		Computer cp=new Computer(w,hel);
		cp.abbrennen(0.5);
	}

}
