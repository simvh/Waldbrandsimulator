package wald;

/**
 * @author s.von.hall
 */

import java.math.BigInteger;

public class Asche extends Waldflaeche implements Comparable<Asche> {
	public boolean busch;
	public int runde;

	public Asche(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.brennzeit = 0;
		this.brennen = false;
		this.zuendcounter = z;
		this.runde = Asche.wald.runde;
		this.inbrantgesteckt = BigInteger.ZERO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Asche arg0) {
		return arg0.runde - this.runde;
		// return
		// (this.inbrantgesteckt.subtract(arg0.inbrantgesteckt)).signum();
	}

	public Asche(int x, int y, int z, point entzundet[], boolean busch) {
		this.x = x;
		this.y = y;
		this.busch = busch;
		this.brennzeit = 0;
		this.brennen = false;
		this.zuendcounter = z;
		this.runde = Asche.wald.runde;
		this.entzundetvon = entzundet;
		this.inbrantgesteckt = BigInteger.ZERO;

	}

	@Override
	public void entzuenden() {
		// TODO Auto-generated method stub

	}

	@Override
	public void feuer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void st(boolean bren) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "A";
	}

	@Override
	public void entzuenden(Waldflaeche w) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void inbrannt() {
		// TODO Auto-generated method stub
		super.inbrannt();
	}

}
