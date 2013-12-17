package wald;

/**
 * @author s.von.hall
 */

public final class point {
	public int x;
	public int y;

	public point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public String toString() {
		return "(" + x + "," + y+")";
	}
}
