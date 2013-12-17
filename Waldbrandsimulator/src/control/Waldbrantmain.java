package control;

import wald.*;

import java.io.*;
import java.util.*;

public class Waldbrantmain {

	/*
	 * private wald wald; private Helfer Helfer[];
	 */
	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		wald wald;
		Helfer helfer[];
		Scanner sc;
		int x, y;

		/* Erstellen des Waldes und der Helfer */
		sc = new Scanner(System.in);
		System.out.println("Anzahl Helfer eingeben");
		helfer = new Helfer[sc.nextInt()];
		System.out.println("Dateiname eingeben");
		File f = new File(sc.next());
		f.renameTo(new File("wald"));
		wald = new wald("wald");
		System.out.println("Start X Koordinate der Helfer eingeben");
		x = sc.nextInt();
		System.out.println("Start Y Koordinate der Helfer eingeben");
		y = sc.nextInt();
		sc.close();
		for (int i = 0; i < helfer.length; i++) {
			helfer[i] = new Helfer(x, y);
		}
		/* Starten der Berechnung */
		Computer comp = new Computer(wald, helfer);

	}

}
