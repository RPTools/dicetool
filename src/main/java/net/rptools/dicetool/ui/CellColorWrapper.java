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

import java.awt.Color;

/**
 * @author drice
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CellColorWrapper {
	private Object wrappedObject;
	private Color background;
	private Color foreground;
	
	public CellColorWrapper(Object o) {
		this(o, Color.WHITE, Color.BLACK);
	}
	
	public CellColorWrapper(Object o, Color foreground, Color background) {
		this.wrappedObject = o;
		this.background = background;
		this.foreground = foreground;
	}

	public String toString() {
		if (wrappedObject == null) return null;

		return wrappedObject.toString();
	}

	public Color getBackground() {
		return background;
	}

	public Color getForeground() {
		return foreground;
	}

	public Object getWrappedObject() {
		return wrappedObject;
	}

	public void setBackground(Color color) {
		background = color;
	}

	public void setForeground(Color color) {
		foreground = color;
	}

	public void setWrappedObject(Object object) {
		wrappedObject = object;
	}
}
