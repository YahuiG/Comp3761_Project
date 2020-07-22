package ca.bcit.comp3761;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JPanel;

public class DFSPane extends JPanel
{
	public static final int EAST = 0;
	public static final int SOUTH = 1;
	public static final int WEST = 2;
	public static final int NORTH = 3;
	public static final int ROWS = Maze.ROW;
	public static final int COLS = Maze.COL;
	
	private Maze maze;
	private Cell[] cells;
	private Stack<Integer> path;
	private ArrayList<Integer> temp;
	private int current=0;
	
	public DFSPane()
	{
		maze = new Maze();
		cells = new Cell[ROWS*COLS];
		maze.createMaze(maze.getRoots(), maze.getCells());
		cells = maze.getCells();
		path = new Stack<Integer>();
		temp = new ArrayList<Integer>();
		visit(cells,0,ROWS*COLS-1);
		
		new Thread(new Runnable()
		{
			@Override
			public void run() 
			{
				int size = getMazePath().size();
				for(int i=0; i< size; i++)
				{
					current =getMazePath().get(i);
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					DFSPane.this.repaint();
				}
			}
		}).start();
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(ROWS*WIDTH,COLS*WIDTH);
	}
	
	public void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, getWidth(), getHeight());
		for(int i =0; i< Maze.ROW*Maze.COL; i++)
		{
			maze.getCells()[i].drawCell(g);
		}
		maze.getMouses()[current].drawMouse(g);
	}

	public void visit(Cell[] cells,int c1,int c2)
	{
		path.push(c1);
		cells[c1].visited =true;
		while(!path.isEmpty())
		{
			int c = path.peek();
			cells[c].visited = true; 
			temp.add(c);
			
			if(path.peek() == c2)
				break;
			
			for(int i=0; i< 4; i++)
			{
		    	if(cells[c].walls[i] == true)
		    		continue;

		    	switch(i)
		    	{
		    	case EAST:
		    		if(c%COLS != COLS-1 && cells[c+1].visited == false)
		    		{
		    			path.push(c+1);
		    		}
		    		break;
		    	case SOUTH:
		    		if((c-c%COLS)/COLS != (ROWS-1) && cells[c+COLS].visited == false)
		    		{
		    			path.push(c+COLS);
		    		}	
		    		break;
		    	case WEST:
		    		if(c%COLS != 0 && cells[c-1].visited == false)
		    		{
		    			path.push(c-1);
		    		}
		    			
		    		break;
		    	case NORTH:
		    		if((c-c%COLS)/COLS != 0 && cells[c-COLS].visited == false)
		    		{
		    			path.push(c-COLS);
		    		}
		    		break;
		    	}	
			}
		
			if(path.peek() == c)
	    		path.pop();
		}
	}
	
	public ArrayList<Integer> getMazePath()
	{
		return temp;
	}
}
