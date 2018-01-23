package net.rptools.dicetool.model;

import junit.framework.TestCase;

import net.rptools.dicetool.model.Button;
import net.rptools.dicetool.model.ButtonGroup;
import net.rptools.dicetool.model.Data;
import net.rptools.dicetool.model.Variable;

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
