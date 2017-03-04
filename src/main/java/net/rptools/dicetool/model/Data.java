/* The MIT License
 * 
 * Copyright (c) 2006 David Rice
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

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicitCollection;

@XStreamAlias("net.rptools.dicetool.dicetool")
@XStreamImplicitCollection(value="buttonGroups")
public class Data extends AbstractData {
	private List<ButtonGroup> buttonGroups = new ArrayList<ButtonGroup>();

	public List<ButtonGroup> getButtonGroups() {
		return buttonGroups;
	}

	public static String toXml(Data data) {
		return getXStream().toXML(data);
	}

	public static Data fromXml(String xml) {
		return (Data) getXStream().fromXML(xml);
	}
	
	static XStream getXStream() {
		XStream xstream = new XStream();
		
		Annotations.configureAliases(xstream, Button.class);
		Annotations.configureAliases(xstream, ButtonGroup.class);
		Annotations.configureAliases(xstream, Data.class);
		Annotations.configureAliases(xstream, Function.class);
		Annotations.configureAliases(xstream, Variable.class);

		return xstream;
	}
}
