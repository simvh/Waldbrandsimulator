package wald;

import java.math.BigInteger;

public class Brand extends Waldflaeche {


	public Brand(int x,int y){
		this.x=x;
		this.y=y;
		this.brennzeit=0;
		this.brennen=true;
		this.zuendcounter=0;
//		this.inbrantgesteckt=BigInteger.ZERO;
	}
	
	@Override
	public void entzünden() {
	}

//	@Override
//	public void entzünden(Waldflaeche w) {
//		// TODO Auto-generated method stub
//		;
//	}
//
//	@Override
//	protected void inbrannt() {
//		
//	}

	@Override
	public void feuer() {
		
		try {
			Waldflaeche.wald.flaeche[this.x][this.y+1].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
		}
		try {
			Waldflaeche.wald.flaeche[this.x+1][this.y].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
		}
		try {
			Waldflaeche.wald.flaeche[this.x][this.y-1].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
		}
		try {
			Waldflaeche.wald.flaeche[this.x-1][this.y].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
		}
		
		
		this.brennzeit++;

	}
@Override
	public void st(boolean bren) {
		if(bren){
		if(this.brennen){
			feuer();
		}
		}
	}

@Override
public String toString() {
	return "B";
}
@Override
    public char toChar() {
        return 'B';
    }
}
