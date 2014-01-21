/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import wald.waldgenerator;

/**
 *
 * @author a.diener
 */
public class Crashtest {
    
    private File tmp, tmpOut;
    private ArrayList<int[]> fires;
    private TheWood player;
    private WoodChecker check;
    private boolean work;
    private JLabel status;
    private ArrayList<Boolean> results;
    private JPanel resPanel;
    private JList resList;
    private DefaultListModel<String> res;
    private int step=100;
    
    public Crashtest(JLabel l){
        status=l;
    }
    
    protected void prepare(){
        tmp=new File("src/data/tmp");
        tmpOut=new File("src/data/tmpOut");
        fires=new ArrayList<>();
        fires.add(new int[]{0,0});
        results=new ArrayList<>();
        res=new DefaultListModel<>();
        resPanel=new JPanel();
        resList=new JList(res);
        resPanel.add(resList);
    }
            
    protected void start(){
        work = true;
        int i=1;
        for(; work; i++){
            results.add(false);
            try{
                player=new TheWood();
                fires.add(new int[]{0,i*step-1});
                fires.add(new int[]{i*step-1,i*step-1});
                fires.add(new int[]{i*step-1,0});
                if(waldgenerator.create(i*step, i*step, fires, tmp)){
                    status.setText(String.format("Dim %d: create.",i*step));
                    java.util.logging.Logger.getLogger(this.getClass().getName()).log(java.util.logging.Level.INFO, String.format("Wood dim %d created", i*step));
                } else {
                    JOptionPane.showMessageDialog(null, "Problems by creating the file\nPlease restart the programm.", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                check=new WoodChecker(tmp);
                System.gc();
                status.setText(String.format("Dim %d: compute.",i*step));
                player.computest(check,100,50,'p',tmp,tmpOut);
                status.setText(String.format("Dim %d: running 5 steps.",i*step));
                player.test(5);
            } catch (OutOfMemoryError e){
                work=false;
                java.util.logging.Logger.getLogger(this.getClass().getName()).log(java.util.logging.Level.SEVERE, String.format("Wood dim %d crashed", i*step));
                break;
            }
            results.set(results.size()-1, true);
        }
        if(!work){
            showResults();
        }
        tmp.delete();
        tmpOut.delete();
    }
    
    protected void stop(){
        work=false;
    }
    
    private void showResults(){
        int i=1;
        int save=0;
        for(boolean b:results) {
            res.addElement(String.format("Dim %d: %b", i*step, b));
            if(b)save=i;
            i++;
        }
        resPanel.add(new JLabel(String.format("%d Trees is doable!", save*save*10000)));
        JOptionPane.showMessageDialog(null, resPanel, "Results", JOptionPane.INFORMATION_MESSAGE);
    }
}
