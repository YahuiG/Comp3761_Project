package ca.bcit.comp3761;

import java.util.Random;

public class Maze 
{
	public static final int ROW = 60;
	public static final int COL = 60;
	public static final int DIRECTION = 4;
	public static final int EAST = 0;
	public static final int SOUTH = 1;
	public static final int WEST = 2;
	public static final int NORTH = 3;
	public static final int WID =10;
	
	private int[] root;
	private Cell[] cells;
	private Mouse[] mouses; 

	public Maze()
	{
	    root = new int[ROW*COL];
		cells = new Cell[ROW*COL];
		mouses = new Mouse[ROW*COL];
		
		int zeroX= 30,zeroY = 30;
		for(int i=0; i< ROW*COL; i++)
		{
			int x = zeroX + (i%COL)*WID;
	        int y = zeroY + ((i-i%COL)/COL)*WID;
			Cell cell = new Cell(x, y);
			Mouse mouse = new Mouse(x, y);
			cells[i] = cell;
			mouses[i] = mouse;
		}
	}
	

	public void initMaze(int roots[], Cell cells[])
	{

		for(int i=0; i<ROW*COL; i++)
		{
			for(int k=0; k<DIRECTION; k++)
				cells[i].walls[k] = true;
		}

		cells[0].walls[WEST] = false;
		cells[ROW*COL-1].walls[EAST] = false;
		
		for(int i=0; i<ROW*COL; i++)
		{
			roots[i] = -1;
		}
	}

	public void createMaze(int roots[], Cell cells[])
	{
		int direction, c1;
		int c2 = -1;
		int CELL_NUM = ROW*COL;
		initMaze(roots,cells);
		Random rand = new Random();
		while(true)
		{
			c1 = rand.nextInt(CELL_NUM);
			direction = rand.nextInt(DIRECTION);
			switch(direction)
			{
			case EAST:
			{
				if(c1%COL == COL-1) c2 = -1;
				else c2 = c1+1;
				break;
			}
			case SOUTH:
			{
				if((c1-c1%COL)/COL == (ROW-1)) c2 =-1;
				else c2 = c1+COL;
				break;
			}
			case WEST:
			{
				if(c1%COL == 0) c2 = -1;
				else c2 = c1-1;
				break;
			}
			case NORTH:
			{
				if((c1-c1%COL)/COL == 0) c2 =-1;
				else c2 = c1-COL;
				break;
			}
			default:
				System.out.println("Romdon number is error!!!");
				System.exit(0);
				break;
			}
			
			if(c2 < 0) continue;
			
			if(isConnect(roots,c1,c2)) continue;
			else 
			{
				removeDoors(roots,c1,c2);
				cells[c1].walls[direction] = false;
				cells[c2].walls[(direction+2)%DIRECTION] = false;
			}
			
			if(isConnect(roots,0,CELL_NUM-1)) break;
		}
	}
	
	public boolean isConnect(final int roots[],int c1, int c2)
	{
		while(roots[c1] >= 0) c1 = roots[c1];
		while(roots[c2] >= 0) c2 = roots[c2];
		if(c1 == c2) 
			return true;
		else
			return false;
	}
	
	public void removeDoors(int roots[],int c1, int c2)
	{
		while(roots[c1] >= 0) c1 = roots[c1];
		while(roots[c2] >= 0) c2 = roots[c2];

		if(roots[c1] > roots[c2])
			roots[c1] = c2;
		else
		{
			if(roots[c1] == roots[c2])
				roots[c1]--;
			roots[c2] =c1;
		}
	}
	

	public int[] getRoots()
	{
		return root;
	}

	public Cell[] getCells()
	{
		return cells;
	}
	
	public Mouse[] getMouses()
	{
		return mouses;
	}
	
}
