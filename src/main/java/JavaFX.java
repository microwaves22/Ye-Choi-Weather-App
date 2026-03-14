import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

import javafx.scene.control.Label;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class JavaFX extends Application {
	//
 	// INITIALIZING
 	//
	Label title;
	Button buttonHome, button3Day, buttonWeek, buttonSearch, buttonTemperature;
	TextField locationInput, todayWeatherText, date, temperature, dayText, dayTemp, nightText, nightTemp, moodOfDay, mood, pictureOfMood, wind, direction, precipitation, humidity, advertisement, adDetails, adImage;
	VBox headerAndInfoBox, navigationBarBox, weatherInformationBox, advertisementBox, windAndDirectionBox, precipAndHumidBox, moodBox;
	HBox root, infoAndAdsBox, headerBox, locationSearch,  infoSection, infoFirstSection, infoSecondSection, infoThirdSection, adSplitBox;
	Scene sceneHome, scene3Day, sceneWeek;
	Image weatherImage,searchImage;
	ImageView weatherIcon, searchIcon;


	public static void main(String[] args) {
		launch(args);
	}

	//
	// Page
	//
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Title of Application
		primaryStage.setTitle("Choi's Yeather App");

		// Navigation buttons on navigation bar
		buttonHome = new Button("Home");
		button3Day = new Button("3Day");
		buttonWeek = new Button("Week");

		// Header Box details
		title = new Label("Choi's Yeather App");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
//		title.setEditable(false);

		locationInput = new TextField("Choose Location");
		searchImage = new Image(getClass().getResource("/icons/Search.png").toExternalForm());
		searchIcon = new ImageView(searchImage);
		searchIcon.setFitWidth(16);
		searchIcon.setFitHeight(16);
		searchIcon.setPreserveRatio(true);
		buttonSearch = new Button();
		buttonSearch.setGraphic(searchIcon);


		// Advertisement Box details
		advertisement = new TextField("Advertisement");
		adDetails = new TextField("Ad details");
		adImage = new TextField("Ad image");

		// info section detail
		weatherImage = new Image(getClass().getResource("/icons/Weather.png").toExternalForm());
		weatherIcon = new ImageView(weatherImage);
		weatherIcon.setFitHeight(50);
		weatherIcon.setFitWidth(50);

		buttonTemperature = new Button("K/F/C");

		// info first section
		todayWeatherText = new TextField("Today's Weather");
		temperature = new TextField("Temperature");
		date = new TextField("Date");

		// info second section
		dayText = new TextField("Day");
		dayTemp = new TextField("Day Temperature");
		nightText = new TextField("Night Text");
		nightTemp = new TextField("Night Temperature");

		// info third section
		wind = new TextField("Wind");
		direction = new TextField("Direction");
		precipitation = new TextField("Precipitation");
		humidity = new TextField("Humidity");

		// mood
		moodOfDay = new TextField("Mood of the day");
		mood = new TextField("Mood");
		pictureOfMood = new TextField("Picture of Mood");

		//inside info third section
		windAndDirectionBox = new VBox(10, wind, direction);
		precipAndHumidBox = new VBox(10, precipitation, humidity);
		moodBox = new VBox(10, moodOfDay, mood, pictureOfMood);

		// sections
		infoSection = new HBox(10, weatherIcon, todayWeatherText, buttonTemperature );
		infoFirstSection = new HBox(10, weatherIcon, temperature, date);
		infoSecondSection = new HBox(10, dayText, dayTemp, nightText, nightTemp);
		infoThirdSection = new HBox(10, windAndDirectionBox, precipAndHumidBox, moodBox);

		adSplitBox = new HBox(10, adDetails, adImage);

		weatherInformationBox = new VBox(10, infoSection, infoFirstSection, infoSecondSection, infoThirdSection);
		// ad box
		advertisementBox = new VBox(10, advertisement, adSplitBox);
		advertisementBox.setStyle ("-fx-background-color: green;");

		// set up info and ads box
		infoAndAdsBox = new HBox(10, weatherInformationBox, advertisementBox);
		infoAndAdsBox.setStyle ("-fx-background-color: grey;");

		locationSearch = new HBox(10, locationInput, buttonSearch);
		locationSearch.setStyle ("-fx-background-color: green;");
		// set up header box
		headerBox = new HBox(10, title, locationSearch);

		// inside scene create to half one for navigation bar and other half of page
		navigationBarBox = new VBox(10, buttonHome, button3Day, buttonWeek);
		navigationBarBox.setStyle ("-fx-background-color: #6EA2EF;");
		navigationBarBox.setPrefWidth(70);

		headerAndInfoBox = new VBox(10, headerBox, infoAndAdsBox);
		headerAndInfoBox.setStyle ("-fx-background-color: white;");

		// set up overall Scene
		root = new HBox(navigationBarBox, headerAndInfoBox);
		sceneHome = new Scene(root, 700, 700);
//		sceneHome.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(sceneHome);
		primaryStage.show();
	}

}
