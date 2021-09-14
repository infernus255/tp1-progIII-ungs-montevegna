package service;

public interface IBoardService {

	void changeLightState(int vertex);

	Boolean[][] get();

	int calculateVertexPosition(int row, int col);

	boolean win();

	int getLength();
}
