package assets;

import snek.GameFieldController;
import snek.Home.Direction;

public class Snake {
	private int x;
	private int y;
	
	// Move
	public void move(Direction direction) {
		
		// Direction
		switch (direction) {
		case UP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		case LEFT:
			x--;
			break;
		case RIGHT:
			x++;
			break;
		}
		
		// Sends snake to the other side if it hits the right wall (and vice versa)
		if (x == GameFieldController.numberOfColumns) {
			x = 0;
		} else if (x < 0) {
			x = GameFieldController.numberOfColumns - 1;
		}

		// Sends snake to the other side if it hits the bottom (and vice versa
		if (y >= GameFieldController.numberOfRows) {
			y = 0;
		} else if (y < 0) {
			y = GameFieldController.numberOfRows - 1;
		}
		
		
		
	}

	// Getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
