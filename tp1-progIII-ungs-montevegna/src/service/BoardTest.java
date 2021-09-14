package service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	BoardService _board;

	@Before
	public void init() {
		_board = new BoardService(4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstNegativeCalculateVertexPosition() {
		_board.calculateVertexPosition(-1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstOverlappedCalculateVertexPosition() {
		_board.calculateVertexPosition(4, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void secondNegativeCalculateVertexPosition() {
		_board.calculateVertexPosition(0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void secondOverlappedCalculateVertexPosition() {
		_board.calculateVertexPosition(0, 4);
	}
	
	@Test
	public void randomLights() {
		System.out.println(_board.toString());
	}

	@Test
	public void changeLightState() {
		System.out.println(_board.toString());
		_board.changeLightState(0);
		System.out.println(_board.toString());
	}
	
}
