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

import net.rptools.parser.ParserException;
import net.rptools.parser.VariableResolver;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author drice
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class VariablePanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = -6160835923220655321L;
    private final int NUM_VARIABLES = 10;
	private final VariableResolver resolver;
	private MainFrame dmTool;

	private Map<JTextField,JTextField> variableToNames = new HashMap<JTextField,JTextField>();
	private Map<JTextField,JTextField> variableToValues = new HashMap<JTextField,JTextField>();
	private JTextField[] variableNames;
	private JTextField[] variableValues;

	public VariablePanel(MainFrame dmTool, VariableResolver resolver) {
		this.dmTool = dmTool;
		this.resolver = resolver;

		initComponents();
	}

	private void initComponents() {
		setLayout(new GridLayout(2, 5));
		variableNames = new JTextField[NUM_VARIABLES];
		for (int j = 0; j < NUM_VARIABLES; j++) {
			JTextField jtf = new JTextField();
			jtf.addActionListener(this);
			variableNames[j] = jtf;
			add(jtf);
		}

		variableValues = new JTextField[NUM_VARIABLES];
		for (int j = 0; j < NUM_VARIABLES; j++) {
			JTextField jtf = new JTextField();
			jtf.addActionListener(this);
			variableValues[j] = jtf;
			add(jtf);
		}
		for (int j = 0; j < NUM_VARIABLES; j++) {
			variableToNames.put(variableNames[j],variableValues[j]);
			variableToValues.put(variableValues[j],variableNames[j]);
		}
	}
	
	public int getCount() {
		return NUM_VARIABLES;
	}
	
	public void setVariable(int index, String name, String value) {
		variableNames[index].setText(name);
		variableValues[index].setText(value);
	}
	
	public String getName(int index) {
		return variableNames[index].getText();
	}
	
	public String getValue(int index) {
		return variableValues[index].getText();
	}
	
	public Map<String, Double> getVariables() {
		Map<String, Double> ret = new HashMap<String, Double>();
		
		for (int i = 0; i < NUM_VARIABLES; i++) {
			String name = variableNames[i].getText();
			
			if (name != null && name.length() > 0 && !ret.containsKey(name)) {
				Double value = Double.valueOf(variableValues[i].getText());
				
				ret.put(name, value);
			}
		}
		
		return ret;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JTextField) {
			JTextField tf = (JTextField) source;
			String value = tf.getText();
			if(variableToNames.containsKey(tf)){
				JTextField valF = variableToNames.get(tf);
				try {
					resolver.setVariable(value, BigDecimal.valueOf(Integer.parseInt(valF.getText())));
				} catch (ParserException e1) {
					e1.printStackTrace();
				}
			} else if(variableToValues.containsKey(tf)) {
				JTextField namF = variableToValues.get(tf);
				try {
					resolver.setVariable(namF.getText(), BigDecimal.valueOf(Integer.valueOf(value)));
				} catch (ParserException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
