package wald;

public class Busch extends Waldflaeche {

	private final static int zuendzeit=1;
	private final static int brenndauer=1;
	
	public Busch(int x,int y){
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
		if(this.brennzeit==Busch.brenndauer){
			this.brennen=false;
			Waldflaeche.wald.flaeche[this.x][this.y]=new Asche(x,y);
			return;
		}
		
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
			}else{
				if(this.zuendcounter>0){
					this.zuendcounter--;
				}
			}
			this.gezuendet=false;
		}
	}


}
