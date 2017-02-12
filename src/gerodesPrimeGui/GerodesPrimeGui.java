package gerodesPrimeGui;

import java.math.BigInteger;

import gerodesSequence.GerodesSequence;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import prime.BigPrimes;

public class GerodesPrimeGui extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FlowPane root = new FlowPane();
		Scene scene = new Scene(root, 1000, 550);

		startingScene(root);
		// generateResearchTillIndex(root, 10);
		// root.getChildren().add(generateGerodesResearch(14));

		primaryStage.setTitle("Gerodes Numbers");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void startingScene(FlowPane root) {
		root.getChildren().add(controlBox(root));
		root.getChildren().add(infoBox());
	}

	private VBox controlBox(FlowPane root) {
		TextField userTextField = new TextField();
		TextField userTextField2 = new TextField();

		Button btn1 = new Button("Generate research");
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					int index = Integer.parseInt(userTextField.getText());
					root.getChildren().add(generateGerodesResearch(index));
				} catch (Exception e) {

				}
			}
		});

		Button btn2 = new Button("Research sequence");
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					root.getChildren().clear();
					startingScene(root);
					int index = Integer.parseInt(userTextField2.getText());
					generateResearchTillIndex(root, index + 1);
				} catch (Exception e) {

				}
			}
		});

		Button btn3 = new Button("Delete");
		btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				root.getChildren().clear();
				startingScene(root);
			}
		});

		VBox box = new VBox(btn1, userTextField, new Label(""), btn2, userTextField2, new Label(""), btn3);

		box.setSpacing(10);
		box.setPadding(new Insets(20));
		return box;
	}

	public void generateResearchTillIndex(FlowPane root, int index) {
		for (int i = 1; i < index; ++i) {
			root.getChildren().add(generateGerodesResearch(i));
		}
	}

	private VBox generateGerodesResearch(int index) {

		Label lb1, lb2, lb3, lb4, lb5, nextPrime, prevPrime, researchnumber;

		BigInteger[] numbers = GerodesSequence.generateGerodesNumbers(index);
		lb1 = getLabelWithColor(numbers[0]);
		lb2 = getLabelWithColor(numbers[1]);

		lb3 = new Label(numbers[2].toString());
		StackPane primorialPane = new StackPane();
		primorialPane.setStyle("-fx-background-color: yellow;");
		primorialPane.getChildren().add(lb3);

		lb4 = getLabelWithColor(numbers[3]);
		lb5 = getLabelWithColor(numbers[4]);
		researchnumber = new Label("Primorial " + String.valueOf(index));

		BigInteger next = BigPrimes.firstNextPrime(numbers[2], true);
		BigInteger prev = BigPrimes.firstNextPrime(numbers[2], false);

		prevPrime = new Label(next + " (+" + next.subtract(numbers[2]) + ")");
		nextPrime = new Label(prev + " (-" + numbers[2].subtract(prev) + ")");
		setPrimeColor(prevPrime);
		setPrimeColor(nextPrime);

		VBox box = new VBox(researchnumber, prevPrime, lb5, lb4, primorialPane, lb2, lb1, nextPrime);

		box.setSpacing(10);
		box.setPadding(new Insets(20));
		return box;
	}

	public Label getLabelWithColor(BigInteger number) {

		Label label = new Label(number.toString());
		boolean isPrime = false;
		try {
			isPrime = BigPrimes.isPrime(number);
		} catch (Exception e) {

		}

		if (isPrime) {
			setPrimeColor(label);
		} else {
			setCompositeColor(label);
		}
		return label;
	}

	private void setCompositeColor(Label l) {
		l.setTextFill(Color.CHOCOLATE);
	}

	public void setPrimeColor(Label l) {
		l.setTextFill(Color.BLUE);
	}

	private VBox infoBox() {
		Label lb1, lb2, lb3, lb4, lb5, nextPrime, PrevPrime, researchnumber;
		lb1 = new Label("+4");
		lb2 = new Label("+2");
		lb3 = new Label("Primorial Number");
		lb4 = new Label("-2");
		lb5 = new Label("-4");
		researchnumber = new Label("Research Step");
		PrevPrime = new Label("Previous Prime");
		nextPrime = new Label("Next Prime");

		VBox info = new VBox(researchnumber, nextPrime, lb5, lb4, lb3, lb2, lb1, PrevPrime);
		info.setSpacing(10);
		info.setPadding(new Insets(20));
		return info;
	}

}
