/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * @author a.diener
 */
public class InputChecker extends InputVerifier {
    String lastGood = "";
    public boolean verify(JComponent input) {
        JTextField text = (JTextField)input;
        String value = text.getText().trim();
        try {
            Integer.parseInt(value);
            lastGood = value;
        } catch (NumberFormatException e) {
            text.setText(lastGood);
            // assumed it should return false
           return false;
        }
        return true;
    }
}
