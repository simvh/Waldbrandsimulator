package wald;

public class Helfer {

	public int x,y;
	public static wald wald;
	private int bewegungsmarken;
	private boolean fällen;
	public static boolean brennt=false;
	private int richtungx,richtungy; 
	
	public Helfer(int x,int y){
		this.x=x;
		this.y=y;
		Helfer.brennt=false;
		this.fällen=false;
		this.richtungx=0;
		this.richtungy=0;
	}
	public int getbewegungsmarken(){
		return bewegungsmarken;
		
	}
	
	public boolean isFällen() {
		return fällen;
	}
	public void baumfällen(int i,int j){
		if(this.fällen){
		Helfer.wald.flaeche[this.x+i][this.y+j]=new abgeholzt(x+i,y+j);
		this.richtungy=0;
		this.richtungx=0;
		this.fällen=false;
		Helfer.wald.Bäume--;
		this.bewegungsmarken--;
		//System.out.println("Timber");
		}else{
			this.bewegungsmarken--;
			this.fällen=true;
			this.richtungx=i;
			this.richtungy=j;
		}
		
	}
	public void gehenhoch(){
		if(this.bewegungsmarken>0){
			this.y--;
			this.bewegungsmarken--;
//			System.out.println("hoch");
		}
	}
	public void gehenrunter(){
		if(this.bewegungsmarken>0){
			this.y++;
			this.bewegungsmarken--;
//			System.out.println("runter");
		}
	}
	public void gehenrechts(){
		if(this.bewegungsmarken>0){
			this.x++;
			this.bewegungsmarken--;
//			System.out.println("rechts");
		}
	}
	public void gehenlinks(){
		if(this.bewegungsmarken>0){
			this.x--;
			this.bewegungsmarken--;
//			System.out.println("links");
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
		//Helfer.brennt=true;
//		System.out.println("Helfer brennt!!!!!!");
//		System.out.println(this.x+" "+this.y+" "+Helfer.wald.flaeche[x][y]);
		//System.out.print("[ "+Helfer.wald.flaeche[x][y].entzundetvon[0]);
	//	for(int i=1;i<12;i++){
	//		System.out.print(", "+Helfer.wald.flaeche[x][y].entzundetvon[i]);
	//			
	//	}System.out.println(" ]");
	}
	
	public void st(){
	/*	if(this.fällen){
			baumfällen(this.richtungx,this.richtungy);
			this.bewegungsmarken=0;
		}else*/{
			this.bewegungsmarken=2;
		}if(Helfer.wald.flaeche[this.x][this.y].brennen){
			this.entzünden();
		}
	}
	
}
