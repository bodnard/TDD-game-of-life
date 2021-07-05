package java.com.epam.exercises.gameoflife;

public interface Board {

	default Board getNextGenerationBoard() {
		return this;
	}

	default void insertCell(Coordinate coordinate) {
		
	}

	default boolean isAlive(Coordinate coordinate) {
		return false;
	}

}