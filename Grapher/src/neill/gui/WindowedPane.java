package neill.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class WindowedPane extends JFrame implements ActionListener
{
	private Dimension size;
	
	public WindowedPane(Dimension size)
	{
		this.size = size;
		
	}
	
	public void addComponent(Component c)
	{
		add(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	
	public Dimension getSize()
	{
		return size;
	}
	
	public void setSize(Dimension newSize)
	{
		this.size = newSize;
	}
}
