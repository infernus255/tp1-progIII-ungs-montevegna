package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dto.UpdateLightBoardDto;
import service.BoardService;
import service.IBoardService;

public class BoardControllerTest {

	IBoardService boardService;
	BoardController board;

	@Before
	public void init() {
		boardService = new BoardService(4);
		board = new BoardController(boardService);
	}

	@Test
	public void testUpdateLight() {
		UpdateLightBoardDto update = new UpdateLightBoardDto();

		System.out.println(toStringMatrix(board.get()));
		update.row = 0;
		update.col = 0;
		board.updateLight(update);
		System.out.println(toStringMatrix(board.get()));
		update.row = 0;
		update.col = 1;
		board.updateLight(update);
		System.out.println(toStringMatrix(board.get()));
		update.row = 3;
		update.col = 3;
		board.updateLight(update);
		System.out.println(toStringMatrix(board.get()));
	}

	private String toStringMatrix(Boolean[][] _matrix) {
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
