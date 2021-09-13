package model.graph;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class NeighborsTest {

	@Test(expected = IllegalArgumentException.class)
	public void negativeVertexTest() {
		Graph graph = new Graph(5);
		graph.getNeighbors(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void overlappedVertexTest() {
		Graph graph = new Graph(5);
		graph.getNeighbors(5);
	}

	@Test
	public void noneWithNeighborsTest() {
		Graph graph = new Graph(5);
		assertEquals(0, graph.getNeighbors(2).size());
	}

	@Test
	public void universalVertexTest() {
		Graph graph = new Graph(4);
		graph.addEdge(1, 0);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);

		Set<Integer> set = graph.getNeighbors(1);

		int[] expected = { 0, 2, 3 };
		Assert.equalsSet(expected, set);
	}

	@Test
	public void normalVertexTest() {
		Graph graph = new Graph(5);
		graph.addEdge(3, 1);
		graph.addEdge(3, 0);
		graph.addEdge(2, 0);

		Set<Integer> set = graph.getNeighbors(3);

		int[] expected = { 1, 0 };
		Assert.equalsSet(expected, set);
	}
}
