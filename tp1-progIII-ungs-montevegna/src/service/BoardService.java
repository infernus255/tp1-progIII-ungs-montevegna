package service;

import java.util.HashSet;

import model.graph.Graph;

public class BoardService implements IBoardService {

	// Facade pattern

	private Graph _graph;
	private Boolean[] _arr;
	private final int _minLightsQty = 6;
	private int _matLength;
	private int _winCount;

	public BoardService(int size) {
		if (size < 0)
			throw new IllegalArgumentException("The parameter " + size + "must be > 0");
		_winCount = 0;
		_matLength = size;
		init();
		randomLightsOn();
	}

	// Randomize lights for a 4x4 board
	private void randomLightsOn() {
		int qty = 6;
		int count = 0;
		HashSet<Integer> lights = new HashSet<Integer>();
		while (count < _minLightsQty) {
			int random = (int) (Math.random() * 16);
			if (lights.add(random))
				count++;
		}
		for (int i = 0; i < qty; ++i) {
			int random = (int) (Math.random() * 16);
			lights.add(random);
		}
		for (int light : lights)
			_arr[light] = true;
		_winCount++;
	}

	public Boolean[][] get() {
		// immutability
		Boolean[][] mat = convertArrayToMatrix(_arr);
		return mat;
	}

	private Boolean[][] convertArrayToMatrix(Boolean[] array) {
		Boolean[][] mat = new Boolean[_matLength][_matLength];
		for (int row = 0; row < _matLength; row++)
			for (int col = 0; col < _matLength; col++)
				mat[row][col] = array[(row * _matLength) + col];
		return mat;
	}

	public int calculateVertexPosition(int row, int col) {
		validVertexMatrix(row);
		validVertexMatrix(col);
		return (row * _matLength) + col;
	}

	private void validVertexMatrix(int i) {
		if (i < 0)
			throw new IllegalArgumentException("Parameter mustn't be negative: " + i);
		if (i >= _matLength)
			throw new IllegalArgumentException("Edges must be between 0 and |Matrix|-1: " + i);
	}
	
	private void validVertexVector(int i) {
		if (i < 0)
			throw new IllegalArgumentException("Parameter mustn't be negative: " + i);
		if (i >= _arr.length-1)
			throw new IllegalArgumentException("Vertex must be between 0 and |Vector|-1: " + i);
	}

	// vertex: From 0 to _arr.length() - 1
	public void changeLightState(int vertex) {
		validVertexVector(vertex);
		HashSet<Integer> neighbors = (HashSet<Integer>) _graph.getNeighbors(vertex);
		_arr[vertex] = !_arr[vertex];
		updateWinCount(vertex);
		if (_arr[vertex])
			_winCount++;
		else
			_winCount--;
		for (int neighbor : neighbors) {
			// change state
			_arr[neighbor] = !_arr[neighbor];
			updateWinCount(neighbor);
		}
	}

	private void updateWinCount(int vertex) {
		if (_arr[vertex])
			_winCount++;
		else
			_winCount--;
	}

	private Boolean isFloor(double number) {
		return number == Math.floor(number);
	}

	public boolean win() {
		return _winCount == _arr.length;
	}

	public int getLength() {
		return _matLength;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int vertex = 0; vertex < _arr.length ; ++vertex) {
			sb.append(_arr[vertex]);
			sb.append(" ");
			if (isFloor(((vertex + 1) / (double)_matLength)))
				sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	private void init() {
		_arr = new Boolean[_matLength * _matLength];
		for (int i = 0; i < _arr.length; i++)
			_arr[i] = false;

		_graph = new Graph(16);

		int[][] mat = new int[_matLength][_matLength];

		int vertex = 0;
		for (int i = 0; i < _matLength; i++)
			for (int j = 0; j < _matLength; j++)
				mat[i][j] = vertex++;

		for (int row = 0; row < _matLength; row++) {
			for (int col = 0; col < _matLength; col++) {
				// add neighbors
				int vertex1 = mat[row][col];
				int vertex2 = -1;
				if (row > 0) // row > 0
				{
					// add top
					vertex2 = mat[row - 1][col];
					_graph.addEdge(vertex1, vertex2);
				}
				if (row < _matLength - 1) // row < lastRow
				{
					// add down
					vertex2 = mat[row + 1][col];
					_graph.addEdge(vertex1, vertex2);
				}
				if (col > 0) // col > 0
				{
					// add left
					vertex2 = mat[row][col - 1];
					_graph.addEdge(vertex1, vertex2);
				}
				if (col < _matLength - 1) // col < lastRow
				{
					// add right
					vertex2 = mat[row][col + 1];
					_graph.addEdge(vertex1, vertex2);
				}
			}
		}
	}
	// 0 1 2 3
	// 4 5 6 7
	// 8 9 10 11
	// 12 13 14 15
}
