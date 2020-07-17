/* The MIT License
 *
 * Copyright (c) 2004,2005,2006 David Rice
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

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author drice
 *     <p>To change the template for this generated type comment go to Window - Preferences - Java -
 *     Code Generation - Code and Comments
 */
public class VariablePanel extends JPanel {
  private static final long serialVersionUID = -6160835923220655321L;
  private final int NUM_VARIABLES = 10;
  private MainFrame dmTool;

  private Map<JTextField, JTextField> variableToNames = new HashMap<JTextField, JTextField>();
  private Map<JTextField, JTextField> variableToValues = new HashMap<JTextField, JTextField>();
  private JTextField[] variableNames;
  private JTextField[] variableValues;

  public VariablePanel(MainFrame dmTool) {
    this.dmTool = dmTool;

    initComponents();
  }

  private void initComponents() {
    setLayout(new GridLayout(2, 5));
    variableNames = new JTextField[NUM_VARIABLES];
    for (int j = 0; j < NUM_VARIABLES; j++) {
      JTextField jtf = new JTextField();
      variableNames[j] = jtf;
      add(jtf);
    }

    variableValues = new JTextField[NUM_VARIABLES];
    for (int j = 0; j < NUM_VARIABLES; j++) {
      JTextField jtf = new JTextField();
      variableValues[j] = jtf;
      add(jtf);
    }
    for (int j = 0; j < NUM_VARIABLES; j++) {
      variableToNames.put(variableNames[j], variableValues[j]);
      variableToValues.put(variableValues[j], variableNames[j]);
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
}
