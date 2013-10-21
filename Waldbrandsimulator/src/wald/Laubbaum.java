package wald;

public class Laubbaum extends Waldflaeche {

	private final static int zuendzeit=4;
	private final static int brenndauer=4;
	
	public Laubbaum(int x,int y){
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
	public void entzünden(Waldflaeche w) {
		this.zuendcounter++;
		this.gezuendet=true;
		for(int i=0;i<12;i++){
			if(this.entzundetvon[i]!=null){
			if(this.entzundetvon[i].x==w.x&&this.entzundetvon[i].y==w.y){
				break;
			}
			}else{
				this.entzundetvon[i]=w;
				break;
			}
		}
	}

	@Override
	public void feuer() {
		if(this.brennzeit==Laubbaum.brenndauer){
			this.brennen=false;
			Waldflaeche.wald.flaeche[this.x][this.y]=new Asche(x,y,this.zuendcounter,this.entzundetvon,this.inbrantgesteckt);
			this.inbrannt();
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

}
