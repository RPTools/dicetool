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

/**
 *
 * @author  drice
 */
public class DicePanel extends javax.swing.JPanel {
    private static final long serialVersionUID = -8627668679690782352L;

    private static final String[] NUMBER_SELECTIONS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "15", "20", "50", "100"};
    
    private MainFrame dmTool;
    
    /** Creates new form DicePanel2 */
    public DicePanel(MainFrame dmTool) {
        this.dmTool = dmTool;
        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
        numberSelect = new javax.swing.JComboBox(NUMBER_SELECTIONS);
        numberSelect.setSelectedItem(NUMBER_SELECTIONS[4]);
        jPanel2 = new javax.swing.JPanel();
        d2 = new javax.swing.JButton();
        d3 = new javax.swing.JButton();
        d4 = new javax.swing.JButton();
        d6 = new javax.swing.JButton();
        d8 = new javax.swing.JButton();
        d10 = new javax.swing.JButton();
        d12 = new javax.swing.JButton();
        d20 = new javax.swing.JButton();
        d100 = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));

        numberSelect.setMaximumSize(new java.awt.Dimension(32767, 35));
        add(numberSelect);

        jPanel2.setLayout(new java.awt.GridLayout(3, 3));

        jPanel2.setMaximumSize(new java.awt.Dimension(200, 200));
        d2.setFont(new java.awt.Font("Dialog", 0, 10));
        d2.setText("d2");
        d2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dieActionPerformed(evt);
            }
        });

        jPanel2.add(d2);

        d3.setFont(new java.awt.Font("Dialog", 0, 10));
        d3.setText("d3");
        d3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dieActionPerformed(evt);
            }
        });

        jPanel2.add(d3);

        d4.setFont(new java.awt.Font("Dialog", 0, 10));
        d4.setText("d4");
        d4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dieActionPerformed(evt);
            }
        });

        jPanel2.add(d4);

        d6.setFont(new java.awt.Font("Dialog", 0, 10));
        d6.setText("d6");
        d6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dieActionPerformed(evt);
            }
        });

        jPanel2.add(d6);

        d8.setFont(new java.awt.Font("Dialog", 0, 10));
        d8.setText("d8");
        d8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dieActionPerformed(evt);
            }
        });

        jPanel2.add(d8);

        d10.setFont(new java.awt.Font("Dialog", 0, 10));
        d10.setText("d10");
        d10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dieActionPerformed(evt);
            }
        });

        jPanel2.add(d10);

        d12.setFont(new java.awt.Font("Dialog", 0, 10));
        d12.setText("d12");
        d12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dieActionPerformed(evt);
            }
        });

        jPanel2.add(d12);

        d20.setFont(new java.awt.Font("Dialog", 0, 10));
        d20.setText("d20");
        d20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dieActionPerformed(evt);
            }
        });

        jPanel2.add(d20);

        d100.setFont(new java.awt.Font("Dialog", 0, 10));
        d100.setText("d100");
        d100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dieActionPerformed(evt);
            }
        });

        jPanel2.add(d100);

        add(jPanel2);

    }
    
    private void dieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dieActionPerformed
        String cmd = evt.getActionCommand();
        
        try {
            int num = Integer.parseInt((String) numberSelect.getSelectedItem());
            
            StringBuffer sb = new StringBuffer(256);
            sb.append(num).append(cmd);

            dmTool.roll(sb.toString());
        } catch (NumberFormatException ex) {
            throw new IllegalStateException("Number format exception occurred... data must be bad.");
        }
    }//GEN-LAST:event_dieActionPerformed
    
    public int getSelectedItem() {
        return Integer.parseInt((String) numberSelect.getSelectedItem());
    }
    
    public void setSelectedItem(int selectedItem) {
        numberSelect.setSelectedItem(String.valueOf(selectedItem));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton d8;
    private javax.swing.JButton d2;
    private javax.swing.JButton d12;
    private javax.swing.JButton d3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton d10;
    private javax.swing.JButton d20;
    private javax.swing.JButton d100;
    private javax.swing.JButton d6;
    private javax.swing.JButton d4;
    private javax.swing.JComboBox numberSelect;
    // End of variables declaration//GEN-END:variables
    
}
