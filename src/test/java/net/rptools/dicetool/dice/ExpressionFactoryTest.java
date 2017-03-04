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
package net.rptools.dicetool.dice;

import java.util.List;
import java.util.ListIterator;

import junit.framework.TestCase;

/**
 * @author drice
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ExpressionFactoryTest extends TestCase {
	private static String[] RESULTS = new String[] {
		"roll(1, 20)+17",
		"total(1, roll(2, 6)+1)",
		"total(1, roll(2, 6)+1)",
		"total(1, roll(2, 6)+1)",
		"total(1, roll(2, 6)+1)",
		"total(1, roll(2, 6)+1)",
		"total(2, roll(1, 6))",
		"total(2, roll(1, 8))",
		"total(2, roll(1, 10))",
		"roll(1, 4)",
	};
 
	public void testGetExpressionStrings() {
		/*
		List l = ExpressionFactory.getExpressions("d20+17;5[2d6+1];d6|d8|d10;d4");
		assertEquals(RESULTS.length, l.size());
		for (ListIterator i = l.listIterator(); i.hasNext(); ) {
			assertEquals(RESULTS[i.nextIndex()], ((Expression) i.next()).getDefinition());
		}
		*/
	}
}
