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
