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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import net.rptools.common.expression.ExpressionParser;
import net.rptools.common.expression.Result;
import net.rptools.dicetool.DiceTool;
import net.rptools.lib.FileUtil;
import net.rptools.lib.image.ImageUtil;
import net.rptools.lib.swing.AboutDialog;
import net.rptools.parser.MapVariableResolver;
import net.rptools.parser.ParserException;
import net.rptools.parser.VariableResolver;

/** @author drice */
public class MainFrame extends JFrame {
  private static final long serialVersionUID = -5149326446698624828L;

  private static String version;

  private final JTabbedPane customRollTab;
  private final JLabel statusBar;

  private final DicePanel dicePanel;
  private final ResultPanel resultPanel;
  private final VariablePanel variablePanel;

  private final AboutDialog aboutDialog;

  private List<CustomRollPanel> rollPanels = new ArrayList<>();

  private final List<RollListener> rollListeners = new ArrayList<>();

  private static final String EXPRESSION_SYNTAX =
      "bags (non cumulative rolls):\n"
          + "  \\d+{ <EG> } - repeater translated into <EG> [; <EG> ]*.\n"
          + "  <EG> [; <EG> ]*  - Roll each expression group in order\n\n"
          + "expression groups <EG> (cumulative rolls):\n"
          + "  \\d+[ <X> ]  - repeater translated into <X> [: <X> ]*.\n"
          + "  <X> [: <X> ]* - Roll each expression and keep a running total\n\n"
          + "expression <X>:\n"
          + "  <DIE> | <CONSTANT> [<+> | <-> | <*> <DIE> | <CONSTANT>]* = <X>\n"
          + "  \\d*[dD]\\d+ = <DIE>\n"
          + "  \\d+ = <CONSTANT>";

