package snek;

import java.util.Timer;
import java.util.TimerTask;

import assets.Apple;
import assets.Snake;
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
	public static int numberOfRows = 15;
	public static int numberOfColumns = 15;

	// Direction
	public static Direction snakeDirection = Direction.RIGHT;

	// Initialize
	public void initialize() {

		System.out.println("Initialized");

		// Initialize Board
		fillBoard();
		initializeGridPaneArray();

		// Add Snake
		Snake snake = new Snake();

		// Add Apple
		Apple apple = new Apple();
		newApple(apple);

		// Initialize Timer and snake coordinates
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			// Run
			@Override
			public void run() {

				// Recolor tile to snake tile
				drawSnakeTile(snake.getX(), snake.getY());

				// Consume
				consumeApple(snake.getX(), snake.getY(), apple);

				// Move
				snake.move(snakeDirection);
				System.out.println("Snake: X" + snake.getX() + ", Y" + snake.getY());

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
		System.out.println("Apple: X" + apple.getX() + ", Y" + apple.getY());
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
