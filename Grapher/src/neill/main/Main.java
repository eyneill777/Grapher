package neill.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import neill.gui.OutputArea;
import neill.math.Limit;
import neill.math.MainFunction;
import net.sourceforge.jeval.*;

public class Main extends JFrame
{
	static Dimension windowDim = new Dimension(700,510);
	JTextField inputField = new JTextField();
	JComponent drawPanel = new JPanel();
	OutputArea outputArea = new OutputArea(this);
	Input input;
	Graph graph;
	public static Evaluator evaluator;
	MainFunction function;
	
	public static void main(String args[])
	{
		Main f = new Main();
		f.setSize(windowDim);
		f.setVisible(true);
	}
	
	public Main()
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		drawPanel.setBounds(0, 0, 500, 500);
		outputArea.setMinimumSize(new Dimension(200, 500));
		clearOutput();
		add(drawPanel, BorderLayout.CENTER);
		add(outputArea, BorderLayout.EAST);
		add(inputField, BorderLayout.SOUTH);
		input = new Input(this);
		inputField.addKeyListener(input);
		drawPanel.addComponentListener(input);
		graph = new Graph(drawPanel);
		evaluator = new Evaluator();
		addCustomFunctions();
		repaint();
	}
	
	public void paint(Graphics gfx)
	{
		Graphics2D g = (Graphics2D) gfx;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		outputArea.repaint();
		inputField.repaint();
		graph.draw((Graphics2D) drawPanel.getGraphics());
	}
	
	public void reset()
	{
		clearOutput();
		function = new MainFunction(inputField.getText(), this);
		repaint();
	}
	
	public Graph getGraph()
	{
		return graph;
	}
	
	private void addCustomFunctions()
	{
		evaluator.putFunction(new Limit(this));
	}
	
	public void printOutput(String output)
	{
		outputArea.setText(outputArea.getText()+output+"\n");
	}
	
	public void clearOutput()
	{
		outputArea.setText("Output Area\t\t"+"\n"+"\n");
	}
}
