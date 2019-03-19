package project;

/*
 * More methods might be added next iteration
 */

public class PrintFormatter {
	private String[] colorList = {"\033[0m","\033[31m","\033[32m","\033[33m","\033[34m","\033[35m","\033[36m"};
	
	public String color(String string, String color) {
		int colorIndex = 0;
		
		if (color.equals("white"))
			colorIndex = 0;
		else if (color.equals("red"))
			colorIndex = 1;
		else if (color.equals("green"))
			colorIndex = 2;
		else if (color.equals("yellow"))
			colorIndex = 3;
		else if (color.equals("blue"))
			colorIndex = 4;
		else if (color.equals("purple"))
			colorIndex = 5;
		else if (color.equals("cyan"))
			colorIndex = 6;
		else 
			throw new IllegalArgumentException("No such color exists!");
		
		StringBuilder sb = new StringBuilder();
		sb.append(colorList[colorIndex]);
		sb.append(string);
		sb.append(colorList[0]);
		
		return sb.toString();
	}
	
	public boolean isColored(String string) {
		return string.contains("\033[3");
	}
}
