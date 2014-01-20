package wald;

import java.io.*;
import java.util.ArrayList;

public class waldgenerator {

    /**
     * @param args
     * @throws IOException
     */
    public static boolean create(int n, int m, File output) {
        return create(n, m, null, output);
    }

    public static boolean create(int n, int m, ArrayList<int[]> fires, File output) {
        int tmp;
        char wald[][] = new char[n][m];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                tmp = (int) (3 * Math.random());
                switch (tmp) {
                    case 0:
                        wald[x][y] = '-';
                        break;
                    case 1:
                        wald[x][y] = 'N';
                        break;
                    case 2:
                        wald[x][y] = 'L';
                        break;
                    default:
                        wald[x][y] = '-';
                }
            }
        }
        if (fires != null) {
            for (int[] i : fires) {
                if (i[0] >= 0 && i[0] < n && i[1] >= 0 && i[1] < m) {
                    wald[i[0]][i[1]] = 'B';
                }
            }
        }
        try {
            write(wald,output);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public static void write(char[][] array, File output) throws IOException{
        if(array==null) return;
        FileWriter fw = null;
        BufferedWriter bw = null;
        int n=array.length;
        int m=array[0].length;
        if (!output.exists()) {
                output.createNewFile();
            }
            fw = new FileWriter(output);
            bw = new BufferedWriter(fw);
            bw.write(n + " " + m + "\n");
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    bw.write(array[x][y]);
                }
                bw.write('\n');
            }
            bw.close();
            System.out.println("fertig");
    }

    public static void main(String[] args) {
        waldgenerator.create(10, 10, new File("wald"));

    }
}
