package wald;

import java.math.BigInteger;

public class Busch extends Waldflaeche {

	private final static int zuendzeit=1;
	private final static int brenndauer=1;
	
	public Busch(int x,int y){
		this.x=x;
		this.y=y;
		this.brennzeit=0;
		this.brennen=false;
		this.zuendcounter=0;
//		this.inbrantgesteckt=BigInteger.ZERO;
	}
	
	@Override
	public void entzünden() {
		this.zuendcounter++;
		this.gezuendet=true;
	}
	
//	public void entzünden(Waldflaeche w) {
//		this.zuendcounter++;
//		this.gezuendet=true;
//		for(int i=0;i<12;i++){
//			if(!this.brennen)
//			if(this.entzundetvon[i]!=null){
//			if(this.entzundetvon[i].x==w.x&&this.entzundetvon[i].y==w.y){
//				break;
//			}
//			}else{
//				this.entzundetvon[i]=new point (w.x,w.y);
//				break;
//			}
//		}
//	}

	@Override
	public void feuer() {
		if(this.brennzeit==Busch.brenndauer){
			this.brennen=false;
			Waldflaeche.wald.flaeche[this.x][this.y]=new Asche(x,y,this.zuendcounter,Waldflaeche.wald.runde-1,true);
//			for(int i=0;i<12;i++){
//				if(this.entzundetvon[i]!=null){
//				Waldflaeche.wald.flaeche[this.entzundetvon[i].x][this.entzundetvon[i].y].gezündet(this);
//			}}
			return;
		}
		wald.brenntnoch=true;
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
		}else{
			if(this.gezuendet){
				if (this.zuendcounter>=Busch.zuendzeit){
					this.brennen=true;
				}
			}else{if (this.brennen==false){
				if(this.zuendcounter>0){
					this.zuendcounter--;
				}
			}
			}
			this.gezuendet=false;
		}
	}

@Override
public String toString() {
	return "-";
}
@Override
    public char toChar() {
        return '-';
    }

}
