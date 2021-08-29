import java.util.ArrayList;

public class CalculationManager {
	private String calculation = "0";
	private CalculationListener listener;
	
	public void addCalculationListener(CalculationListener listener)
	{
		this.listener = listener;
		listener.updateCalculation();
	}
	
	public String getCalculation()
	{
		return calculation;
	}
	
	public void addCharacter(String character)
	{
		calculation = (calculation == "0" 
				&& character != "."
				&& character != "+"
				&& character != "-"
				&& character != "x"
				&& character != "/"
				) ? character : calculation + character;
		listener.updateCalculation();
	}
	
	public void deleteLastCharacter()
	{	
		calculation = calculation.length() > 1 ? calculation.substring(0, calculation.length() - 1) : "0";
		listener.updateCalculation();
	}
	
	public void clear()
	{
		calculation = "0";
		listener.updateCalculation();
	}
	
	public void calculate()
	{
		ArrayList<String> blocks = new ArrayList<String>();
		String currentBlock = "";
		
		try {
		for(int i = 0; i < calculation.length(); i++)
		{
			if(calculation.charAt(i) == 'x'
			|| calculation.charAt(i) == '/' 
			|| calculation.charAt(i) == '+' 
			|| calculation.charAt(i) == '-')
			{
				blocks.add( i == 0 ? "0" : currentBlock);
				if(calculation.charAt(i) == 'x') {blocks.add("x");}
				if(calculation.charAt(i) == '/') {blocks.add("/");}
				if(calculation.charAt(i) == '+') {blocks.add("+");}
				if(calculation.charAt(i) == '-') {blocks.add("-");}
				
				currentBlock = "";
			}
			else
			{
				if(calculation.charAt(i) != '.') {
					currentBlock += Character.getNumericValue(calculation.charAt(i));
				}
				else {
					currentBlock += calculation.charAt(i);
				}
				if(i == (calculation.length() - 1))
					blocks.add(currentBlock);
			}
		}
		
		while(blocks.size() > 1)
		{
			for(int i = 0; i < blocks.size(); i++)
			{
				if(blocks.get(i) == "x" || blocks.get(i) == "/")
				{
					double a = Double.parseDouble(blocks.get(i - 1));
					double b = Double.parseDouble(blocks.get(i + 1));
					double ans = blocks.get(i) == "x" ? (a * b) : (a / b);
					blocks.remove(i + 1);
					blocks.remove(i);
										
					if(Math.floor(ans) == ans)
					{
						blocks.set(i - 1, Integer.toString((int)ans));
					}
					else
					{
						blocks.set(i - 1, Double.toString(ans));
					}					
				}
			}
			
			for(int i = 0; i < blocks.size(); i++)
			{
				if(blocks.get(i) == "+" || blocks.get(i) == "-")
				{
					double a = Double.parseDouble(blocks.get(i - 1));
					double b = Double.parseDouble(blocks.get(i + 1));
					double ans = blocks.get(i) == "+" ? (a + b) : (a - b);
					blocks.remove(i + 1);
					blocks.remove(i);
					
					if(Math.floor(ans) == ans)
					{
						blocks.set(i - 1, Integer.toString((int)ans));
					}
					else
					{
						blocks.set(i - 1, Double.toString(ans));
					}
					
				}
			}
		}
		
		if(Double.parseDouble(blocks.get(0)) == 2147483647) {
		calculation = "Error: Cannot divide by zero";
		listener.updateCalculation();
		calculation = "0";
		return;
		}
		
		calculation = blocks.get(0);		
		listener.updateCalculation();
		}
		catch(Exception e)
		{
			calculation = "Error: Invalid input";
			System.err.println("Caught exception: " + e);
			listener.updateCalculation();
			calculation = "0";
		}
		

	}
	
}
