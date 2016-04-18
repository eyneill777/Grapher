package neill.math;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.events.EventException;

import neill.main.EvaluationOptions;
import neill.main.Main;
import neill.main.Tools;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.FunctionConstants;
import net.sourceforge.jeval.function.FunctionException;
import net.sourceforge.jeval.function.FunctionResult;

public class MainFunction implements Function
{
	String functionString;
	Map<String, String> firstOrderFunctions = new HashMap<String, String>();
	
	public MainFunction(String functionString, Main main) 
	{
		fillFirstOrderFunctions();
		this.functionString = Tools.formatStringVariable(functionString, EvaluationOptions.independantVariable);
		main.getGraph().reset(this, main);
	}

	@Override
	public FunctionResult execute(Evaluator eval, String independentVariableValue) throws FunctionException 
	{
		try {
			return new FunctionResult(eval.evaluate(functionString),FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
		} 
		catch (EvaluationException e) 
		{
			try{
				eval.putVariable(EvaluationOptions.independantVariable, independentVariableValue);
				return new FunctionResult(eval.evaluate(functionString),FunctionConstants.FUNCTION_RESULT_TYPE_NUMERIC);
			}
			catch (EvaluationException e1) {
				System.out.println("Error at "+EvaluationOptions.independantVariable+" equals "+independentVariableValue);
				e1.printStackTrace();
				return null;
			}
		}
	}
	
	private void fillFirstOrderFunctions()
	{
		firstOrderFunctions.put("Deriv", EvaluationOptions.independantVariable);
	}
	
	private void doFirstOrderFunctions()
	{
		
	}

	@Override
	public String getName() 
	{ 
		return EvaluationOptions.dependantVariable;
	}
	
	public String getFunctionString()
	{
		return functionString;
	}
}
