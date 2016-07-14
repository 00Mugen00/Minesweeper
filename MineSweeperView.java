package prMineSweeper;

import java.awt.event.ActionListener;

public interface MineSweeperView {
	void controller(ActionListener ctr);
	int getRow(int x);
	int getColumn(int x);
	boolean update(MineSweeper minesweeper);
}
