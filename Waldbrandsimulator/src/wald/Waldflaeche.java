/**
 * 
 */
package wald;
import java.math.*;
/**
 * @author s.von.hall
 *
 */

public abstract class Waldflaeche {

	

	
	
	/**
	 * Diese Funktion zuendet das Element einmal an.
	 */
	public abstract void entzünden();
	/**
	 * @param w Waldflaeche die in entzuendetvon notiert werden soll
	 */
//	public abstract void entzünden(Waldflaeche w);
//	/**
//	 * brennt die Waldflaeche
//	 */
//	/**
//	 * wurde die Waldflaeche in dieser Runde angezuendet
//	 */
	public boolean brennen;
	protected boolean gezuendet;
	/**
	 * wielange dauert es bis die Waldflaeche brennt
	 */
	protected static  int zuendzeit;
	/**
	 * Position im Wald
	 */
	public int x,y;
	/**
	 *gibt an wie lange die Flaeche schon brennt
	 */
	public int brennzeit;
	/**
	 * gibt an wielange die Flaeche maximal brennt
	 */
	protected static int brenndauer;
	/**
	 * gibt an wie oft die Flaeche schon angezuende wurde
	 */
	public int zuendcounter;
	/**
	 * Diese Funktion zuendet die umliegenden Felder an
	 */
	protected abstract void feuer();
	/**
	 * der Wald zur Flaeche
	 */
	public static wald wald; 
	/**
	 * @param bren wird das Feuer aktualisiert
	 * Die Statusfunktion aktualisiert den Status der Flaeche.
	 */
	public abstract void st(boolean bren);
	/**
	 * eine Liste der Positionen von denen die Flaeche angezuendet wurde
	 */
//	public point entzundetvon[]=new point[12];
//	/**
//	 * eine Liste von Positionen die von der Flaeche angezuendet wurde
//	 */
//	public point etbrannt[]=new point[12];
//	public BigInteger inbrantgesteckt;
	
//	protected void gezündet(Waldflaeche w){
//		for(int i=0;i<12;i++){
//			if(this.etbrannt[i]!=null){
//			if(this.etbrannt[i].x==w.x&&this.etbrannt[i].y==w.y){
//				break;
//			}
//			}else{
//				this.etbrannt[i]=new point(w.x,w.y);
//				break;
//			}
//		}
//	}
//	
//	protected void inbrannt(){
//		if(this.toString().equals("B")){
//			return;
//		}
//		for (int i=0;i<12;i++){
//			try{if(this.entzundetvon[i]==null){
//				break;
//			}
//				Waldflaeche.wald.flaeche[this.entzundetvon[i].x][this.entzundetvon[i].y].inbrantgesteckt=Waldflaeche.wald.flaeche[this.entzundetvon[i].x][this.entzundetvon[i].y].inbrantgesteckt.add(BigInteger.ONE);
//				Waldflaeche.wald.flaeche[this.entzundetvon[i].x][this.entzundetvon[i].y].inbrannt();
//			}catch(Exception e){}
//		}
//	}
//	
//	/**
//	 * @return BigInteger Anzahl der durch das Feld entzuendeter Baeume (rekursiv ermittelt)
//	 */
//	public BigInteger bst(){
//		if (!this.inbrantgesteckt.equals(BigInteger.ZERO)){
//			return this.inbrantgesteckt;
//		}
//		for(int i=0;i<12;i++){try{
//			this.inbrantgesteckt=this.inbrantgesteckt.add(Waldflaeche.wald.flaeche[this.etbrannt[i].x][this.etbrannt[i].y].bst());
//		}catch(Exception e){}}
//			if(this.toString().equals("A")){
//				if(((Asche)this).busch){
//					return this.inbrantgesteckt;
//				}else{
//					return this.inbrantgesteckt.add(BigInteger.ONE);
//				}
//			}else{if(this.brennen){return this.inbrantgesteckt.add(BigInteger.ONE);
//			}else{
//				return this.inbrantgesteckt;
//			}
//		}
//	
//	
//	
//	}
}
	
