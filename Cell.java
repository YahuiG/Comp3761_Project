package ca.bcit.comp3761;

import java.awt.Graphics;

public class Cell 
{
	public static final int DIRECTION_NUM =4;
	public static final int EAST = 0;
	public static final int SOUTH = 1;
	public static final int WEST = 2;
	public static final int NORTH = 3;
	public static final int WIDTH = 10;
	
	public boolean[] walls=new boolean[]{true,true,true,true};
    public int x=0;
	public int y=0;
	boolean visited = false; 
	
	public Cell(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	void drawCell(Graphics g) 
	{
		int x1=0,y1=0,x2=0,y2=0;
	    for(int i=0; i<4; i++)
	    {
	    	// 没有墙，continue
	    	if(walls[i] == false)
	    		continue;
	    	
	    	// 有墙画线
	    	switch(i)
	    	{
	    	case EAST:
	    		x1 = this.x+WIDTH;
	    		y1 = this.y;
	    		x2 = x1;
	    		y2 = y1+WIDTH;
	    		break;
	    	case SOUTH:
	    		x1 = this.x;
	    		y1 = this.y+WIDTH;
	    		x2 = x1+WIDTH;
	    		y2 = y1;
	    		break;
	    	case WEST:
	    		x1 = this.x;
	    		y1 = this.y;
	    		x2 = x1;
	    		y2 = y1+WIDTH;
	    		break;
	    	case NORTH:
	    		x1 = this.x;
	    		y1 = this.y;
	    		x2 = x1+WIDTH;
	    		y2 = y1;
	    		break;
	    	}
	    	g.drawLine(x1, y1, x2, y2);	
	    }
	}    	
}
