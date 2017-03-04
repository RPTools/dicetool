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

import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractListModel;

import net.rptools.dicetool.resultset.Row;
import net.rptools.dicetool.resultset.Row;


/**
 * 
 * @author drice
 */
public class RowListModel extends AbstractListModel {
    private static final long serialVersionUID = -4240626548954778007L;

    private static final int MAX_ROWS = 1000;

    private List<Row> rows = new LinkedList<Row>();

    /** Creates a new instance of DieGroupTableModel */
    public RowListModel() {
    }

    public Row getRow(int rowIndex) {
        Object o = rows.get(rowIndex);

        if (o == null)
            return null;

        Row row = (Row) o;

        return row;
    }
    
    public void addRow(Row row) {
    	rows.add(row);
    	if (trimRows()) {
    		fireContentsChanged(this, -1, -1);
    	} else {
    		fireContentsChanged(this, rows.size() - 1, rows.size() - 1);
    	}
    }
    
    public void addRows(Row... rs) {
    	for (Row r : rs)
    		this.rows.add(r);

    	if (trimRows()) {
    		fireContentsChanged(this, -1, -1);
    	} else {
    		fireContentsChanged(this, rows.size() - rs.length, rows.size() - 1);
    	}
    }
    
    private boolean trimRows() {
    	if (rows.size() > MAX_ROWS) {
    		int trimCount = rows.size() - MAX_ROWS;

	    	for (int i = 0; i < trimCount; i++)
	    		rows.remove(0);
	    	
	    	return true;
    	}
    	
    	return false;
    }

    public void clear() {
        rows.clear();

        fireContentsChanged(this, -1, -1);
    }

	public int getSize() {
		return rows.size();
	}

	public Object getElementAt(int index) {
		return rows.get(index);
	}
}
