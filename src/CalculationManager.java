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
			char currentCharacter = calculation.charAt(i);
			
			if(currentCharacter == '.') {
				currentBlock += currentCharacter;
				continue;
			}
			if(Character.isDigit(currentCharacter)) {
				currentBlock += Character.getNumericValue(currentCharacter);
				if(i == (calculation.length() - 1))
					blocks.add(currentBlock);
				continue;
			}
			
			blocks.add( i == 0 ? "0" : currentBlock);
			if(currentCharacter == 'x') {blocks.add("x");}
			if(currentCharacter == '/') {blocks.add("/");}
			if(currentCharacter == '+') {blocks.add("+");}
			if(currentCharacter == '-') {blocks.add("-");}
			currentBlock = "";
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
						continue;
					}
					blocks.set(i - 1, Double.toString(ans));				
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
						continue;
					}
					blocks.set(i - 1, Double.toString(ans));
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
