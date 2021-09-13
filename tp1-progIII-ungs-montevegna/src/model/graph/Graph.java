package model.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Graph {

	// adjacency matrix
	private boolean[][] _matrix;

	public Graph(int vertexes) {
		_matrix = new boolean[vertexes][vertexes];
	}

	private void validateDiffentVertexes(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("Loops aren't allowed: (" + i + " , " + j + ")");
	}

	private void validVertex(int i) {
		if (i < 0)
			throw new IllegalArgumentException("Parameter mustn't be negative: " + i);
		if (i >= _matrix.length)
			throw new IllegalArgumentException("Edges must be between 0 and |V|-1: " + i);
	}

	public void addEdge(int i, int j) {
		validVertex(i);
		validVertex(j);
		validateDiffentVertexes(i, j);

		_matrix[i][j] = true;
		_matrix[j][i] = true;
	}

	public void removeEdge(int i, int j) {
		validVertex(i);
		validVertex(j);
		validateDiffentVertexes(i, j);

		_matrix[i][j] = false;
		_matrix[j][i] = false;
	}

	public boolean existsEdge(int i, int j) {
		validVertex(i);
		validVertex(j);

		return _matrix[i][j];
	}

	public int length() {
		return _matrix.length;
	}

	public Set<Integer> getNeighbors(int i) {
		validVertex(i);

		Set<Integer> ret = new HashSet<Integer>();
		for (int j = 0; j < length(); ++j)
			if (i != j) {
				if (existsEdge(i, j))
					ret.add(j);
			}
		return ret;
	}

	public boolean[][] getMatrix() {
		// immutability
		boolean[][] mat = Arrays.stream(_matrix).map(r -> r.clone()).toArray(boolean[][]::new);
		return mat;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < _matrix.length; ++row) {
			for (int col = 0; col < _matrix[row].length; ++col) {
				sb.append(_matrix[row][col]);
				sb.append(" ");
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
