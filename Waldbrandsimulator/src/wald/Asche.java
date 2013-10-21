package wald;

public class Asche extends Waldflaeche {

	public int runde;
	public Asche(int x,int y,int z){
		this.x=x;
		this.y=y;
		this.brennzeit=0;
		this.brennen=false;
		this.zuendcounter=z;
		this.runde=Asche.wald.runde;
	}
	
	public Asche(int x,int y,int z,Waldflaeche entzundet[],int inbranntgesteckt){
		this.x=x;
		this.y=y;
		this.brennzeit=0;
		this.brennen=false;
		this.zuendcounter=z;
		this.runde=Asche.wald.runde;
		this.entzundetvon=entzundet;
		this.inbrantgesteckt=inbranntgesteckt;
		
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

	@Override
	public void entzünden(Waldflaeche w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void inbrannt() {
		// TODO Auto-generated method stub
		super.inbrannt();
	}

}
