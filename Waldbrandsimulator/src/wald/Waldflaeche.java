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
	public Waldflaeche entzundetvon[]=new Waldflaeche[12];
	public int inbrantgesteckt;
	
	
	protected void inbrannt(){
		
		for (int i=0;i<12;i++){
			try{
				Waldflaeche.wald.flaeche[this.entzundetvon[i].x][this.entzundetvon[i].y].inbrantgesteckt++;
				Waldflaeche.wald.flaeche[this.entzundetvon[i].x][this.entzundetvon[i].y].inbrannt();
			}catch(Exception e){}
		}
	}
	
	
	}
	
