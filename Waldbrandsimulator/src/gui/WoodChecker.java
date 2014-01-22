/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexander
 */
public class WoodChecker {
    
    private boolean itIs;
    protected ArrayList<int[]> fires;
    protected char wood[][];
    private int size[];
    
    public WoodChecker(File file){
        itIs=check(file);
        if(!itIs){
            fires=null;
            size=null;
        }
    }
    
    private boolean check(File file){
        int x = 0, y = 0, pos, tmp;
        fires = new ArrayList<>();
        String line;
        try {
            Scanner sc = new Scanner(file);
            if (sc.hasNextInt()) {
                x = sc.nextInt();
            } else {
                sc.close();
                return false;
            }
            if (sc.hasNextInt()) {
                y = sc.nextInt();
            } else {
                sc.close();
                return false;
            }
            if(x<1||y<1) return false;
            tmp = 0;
            wood=new char[x][y];
            while (sc.hasNext()) {
                line = sc.next();
                if(line.matches("\\s")) continue;
                if (line.length() != y) {
                    sc.close();
                    return false;
                }
                if ((pos = line.indexOf("B")) > -1) {
                    fires.add(new int[]{tmp, pos});
                }
                wood[tmp]=line.toCharArray();
                tmp++;
            }
            if (tmp != x) {
                sc.close();
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        size=new int[]{x,y};
        return true;
    }
    protected boolean isWood(){
        return itIs;
    }
    
    protected int[] getSize(){
        return size.clone();
    }
}
