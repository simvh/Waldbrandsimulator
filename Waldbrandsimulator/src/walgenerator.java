import java.io.*;
public class walgenerator {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		FileWriter out = null;
		try {
			out = new FileWriter("wald",false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i;try{
			out.append("1000 1000\n");
		for (int x=0;x<1000;x++){
			for(int y=0;y<1000;y++){
				i=(int)(3*Math.random());
				switch(i){
				case 0: out.append('-');break;
				case 1: out.append('N');break;
				case 2: out.append('L');break;
				case 3: out.append('B');System.out.println("Brand");break;
				default:out.append('-');
				}
			}out.append('\n');
		}
		}catch (IOException e){
			e.printStackTrace();
		}System.out.println("fertig");
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
