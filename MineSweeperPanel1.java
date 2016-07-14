package prMineSweeper;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MineSweeperPanel1 extends JPanel implements MineSweeperView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[][] tiles;
	private JLabel message;
	private int[][] command;
	private static final String GAME_OVER="Game Over";
	private static final String GAME_IN_PROGRESS="Game in Progress";
	public MineSweeperPanel1(MineSweeper minesweeper) {
		int rows = minesweeper.getCells().length;
		int columns = minesweeper.getCells()[0].length;

		tiles = new JButton[rows][columns];
		command = new int[rows][columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				tiles[i][j] = new JButton();
				tiles[i][j].setPreferredSize(new Dimension(20, 20));
			}
		}
		message = new JLabel(GAME_IN_PROGRESS);

		setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(rows, columns));
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				centerPanel.add(tiles[i][j]);
			}
		}

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		southPanel.add(message);

		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}

	public void controller(ActionListener ctr) {
		int x = 0;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j].addActionListener(ctr);
				tiles[i][j].setActionCommand(Integer.toString(x));
				command[i][j] = x;
//				System.out.print(x + " ");
				x++;
			}
//			System.out.println();
		}
//		System.out.println("\n");
//		for (int i = 0; i < tiles.length; i++) {
//			for (int j = 0; j < tiles[0].length; j++) {
//				System.out.print(command[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	public int getRow(int x) {
		int solution = 0;
		boolean found = false;
		int i = 0;
		while (!found && i < command.length) {
			int j = 0;
			while (!found && j < command[0].length) {
				if (command[i][j] == x) {
					solution = i;
					found = true;
				}
				j++;
			}
			i++;
		}
		return solution;
	}

	public int getColumn(int x) {
		int solution = 0;
		boolean found = false;
		int i = 0;
		while (!found && i < command.length) {
			int j = 0;
			while (!found && j < command[0].length) {
				if (command[i][j] == x) {
					solution = j;
					found = true;
				}
				j++;
			}
			i++;
		}
		return solution;
	}

	private boolean showNumber(int i, int j, int number) {
		if (number < 9) {
			tiles[i][j].setIcon(new ImageIcon(Integer.toString(number) + ".png"));
			return false;
		}else{
			tiles[i][j].setIcon(new ImageIcon("mine.png"));
			message.setText(GAME_OVER);
			return true;
		}
	}
	
	private void restartTiles(){
		for(int i=0; i<tiles.length; i++){
			for(int j=0; j<tiles[0].length; j++){
				tiles[i][j].setIcon(new ImageIcon());
			}
		}
	}

	public boolean update(MineSweeper minesweeper) {
		if(message.getText().equals(GAME_OVER)){
			message.setText(GAME_IN_PROGRESS);
		}
		boolean gameover=false;
		int[][] cells = minesweeper.getCells();
		boolean[][] visibility = minesweeper.getVisibility();
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (visibility[i][j]) {
					gameover=gameover||showNumber(i, j, cells[i][j]);
				}
			}
		}
		if(gameover){
			restartTiles();
		}
		return gameover;
	}

	public void printMessage(String msg){
		message.setText(msg);
	}
}
