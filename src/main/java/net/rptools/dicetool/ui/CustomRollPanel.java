/* The MIT License
 * 
 * Copyright (c) 2004,2005 David Rice
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation files 
 * (the "Software"), to deal in the Software without restriction, 
 * including without limitation the rights to use, copy, modify, merge, 
 * publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be 
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS 
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN 
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE 
 * SOFTWARE.
 */
package net.rptools.dicetool.ui;

import javax.swing.JOptionPane;

/**
 *
 * @author  drice
 */
public class CustomRollPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 341091181935322593L;

    private static final int NUM_CUSTOM_ROLLS = 20;
    
    private MainFrame dmTool;

    private javax.swing.JTextField rollDefinition[];
    private javax.swing.JButton    rollButton[];
    
    private javax.swing.JPanel inputPanel;
    
    
    /** Creates new form CustomRollPanel */
    public CustomRollPanel(MainFrame dmTool) {
        initComponents();
        
        this.dmTool = dmTool;
    }
    
    private void initComponents() {
        inputPanel = new javax.swing.JPanel();
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        inputPanel.setLayout(new java.awt.GridLayout(NUM_CUSTOM_ROLLS, 2));
        
        rollDefinition = new javax.swing.JTextField[NUM_CUSTOM_ROLLS];
        rollButton     = new javax.swing.JButton[NUM_CUSTOM_ROLLS];
        
        for (int i = 0; i < NUM_CUSTOM_ROLLS; i++) {
            rollDefinition[i] = new javax.swing.JTextField();
            inputPanel.add(rollDefinition[i]);
            
            rollButton[i]     = new javax.swing.JButton();
            rollButton[i].setFont(new java.awt.Font("Dialog", 0, 10));
            rollButton[i].setText("Roll");
            rollButton[i].setActionCommand(String.valueOf(i));
            rollButton[i].setMaximumSize(new java.awt.Dimension(30, 20));
            rollButton[i].setMinimumSize(new java.awt.Dimension(30, 20));
            rollButton[i].setPreferredSize(new java.awt.Dimension(30, 20));
            rollButton[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    saveActionPerformed(evt);
                }
            });

            rollButton[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    CustomRollPanel.this.mousePressed(evt);
                }
            });

            inputPanel.add(rollButton[i]);
        }
        
        add(inputPanel);
    }

    private void mousePressed(java.awt.event.MouseEvent evt) {
        //javax.swing.JDialog dialog = new RenameCustomRollDialog(this.getParent(), true);
        
        //dialog.setVisible(true);

        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            javax.swing.JButton button = (javax.swing.JButton) evt.getComponent();

            button.setText(JOptionPane.showInputDialog(dmTool, "Roll name", button.getText()));
        }
    }
    
    public int getCount() {
        return NUM_CUSTOM_ROLLS;
    }
    
    public String getDefinition(int index) {
        return rollDefinition[index].getText();
    }
    
    public String getButtonLabel(int index) {
        return rollButton[index].getText();
    }
    
    public void setDefinition(int index, String definition) {
        rollDefinition[index].setText(definition);
    }

    public void setButtonLabel(int index, String label) {
        rollButton[index].setText(label);
    }
    
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {
//        javax.swing.JTextField field = new javax.swing.JTextField();
//        field.setEditable(false);
//        
//        javax.swing.JButton button = new javax.swing.JButton();
//        
//        customPanel.add(field);
//        customPanel.add(button);
//        
//        customPanel.revalidate();
        
//        dmTool.roll(rollDefinition.getText());
        
        int which = Integer.parseInt(evt.getActionCommand());
        dmTool.roll(rollDefinition[which].getText());
//        System.out.println("Save action pressed.");
    }
    
    
}
