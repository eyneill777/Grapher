package neill.gui;

import java.awt.Dimension;
import java.awt.ScrollPane;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import neill.main.Main;

public class OutputArea extends JScrollPane
{
	Main main;
	JTextArea textArea;
	
	public OutputArea(Main main)
	{
		this.main = main;
		this.textArea = new JTextArea();
		textArea.setEditable(false);
		add(textArea);
		setViewportView(textArea);
	}
	
	public void setText(String text)
	{
		textArea.setText(text);
	}
	
	public String getText()
	{
		return textArea.getText();
	}
}
