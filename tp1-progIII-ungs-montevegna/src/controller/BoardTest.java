package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	
	BoardController _board;
	
	@Before
	public void init() {
		_board = new BoardController();
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
