package ca.bcit.comp3761;

import javax.swing.JFrame;

public class MazeFrame extends JFrame
{
	public MazeFrame()
	{
		setTitle("MazeTest");
		setSize(700,700);
		//MazePane mazePanel = new MazePane();
		DFSPane dfs = new DFSPane();
		add(dfs);
       
		setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		MazeFrame frame = new MazeFrame();
	}
}
