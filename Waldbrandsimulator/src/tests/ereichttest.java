package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import wald.Asche;
import wald.Helfer;
import wald.wald;

import control.Computer;
import control.Weg;

public class ereichttest {
	wald w;
	Helfer helfer[]=new Helfer[4];
	Computer cp;
	Asche a[]= new Asche[4];
	@Before
	public void setUp() throws Exception {
		w=new wald(control.pfad.src+"data/wegdiasenktest1.txt");
		helfer[0]=new Helfer(9,9);
		helfer[1]=new Helfer(9,0);
		helfer[2]=new Helfer(0,9);
		helfer[3]=new Helfer(0,0);
		cp=new Computer(w, helfer);
		a[0]=new Asche(0, 0, 0,200,false);
		a[1]=new Asche(0, 9, 0,200,false);
		a[2]=new Asche(9, 0, 0,200,false);
		a[3]=new Asche(9, 9, 0,200,false);
		cp.setBrenn(new wald(control.pfad.src+"data/testbrenn.txt"));
	}

	@Test
	public void testEreicht() throws FileNotFoundException {
		assertNotNull("should be the same", cp.ereicht(helfer[0], a[0]));
		cp.setWald(new wald(control.pfad.src+"data/wegdiasenktest2.txt"));
		assertNotNull("should be the same", cp.ereicht(helfer[3], a[3]));
		cp.setWald(new wald(control.pfad.src+"data/wegdiasenktest3.txt"));
		assertNotNull("should be the same", cp.ereicht(helfer[2], a[2]));
		cp.setWald(new wald(control.pfad.src+"data/wegdiasenktest4.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[1], a[1]));
		cp.setWald(new wald(control.pfad.src+"data/wegdiawagtest1.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[0], a[0]));
		cp.setWald(new wald(control.pfad.src+"data/wegdiawagtest2.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[3], a[3]));
		cp.setWald(new wald(control.pfad.src+"data/wegdiawagtest3.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[2], a[2]));
		cp.setWald(new wald(control.pfad.src+"data/wegdiawagtest4.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[1], a[1]));
		
		
		cp.setWald(new wald(control.pfad.src+"data/wegsenkwagtest1.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[0], a[0]));
		cp.setWald(new wald(control.pfad.src+"data/wegsenkwagtest2.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[3], a[3]));
		cp.setWald(new wald(control.pfad.src+"data/wegsenkwagtest3.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[2], a[2]));
		cp.setWald(new wald(control.pfad.src+"data/wegsenkwagtest4.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[1], a[1]));
		
		
		cp.setWald(new wald(control.pfad.src+"data/wegwagsenktest1.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[0], a[0]));
		cp.setWald(new wald(control.pfad.src+"data/wegwagsenktest2.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[3], a[3]));
		cp.setWald(new wald(control.pfad.src+"data/wegwagsenktest3.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[2], a[2]));
		cp.setWald(new wald(control.pfad.src+"data/wegwagsenktest4.txt"));
		assertNotNull("should be the same",cp.ereicht(helfer[1], a[1]));
		
		
	}

}
