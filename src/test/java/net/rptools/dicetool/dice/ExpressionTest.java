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

import junit.framework.TestCase;

/**
 * @author drice
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ExpressionTest extends TestCase {

	/**
	 * Constructor for ExpressionTest.
	 * @param arg0
	 */
	public ExpressionTest(String arg0) {
		super(arg0);
	}

	public void testExpression() {
	}

//	public void testFixFunctions() {
//		assertEquals("add<2+10,17*22>", 
//			Expression.fixFunctions("add(2+10,17*22)"));
//		assertEquals("add<10,2+17*(2+multiply<20,(2*8)+7>)>", 
//			Expression.fixFunctions("add(10,2+17*(2+multiply(20,(2*8)+7)))"));
//	}
//
//	public void testDetectParameter() {
//		assertEquals("1", 
//			Expression.detectParameter("foo<1+2,12+(17*3)>", 5, Expression.DETECT_BACKWARD));
//		assertEquals("17", 
//			Expression.detectParameter("foo<1+2,12+(17*3)>", 14, Expression.DETECT_BACKWARD));
//		assertEquals("(2+3)",
//			Expression.detectParameter("foo<(2+3)+2>", 9, Expression.DETECT_BACKWARD));
//		assertEquals("2",
//			Expression.detectParameter("2+3", 1, Expression.DETECT_BACKWARD));
//
//		assertEquals("(17*3)",
//			Expression.detectParameter("foo<1+2,12+(17*3)>", 10, Expression.DETECT_FOREWARD));
//		assertEquals("3",
//			Expression.detectParameter("2+3", 1, Expression.DETECT_FOREWARD));
//	}
//	
//	public void testConvertInfixToFunctionNotation() {
//		//System.out.println(Expression.convertInfixToFunctionNotation("2+3"));
//		System.out.println(Expression.convertInfixToFunctionNotation("1+2*3"));
//		//System.out.println(Expression.convertInfixToFunctionNotation("(1+2)*3"));
//		//System.out.println(Expression.convertInfixToFunctionNotation("foo<1+2,12+(17*3)>"));
//	}
}
