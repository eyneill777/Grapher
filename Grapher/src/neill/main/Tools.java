package neill.main;

public class Tools 
{
	public static boolean isNumber(char character)
	{
		if(character >='0' && character <= '9')
			return true;
		else
			return false;
	}
	
	public static boolean isLowerCaseCharacter(char character)
	{
		if(character >='a' && character <= 'z')
			return true;
		else
			return false;
	}
	
	public static String formatStringVariable(String function, String variable)
	{
		return function.replaceAll(variable, "#{"+variable+"}");
	}
}
