/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import control.Computer;
import control.Modus;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import wald.Helfer;
import wald.wald;
import wald.waldgenerator;

/**
 *
 * @author a.diener
 */
public class TheWood extends javax.swing.JFrame {

    private BufferedImage ash = null;
    private BufferedImage leaf = null;
    private BufferedImage pin = null;
    private BufferedImage bush = null;
    private BufferedImage fire = null;
    private BufferedImage stump = null;
    private BufferedImage man = null;
    private BufferedImage axe = null;
    private BufferedImage image;
    private ImageIcon icon;
    private W woodPanel;
    private File inFile;
    private File outFile;
    private javax.swing.JScrollPane woodScroll;
    private Computer comp;
    private boolean onfire;
    private int round;
    private boolean working;
    private double MBSaved;
    private double trees;
    private boolean checkTrees;
    private Timer burnTimer;
    private Timer runTimer;

    private boolean work() {
        return working;
    }

    private void checkTrees(boolean value) {
        checkTrees = value;
    }

    private boolean enoughTrees() {
        trees = comp.getWald().Bäume / (double) (comp.getWald().Waldbestand);
        if (!checkTrees) {
            return true;
        }
        return trees >= MBSaved;
    }

    private void nextRound() {
        round++;
        lRound.setText(round + "");
    }

    protected void drawWood(WoodChecker check) {
        if (!check.isWood()) {
            return;
        }
        draw(check.wood);
    }

    private MainFrame mainFrame;