  /** Creates a new instance of DMTool */
  public MainFrame() {
    super("Dice Tool");

    int numCustomRollPanels = getNumRollPanels();

    dicePanel = new DicePanel(this);
    resultPanel = new ResultPanel(this);

    for (int i = 0; i < numCustomRollPanels; i++) {
      CustomRollPanel rollPanel = new CustomRollPanel(this);
      rollPanels.add(rollPanel);

      loadRollPanelPreferences(i, rollPanel);
    }

    String credits = "";
    String version;
    Image logo = null;
    try {
      credits =
          new String(FileUtil.loadResource("java/net.rptools.dicetool.dicetool/credits.html"));
      version = MainFrame.getVersion();
      credits = credits.replace("%VERSION%", version);
      logo = ImageUtil.getImage("net/rptools/lib/image/rptools-logo.png");

    } catch (Exception ioe) {
      ioe.printStackTrace();
    }
    aboutDialog = new AboutDialog(this, logo, credits);

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    menu.setMnemonic('F');
    menuBar.add(menu);

    menu.add(
        new AbstractAction("Load") {
          private static final long serialVersionUID = 5894464716901662587L;

          public void actionPerformed(ActionEvent e) {
            loadPreferences();
          }
        });

    menu.add(
        new AbstractAction("Save") {
          private static final long serialVersionUID = 2069214913432460961L;

          public void actionPerformed(ActionEvent e) {
            savePreferences();
          }
        });

    menu.add(
        new AbstractAction("Exit") {
          private static final long serialVersionUID = 1934074661129316121L;

          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });

    JMenu contextMenu = new ContextMenu(this, "Tab");
    contextMenu.setMnemonic('T');
    menuBar.add(contextMenu);

    menu = new JMenu("Data");
    menu.setMnemonic('D');
    menuBar.add(menu);

    menu.add(
        new AbstractAction("Clear") {
          private static final long serialVersionUID = -1423278621970926792L;

          public void actionPerformed(ActionEvent e) {
            resultPanel.clearList();
          }
        });

    menu.add(
        new AbstractAction("Statistics") {
          private static final long serialVersionUID = -7511992886537497232L;

          public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MainFrame.this, "Random: " + "\nAverage: ");
          }
        });

    menu = new JMenu("Help");
    menu.setMnemonic('H');
    menuBar.add(menu);

    menu.add(
        new AbstractAction("Syntax") {
          private static final long serialVersionUID = -3156673526417487961L;

          public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MainFrame.this, EXPRESSION_SYNTAX);
          }
        });

    menu.add(
        new AbstractAction("About") {
          private static final long serialVersionUID = 7099281595551261802L;

          public void actionPerformed(ActionEvent e) {
            aboutDialog.setVisible(true);
          }
        });

    variablePanel = new VariablePanel(this);
    loadVariablePreferences();

    this.setJMenuBar(menuBar);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.add(dicePanel);

    customRollTab = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    for (CustomRollPanel rollPanel : rollPanels) {
      customRollTab.add(rollPanel);
    }

    panel.add(customRollTab);

    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, resultPanel);
    splitPane.setResizeWeight(0);
    splitPane.setOneTouchExpandable(true);
    splitPane.setContinuousLayout(true);

    statusBar = new JLabel("");
    resultSelectionChanged(0);

    JPanel vertPanel = new JPanel();
    this.getContentPane().add(vertPanel);

    GridBagConstraints con = new GridBagConstraints();
    GridBagLayout layout = new GridBagLayout();
    vertPanel.setLayout(layout);

    // ---------------------------------------------------------------------
    // Add the split pane to the main window
    // ---------------------------------------------------------------------
    con.gridx = 0;
    con.gridy = 0;
    con.fill = GridBagConstraints.BOTH;

    con.gridwidth = GridBagConstraints.RELATIVE;
    con.gridheight = 1;

    con.anchor = GridBagConstraints.FIRST_LINE_START;

    con.weightx = 1.0;
    con.weighty = 1.0;

    vertPanel.add(splitPane, con);

    // ---------------------------------------------------------------------
    // Add the variable panel to the main window
    // ---------------------------------------------------------------------

    con.gridy = 1;
    con.weighty = 0.0;

    vertPanel.add(variablePanel, con);

    // ---------------------------------------------------------------------
    // Add the status panel to the main window
    // ---------------------------------------------------------------------
    JPanel statusPanel = new JPanel();
    statusPanel.add(statusBar);
    statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
    statusPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

    con.gridy = 2;

    vertPanel.add(statusPanel, con);

    this.setSize(400, 645);

    loadPreferences();
    this.setVisible(true);

    new WindowListener(this);
  }

  private String format(Result result) {

    Object value = result.getValue();

    StringBuilder sb = new StringBuilder(64);
    sb.append(result.getExpression()).append(" = ");

    String detailExpression = result.getDetailExpression();
    if (!detailExpression.equals(value.toString())) {
      sb.append("(").append(detailExpression).append(") = ");
    }

    sb.append(value);
    if (result.getDescription() != null) {
      sb.append(" // ").append(result.getDescription());
    }
    return sb.toString();
  }

  public void roll(String definition) {
    ExpressionParser parser = new ExpressionParser();

    VariableResolver resolver = new MapVariableResolver();
    try {

      for (int i = 0; i < variablePanel.getCount(); i++) {
        try {
          resolver.setVariable(
              variablePanel.getName(i),
              BigDecimal.valueOf(Integer.parseInt(variablePanel.getValue(i))));
        } catch (ParserException e) {
          e.printStackTrace();
        }
      }

      for (RollListener l : rollListeners) {
        l.rolled(Collections.singletonList(format(parser.evaluate(definition, resolver))));
      }
    } catch (ParserException e) {
      JOptionPane.showMessageDialog(this, e.getMessage());
    }

    for (int i = 0; i < variablePanel.getCount(); i++) {
      try {
        String var = variablePanel.getName(i);
        Object val = resolver.getVariable(var);
        if (val != null) variablePanel.setVariable(i, var, val.toString());
        resolver.setVariable(
            variablePanel.getName(i),
            BigDecimal.valueOf(Integer.parseInt(variablePanel.getValue(i))));
      } catch (ParserException e) {
        e.printStackTrace();
      }
    }
  }

  public static String getVersion() {
    if (version == null) {
      version = "DEVELOPMENT";
      try {
        if (MainFrame.class.getClassLoader().getResource("net/rptools/dicetool/version.txt")
            != null) {
          version = new String(FileUtil.loadResource("net/rptools/dicetool/version.txt"));
        }
      } catch (IOException ioe) {
        version = "CAN'T FIND VERSION FILE";
      }
    }

    return version;
  }

  public void registerRollListener(RollListener l) {
    rollListeners.add(l);
  }

  public int getNumRollPanels() {
    Preferences pkg_prefs = Preferences.userNodeForPackage(DiceTool.class);
    Preferences prefs = pkg_prefs.node("customRolls");

    try {
      return prefs.childrenNames().length;
    } catch (BackingStoreException e) {
      return 1;
    }
  }

  public void loadRollPanelPreferences(int index, CustomRollPanel rollPanel) {
    Preferences pkg_prefs = Preferences.userNodeForPackage(DiceTool.class);

    Preferences prefs = pkg_prefs.node("customRolls");
    String name = prefs.get("TabName" + index, String.valueOf(index));

    rollPanel.setName(name);

    prefs = pkg_prefs.node("customRolls/Tab" + index);

    for (int i = 0; i < rollPanel.getCount(); i++) {
      String buttonLabel = prefs.get("button" + i, "Roll");
      String definition = prefs.get("definition" + i, "");

      rollPanel.setButtonLabel(i, buttonLabel);
      rollPanel.setDefinition(i, definition);
    }
  }

  public void loadVariablePreferences() {
    Preferences pkg_prefs = Preferences.userNodeForPackage(DiceTool.class);

    Preferences prefs = pkg_prefs.node("customVariables");

    for (int i = 0; i < variablePanel.getCount(); i++) {
      String name = prefs.get("name" + i, "var" + i);
      String value = prefs.get("value" + i, "0");

      variablePanel.setVariable(i, name, value);
    }
  }

  public void loadPreferences() {
    Preferences pkg_prefs = Preferences.userNodeForPackage(DiceTool.class);

    int diceSelection = pkg_prefs.getInt("diceSelection", 5);
    dicePanel.setSelectedItem(diceSelection);
  }

  public void savePreferences() {
    Preferences pkg_prefs = Preferences.userNodeForPackage(DiceTool.class);

    pkg_prefs.putInt("diceSelection", dicePanel.getSelectedItem());

    saveRollPanelPreferences(pkg_prefs);
    saveVariablePreferences(pkg_prefs);
  }

  public void saveRollPanelPreferences(Preferences rootPrefs) {
    Preferences prefs = rootPrefs.node("customRolls");
    try {
      prefs.removeNode();
    } catch (BackingStoreException e) {
      // dump it, this is bad.
    }
    prefs = rootPrefs.node("customRolls");

    prefs.putBoolean("tabbed", true);

    int index = 0;
    for (CustomRollPanel rollPanel : rollPanels) {

      String name = rollPanel.getName();
      prefs.put("TabName" + index, name);

      Preferences tabPrefs = prefs.node("Tab" + index);

      for (int j = 0; j < rollPanel.getCount(); j++) {
        String buttonLabel = rollPanel.getButtonLabel(j);
        String definition = rollPanel.getDefinition(j);

        tabPrefs.put("button" + j, buttonLabel);
        tabPrefs.put("definition" + j, definition);
      }
    }
  }

  public void saveVariablePreferences(Preferences rootPrefs) {
    Preferences prefs = rootPrefs.node("customVariables");

    for (int i = 0; i < variablePanel.getCount(); i++) {

      String name = variablePanel.getName(i);
      String value = variablePanel.getValue(i);

      prefs.put("name" + i, name);
      prefs.put("value" + i, value);
    }
  }

  public void exportPreferences(File file, int tabIndex) {
    Preferences pkg_prefs = Preferences.userNodeForPackage(DiceTool.class);
    Preferences prefs = pkg_prefs.node("customRolls");
    Preferences tabPrefs = prefs.node("Tab" + tabIndex);

    try {
      tabPrefs.exportSubtree(new FileOutputStream(file));
    } catch (IOException | BackingStoreException e) {
      e.printStackTrace(System.err);
    }
  }

  /*	public void convertPreferences() {
  		Preferences pkg_prefs = Preferences.userNodeForPackage(DMTool.class);
  		Preferences prefs = pkg_prefs.node("customRolls");

  		boolean tabbed = prefs.getBoolean("tabbed", false);

  		if (!tabbed) {
  			String[] labels = new String[20];
  			String[] definitions = new String[20];
  			for (int i = 0; i < 20; i++) {
  				labels[i] = prefs.get("button" + i, "Roll");
  				definitions[i] = prefs.get("definition" + i, "");
  			}

  			try {
  				prefs.removeNode();
  			} catch (BackingStoreException e) {
  				// dump it, this is bad.
  			}
  			prefs = pkg_prefs.node("customRolls");

  			prefs.putBoolean("tabbed", true);
  			prefs.put("TabName0", "*");

  			prefs = prefs.node("Tab0");

  			for (int i = 0; i < 20; i++) {
  				prefs.put("button" + i, labels[i]);
  				prefs.put("definition" + i, definitions[i]);
  			}
  		}
  	}
  */
  public void resultSelectionChanged(double sum) {
    if (sum == 0) {
      statusBar.setText("total: <select rows in the result table>");
    } else {
      statusBar.setText("total: " + sum);
    }
  }

  /** @return Returns the customRollTab. */
  public JTabbedPane getCustomRollTab() {
    return customRollTab;
  }
  /** @return Returns the rollPanels. */
  public List<CustomRollPanel> getRollPanels() {
    return rollPanels;
  }
}
