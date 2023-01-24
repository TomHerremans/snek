package snek;

	import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
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

	// Variables
	int score = 0;

	// Board Size
	int numberOfRows = 15;
	int numberOfColumns = 15;

	// Direction
	public static Direction snakeDirection = Direction.RIGHT;

	// Initialize
	public void initialize() {

		System.out.println("Initialized");

		// Initialize Board
		fillBoard();
		initializeGridPaneArray();

		// Add Apple
		Apple apple = new Apple();
		newApple(apple);

		// Initialize Timer and snake coordinates
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			// Snake Head Position
			int x = 0;
			int y = 0;

			// Run
			@Override
			public void run() {

				// Recolor tile to snake tile
				drawSnakeTile(x, y);

				// Consume
				consumeApple(x, y, apple);

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

				// Update Score Counter
				updateScoreCounter(score);

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

	// New Apple
	private void newApple(Apple apple) {

		apple.setX((int) (Math.random() * numberOfColumns));
		apple.setY((int) (Math.random() * numberOfRows));
		drawAppleTile(apple.getX(), apple.getY());
		
		System.out.println("New Apple");
		System.out.println("Apple X: " + apple.getX() + " Apple Y: " + apple.getY());
	}

	// Consume Apple
	private void consumeApple(int x, int y, Apple apple) {
		// TODO Auto-generated method stub

		if (x == apple.getX() && y == apple.getY()) {
			System.err.println("Consume");
			score++;
			newApple(apple);
		}

	}

	// Update Score Counter
	private void updateScoreCounter(int score) {
		scoreDisplay.setText("Score: " + score);
	}

	// Draw Snake Tile
	private void drawSnakeTile(int y, int x) {
		// TODO Auto-generated method stub
		boardElements[x][y].setFill(Color.GREEN);

	}

	// Draw Apple Tile
	private void drawAppleTile(int y, int x) {
		// TODO Auto-generated method stub
		boardElements[x][y].setFill(Color.RED);

	}

	// Clear Tile
	private void clearTile(int y, int x) {
		// TODO Auto-generated method stub
		boardElements[x][y].setFill(Color.BLACK);

	}

}
