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

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

/**
 * @author drice
 *     <p>To change the template for this generated type comment go to Window - Preferences - Java -
 *     Code Generation - Code and Comments
 */
public class Test01 {
  public static void main(String[] args) throws Exception {
    Context cx = Context.enter();
    try {
      Scriptable scope = cx.initStandardObjects();
      cx.evaluateReader(
          scope, new InputStreamReader(new FileInputStream("src/functions.js")), "<cmd>", 1, null);

      Object[] ids = scope.getIds();
      for (int i = 0; i < ids.length; i++) {
        System.out.println("ID: " + ids[i]);
      }

      Object fObj = scope.get("registerFunctions", scope);
      if (!(fObj instanceof Function)) {
        System.out.println("f is undefined or not a function.");
      } else {
        Object functionArgs[] = {};
        Function f = (Function) fObj;
        Object result = f.call(cx, scope, scope, functionArgs);

        System.out.println(result.getClass().getName());

        Map m = (Map) Context.toType(result, Map.class);

        Function f2 = (Function) m.get("foo");

        // Function f2 = (Function) result;
        System.out.println(f2.call(cx, scope, scope, functionArgs));
      }
      //			Object result = func.call(cx, scope, func, new Object[] { new
      // Integer(10) });
      //			System.err.println(cx.toString(result));
    } finally {
      Context.exit();
    }
  }
}
