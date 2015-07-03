package mapCreator;

public class DFS {

	private static int rows, cols;
	private static boolean[][] mark;

	public static boolean isConnected(boolean[][][] matrix) {
		rows = matrix.length;
		cols = matrix[0].length;
		mark = new boolean[rows][cols];
		dfs(matrix, 0, 0);
		for (int i = 0; i < rows; ++i)
			for (int j = 0; j < cols; ++j)
				if (!mark[i][j])
					return false;
		return true;
	}

	private static void dfs(boolean[][][] matrix, int r, int c) {
		if (r >= rows || c >= cols || mark[r][c] == true)
			return;
		mark[r][c] = true;
		if (!matrix[r][c][MapCreator.Down])
			dfs(matrix, r + 1, c);
		if (!matrix[r][c][MapCreator.Right])
			dfs(matrix, r, c + 1);
	}
}
