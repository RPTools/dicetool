package net.rptools.dicetool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

import com.jeta.forms.components.panel.FormPanel;

public class Test02 {

	public static void main(String... args) throws Exception {
		FormPanel panel = new FormPanel("java/net.rptools.dicetool.dicetool/ui/forms/QuickRollPanel.xml");
		 AbstractButton addbtn = panel.getButton( "rollD2" );
		 addbtn.addActionListener( new ActionListener()
		   {
		     public void actionPerformed( ActionEvent evt )
		     {
		        System.out.println("Action done");
		     }
		   });
		 
		 JFrame frame = new JFrame();
		 frame.add(panel);
		 
		 frame.setVisible(true);
	}
}
