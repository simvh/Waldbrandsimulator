package wald;

import java.math.BigInteger;

public class Laubbaum extends Waldflaeche {

	private final static int zuendzeit=4;
	private final static int brenndauer=4;
//	private boolean gezuendet;
	public Laubbaum(int x,int y){
		this.x=x;
		this.y=y;
		this.brennzeit=0;
		this.brennen=false;
		this.zuendcounter=0;
		this.gezuendet=false;
//		this.inbrantgesteckt=BigInteger.ZERO;
	}
	
	@Override
	public void entzünden() {
		this.zuendcounter++;
		this.gezuendet=true;
	}
//	public void entzünden(Waldflaeche w) {
//		this.zuendcounter++;
//		this.gezuendet=true;if(!this.brennen)
//		for(int i=0;i<12;i++){
//			if(this.entzundetvon[i]!=null){
//			if(this.entzundetvon[i].x==w.x&&this.entzundetvon[i].y==w.y){
//				break;
//			}
//			}else{
//				this.entzundetvon[i]=new point(w.x,w.y);
//				break;
//			}
//		}
//	}

	@Override
	public void feuer() {
		if(this.brennzeit==Laubbaum.brenndauer){
			this.brennen=false;
			Waldflaeche.wald.flaeche[this.x][this.y]=new Asche(x,y,this.zuendcounter,Waldflaeche.wald.runde-4,false);
//			this.inbrannt();
//			for(int i=0;i<12;i++){
//				if(this.entzundetvon[i]!=null){
//				Waldflaeche.wald.flaeche[this.entzundetvon[i].x][this.entzundetvon[i].y].gezündet(this);
//			}}
			Waldflaeche.wald.Bäume--;
			return;
		}
		Waldflaeche.wald.brenntnoch=true;
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
		try {
			Waldflaeche.wald.flaeche[this.x+1][this.y+1].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
			
		}
		try {
			Waldflaeche.wald.flaeche[this.x-1][this.y+1].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
			
		}
		try {
			Waldflaeche.wald.flaeche[this.x][this.y+2].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
			
		}
		try {
			Waldflaeche.wald.flaeche[this.x+1][this.y-1].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
			
		}
		try {
			Waldflaeche.wald.flaeche[this.x-1][this.y-1].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
			
		}
		try {
			Waldflaeche.wald.flaeche[this.x][this.y-2].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
			
		}
		try {
			Waldflaeche.wald.flaeche[this.x+2][this.y].entzünden();
		}catch (ArrayIndexOutOfBoundsException e){
			
		}
		try {
			Waldflaeche.wald.flaeche[this.x-2][this.y].entzünden();
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
				if (this.zuendcounter>=Laubbaum.zuendzeit){
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
	return "L";
}

@Override
    public char toChar() {
        return 'L';
    }
    
}
