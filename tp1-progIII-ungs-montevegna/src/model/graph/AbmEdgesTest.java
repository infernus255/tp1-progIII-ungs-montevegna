package model.graph;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbmEdgesTest {

	@Test(expected = IllegalArgumentException.class)
	public void firstNegativeVertexTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(-1, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstOverlappedVertexTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(5, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void secondNegativeVertexTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(3, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void secondOverlappedVertexTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(2, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addLoopTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(2, 2);
	}

	@Test
	public void existsEdgeTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(2, 3);

		assertTrue(grafo.existsEdge(2, 3));
	}

	@Test
	public void existsOppositeEdgeTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(2, 3);

		assertTrue(grafo.existsEdge(3, 2));
	}

	@Test
	public void nonExistsEdgeTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(2, 3);

		assertFalse(grafo.existsEdge(1, 4));
	}

	@Test
	public void removeExistingEdgeTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(2, 3);
		grafo.removeEdge(2, 3);

		assertFalse(grafo.existsEdge(2, 3));
	}

	@Test
	public void removeNonExistingEdgeTest() {
		Graph grafo = new Graph(5);
		grafo.removeEdge(3, 2);

		assertFalse(grafo.existsEdge(3, 2));
	}

	@Test
	public void removeEdgeTwiceTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(3, 2);
		grafo.removeEdge(3, 2);
		grafo.removeEdge(3, 2);

		assertFalse(grafo.existsEdge(3, 2));
	}

	@Test
	public void addEdgeTwiceTest() {
		Graph grafo = new Graph(5);
		grafo.addEdge(3, 2);
		grafo.addEdge(3, 2);

		assertTrue(grafo.existsEdge(3, 2));
	}
}
