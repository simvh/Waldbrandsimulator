/**
 * 
 */
package wald;

/**
 * @author s.von.hall
 *
 */
public abstract class Waldflaeche {

	

	
	public abstract void entzünden();
	public abstract void entzünden(Waldflaeche w);
	protected boolean brennen,gezuendet;
	protected static  int zuendzeit;
	protected int x,y;
	protected int brennzeit;
	protected static  int brenndauer;
	protected int zuendcounter;
	public abstract void feuer();
	public static wald wald; 
	public abstract void st(boolean bren);
	public point entzundetvon[]=new point[12];
	public point etbrannt[]=new point[12];
	public long inbrantgesteckt=0;
	
	protected void gezündet(Waldflaeche w){
		for(int i=0;i<12;i++){
			if(this.etbrannt[i]!=null){
			if(this.etbrannt[i].x==w.x&&this.etbrannt[i].y==w.y){
				break;
			}
			}else{
				this.etbrannt[i]=new point(w.x,w.y);
				break;
			}
		}
	}
	
	protected void inbrannt(){
		if(this.toString().equals("B")){
			return;
		}
		for (int i=0;i<12;i++){
			try{if(this.entzundetvon[i]==null){
				break;
			}
				Waldflaeche.wald.flaeche[this.entzundetvon[i].x][this.entzundetvon[i].y].inbrantgesteckt++;
				Waldflaeche.wald.flaeche[this.entzundetvon[i].x][this.entzundetvon[i].y].inbrannt();
			}catch(Exception e){}
		}
	}
	
	public long bst(){
		if (this.inbrantgesteckt!=0){
			return this.inbrantgesteckt;
		}
		for(int i=0;i<12;i++){try{
			this.inbrantgesteckt+=Waldflaeche.wald.flaeche[this.etbrannt[i].x][this.etbrannt[i].y].bst();
		}catch(Exception e){}}
			if(this.toString().equals("A")){
				if(((Asche)this).busch){
					return this.inbrantgesteckt;
				}else{
					return this.inbrantgesteckt+1;
				}
			}else{if(this.brennen){return this.inbrantgesteckt+1;
			}else{
				return this.inbrantgesteckt;
			}
		}
	
	
	
	}
}
	
