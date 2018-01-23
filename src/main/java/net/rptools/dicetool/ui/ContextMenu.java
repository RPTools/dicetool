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

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JOptionPane;


/**
 *
 * @author  drice
 */
public class ContextMenu extends JMenu {
    private static final long serialVersionUID = -8983330745044681975L;
    private MainFrame mainFrame;
	
    /** Creates a new instance of ContentMenu */
    public ContextMenu(MainFrame tool, String name) {
    	super(name);
    	
    	this.mainFrame = tool;
    	
		this.addSeparator();
		
		this.add(new AbstractAction("New") {
            private static final long serialVersionUID = -5599644198250553600L;

            public void actionPerformed(ActionEvent e) {
				
				String name = JOptionPane.showInputDialog(mainFrame, "Tab name", String.valueOf(mainFrame.getRollPanels().size()));
				
				// Create a new Tab
				CustomRollPanel rollPanel = new CustomRollPanel(mainFrame);
				rollPanel.setName(name);
				
				mainFrame.getRollPanels().add(rollPanel);
				
				mainFrame.getCustomRollTab().removeAll();
				for (Iterator i = mainFrame.getRollPanels().iterator(); i.hasNext(); ) {
					rollPanel = (CustomRollPanel) i.next();
					mainFrame.getCustomRollTab().add(rollPanel);
				}
			}
		});

		this.add(new AbstractAction("Delete") {
            private static final long serialVersionUID = -6862072617245621294L;

            public void actionPerformed(ActionEvent e) {
				int selectedIndex = mainFrame.getCustomRollTab().getSelectedIndex();
				
				CustomRollPanel rollPanel = (CustomRollPanel) mainFrame.getRollPanels().remove(selectedIndex);
				mainFrame.getCustomRollTab().remove(rollPanel);
			}
		});

		this.add(new AbstractAction("Rename") {
            private static final long serialVersionUID = -5890580966305343270L;

            public void actionPerformed(ActionEvent e) {
				int selectedIndex = mainFrame.getCustomRollTab().getSelectedIndex();
				
				CustomRollPanel rollPanel = (CustomRollPanel) mainFrame.getRollPanels().get(selectedIndex);
				
				String name = JOptionPane.showInputDialog(mainFrame, "Tab name", rollPanel.getName()); 
				
				rollPanel.setName(name);
				mainFrame.getCustomRollTab().removeAll();
				for (Iterator i = mainFrame.getRollPanels().iterator(); i.hasNext(); ) {
					rollPanel = (CustomRollPanel) i.next();
					mainFrame.getCustomRollTab().add(rollPanel);
				}
				
				mainFrame.getCustomRollTab().setSelectedIndex(selectedIndex);
			}
		});
		
		this.addSeparator();
		
		this.add(new AbstractAction("Move ->") {
            private static final long serialVersionUID = -4248301524310372222L;

            public void actionPerformed(ActionEvent e) {
				int selectedIndex = mainFrame.getCustomRollTab().getSelectedIndex();
				
				if (selectedIndex < mainFrame.getRollPanels().size() - 1) {
					CustomRollPanel rollPanel = (CustomRollPanel) mainFrame.getRollPanels().remove(selectedIndex);
					
					mainFrame.getRollPanels().add(selectedIndex + 1, rollPanel);
					
					mainFrame.getCustomRollTab().removeAll();
					for (Iterator i = mainFrame.getRollPanels().iterator(); i.hasNext(); ) {
						rollPanel = (CustomRollPanel) i.next();
						mainFrame.getCustomRollTab().add(rollPanel);
					}
					
					mainFrame.getCustomRollTab().setSelectedIndex(selectedIndex + 1);
				}
			}
		});
		
		this.addSeparator();
		
		this.add(new AbstractAction("Export") {
            private static final long serialVersionUID = -6958791608623693284L;

            public void actionPerformed(ActionEvent e) {
				int selectedIndex = mainFrame.getCustomRollTab().getSelectedIndex();
				
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Export tab");
				fc.setCurrentDirectory(new File("."));
				
				int result = fc.showOpenDialog(mainFrame);
				
				if (result == JFileChooser.CANCEL_OPTION) {
					
				} else if (result == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					
					mainFrame.exportPreferences(file, selectedIndex);
				}
				
			}
		});
    }
    
}
