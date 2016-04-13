package neill.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JComponent;

import neill.math.MainFunction;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;

public class Graph 
{
	double leftBound = -10, rightBound = 10, upperBound = 10, lowerBound = -10;
	JComponent drawPanel;
	ArrayList<Point> pointList = new ArrayList<Point>();
	Function drawnFunction;
	
	public Graph(JComponent drawPanel)
	{
		this.drawPanel = drawPanel;
	}
	
	private void addPoint(double independantVariableValue, Function function)
	{
		if(function != null)
		{
			try
			{
			Main.evaluator.putVariable(EvaluationOptions.independantVariable, independantVariableValue+"");
			double dependentVariableValue = Double.parseDouble(function.execute(Main.evaluator, independantVariableValue+"").getResult());
			Main.evaluator.clearVariables();
			double y = drawPanel.getHeight()-(drawPanel.getHeight()/(Math.abs(lowerBound-upperBound))*(dependentVariableValue-lowerBound));
			double x = drawPanel.getWidth()/(Math.abs(leftBound-rightBound))*(independantVariableValue-leftBound);
			pointList.add(new Point((int) x,(int) y));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void reset(Function function, Main main)
	{
		pointList.clear();
		double pixelSize = (Math.abs(leftBound)+Math.abs(rightBound))/main.getWidth();
		for(double i=leftBound;i<rightBound;i+=pixelSize)
		{
			addPoint(i, function);
		}
	}
	
	public void draw(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.fillRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());
		
		//Draw Axis
		double hLoc = drawPanel.getWidth()/(Math.abs(leftBound-rightBound))*(Math.abs(leftBound-rightBound)-Math.abs(rightBound));
		double vLoc = drawPanel.getHeight()/(Math.abs(lowerBound-upperBound))*(Math.abs(lowerBound-upperBound)-Math.abs(upperBound));
		g.setColor(Color.black);
		g.drawLine(0, (int)vLoc, drawPanel.getWidth(), (int)vLoc);
		g.drawLine((int)hLoc, 0, (int)hLoc, drawPanel.getWidth());
		
		//Draw Tick marks
		for(int x = (int) (leftBound+.5);x<=rightBound;x++)
		{
			double loc = drawPanel.getWidth()/(Math.abs(leftBound-rightBound))*(x-leftBound);
			g.drawLine((int)(loc+.5), (int)vLoc-2, (int) (loc+.5), (int)vLoc+2);
		}
		for(int y = (int) (lowerBound+.5);y<=upperBound;y++)
		{
			double loc = drawPanel.getHeight()-(drawPanel.getHeight()/(Math.abs(lowerBound-upperBound))*(y-lowerBound));
			g.drawLine((int)hLoc-2, (int)(loc+.5), (int)(hLoc+2), (int) (loc+.5));
		}
		
		//Draw Line
		g.setColor(Color.blue);
		for(int i=0;i<pointList.size()-1;i++)
		{
			g.drawLine(pointList.get(i).x, pointList.get(i).y, pointList.get(i+1).x, pointList.get(i+1).y);
		}
	}
}
