package controller;

import dto.UpdateLightBoardDto;
import service.IBoardService;

public class BoardController {

	private final IBoardService _boardService;

	public BoardController(IBoardService boardService) {
		this._boardService = boardService;
	}

	public void updateLight(UpdateLightBoardDto updateLightBoardDto) {
		int vertex = _boardService.calculateVertexPosition(updateLightBoardDto.row, updateLightBoardDto.col);
		_boardService.changeLightState(vertex);
	}

	public Boolean[][] get() {
		return _boardService.get();
	}

	public boolean win() {
		return _boardService.win();
	}
}
