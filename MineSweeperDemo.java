package prMineSweeper;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MineSweeperDemo {
	public static void main(String[] args){
		int size=10;
		int[][] cells = new int[][] {{1,1,1,2,9,9,9,1,0,0},
			{9,1,1,9,4,9,4,2,1,0},
			{1,2,2,2,3,2,3,9,1,0},
			{0,1,9,2,2,9,2,1,1,0},
			{0,1,1,2,9,2,1,0,0,0},
			{1,1,0,1,1,1,0,0,1,1},
			{9,1,0,0,0,0,0,0,1,9},
			{2,2,1,1,1,0,0,0,1,1},
			{9,1,1,9,1,0,0,0,1,1},
			{1,1,1,1,1,0,0,0,1,9}
		};
		boolean[][] visibility = new boolean[size][size];
		MineSweeper minesweeper = new MineSweeper(cells,visibility);
		MineSweeperView mineSweeperView = new MineSweeperPanel1(minesweeper);
		
		ActionListener mineSweeperCtr = new MineSweeperCtr(mineSweeperView,minesweeper);
		mineSweeperView.controller(mineSweeperCtr);
		JFrame window = new JFrame("Minesweeper");  	
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane((JPanel) mineSweeperView);	
		window.pack();
		window.setVisible(true); 
		window.setResizable(false);
		ImageIcon img = new ImageIcon("icon.png");
		window.setIconImage(img.getImage());
	}
}
