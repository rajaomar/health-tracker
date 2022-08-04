package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Node;

public class HealthTrackerController {

	@FXML
	private TextField nameTextField;

	@FXML
	private Label introductionLabel;

	@FXML
	private TextField ageTextField;

	@FXML
	public TextField currentweightTextField;

	@FXML
	private TextField heightTextField;

	@FXML
	private ChoiceBox<String> activityChoiceBox;

	@FXML
	private Label validName;

	@FXML
	private Label validGender;

	@FXML
	private Label errorInputs;

	@FXML
	public Label usersName;

	@FXML
	private ChoiceBox<String> activityLevelChoiceBox;

	@FXML
	private TextField weightToChange;

	@FXML
	private ChoiceBox<String> weeklyWeightChange;

	@FXML
	private ChoiceBox<String> weightChange;

	@FXML
	private Label sceneThreeErrorLabel;

	@FXML
	private Label bmiLabel;

	@FXML
	private Label bmrLabel;

	@FXML
	private Label obesityClassLabel;

	@FXML
	private Label weightLossDaily;

	@FXML
	public ChoiceBox<String> genderChoiceBox;

	@FXML
	public ChoiceBox<String> dietType;

	@FXML
	private Label sceneFourErrorLabel;

	@FXML
	private Label sceneFiveErrorLabel;

	@FXML
	public boolean foodsPicked = false;

	private Stage stage;
	private Scene scene;

