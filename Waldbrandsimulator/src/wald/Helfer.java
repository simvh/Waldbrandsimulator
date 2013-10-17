package wald;

public class Helfer {

	protected int x,y;
	public static wald wald;
	private int bewegungsmarken;
	private boolean fällen;
	public static boolean brennt;
	private int richtungx,richtungy; 
	
	public Helfer(int x,int y){
		this.x=x;
		this.y=y;
		Helfer.brennt=false;
		this.fällen=false;
		this.richtungx=0;
		this.richtungy=0;
	}
	
	
	public void baumfällen(int i,int j){
		if(this.fällen){
		Helfer.wald.flaeche[this.x+i][this.y+j]=new abgeholzt(x+i,y+j);
		this.richtungy=0;
		this.richtungx=0;
		this.fällen=false;
		Helfer.wald.Bäume--;
		}else{
			this.fällen=true;
			this.richtungx=i;
			this.richtungy=j;
		}
		
	}
	public void gehenhoch(){
		if(this.bewegungsmarken>0){
			this.x--;
			this.bewegungsmarken--;
		}
	}
	public void gehenrunter(){
		if(this.bewegungsmarken>0){
			this.x++;
			this.bewegungsmarken--;
		}
	}
	public void gehenrechts(){
		if(this.bewegungsmarken>0){
			this.y++;
			this.bewegungsmarken--;
		}
	}
	public void gehenlinks(){
		if(this.bewegungsmarken>0){
			this.y--;
			this.bewegungsmarken--;
		}
	}
	public void feuerlegen(int i,int j){
		Helfer.wald.flaeche[this.x+i][this.y+j].entzünden();
		Helfer.wald.flaeche[this.x+i][this.y+j].entzünden();
		Helfer.wald.flaeche[this.x+i][this.y+j].entzünden();
		Helfer.wald.flaeche[this.x+i][this.y+j].entzünden();
		Helfer.wald.flaeche[this.x+i][this.y+j].entzünden();
	}
	public void entzünden(){
		Helfer.brennt=true;
	}
	
	public void st(){
		if(this.fällen){
			baumfällen(this.richtungx,this.richtungy);
			this.bewegungsmarken=0;
		}else{
			this.bewegungsmarken=2;
		}if(Helfer.wald.flaeche[this.x][this.y].brennen){
			this.entzünden();
		}
	}
	
}
