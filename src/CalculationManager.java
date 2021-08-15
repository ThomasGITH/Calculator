import java.util.ArrayList;

public class CalculationManager {
	private String calculation = "0";
	private CalculationListener listener;
	
	public CalculationManager()
	{}
	
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
		calculation = calculation == "0" ? character : calculation + character;
		listener.updateCalculation();
	}
	
	public void deleteLastCharacter()
	{	
		calculation = calculation.substring(0, calculation.length() - 1);;
		listener.updateCalculation();
	}
	
	public void clear()
	{
		calculation = new String("0");
		listener.updateCalculation();
	}
	
	public void calculate()
	{
		//First divide all characters up into blocks of full numbers and operators
		ArrayList<String> blocks = new ArrayList<String>();
		String currentBlock = "";
		
		for(int i = 0; i < calculation.length(); i++)
		{
			if(calculation.charAt(i) == '*' || calculation.charAt(i) == '/' || calculation.charAt(i) == '+' || calculation.charAt(i) == '-')
			{
				blocks.add( i == 0 ? "0" : currentBlock);
				//blocks.add(calculation.substring(i, i + 1));
				if(calculation.charAt(i) == '*') {blocks.add("*");}
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
				if(blocks.get(i) == "*" || blocks.get(i) == "/")
				{
					float a = Float.parseFloat(blocks.get(i - 1));
					float b = Float.parseFloat(blocks.get(i + 1));
					float ans = blocks.get(i) == "*" ? (a * b) : (a / b);
					blocks.remove(i + 1);
					blocks.remove(i);
					blocks.set(i - 1, Float.toString(ans));
				}
			}
			
			for(int i = 0; i < blocks.size(); i++)
			{
				if(blocks.get(i) == "+" || blocks.get(i) == "-")
				{
					float a = Float.parseFloat(blocks.get(i - 1));
					float b = Float.parseFloat(blocks.get(i + 1));
					float ans = blocks.get(i) == "+" ? (a + b) : (a - b);
					blocks.remove(i + 1);
					blocks.remove(i);
					blocks.set(i - 1, Float.toString(ans));
				}
			}
		}
		
		calculation = Float.isFinite(Float.parseFloat(blocks.get(0))) ? blocks.get(0) : "ERROR";		
		listener.updateCalculation();
		

	}
	
}
