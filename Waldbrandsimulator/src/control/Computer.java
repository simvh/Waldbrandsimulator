package control;

import gui.TheWood;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import wald.*;

public class Computer {

    private wald wald, brenn;
    private Helfer helfer[];
    private ArrayList<Asche> asche;
    private Asche ziele[];
    private Weg weg[];
    private boolean da = false;
    private int gefällt = 0;
    private double rest = 0.5;
    private Modus modus = Modus.ernstfallmod;
    private String outstr = control.pfad.src + "data/out";
    private char[][] wood;

    public Modus getModus() {
        return modus;
    }

    public void setModus(Modus modus) {
        this.modus = modus;
    }

    public void setOutstr(String outstr) {
        this.outstr = outstr;
    }

    public Computer(wald wald, Helfer helfer[]) throws FileNotFoundException {
        this.wald = wald;
        out(wald, this.outstr);
//	this.kopie=new wald(this.outstr);
        this.brenn = new wald(this.outstr);
        this.helfer = helfer;
        Helfer.wald = this.wald;
        this.asche = new ArrayList<Asche>();
        this.ziele = new Asche[helfer.length];
        this.weg = new Weg[helfer.length];
        this.wood = new char[wald.flaeche.length][wald.flaeche[0].length];
    }

    public void abbrennen(double res) {
        this.brenn.runde();
        this.brenn.runde();
        this.brenn.runde();
        this.brenn.runde();
        this.brenn.runde();
        this.brenn.runde();
        while (((this.brenn.Bäume / (double) (this.brenn.Waldbestand)) > res) && (this.brenn.brenntnoch)) {
            this.brenn.runde();
        }
        this.brenn.runde();
        this.brenn.runde();
        this.brenn.runde();
        this.brenn.runde();
//	this.brenn.end();
//	this.out(this.brenn,"data/out");
//	System.out.println((this.brenn.Bäume/(double)(this.brenn.Waldbestand)));
//	this.brenn.end();
//	this.out(this.brenn,"./Waldbrandsimulator/data/out");
//	System.out.println((this.brenn.Bäume/(double)(this.brenn.Waldbestand)));
    }

    public void prepare() {
//System.out.println(Math.round(((double)(this.wald.flaeche.length+this.wald.flaeche[0].length)*20)/100));
        if (this.wald.Waldbestand == 0) return;
        this.abbrennen(rest);
        Waldflaeche.wald = this.wald;
        for (int i = 0; i < this.brenn.flaeche.length; i++) {
            for (int j = 0; j < this.brenn.flaeche[0].length; j++) {
                if (this.brenn.flaeche[i][j].toString().equals("A") && ((Asche) this.brenn.flaeche[i][j]).busch == false) {
                    this.asche.add((Asche) this.brenn.flaeche[i][j]);
                }
            }
        }

        Collections.sort(this.asche);
        Collections.reverse(this.asche);
        for (int i = 0; i < this.helfer.length; i++) {
            if (!zielermitteln(i)) {}
        }
    }
    