	public void switchToScene2(ActionEvent event) throws IOException {

		validName.setText("");
		String name = nameTextField.getText();
		AnchorPane root = FXMLLoader.load(getClass().getResource("Scene_2.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		// Code used for checking if String contains only alphabets in Java:
		// Date accessed July 29th, 2022
		// https://www.tutorialkart.com/java/how-to-check-if-string-contains-only-
		// alphabets-in-java/#:~:text=To%20check%20if%20String%20contains%20only
		// %20alphabets%20in%20Java%2C%20call,alphabets%20(uppercase%20or%20lowercase)

		// Website used for learning how to move label within JavaFX
		// Date Accessed: July 30th, 2022
		// http://www.java2s.com/Code/Java/JavaFX/MoveaLabelbyusingsetTranslateY.htm

		if (name != "" && name.matches("[a-zA-Z]+")) {
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			Label nameLabel = new Label();
			nameLabel.setText(nameTextField.getText());
			nameLabel.setTranslateX(80);
			nameLabel.setTranslateY(20.4);
			nameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

			root.getChildren().add(nameLabel);

		} else {
			errorInputs.setTextFill(Color.color(1, 0, 0));
			errorInputs.setText("Please enter a valid name");
		}

	}

	// Code used for switching scenes:
	// Date accessed Jul 28, 2022
	// https://www.youtube.com/watch?v=hcM-R-YOKkQ
	public void switchToScene3(ActionEvent event) throws IOException {
		AnchorPane root = FXMLLoader.load(getClass().getResource("Scene_3.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToScene4(ActionEvent event) throws IOException {
		AnchorPane root = FXMLLoader.load(getClass().getResource("Scene_4.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		sceneThreeErrorLabel.setTextFill(Color.color(1, 0, 0));

		ErrorMessage error = new ErrorMessage(genderChoiceBox.getValue(), ageTextField.getText(),
				heightTextField.getText(), currentweightTextField.getText(), weightToChange.getText(),
				activityLevelChoiceBox.getValue(), weeklyWeightChange.getValue(), weightChange.getValue());
		sceneThreeErrorLabel.setText(error.getErrorText());

		if (error.isChangeScene()) {
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			makeLabels(root);
		}
	}

	@FXML
	public void switchToScene5(ActionEvent event) throws IOException {
		AnchorPane root = FXMLLoader.load(getClass().getResource("Scene_5.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		if (dietType.getValue() != null) {
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			ChoiceBox breakfast = new ChoiceBox();
			ChoiceBox lunch = new ChoiceBox();
			ChoiceBox dinner = new ChoiceBox();			
			makeFoodChoiceboxes(breakfast, lunch, dinner, dietType.getValue(), root);
		} else {
			sceneFourErrorLabel.setText("Please pick a diet type to proceed.");
		}
	}

	@FXML
	public void switchToScene6(ActionEvent event) throws IOException {
		AnchorPane root = FXMLLoader.load(getClass().getResource("Scene_6.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		areFoodsPicked(null, null, null);
		if (genderChoiceBox.getValue()!="") {
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			sceneFiveErrorLabel.setText("Please pick a diet type to proceed.");
		}

	}

	@FXML
	public void switchToScene7(ActionEvent event) throws IOException {
		AnchorPane root = FXMLLoader.load(getClass().getResource("Scene_7.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	// Website used for learning how to move label within JavaFX
	// Date Accessed: July 30th, 2022
	// http://www.java2s.com/Code/Java/JavaFX/MoveaLabelbyusingsetTranslateY.htm
	/**
	 * 
	 * @param root
	 * @param gender
	 */
	void makeLabels(AnchorPane root) {
		Label newBmiLabel = new Label();
		newBmiLabel.setTranslateY(73);
		newBmiLabel.setTranslateX(159);
		Label newBmrLabel = new Label();
		newBmrLabel.setTranslateY(92);
		newBmrLabel.setTranslateX(187);
		Label obesityClassLabel = new Label();
		obesityClassLabel.setTranslateY(112);
		obesityClassLabel.setTranslateX(98);
		Label targetCaloriesLabel = new Label();
		targetCaloriesLabel.setTranslateY(190);
		targetCaloriesLabel.setTranslateX(245);
		Label daysNeededLabel = new Label();
		daysNeededLabel.setTranslateX(352.9);
		daysNeededLabel.setTranslateY(250.6);

		Label[] labels = { newBmrLabel, newBmiLabel, obesityClassLabel, targetCaloriesLabel, daysNeededLabel };
		for (Label label : labels)
			root.getChildren().add(label);

		Person user = new Person(genderChoiceBox.getValue(), Integer.parseInt(ageTextField.getText()),
				Integer.parseInt(heightTextField.getText()), Integer.parseInt(currentweightTextField.getText()),
				Double.parseDouble(weeklyWeightChange.getValue()), weightChange.getValue(),
				Integer.parseInt(weightToChange.getText()), activityLevelChoiceBox.getValue());

		setLabelText(newBmrLabel, newBmiLabel, obesityClassLabel, targetCaloriesLabel, daysNeededLabel, user);

		// Citation for Styling text
		// Date Accessed July 31st, 2022
		// https://docs.oracle.com/javafx/2/text/jfxpub-text.htm

		// Citation for Colouring text
		// Date Accessed July 31st, 2022
		// https://docs.oracle.com/javafx/2/api/javafx/scene/paint/Color.html

		// Citation for Colouring text
		// Date Accessed July 31st, 2022
		// https://stackoverflow.com/questions/61052676/how-to-change-color-of-text-in-javafx-label

	}

	void setLabelText(Label bmr, Label bmi, Label obesity, Label targetCalories, Label daysNeeded, Person theUser) {

		bmr.setText(theUser.getBmr() + "");
		bmr.setFont(Font.font("System", FontWeight.BOLD, 14));

		targetCalories.setText(theUser.getDailyIntake() + "");
		targetCalories.setTextFill(Color.color(0, 0, 1));
		targetCalories.setFont(Font.font("System", FontWeight.BOLD, 14));

		Bmi usersBmi = new Bmi(theUser.getHeight(), theUser.getWeight());

		bmi.setText(String.format("%.1f", usersBmi.getBmiValue()));
		bmi.setFont(Font.font("System", FontWeight.BOLD, 14));

		obesity.setText(usersBmi.obesityLabelText(usersBmi.getBmiValue()));
		daysNeeded.setFont(Font.font("System", FontWeight.BOLD, 14));

		daysNeeded.setText(theUser.getDaysNeeded() + "");
		daysNeeded.setFont(Font.font("System", FontWeight.BOLD, 14));
		daysNeeded.setTextFill(Color.color(0.39, 0.34, 1));
	}

	void makeFoodChoiceboxes(ChoiceBox breakfast, ChoiceBox lunch, ChoiceBox dinner, String usersDietType,
			AnchorPane root) {
		FoodOptions usersFoodPreferance = new FoodOptions(usersDietType);

		breakfast.setTranslateX(100);
		breakfast.setTranslateY(59.8);
		breakfast.getItems().add(usersFoodPreferance.getBreakfast1());
		breakfast.getItems().add(usersFoodPreferance.getBreakfast2());
		breakfast.getItems().add(usersFoodPreferance.getBreakfast3());

		lunch.setTranslateX(100);
		lunch.setTranslateY(120);
		lunch.getItems().add(usersFoodPreferance.getLunch1());
		lunch.getItems().add(usersFoodPreferance.getLunch2());
		lunch.getItems().add(usersFoodPreferance.getLunch3());

		dinner.setTranslateX(100);
		dinner.setTranslateY(181.1);
		dinner.getItems().add(usersFoodPreferance.getDinner1());
		dinner.getItems().add(usersFoodPreferance.getDinner2());
		dinner.getItems().add(usersFoodPreferance.getDinner3());
		root.getChildren().add(lunch);
		root.getChildren().add(breakfast);
		root.getChildren().add(dinner);

	}

	boolean areFoodsPicked(String breakfast, String lunch, String dinner) {
		if (breakfast != "" && lunch != "" && dinner != "") {
			return true;
		} else {
			return false;

		}

	}
}