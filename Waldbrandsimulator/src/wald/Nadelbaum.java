package wald;

public class Nadelbaum extends Waldflaeche {

	private final static int zuendzeit=2;
	private final static int brenndauer=2;
	
	public Nadelbaum(int x,int y){
		this.x=x;
		this.y=y;
		this.brennzeit=0;
		this.brennen=false;
		this.zuendcounter=0;
	}
	
	@Override
	public void entzünden() {
		this.zuendcounter++;
		this.gezuendet=true;
	}

	@Override
	public void feuer() {
		if(this.brennzeit==Nadelbaum.brenndauer){
			this.brennen=false;
			Waldflaeche.wald.flaeche[this.x][this.y]=new Asche(x,y,this.zuendcounter);
			Waldflaeche.wald.Bäume--;
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
				if (this.zuendcounter>=Nadelbaum.zuendzeit){
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
	return "N";
}

}
