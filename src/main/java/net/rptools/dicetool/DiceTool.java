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

import java.awt.Toolkit;
import java.security.SecureRandom;
import java.util.Random;
import javax.swing.UIManager;
import net.rptools.dicetool.ui.MainFrame;

/**
 * @author drice
 *     <p>To change the template for this generated type comment go to Window - Preferences - Java -
 *     Code Generation - Code and Comments
 */
public class DiceTool {
  public static Random RANDOM = new SecureRandom();

  /** @param args the command line arguments */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
    } catch (Exception e) {
      System.err.println("Exception during look and feel setup: " + e);
    }

    //    	 Draw frame contents on resize
    Toolkit.getDefaultToolkit().setDynamicLayout(true);

    MainFrame tool = new MainFrame();
  }
}
