package wald;

public class Asche extends Waldflaeche {

	
	public Asche(int x,int y,int z){
		this.x=x;
		this.y=y;
		this.brennzeit=0;
		this.brennen=false;
		this.zuendcounter=z;
	}
	
	@Override
	public void entzünden() {
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

}
