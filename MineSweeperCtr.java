package prMineSweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperCtr implements ActionListener {
	private MineSweeperView view;
	private MineSweeper minesweeper;
	private boolean gameover;
	public MineSweeperCtr(MineSweeperView view,MineSweeper minesweeper){
		this.view=view;
		this.minesweeper=minesweeper;
		this.gameover=false;
	}
	public void actionPerformed(ActionEvent e) {
		if(gameover){
			minesweeper.restartGame();
			view.update(minesweeper);
		}
		String command = e.getActionCommand();
//		System.out.println(command);
		int row = view.getRow(Integer.parseInt(command));
		int column = view.getColumn(Integer.parseInt(command));
//		System.out.println("("+row+","+column+")");
		minesweeper.openCell(row,column);
		gameover = view.update(minesweeper);
	}
}