    /**
     * Creates new form ShowTheWood
     */
    public TheWood() {
        this.runTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comp.helfersteuerung();
                comp.runde();
                nextRound();
                draw();
                if (!enoughTrees()) {
                    int result = JOptionPane.showConfirmDialog(null, "The limit your need can't be saved!\nResume?", "Limit exceeded!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    switch (result) {
                        case JOptionPane.YES_OPTION:
                            checkTrees(false);
                            break;
                        case JOptionPane.NO_OPTION:
                            runTimer.stop();
                            break;
                        case JOptionPane.CLOSED_OPTION:
                            checkTrees(false);
                            break;
                    }
                    finish();
                }
                if (!work()) {
                    runTimer.stop();
                }
            }
        });
        this.burnTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comp.runde();
                enoughTrees();
                nextRound();
                draw();
                if (!work()) {
                    burnTimer.stop();
                }
            }
        });
        initComponents();
        bRun.setVisible(false);
        bRStep.setVisible(false);
        jToolBar1.setVisible(false);
        try {
            ash = ImageIO.read(new FileInputStream("bilder/Asche.png"));
            pin = ImageIO.read(new FileInputStream("bilder/nadelbaum.png"));
            leaf = ImageIO.read(new FileInputStream("bilder/laubbaum.png"));
            bush = ImageIO.read(new FileInputStream("bilder/roehricht.png"));
            fire = ImageIO.read(new FileInputStream("bilder/Feuer.png"));
            stump = ImageIO.read(new FileInputStream("bilder/Baumstumpf.png"));
            man = ImageIO.read(new FileInputStream("bilder/helfer.png"));
            axe = ImageIO.read(new FileInputStream("bilder/axe.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error by opening images!\nPlease reload the programm.", "Error!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(TheWood.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TheWood(MainFrame main) {
        this();
        mainFrame = main;
    }

    private void createImg(char[][] wood) {
        int ht = 20, wd = 20;
        int rows = wood.length;
        int cols = wood[0].length;
        Graphics gr;
        // create the offscreen buffer and associated Graphics
        image = new BufferedImage(wd * cols, ht * rows, BufferedImage.TYPE_4BYTE_ABGR);
        gr = image.getGraphics();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                switch (wood[r][c]) {
                    case 'B':
                        gr.drawImage(fire, r * ht, c * wd, this);
                        break;
                    case 'L':
                        gr.drawImage(leaf, r * ht, c * wd, this);
                        break;
                    case 'N':
                        gr.drawImage(pin, r * ht, c * wd, this);
                        break;
                    case '-':
                        gr.drawImage(bush, r * ht, c * wd, this);
                        break;
                    case 'A':
                        gr.drawImage(ash, r * ht, c * wd, this);
                        break;
                    case 'a':
                        gr.drawImage(stump, r * ht, c * wd, this);
                        break;
                }
            }
        }
    }

    private void createImg() {
        wald wood = comp.getWald();
        Helfer[] help = comp.getHelfer();
        int ht = 20, wd = 20;
        int rows = wood.flaeche.length;
        int cols = wood.flaeche[0].length;
        Graphics gr;
        // create the offscreen buffer and associated Graphics
        image = new BufferedImage(wd * cols, ht * rows, BufferedImage.TYPE_4BYTE_ABGR);
        gr = image.getGraphics();
        onfire = false;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                switch (wood.flaeche[r][c].toChar()) {
                    case 'B':
                        gr.drawImage(fire, r * ht, c * wd, this);
                        break;
                    case 'L':
                        gr.drawImage(leaf, r * ht, c * wd, this);
                        if (wood.flaeche[r][c].brennen) {
                            onfire = true;
                            gr.drawImage(fire, r * ht, c * wd, this);
                        }
                        break;
                    case 'N':
                        gr.drawImage(pin, r * ht, c * wd, this);
                        if (wood.flaeche[r][c].brennen) {
                            onfire = true;
                            gr.drawImage(fire, r * ht, c * wd, this);
                        }
                        break;
                    case '-':
                        if (wood.flaeche[r][c].brennen) {
                            onfire = true;
                            gr.drawImage(fire, r * ht, c * wd, this);
                        }
                        gr.drawImage(bush, r * ht, c * wd, this);
                        break;
                    case 'A':
                        gr.drawImage(ash, r * ht, c * wd, this);
                        break;
                    case 'a':
                        gr.drawImage(stump, r * ht, c * wd, this);
                        break;
                }
            }
        }
        if (!onfire && round > 2) {
            working = false;
            finish();
        } else {
        }
        for (Helfer h : help) {
            gr.drawImage(man, h.x * ht, h.y * wd, this);
        }
    }

    class W extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(image, 0, 0, null);
        }
    }

    private boolean draw() {
        createImg();
        if (woodScroll == null) {
            jToolBar1.setVisible(true);
            woodPanel = new W();
            woodPanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
            woodScroll = new javax.swing.JScrollPane(woodPanel);
            jPanel2.setLayout(new BorderLayout());
            jPanel2.add(woodScroll, BorderLayout.CENTER);
        } else {
            woodPanel.updateUI();
            woodScroll.updateUI();
        }
        return true;
    }

    public boolean draw(char[][] wood) {
        createImg(wood);
        if (woodScroll == null) {
            jToolBar1.setVisible(true);
            System.out.print("here!");
            woodPanel = new W();
            woodPanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
            woodScroll = new javax.swing.JScrollPane(woodPanel);
            jPanel2.setLayout(new BorderLayout());
            jPanel2.add(woodScroll, BorderLayout.CENTER);
        } else {
            System.out.print("there!");
            woodPanel.updateUI();
            woodScroll.updateUI();
        }
        return true;
    }

    protected void compute(WoodChecker check, int helpers, int wantSave, char mode, File in, File out) {
        inFile = in;
        outFile = out;
        inL.setText(PathShortener.pathLengthShortener(inFile.getName(), 15));
        outL.setText(PathShortener.pathLengthShortener(outFile.getName(), 15));
        helpL.setText(helpers + "");
        saveL.setText(wantSave + "");
        lMode.setText(mode + "");
        round = 0;
        lRound.setText(round + "");
        MBSaved = wantSave / 100.;
        try {
            Helfer helfer[] = new Helfer[helpers];
            for (int i = 0; i < helpers; i++) {
                switch (i % 4) {
                    case 0:
                        helfer[i] = new Helfer(check.getSize()[0] / 2, 0);
                        break;
                    case 1:
                        helfer[i] = new Helfer(0, check.getSize()[1] / 2);
                        break;
                    case 2:
                        helfer[i] = new Helfer(check.getSize()[0] / 2, check.getSize()[1] - 1);
                        break;
                    case 3:
                        helfer[i] = new Helfer(check.getSize()[0] - 1, check.getSize()[1] / 2);
                        break;
                }
            }
            wald wood = new wald(check.wood);
            Computer computer = new Computer(wood, helfer);
            computer.setOutstr(out.getAbsolutePath());
            computer.setModus(Modus.ernstfallmod);

            computer.prepare();
            comp = computer;
            comp.runde();
            draw(comp.getWood());
            bRun.setVisible(true);
            bRStep.setVisible(true);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Some problems by accessing the input file", "Error!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean readFileData(File f) {
        WoodChecker check = new WoodChecker(f);
        return check.isWood();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser(){
            @Override
            public void approveSelection(){
                File f = getSelectedFile();
                if(f.exists()){
                    if(!readFileData(f)){
                        JOptionPane.showMessageDialog(this,"That is not a Wood-File","Wrong file!",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        super.approveSelection();
                    }
                } else {
                    JOptionPane.showMessageDialog(this,"The file dosen't exists","Wrong input!",JOptionPane.INFORMATION_MESSAGE);
                }

            }
        };
        ;
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        bBurn = new javax.swing.JButton();
        bBStep = new javax.swing.JButton();
        bRun = new javax.swing.JButton();
        bRStep = new javax.swing.JButton();
        bPause = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lRound = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bWrite = new javax.swing.JButton();
        bState = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        helpL = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        saveL = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lMode = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inL = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        outL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenu2.setText("jMenu2");

        jToolBar1.setRollover(true);

        bBurn.setText("Burn It");
        bBurn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bBurn.setFocusable(false);
        bBurn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bBurn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bBurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBurnActionPerformed(evt);
            }
        });
        jToolBar1.add(bBurn);

        bBStep.setText("Burn Step");
        bBStep.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bBStep.setFocusable(false);
        bBStep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bBStep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bBStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBStepActionPerformed(evt);
            }
        });
        jToolBar1.add(bBStep);

        bRun.setText("Run");
        bRun.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bRun.setFocusable(false);
        bRun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bRun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRunActionPerformed(evt);
            }
        });
        jToolBar1.add(bRun);

        bRStep.setText("Run Step");
        bRStep.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bRStep.setFocusable(false);
        bRStep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bRStep.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bRStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRStepActionPerformed(evt);
            }
        });
        jToolBar1.add(bRStep);

        bPause.setText("Pause");
        bPause.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bPause.setFocusable(false);
        bPause.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bPause.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPauseActionPerformed(evt);
            }
        });
        jToolBar1.add(bPause);

        bReset.setText("Reset");
        bReset.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bReset.setFocusable(false);
        bReset.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bReset.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });
        jToolBar1.add(bReset);

        jLabel1.setText(" Round: ");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.add(jLabel1);

        lRound.setText("0");
        lRound.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.add(lRound);

        jLabel4.setText(" ");
        jToolBar1.add(jLabel4);

        bWrite.setText("Write wood to the output");
        bWrite.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bWrite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bWriteActionPerformed(evt);
            }
        });
        jToolBar1.add(bWrite);

        bState.setText("Show State");
        bState.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bState.setFocusable(false);
        bState.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bState.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bStateActionPerformed(evt);
            }
        });
        jToolBar1.add(bState);

        jToolBar2.setRollover(true);

        jLabel2.setText("Current: ");
        jToolBar2.add(jLabel2);

        helpL.setText("0");
        jToolBar2.add(helpL);

        jLabel3.setText(" Helpers, ");
        jToolBar2.add(jLabel3);

        saveL.setText("0");
        jToolBar2.add(saveL);

        jLabel6.setText(" % Wood must be saved | Mode: ");
        jToolBar2.add(jLabel6);

        lMode.setText(".");
        jToolBar2.add(lMode);

        jLabel5.setText(" | Input:");
        jToolBar2.add(jLabel5);

        inL.setText("...");
        jToolBar2.add(inL);

        jLabel7.setText(" | Output: ");
        jToolBar2.add(jLabel7);

        outL.setText("...");
        jToolBar2.add(outL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        jMenuItem2.setText("Open Wood");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Change Output");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Close Window");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Help");

        jMenuItem4.setText("About");
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void show(File file, File out) {
        if (out != null) {
            outFile = out;
            outL.setText(PathShortener.pathLengthShortener(outFile.getName(), 15));
        }
        inFile = file;
        bRun.setVisible(false);
        bRStep.setVisible(false);
        helpL.setText(0 + "");
        saveL.setText(0 + "");
        lMode.setText(".");
        round = 0;
        lRound.setText(round + "");
        inL.setText(PathShortener.pathLengthShortener(inFile.getName(), 15));
        WoodChecker check = new WoodChecker(inFile);
        wald wood = new wald(check.wood);
        Computer computer;
        try {
            computer = new Computer(wood, new Helfer[]{});
            computer.setModus(Modus.ernstfallmod);
            computer.prepare();
            comp = computer;
            draw(check.wood);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheWood.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.revalidate();
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (inFile == null) {
            fileChooser.setCurrentDirectory(new File("src/data/wald"));
        } else {
            fileChooser.setCurrentDirectory(inFile);
            fileChooser.setSelectedFile(inFile);
        }
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            inFile = fileChooser.getSelectedFile();
            show(inFile, null);
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void bBStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBStepActionPerformed
        comp.runde();
        nextRound();
        draw();
    }//GEN-LAST:event_bBStepActionPerformed

    private void bPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPauseActionPerformed
        working = false;
    }//GEN-LAST:event_bPauseActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        working = false;
        round = 0;
        lRound.setText(round + "");
        if (bRun.isVisible()) {
            compute(new WoodChecker(inFile), Integer.parseInt(helpL.getText()),
                    Integer.parseInt(saveL.getText()), lMode.getText().charAt(0), inFile, outFile);
        } else {
            WoodChecker check = new WoodChecker(inFile);
            wald wood = new wald(check.wood);
            Computer computer;
            try {
                computer = new Computer(wood, new Helfer[]{});
                computer.setModus(Modus.ernstfallmod);
                computer.prepare();
                comp = computer;
                draw(check.wood);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TheWood.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bResetActionPerformed

    private void bRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRunActionPerformed
        working = true;
        checkTrees = true;
        runTimer.start();
    }//GEN-LAST:event_bRunActionPerformed

    private void bBurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBurnActionPerformed
        working = true;
        checkTrees = false;
        burnTimer.start();
    }//GEN-LAST:event_bBurnActionPerformed

    private void bRStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRStepActionPerformed
        comp.helfersteuerung();
        comp.runde();
        nextRound();
        draw();
    }//GEN-LAST:event_bRStepActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (outFile == null) {
            fileChooser.setCurrentDirectory(new File("src/data/out"));
        } else {
            fileChooser.setCurrentDirectory(outFile);
            fileChooser.setSelectedFile(outFile);
        }
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            outFile = fileChooser.getSelectedFile();
            outL.setText(PathShortener.pathLengthShortener(outFile.getName(), 15));
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void writeToFile() {
        if (outFile == null) {
            JOptionPane.showMessageDialog(this, "Select the output first!", "No file selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            waldgenerator.write(comp.getWood(), outFile);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Problems by accessing the output file", "Error!", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(TheWood.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "File " + outFile.getName() + " is written", "Done", JOptionPane.INFORMATION_MESSAGE);
    }

    private void bWriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bWriteActionPerformed
        writeToFile();
    }//GEN-LAST:event_bWriteActionPerformed

    private void bStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bStateActionPerformed
        showStates();
    }//GEN-LAST:event_bStateActionPerformed

    private void finish() {
        String[] options = new String[]{"OK", "Write Wood to the output"};
        int result = JOptionPane.showOptionDialog(null,
                String.format("Runde: %d\nLost trees: %f %%\nTrees felled: %d\nTrees remains: %d",
                        round, ((1 - trees) * 100), comp.getGefällt(), comp.getWald().Bäume),
                trees > MBSaved ? "Wood can be saved!!!" : "Stats",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        switch (result) {
            case 0:
                return;
            case 1:
                writeToFile();
            default:
        }
    }

    private void showStates() {
        String[] options = new String[]{"OK"};
        JOptionPane.showOptionDialog(null,
                String.format("Runde: %d\nLost trees: %f %%\nTrees felled: %d\nTrees remains: %d",
                        round, ((1 - trees) * 100), comp.getGefällt(), comp.getWald().Bäume), "Stats",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheWood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TheWood().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBStep;
    private javax.swing.JButton bBurn;
    private javax.swing.JButton bPause;
    private javax.swing.JButton bRStep;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bRun;
    private javax.swing.JButton bState;
    private javax.swing.JButton bWrite;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel helpL;
    private javax.swing.JLabel inL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel lMode;
    private javax.swing.JLabel lRound;
    private javax.swing.JLabel outL;
    private javax.swing.JLabel saveL;
    // End of variables declaration//GEN-END:variables
}
