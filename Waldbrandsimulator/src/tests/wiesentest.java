package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import wald.Helfer;
import wald.keinWaldExeption;
import wald.wald;
import control.Computer;

public class wiesentest {
	wald w;
	Helfer helfer[]=new Helfer[4];
	Computer cp;
	@Before
	public void setUp() throws Exception {
		w=new wald("src/data/wiesentest.txt");
		helfer[0]=new Helfer(9,9);
		helfer[1]=new Helfer(9,0);
		helfer[2]=new Helfer(0,9);
		helfer[3]=new Helfer(0,0);
		cp=new Computer(w, helfer);
	}

	@Test
	public void testBerechnen() {
		try{cp.berechnen();
		fail("expected keinWaldExeption ");
		}catch(keinWaldExeption e){
			assertNotNull(e);
		}
	}

}
