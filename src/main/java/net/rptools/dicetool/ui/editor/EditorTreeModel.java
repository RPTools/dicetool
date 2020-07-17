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
package net.rptools.dicetool.ui.editor;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class EditorTreeModel extends DefaultTreeModel {

  //	private final Data data;

  public EditorTreeModel(TreeNode root) {
    super(root);
  }

  //	public EditorTreeModel(Data data) {
  //		super(TreeNode);
  ////		TreeNode node = new TreeNode();
  //		this.data = data;
  //	}

  public Object getRoot() {
    // TODO Auto-generated method stub
    return null;
  }

  public Object getChild(Object parent, int index) {
    // TODO Auto-generated method stub
    return null;
  }

  public int getChildCount(Object parent) {
    // TODO Auto-generated method stub
    return 0;
  }

  public boolean isLeaf(Object node) {
    // TODO Auto-generated method stub
    return false;
  }

  public void valueForPathChanged(TreePath path, Object newValue) {
    // TODO Auto-generated method stub

  }

  public int getIndexOfChild(Object parent, Object child) {
    // TODO Auto-generated method stub
    return 0;
  }

  public void addTreeModelListener(TreeModelListener l) {
    // TODO Auto-generated method stub

  }

  public void removeTreeModelListener(TreeModelListener l) {
    // TODO Auto-generated method stub

  }
}
