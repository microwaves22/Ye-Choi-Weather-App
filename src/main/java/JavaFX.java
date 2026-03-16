import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.*;

public class JavaFX extends Application {
	//
 	// INITIALIZING
 	//
	Label title, advertisement, adDetails;
	Button buttonHome, button3Day, buttonWeek, buttonSearch, buttonTemperature;
	TextField locationInput, todayWeatherText, date, temperature, dayText, dayTemp, nightText, nightTemp, moodOfDay, mood, pictureOfMood, wind, direction, precipitation, humidity;
	VBox headerAndInfoBox, navigationBarBox, weatherInformationBox, advertisementBox, windAndDirectionBox, precipAndHumidBox, moodBox;
	HBox root, infoAndAdsBox, headerBox, locationSearch,  infoSection, infoFirstSection, infoSecondSection, infoThirdSection, adSplitBox;
	Scene sceneHome, scene3Day, sceneWeek;
	Image weatherImage,searchImage, homeImage, threeDayImage, weekImage, adImage;
	ImageView weatherIcon, searchIcon, homeIcon, threeDayIcon, weekIcon,adImageView;


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
		// Home button
		homeImage = new Image(getClass().getResource("/icons/Home.png").toExternalForm());
		homeIcon = new ImageView(homeImage);
		homeIcon.setFitWidth(40);
		homeIcon.setFitHeight(40);
		homeIcon.setPreserveRatio(true);
		buttonHome = new Button();
		buttonHome.setGraphic(homeIcon);
		// 3-Day button
		threeDayImage = new Image(getClass().getResource("icons/3-Day.png").toExternalForm());
		threeDayIcon = new ImageView(threeDayImage);
		threeDayIcon.setFitWidth(40);
		threeDayIcon.setFitHeight(40);
		threeDayIcon.setPreserveRatio(true);
		button3Day = new Button();
		button3Day.setGraphic(threeDayIcon);
		// Week button
		weekImage = new Image(getClass().getResource("icons/Week.png").toExternalForm());
		weekIcon = new ImageView((weekImage));
		weekIcon.setFitWidth(40);
		weekIcon.setFitHeight(40);
		weekIcon.setPreserveRatio(true);
		buttonWeek = new Button();
		buttonWeek.setGraphic(weekIcon);

		//
		// Header Box Details
 		//
		title = new Label("Choi's Yeather App");
		title.getStyleClass().add("title");

		locationInput = new TextField("Choose Location");
		searchImage = new Image(getClass().getResource("/icons/Search.png").toExternalForm());
		searchIcon = new ImageView(searchImage);
		searchIcon.setFitWidth(16);
		searchIcon.setFitHeight(16);
		searchIcon.setPreserveRatio(true);
		buttonSearch = new Button();
		buttonSearch.setGraphic(searchIcon);


		// Advertisement Box details
		advertisement = new Label("Advertisement");
		adDetails = new Label("Looking for a local dental assistant? Look no farther!");
		//adDetails.setMinimumSize(new Dimension(100, 50));
		adImage = new Image(getClass().getResource("/linkedIn/Anna's_LinkedIn.jpg").toExternalForm());
		adImageView = new ImageView(adImage);
		adImageView.setFitHeight(100);
		adImageView.setFitWidth(100);

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

		adSplitBox = new HBox(10, adDetails, adImageView);
		adSplitBox.setAlignment(Pos.CENTER);

		weatherInformationBox = new VBox(10, infoSection, infoFirstSection, infoSecondSection, infoThirdSection);
		// ad box
		advertisementBox = new VBox(10, advertisement, adSplitBox);
		advertisementBox.setStyle ("-fx-background-color: #FFD1DF;");

		// set up info and ads box
		infoAndAdsBox = new HBox(10, weatherInformationBox, advertisementBox);
		infoAndAdsBox.setStyle ("-fx-background-color: grey;");

		locationSearch = new HBox(10, locationInput, buttonSearch);
		locationSearch.getStyleClass().add("locationSearch");

		headerBox = new HBox(10, title, locationSearch);
		headerBox.getStyleClass().add("headerBox");

		// inside scene create to half one for navigation bar and other half of page
		navigationBarBox = new VBox(10, buttonHome, button3Day, buttonWeek);
		navigationBarBox.setAlignment(Pos.TOP_CENTER);
		navigationBarBox.setPadding((new Insets(10, 0, 0, 0)));
		navigationBarBox.setStyle ("-fx-background-color: #6EA2EF;");
		navigationBarBox.setPrefWidth(100);

		headerAndInfoBox = new VBox(10, headerBox, infoAndAdsBox);
		headerAndInfoBox.setStyle ("-fx-background-color: white;");

		// set up overall Scene
		root = new HBox(navigationBarBox, headerAndInfoBox);
		sceneHome = new Scene(root, 700, 700);
		sceneHome.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

		primaryStage.setScene(sceneHome);
		primaryStage.show();
	}

}