    public void runde(){
        wald.runde(wood);
        if(da){
        	try {
				neubrennen();
			} catch (FileNotFoundException e) {
				Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, e);
			}
        }
    }

    public void berechnen() {
//System.out.println(Math.round(((double)(this.wald.flaeche.length+this.wald.flaeche[0].length)*20)/100));
        if (this.wald.Waldbestand == 0) {
            throw (new keinWaldExeption());
        }
        this.abbrennen(rest);
        Waldflaeche.wald = this.wald;
        for (int i = 0; i < this.brenn.flaeche.length; i++) {
            for (int j = 0; j < this.brenn.flaeche[0].length; j++) {
                if (this.brenn.flaeche[i][j].toString().equals("A") && ((Asche) this.brenn.flaeche[i][j]).busch == false) {
                    this.asche.add((Asche) this.brenn.flaeche[i][j]);
                }
            }
        }
        /*	a=this.asche.toArray(new Asche[0]);
         System.out.println(a);
         Arrays.sort(a);
         this.asche=new ArrayList(Arrays.asList(a));
         a=null;*/

        Collections.sort(this.asche);
        Collections.reverse(this.asche);
//	System.out.println(this.asche);
        for (int i = 0; i < this.helfer.length; i++) {
            if (!zielermitteln(i)) {}
        }
//	System.out.println("Hier");
        helfersteuerung();
        this.wald.runde();
        helfersteuerung();
        this.wald.runde();
        helfersteuerung();
        this.wald.runde();
        helfersteuerung();
        this.wald.runde();
        helfersteuerung();
        this.wald.runde();
        helfersteuerung();
        this.wald.runde();
        helfersteuerung();
        while (((this.wald.Bäume / (double) (this.wald.Waldbestand)) > rest) && (this.wald.brenntnoch) && (Helfer.brennt == false)) {
            if (this.modus == Modus.preventievmod) {
                this.wald.runde();//System.out.print("[ ");for(Asche a:this.ziele)System.out.print(a+", ");System.out.println("]");
                helfersteuerung();
            } else {
                this.wald.runde();
                helfersteuerung();
                this.wald.runde();
                helfersteuerung();
                this.wald.runde();
                helfersteuerung();
                this.wald.runde();
                helfersteuerung();
                if (da) {
                    try {
                        neubrennen();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        System.out.println("Runde: " + this.wald.runde);
        System.out.println("mehr als 50% der Bäume: " + ((this.wald.Bäume / (double) (this.wald.Waldbestand)) > 0.5));
        System.out.println("verbrannt in %:" + (1 - (this.wald.Bäume / (double) (this.wald.Waldbestand))));
        System.out.println("brenntnoch: " + this.wald.brenntnoch);
        System.out.println("gefällt: " + this.gefällt);
        System.out.println(outstr);
        out(this.wald, outstr);
    }

    public void helfersteuerung() {
        for (int i = 0; i < this.helfer.length; i++) {//System.out.println(this.weg[i]);
            if (this.ziele[i] == null) {
                zielermitteln(i);
            }
//		System.out.println(this.helfer[i].x+" "+this.helfer[i].y);
            if (this.ziele[i] != null) {
                weglaufen(i);
            } else {
                if (this.weg[i] != null) {
                    try {
                        if (this.wald.flaeche[this.helfer[i].x + 1][this.helfer[i].y].toString().equals("a")) {
                            this.helfer[i].gehenrechts();
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }

                    try {

                        if (this.wald.flaeche[this.helfer[i].x - 1][this.helfer[i].y].toString().equals("a")) {
                            this.helfer[i].gehenlinks();
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {

                        if (this.wald.flaeche[this.helfer[i].x][this.helfer[i].y + 1].toString().equals("a")) {
                            this.helfer[i].gehenrunter();
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {

                        if (this.wald.flaeche[this.helfer[i].x][this.helfer[i].y - 1].toString().equals("a")) {
                            this.helfer[i].gehenhoch();
                            continue;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }

                }
            }
//			System.out.println(this.helfer[i].x+" "+this.helfer[i].y);
            helfer[i].st();
        }
    }

    public boolean zielermitteln(int h) {
        int i = 0;
        Weg weg;
        if (asche.isEmpty()) {
            ziele[h] = null;
            return false;
        }
        do {
            weg = ereicht(helfer[h], asche.get(i));
            i++;
        } while (weg == null && i < asche.size());
        if (i < asche.size()) {
            this.weg[h] = weg;
            this.ziele[h] = this.asche.remove(i - 1);
            return true;
        } else {
            ziele[h] = null;
            return false;
        }
    }

    public void neubrennen() throws FileNotFoundException {
        out(this.wald, this.outstr);
        this.brenn = new wald(this.outstr);
//	this.brenn=new wald(this.wald);
        this.brenn.runde = this.wald.runde;
        for (int x = 0; x < wald.flaeche.length; x++) {
            for (int y = 0; y < wald.flaeche[0].length; y++) {
                brenn.flaeche[x][y].brennen = wald.flaeche[x][y].brennen;
                brenn.flaeche[x][y].brennzeit = wald.flaeche[x][y].brennzeit;
                brenn.flaeche[x][y].zuendcounter = wald.flaeche[x][y].zuendcounter;
            }
        }
        this.brenn.runde = this.wald.runde;
        this.brenn.Bäume = this.wald.Bäume;
        this.brenn.Waldbestand = this.wald.Waldbestand;
        this.brenn.brenntnoch = this.wald.brenntnoch;
        this.abbrennen(this.rest);
        this.asche = new ArrayList<Asche>();
        for (int i = 0; i < this.brenn.flaeche.length; i++) {
            for (int j = 0; j < this.brenn.flaeche[0].length; j++) {
                if (this.brenn.flaeche[i][j].toString().equals("A") && !((Asche) this.brenn.flaeche[i][j]).busch) {
                    boolean broken = false;
                    for (int l = 0; l < this.helfer.length; l++) {
                        if (ziele[l] != null) {
                            if (i == this.ziele[l].x && j == this.ziele[l].y) {
                                broken = true;
                                break;
                            }
                        }
                    }
                    if (!broken) {
                        this.asche.add((Asche) this.brenn.flaeche[i][j]);
                    }
                }
            }
        }//System.out.println(this.asche);
        Collections.sort(this.asche);
        Collections.reverse(this.asche);
        Waldflaeche.wald = this.wald;

    }

    private void preventiev(int i) {
        /*for(int j=0;j<helfer.length;j++){
         if(j!=i){
         zielermitteln(j);
         }
         }*/
        zielermitteln(i);
    }

    public Weg ereicht(Helfer h, Asche w) {
        point helfer = new point(h.x, h.y);
        point ziel = new point(w.x, w.y);
        int rund = this.wald.runde;
        int c = 2 - h.getbewegungsmarken();
        if (w.runde <= rund + (Math.abs(h.x - w.x) + Math.abs(h.y - w.y) / 2)) {
            return null;
        }
        for (Weg weg : Weg.values()) {
            boolean broken = false;
            helfer = new point(h.x, h.y);
            rund = this.wald.runde;
            c = 2 - h.getbewegungsmarken();
            if (c == 2) {
                rund++;
                c = 0;
            }/*if((Math.abs(helfer.x-ziel.x)+Math.abs(helfer.y-ziel.y)>=Math.round(((double)Helfer.wald.flaeche.length+Helfer.wald.flaeche[0].length*20)/100))){
             return null;
             }*/

            while (Math.abs(helfer.x - ziel.x) + Math.abs(helfer.y - ziel.y) > 1) {// noch nicht am Ziel
                if (c == 2) {
                    rund++;
                    c = 0;
                }
                if (this.brenn.flaeche[helfer.x][helfer.y].toString().equals("B")) {
                    broken = true;
                }
                if (this.brenn.flaeche[helfer.x][helfer.y].toString().equals("A")) {
                    if (((Asche) this.brenn.flaeche[helfer.x][helfer.y]).runde - 2 <= rund) {
                        switch (this.wald.flaeche[helfer.x][helfer.y].toString()) {
                            case "L":
                                if (rund - 2 <= ((Asche) this.brenn.flaeche[helfer.x][helfer.y]).runde + 5) {
                                    broken = true;
                                }
                                break;
                            case "N":
                                if (rund - 2 <= ((Asche) this.brenn.flaeche[helfer.x][helfer.y]).runde + 3) {
                                    broken = true;
                                }
                                break;
                            case "-":
                                if (rund - 2 <= ((Asche) this.brenn.flaeche[helfer.x][helfer.y]).runde + 2) {
                                    broken = true;
                                }
                                break;
                            case "B":
                                broken = true;
                                break;
                        }
                        if (broken) {
                            break;
                        }
                    }
                }
                if (helfer.x - ziel.x < 0) {
                    if (helfer.y - ziel.y < 0) {
                        switch (weg) {
                            case wagsenk:
                                helfer.x++;
                                c++;
                                break;
                            case senkwag:
                                helfer.y++;
                                c++;
                                break;
                            case wagdia:
                                if (Math.abs(helfer.x - ziel.x) < Math.abs(helfer.y - ziel.y)) {
                                    helfer.y++;
                                    c++;
                                } else {
                                    helfer.x++;
                                    c++;
                                }
                                break;
                            case senkdia:
                                if (Math.abs(helfer.x - ziel.x) <= Math.abs(helfer.y - ziel.y)) {
                                    helfer.y++;
                                    c++;
                                } else {
                                    helfer.x++;
                                    c++;
                                }
                                break;
                        }
                    } else {
                        if (helfer.y - ziel.y > 0) {
                            switch (weg) {
                                case wagsenk:
                                    helfer.x++;
                                    c++;
                                    break;
                                case senkwag:
                                    helfer.y--;
                                    c++;
                                    break;
                                case wagdia:
                                    if (Math.abs(helfer.x - ziel.x) < Math.abs(helfer.y - ziel.y)) {
                                        helfer.y--;
                                        c++;
                                    } else {
                                        helfer.x++;
                                        c++;
                                    }
                                    break;
                                case senkdia:
                                    if (Math.abs(helfer.x - ziel.x) <= Math.abs(helfer.y - ziel.y)) {
                                        helfer.y--;
                                        c++;
                                    } else {
                                        helfer.x++;
                                        c++;
                                    }
                                    break;
                            }
                        } else {
                            helfer.x++;
                            c++;

                        }

                    }
                } else {
                    if (helfer.x - ziel.x > 0) {

                        if (helfer.y - ziel.y < 0) {
                            switch (weg) {
                                case wagsenk:
                                    helfer.x--;
                                    c++;
                                    break;
                                case senkwag:
                                    helfer.y++;
                                    c++;
                                    break;
                                case wagdia:
                                    if (Math.abs(helfer.x - ziel.x) < Math.abs(helfer.y - ziel.y)) {
                                        helfer.y++;
                                        c++;
                                    } else {
                                        helfer.x--;
                                        c++;
                                    }
                                    break;
                                case senkdia:
                                    if (Math.abs(helfer.x - ziel.x) <= Math.abs(helfer.y - ziel.y)) {
                                        helfer.y++;
                                        c++;
                                    } else {
                                        helfer.x--;
                                        c++;
                                    }
                                    break;
                            }
                        } else {
                            if (helfer.y - ziel.y > 0) {
                                switch (weg) {
                                    case wagsenk:
                                        helfer.x--;
                                        c++;
                                        break;
                                    case senkwag:
                                        helfer.y--;
                                        c++;
                                        break;
                                    case wagdia:
                                        if (Math.abs(helfer.x - ziel.x) < Math.abs(helfer.y - ziel.y)) {
                                            helfer.y--;
                                            c++;
                                        } else {
                                            helfer.x--;
                                            c++;
                                        }
                                        break;
                                    case senkdia:
                                        if (Math.abs(helfer.x - ziel.x) <= Math.abs(helfer.y - ziel.y)) {
                                            helfer.y--;
                                            c++;
                                        } else {
                                            helfer.x--;
                                            c++;
                                        }
                                        break;
                                }
                            } else {
                                helfer.x--;
                                c++;

                            }

                        }
                    } else {//helfer vertikal zum ziel verschoben
                        if (helfer.y - ziel.y < 0) {
                            helfer.y++;
                            c++;

                        } else {
                            helfer.y--;
                            c++;

                        }

                    }
                }/*if(c==2){
                 rund++;
                 c=0;
                 }*/
                /*if(this.brenn.flaeche[helfer.x][helfer.y].toString().equals("B"))broken=true;
                 if(this.brenn.flaeche[helfer.x][helfer.y].toString().equals("A")){
                 if(((Asche)this.brenn.flaeche[helfer.x][helfer.y]).runde+2>=rund){
                 switch(this.wald.flaeche[helfer.x][helfer.y].toString()){
                 case "L":if(rund+2>=((Asche)this.brenn.flaeche[helfer.x][helfer.y]).runde-5){broken=true;}break;
                 case "N":if(rund+2>=((Asche)this.brenn.flaeche[helfer.x][helfer.y]).runde-3){broken=true;}break;
                 case "-":if(rund+2>=((Asche)this.brenn.flaeche[helfer.x][helfer.y]).runde-2){broken=true;}break;
                 case "B":broken=true;break;
                 }
                 if(broken){
                 break;
                 }
                 }
                 }*/

            }
            rund++;
            if (this.brenn.flaeche[helfer.x][helfer.y].toString().equals("B")) {
                broken = true;
            }
            if (this.brenn.flaeche[helfer.x][helfer.y].toString().equals("A")) {
                if (((Asche) this.brenn.flaeche[helfer.x][helfer.y]).runde - 3 <= rund) {
                    switch (this.wald.flaeche[helfer.x][helfer.y].toString()) {
                        case "L":
                            if (rund - 2 < ((Asche) this.brenn.flaeche[helfer.x][helfer.y]).runde + 5) {
                                broken = true;
                            }
                            break;
                        case "N":
                            if (rund - 2 < ((Asche) this.brenn.flaeche[helfer.x][helfer.y]).runde + 3) {
                                broken = true;
                            }
                            break;
                        case "-":
                            if (rund - 2 < ((Asche) this.brenn.flaeche[helfer.x][helfer.y]).runde + 2) {
                                broken = true;
                            }
                            break;
                        case "B":
                            broken = true;
                            break;
                    }
                }
            }

            if (broken == false) {
                return weg;
            }
        }
        return null;
    }

    public void setBrenn(wald brenn) {
        this.brenn = brenn;
    }

    private void weglaufen(int i) {
        Helfer helfer = this.helfer[i];
        Asche ziel = this.ziele[i];
        Weg weg = this.weg[i];
        while (helfer.getbewegungsmarken() > 0) {
            if (Math.abs(helfer.x - ziel.x) + Math.abs(helfer.y - ziel.y) > 1) {// noch nicht am Ziel
                if (helfer.x - ziel.x < 0) {
                    if (helfer.y - ziel.y < 0) {
                        switch (weg) {
                            case wagsenk:
                                helfer.gehenrechts();
                                break;
                            case senkwag:
                                helfer.gehenrunter();
                                break;
                            case wagdia:
                                if (Math.abs(helfer.x - ziel.x) < Math.abs(helfer.y - ziel.y)) {
                                    helfer.gehenrunter();
                                } else {
                                    helfer.gehenrechts();
                                }
                                break;
                            case senkdia:
                                if (Math.abs(helfer.x - ziel.x) <= Math.abs(helfer.y - ziel.y)) {
                                    helfer.gehenrunter();
                                } else {
                                    helfer.gehenrechts();
                                }
                                break;
                        }
                    } else {
                        if (helfer.y - ziel.y > 0) {
                            switch (weg) {
                                case wagsenk:
                                    helfer.gehenrechts();
                                    break;
                                case senkwag:
                                    helfer.gehenhoch();
                                    break;
                                case wagdia:
                                    if (Math.abs(helfer.x - ziel.x) < Math.abs(helfer.y - ziel.y)) {
                                        helfer.gehenhoch();
                                    } else {
                                        helfer.gehenrechts();
                                    }
                                    break;
                                case senkdia:
                                    if (Math.abs(helfer.x - ziel.x) <= Math.abs(helfer.y - ziel.y)) {
                                        helfer.gehenhoch();
                                    } else {
                                        helfer.gehenrechts();
                                    }
                                    ;
                                    break;
                            }
                        } else {
                            helfer.gehenrechts();
                            continue;
                        }

                    }
                } else {
                    if (helfer.x - ziel.x > 0) {

                        if (helfer.y - ziel.y < 0) {
                            switch (weg) {
                                case wagsenk:
                                    helfer.gehenlinks();
                                    break;
                                case senkwag:
                                    helfer.gehenrunter();
                                    break;
                                case wagdia:
                                    if (Math.abs(helfer.x - ziel.x) < Math.abs(helfer.y - ziel.y)) {
                                        helfer.gehenrunter();
                                    } else {
                                        helfer.gehenlinks();
                                    }
                                    break;
                                case senkdia:
                                    if (Math.abs(helfer.x - ziel.x) <= Math.abs(helfer.y - ziel.y)) {
                                        helfer.gehenrunter();
                                    } else {
                                        helfer.gehenlinks();
                                    }
                                    ;
                                    break;
                            }
                        } else {
                            if (helfer.y - ziel.y > 0) {
                                switch (weg) {
                                    case wagsenk:
                                        helfer.gehenlinks();
                                        break;
                                    case senkwag:
                                        helfer.gehenhoch();
                                        break;
                                    case wagdia:
                                        if (Math.abs(helfer.x - ziel.x) < Math.abs(helfer.y - ziel.y)) {
                                            helfer.gehenhoch();
                                        } else {
                                            helfer.gehenlinks();
                                        }
                                        break;
                                    case senkdia:
                                        if (Math.abs(helfer.x - ziel.x) <= Math.abs(helfer.y - ziel.y)) {
                                            helfer.gehenhoch();
                                        } else {
                                            helfer.gehenlinks();
                                        }
                                        ;
                                        break;
                                }
                            } else {
                                helfer.gehenlinks();
                                continue;
                            }

                        }
                    } else {//helfer vertikal zum ziel verschoben
                        if (helfer.y - ziel.y < 0) {
                            helfer.gehenrunter();
                            continue;
                        } else {
                            helfer.gehenhoch();
                            continue;
                        }

                    }
                }
            } else {// schon am Ziel
                if (!helfer.isFällen()) {
                    helfer.baumfällen(ziel.x, ziel.y);

                } else {
                    helfer.baumfällen(ziel.x, ziel.y);
//				this.kopie.flaeche[ziel.x][ziel.y]=new abgeholzt(ziel.x,ziel.y);
                    this.gefällt++;
                    switch (this.modus) {

                        case preventievmod:
                            try {
                                neubrennen();
                                preventiev(i);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Computer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case ernstfallmod:
                            da = true;
                            zielermitteln(i);
                            break;
                    }
                }
            }
        }
    }

    /**
     * @param wa
     */
    /*private void out(wald wa){
     // TODO Auto-generated method stub
     FileWriter out = null;
     try {
     out = new FileWriter("out",false);
     } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }
     int i;try{
     out.append(""+wa.flaeche.length+" "+wa.flaeche[0].length+"\n");
     for (int x=0;x<wa.flaeche.length;x++){
     for(int y=0;y<wa.flaeche[0].length;y++){
     out.append(wa.flaeche[x][y].toString());
     }out.append('\n');
     }
	
     }catch (IOException e){
     e.printStackTrace();
     }
     try {
     out.close();
     } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }

     }*/
    private void out(wald wa, String str) {
        // TODO Auto-generated method stub
        FileWriter out = null;
        try {
            out = new FileWriter(str, false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            out.append("" + wa.flaeche.length + " " + wa.flaeche[0].length + "\n");
            for (int x = 0; x < wa.flaeche.length; x++) {
                for (int y = 0; y < wa.flaeche[0].length; y++) {
                    out.append(wa.flaeche[x][y].toString());
                }
                out.append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Helfer[] getHelfer() {
        return helfer;
    }

    public void setHelfer(Helfer[] helfer) {
        this.helfer = helfer;
    }

    public void setWald(wald wald) {
        this.wald = wald;
    }

    public wald getWald() {
        return wald;
    }
    
    public char[][] getWood() {
        return wood;
    }
    
    public int getGefällt(){
        return gefällt;
    }
}
