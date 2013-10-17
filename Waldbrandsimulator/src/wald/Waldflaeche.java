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
	protected boolean brennen,gezuendet;
	protected static  int zuendzeit;
	protected int x,y;
	protected int brennzeit;
	protected static  int brenndauer;
	protected int zuendcounter;
	public abstract void feuer();
	public static wald wald; 
	public abstract void st(boolean bren);
}
