package tests;

import java.io.FileNotFoundException;

import wald.*;
import control.*;

public class demo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Helfer helfer[] = new Helfer[6 * 4];
		for (int i = 0; i < 6; i++) {
			helfer[i * 4 + 0] = new Helfer(50, 0);
			helfer[i * 4 + 1] = new Helfer(0, 50);
			helfer[i * 4 + 2] = new Helfer(50, 99);
			helfer[i * 4 + 3] = new Helfer(99, 50);
		}
		try {
			wald Wald = new wald("./Waldbrandsimulator/src/data/demowald1");
			Computer computer = new Computer(Wald, helfer);
			computer.setOutstr("./Waldbrandsimulator/src/data/demo1out");
			computer.setModus(Modus.preventievmod);
			computer.setModus(Modus.ernstfallmod);
			computer.berechnen();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
