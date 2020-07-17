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
package net.rptools.dicetool.model;

import junit.framework.TestCase;

public class DataTest extends TestCase {

  public void testToFromXml() {
    Data d = new Data();
    d.getVariables().add(new Variable("var1", "value1"));
    d.getVariables().add(new Variable("var2", "value2"));
    d.setJavascript("\nfunction f(a, b) {\n    return a > b;\n}\n");

    ButtonGroup bg1 = new ButtonGroup("bg1");
    bg1.getButtons().add(new Button("b1", "d20+7", "d2+4"));
    d.getButtonGroups().add(bg1);

    ButtonGroup bg2 = new ButtonGroup("bg2");
    bg2.getButtons().add(new Button("b2a", "d20+7", "d2+4"));
    bg2.getButtons().add(new Button("b2b", "d20+7", "d2+4"));
    bg2.getButtons().add(new Button("b2c", "d20+7", "d2+4"));
    d.getButtonGroups().add(bg2);

    String s = Data.toXml(d);
    System.out.println(s);

    Data d2 = Data.fromXml(s);
    assertEquals(s, Data.toXml(d2));
  }
}
