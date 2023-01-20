package snek;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Home extends Application {

	// Enum Directions
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	// Launch
	public static void main(String[] args) {
		Application.launch(args);
	}

	// Start
	@Override
	public void start(Stage primaryStage) throws Exception {

		AnchorPane parent = FXMLLoader.load(getClass().getResource("GameField.fxml"));
		Scene scene = new Scene(parent);

		primaryStage.setResizable(false);
		primaryStage.setTitle("Snek");

		// Snake Controls
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				switch (event.getCode()) {
				case UP:
					System.out.println("Direction changed: Up");
					GameFieldController.snakeDirection = Direction.UP;
					break;
				case DOWN:
					System.out.println("Direction changed: Down");
					GameFieldController.snakeDirection = Direction.DOWN;
					break;
				case LEFT:
					System.out.println("Direction changed: Left");
					GameFieldController.snakeDirection = Direction.LEFT;
					break;
				case RIGHT:
					System.out.println("Direction changed: Right");
					GameFieldController.snakeDirection = Direction.RIGHT;
					break;
				default:
					break;
				}
			}

		});

		// Set & Show Scene
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
