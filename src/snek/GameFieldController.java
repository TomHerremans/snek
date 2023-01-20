package snek;

import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import snek.Home.Direction;

public class GameFieldController {

	// FXML stuff
	@FXML
	private GridPane board;

	@FXML
	private Text scoreDisplay;

	private Rectangle[][] boardElements = null;

	// Board Size
	int numberOfRows = 15;
	int numberOfColumns = 15;

	// Direction
	public static Direction snakeDirection = Direction.RIGHT;

	// Initialize
	public void initialize() {

		System.out.println("Initialized");

		fillBoard();
		initializeGridPaneArray();

		// Initialize Timer and snake coordinates
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int x = 0;
			int y = 0;

			@Override
			public void run() {
				// Recolour tile to snake tile
				boardElements[y][x].setFill(javafx.scene.paint.Color.GREEN);

				// Directions
				switch (snakeDirection) {
				case UP:
					System.out.println("Up " + "X" + x + "Y" + y);
					y--;
					break;
				case DOWN:
					System.out.println("Down " + "X" + x + "Y" + y);
					y++;
					break;
				case LEFT:
					System.out.println("Left " + "X" + x + "Y" + y);
					x--;
					break;
				case RIGHT:
					System.out.println("Right " + "X" + x + "Y" + y);
					x++;
					break;
				}

				// Sends snake to the other side if it hits the right wall (and vice versa)
				if (x == numberOfColumns) {
					x = 0;
				} else if (x < 0) {
					x = numberOfColumns - 1;
				}

				// Sends snake to the other side if it hits the bottom (and vice versa
				if (y >= numberOfRows) {
					y = 0;
				} else if (y < 0) {
					y = numberOfRows - 1;
				}

			}

		};

		timer.schedule(task, 2000, 500);

	}

	// Initialize GridPane Array
	private void initializeGridPaneArray() {
		boardElements = new Rectangle[numberOfRows][numberOfColumns];

		for (Node node : board.getChildren()) {
			boardElements[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = (Rectangle) node;
		}
	}

	// Fill Board
	private void fillBoard() {
		int numberOfColumns = board.getColumnCount();
		int numberOfRows = board.getRowCount();

		for (int i = 0; i < numberOfRows; i++) {

			for (int j = 0; j < numberOfColumns; j++) {
				Rectangle rectangle = new Rectangle(44, 44);
				board.add(rectangle, j, i);
			}

		}
	}

}
