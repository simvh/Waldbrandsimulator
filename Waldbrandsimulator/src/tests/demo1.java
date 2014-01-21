package tests;

import java.io.FileNotFoundException;

import wald.*;
import control.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class demo1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int h = 4;
        Helfer helfer[] = new Helfer[h * 4];
        for (int i = 0; i < h; i++) {
            helfer[i * 4 + 0] = new Helfer(50, 0);
            helfer[i * 4 + 1] = new Helfer(0, 50);
            helfer[i * 4 + 2] = new Helfer(50, 99);
            helfer[i * 4 + 3] = new Helfer(99, 50);
        }
        try {
            wald Wald = new wald(control.pfad.src + "data/demowald1");
            Computer computer = new Computer(Wald, helfer);
            computer.setOutstr(control.pfad.src + "data/demo1out");
            computer.setModus(Modus.preventievmod);
            computer.setModus(Modus.ernstfallmod);
            computer.berechnen();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
        }
        h = 12;
        helfer = new Helfer[h * 4];
        for (int i = 0; i < h; i++) {
            helfer[i * 4 + 0] = new Helfer(50, 0);
            helfer[i * 4 + 1] = new Helfer(0, 50);
            helfer[i * 4 + 2] = new Helfer(50, 99);
            helfer[i * 4 + 3] = new Helfer(99, 50);
        }
        try {
            wald Wald = new wald(control.pfad.src + "data/demowald3");
            Computer computer = new Computer(Wald, helfer);
            computer.setOutstr(control.pfad.src + "data/demo3out");
            computer.setModus(Modus.preventievmod);
            computer.setModus(Modus.ernstfallmod);
            computer.berechnen();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < h; i++) {
            helfer[i * 4 + 0] = new Helfer(50, 0);
            helfer[i * 4 + 1] = new Helfer(0, 50);
            helfer[i * 4 + 2] = new Helfer(50, 99);
            helfer[i * 4 + 3] = new Helfer(99, 50);
        }
        try {
            wald Wald = new wald(control.pfad.src + "data/demowald4");
            Computer computer = new Computer(Wald, helfer);
            computer.setOutstr(control.pfad.src + "data/demo4out");
            computer.setModus(Modus.preventievmod);
            computer.setModus(Modus.ernstfallmod);
            computer.berechnen();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < h; i++) {
            helfer[i * 4 + 0] = new Helfer(50, 0);
            helfer[i * 4 + 1] = new Helfer(0, 50);
            helfer[i * 4 + 2] = new Helfer(50, 99);
            helfer[i * 4 + 3] = new Helfer(99, 50);
        }
        try {
            wald Wald = new wald(control.pfad.src + "data/demowald5");
            Computer computer = new Computer(Wald, helfer);
            computer.setOutstr(control.pfad.src + "data/demo5out");
            computer.setModus(Modus.preventievmod);
            computer.setModus(Modus.ernstfallmod);
            computer.berechnen();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < h; i++) {
            helfer[i * 4 + 0] = new Helfer(50, 0);
            helfer[i * 4 + 1] = new Helfer(0, 50);
            helfer[i * 4 + 2] = new Helfer(50, 99);
            helfer[i * 4 + 3] = new Helfer(99, 50);
        }
        try {
            wald Wald = new wald(control.pfad.src + "data/demowald6");
            Computer computer = new Computer(Wald, helfer);
            computer.setOutstr(control.pfad.src + "data/demo6out");
            computer.setModus(Modus.preventievmod);
            computer.setModus(Modus.ernstfallmod);
            computer.berechnen();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < h; i++) {
            helfer[i * 4 + 0] = new Helfer(50, 0);
            helfer[i * 4 + 1] = new Helfer(0, 50);
            helfer[i * 4 + 2] = new Helfer(50, 99);
            helfer[i * 4 + 3] = new Helfer(99, 50);
        }
        try {
            wald Wald = new wald(control.pfad.src + "data/demowald7");
            Computer computer = new Computer(Wald, helfer);
            computer.setOutstr(control.pfad.src + "data/demo7out");
            computer.setModus(Modus.preventievmod);
            computer.setModus(Modus.ernstfallmod);
            computer.berechnen();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < h; i++) {
            helfer[i * 4 + 0] = new Helfer(50, 0);
            helfer[i * 4 + 1] = new Helfer(0, 50);
            helfer[i * 4 + 2] = new Helfer(50, 99);
            helfer[i * 4 + 3] = new Helfer(99, 50);
        }
        try {
            wald Wald = new wald(control.pfad.src + "data/demowald8");
            Computer computer = new Computer(Wald, helfer);
            computer.setOutstr(control.pfad.src + "data/demo8out");
            computer.setModus(Modus.preventievmod);
            computer.setModus(Modus.ernstfallmod);
            computer.berechnen();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
        }
        for (int i = 0; i < h; i++) {
            helfer[i * 4 + 0] = new Helfer(50, 0);
            helfer[i * 4 + 1] = new Helfer(0, 50);
            helfer[i * 4 + 2] = new Helfer(50, 99);
            helfer[i * 4 + 3] = new Helfer(99, 50);
        }
        try {
            wald Wald = new wald(control.pfad.src + "data/demowald9");
            Computer computer = new Computer(Wald, helfer);
            computer.setOutstr(control.pfad.src + "data/demo9out");
            computer.setModus(Modus.preventievmod);
            computer.setModus(Modus.ernstfallmod);
            computer.berechnen();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
        }
//		h=500;helfer=new Helfer[h*4];
//		for(int i=0;i<h;i++){
//			helfer[i*4+0]=new Helfer(50,0);
//		helfer[i*4+1]=new Helfer(0,50);
//		helfer[i*4+2]=new Helfer(50,99);
//		helfer[i*4+3]=new Helfer(99,50);
//		}
//		try {
//			wald Wald=new wald("./Waldbrandsimulator/data/demowald10");
//			Computer computer=new Computer(Wald,helfer);
//			computer.setOutstr("./Waldbrandsimulator/data/demo10out");
//			computer.setModus(Modus.preventievmod);
////			computer.setModus(Modus.ernstfallmod);
//			computer.berechnen();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
//		}

    }
//		Helfer helfer[]=new Helfer[6*4];
//	for(int i=0;i<6;i++){
//		helfer[i*4+0]=new Helfer(50,0);
//	helfer[i*4+1]=new Helfer(0,50);
//	helfer[i*4+2]=new Helfer(50,99);
//	helfer[i*4+3]=new Helfer(99,50);
//	}
//	try {
//		wald Wald=new wald("./Waldbrandsimulator/data/demowald1");
//		Computer computer=new Computer(Wald,helfer);
//		computer.setOutstr("./Waldbrandsimulator/data/demo1out");
//		computer.setModus(Modus.preventievmod);
//		computer.setModus(Modus.ernstfallmod);
//		computer.berechnen();
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
//	}
}
