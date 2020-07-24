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
package net.rptools.dicetool;

import com.jeta.forms.components.panel.FormPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JFrame;

public class Test02 {

  public static void main(String... args) throws Exception {
    FormPanel panel =
        new FormPanel("java/net.rptools.dicetool.dicetool/ui/forms/QuickRollPanel.xml");
    AbstractButton addbtn = panel.getButton("rollD2");
    addbtn.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            System.out.println("Action done");
          }
        });

    JFrame frame = new JFrame();
    frame.add(panel);

    frame.setVisible(true);
  }
}
