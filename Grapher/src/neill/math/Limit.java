package neill.math;

import java.util.ArrayList;

import neill.main.EvaluationOptions;
import neill.main.Main;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionConstants;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionHelper;
import net.sourceforge.jeval.function.FunctionResult;

public class Limit implements Function
{
	/**
	 * Warning: Limits do not work currently
	 */
	final String name = "lim";
	Main main;
	String argument;
	String result = "0";
	
	public Limit(Main main)
	{
		this.main = main;
	}

	@Override
	public FunctionResult execute(Evaluator eval, String argument) throws FunctionException 
	{
		
		if(argument.equals(this.argument))
		{
			return new FunctionResult(result, FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
		}
		else
		{
			this.argument = argument;
			ArrayList<String> parameters = FunctionHelper.getStrings(argument, ',');
			String function = parameters.get(0);
			double limitVariableValue = Double.parseDouble(parameters.get(1));
			eval.clearVariables();
			eval.putVariable(EvaluationOptions.independantVariable, limitVariableValue+"");
			try 
			{
				result = eval.evaluate(function); //Try merely substitution the variable
			} 
			catch (EvaluationException e) 
			{
				//Put code for other types of limit evaluation here
			}
		}
		main.printOutput("lim("+argument+")");
		main.printOutput(result);
		
		return new FunctionResult(result, FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
	}

	@Override
	public String getName() 
	{
		return name;
	}
}
