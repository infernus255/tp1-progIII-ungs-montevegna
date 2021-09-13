package controller;

import java.util.Arrays;
import java.util.HashSet;

import model.graph.Graph;

public class BoardController {

	private Graph _graph;
	private boolean[] _arr;
	private final int _minLightsQty = 6;

	public BoardController() {
		_arr = new boolean[16];
		initGraph();
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
	}

	public boolean[][] getBoard() {
		return _graph.getMatrix();
	}

	// vertex: From 0 to _arr.length() - 1
	public boolean[] changeLightState(int vertex) {
		HashSet<Integer> neighbors = (HashSet<Integer>) _graph.getNeighbors(vertex);
		_arr[vertex] = !_arr[vertex];
		for (int neighbor : neighbors)
			// change state
			_arr[neighbor] = !_arr[neighbor];

		// immutability
		boolean[] mat = Arrays.copyOf(_arr, _arr.length);
		return mat;
	}

	private boolean isFloor(double number) {
		return number == Math.floor(number);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		double sqrt = (int) Math.sqrt(_arr.length);
		for (int vertex = 0; vertex < _arr.length; ++vertex) {
			sb.append(_arr[vertex]);
			sb.append(" ");
			if ( isFloor(((vertex + 1) / sqrt)))
				sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

//	private void initMat() {
//		for (int row = 0; row < _mat.length; ++row) {
//			for (int col = 0; col < _mat[row].length; ++col) {
//				_mat[row][col]
//			}
//		}
//	}

	private void initGraph() {
		_graph = new Graph(16);
		_graph.addEdge(0, 1);
		_graph.addEdge(0, 4);
		_graph.addEdge(0, 5);
		_graph.addEdge(1, 0);
		_graph.addEdge(1, 4);
		_graph.addEdge(1, 5);
		_graph.addEdge(1, 6);
		_graph.addEdge(1, 2);
		_graph.addEdge(2, 1);
		_graph.addEdge(2, 5);
		_graph.addEdge(2, 6);
		_graph.addEdge(2, 7);
		_graph.addEdge(2, 3);
		_graph.addEdge(3, 2);
		_graph.addEdge(3, 6);
		_graph.addEdge(3, 7);
		_graph.addEdge(4, 0);
		_graph.addEdge(4, 1);
		_graph.addEdge(4, 5);
		_graph.addEdge(4, 9);
		_graph.addEdge(4, 8);
		_graph.addEdge(5, 0);
		_graph.addEdge(5, 1);
		_graph.addEdge(5, 2);
		_graph.addEdge(5, 4);
		_graph.addEdge(5, 6);
		_graph.addEdge(5, 8);
		_graph.addEdge(5, 9);
		_graph.addEdge(5, 10);
		_graph.addEdge(6, 1);
		_graph.addEdge(6, 2);
		_graph.addEdge(6, 3);
		_graph.addEdge(6, 5);
		_graph.addEdge(6, 7);
		_graph.addEdge(6, 1);
		_graph.addEdge(6, 9);
		_graph.addEdge(6, 10);
		_graph.addEdge(6, 11);
		_graph.addEdge(7, 2);
		_graph.addEdge(7, 3);
		_graph.addEdge(7, 2);
		_graph.addEdge(7, 6);
		_graph.addEdge(7, 10);
		_graph.addEdge(7, 11);
		_graph.addEdge(8, 4);
		_graph.addEdge(8, 5);
		_graph.addEdge(8, 9);
		_graph.addEdge(8, 12);
		_graph.addEdge(8, 13);
		_graph.addEdge(9, 4);
		_graph.addEdge(9, 5);
		_graph.addEdge(9, 6);
		_graph.addEdge(9, 4);
		_graph.addEdge(9, 8);
		_graph.addEdge(9, 10);
		_graph.addEdge(9, 10);
		_graph.addEdge(9, 12);
		_graph.addEdge(9, 13);
		_graph.addEdge(9, 14);
		_graph.addEdge(10, 5);
		_graph.addEdge(10, 6);
		_graph.addEdge(10, 7);
		_graph.addEdge(10, 9);
		_graph.addEdge(10, 11);
		_graph.addEdge(10, 13);
		_graph.addEdge(10, 14);
		_graph.addEdge(10, 15);
		_graph.addEdge(11, 6);
		_graph.addEdge(11, 7);
		_graph.addEdge(11, 10);
		_graph.addEdge(11, 14);
		_graph.addEdge(11, 15);
		_graph.addEdge(12, 8);
		_graph.addEdge(12, 9);
		_graph.addEdge(12, 13);
		_graph.addEdge(13, 8);
		_graph.addEdge(13, 9);
		_graph.addEdge(13, 10);
		_graph.addEdge(13, 12);
		_graph.addEdge(13, 14);
		_graph.addEdge(14, 9);
		_graph.addEdge(14, 10);
		_graph.addEdge(14, 11);
		_graph.addEdge(14, 13);
		_graph.addEdge(14, 15);
		_graph.addEdge(15, 10);
		_graph.addEdge(15, 11);
		_graph.addEdge(15, 14);
	}
	// 0 1 2 3
	// 4 5 6 7
	// 8 9 10 11
	// 12 13 14 15
}
