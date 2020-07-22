package ca.bcit.comp3761;

import java.awt.Color;
import java.awt.Graphics;

public class Mouse 
{	
	public static final int WID = 10;
	private int x = 0;
	private int y = 0;
	
	public Mouse(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void drawMouse(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(x, y, WID,WID);	
	}
}
