package prMineSweeper;

public class MineSweeper {
	private int[][] cells;
	private boolean[][] visibility;

	public MineSweeper(int[][] cells, boolean[][] visibility) {
		this.cells = cells;
		this.visibility = visibility;
	}

	public int[][] getCells() {
		return cells;
	}

	public boolean[][] getVisibility() {
		return visibility;
	}

	public void openCell(int x, int y) {
		if ((x >= 0 && x < cells.length && y >= 0 && y < cells[0].length)) {
			if (cells[x][y] == 0&&!visibility[x][y]) {
				visibility[x][y] = true;
				openCell(x + 1, y);
				openCell(x, y + 1);
				openCell(x - 1, y);
				openCell(x, y - 1);
			}else{
				visibility[x][y] = true;
			}
		}
	}

	public void print() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (visibility[i][j]) {
					System.out.print(cells[i][j]+" ");
				} else {
					System.out.print("* ");
				}
			}
			System.out.println();
		}
	}
	
	public void restartGame(){
		for(int i=0; i<visibility.length;i++){
			for(int j=0; j<visibility[0].length;j++){
				visibility[i][j] = false;
			}
		}
	}

	public boolean gameFinished(){
		int numberOfMines=0;
		int numberClosedTiles=0;
		for(int i=0; i<visibility.length;i++){
			for(int j=0; j<visibility[0].length;j++){
				if(cells[i][j]==9){
					numberOfMines++;
				}
				if(!visibility[i][j]){
					numberClosedTiles++;
				}
			}
		}
		return numberOfMines==numberClosedTiles;
	}
}
