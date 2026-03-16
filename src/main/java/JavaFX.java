import javafx.application.Application;

import javafx.event.Event;
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

import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;

public class JavaFX extends Application {
	//
	// INITIALIZING
	//
	Hyperlink linkAnna, linkMichelle;
	Label title, advertisement, advertisementAnna, advertisementMichelle, adDetailsAnna, adDetailsMichelle, todayWeatherText;
	Button buttonHome, buttonWeek, buttonSearch, buttonTemperature;
	TextField locationInput, date, temperature, dayText, dayTemp, nightText, nightTemp, moodOfDay, mood, pictureOfMood, wind, direction, precipitation, humidity;
	VBox headerAndInfoBox, weatherInformationBox, advertisementBoxAnna, advertisementBoxMichelle, windAndDirectionBox, precipAndHumidBox, moodBox;

	VBox weekBox, weekDay1Info, weekDay2Info, weekDay3Info, weekDay4Info, weekDay5Info, weekDay6Info, weekDay7Info;
	HBox navigationBarBox, weekRoot, weekRow1, weekRow2, weekRow3, weekRow4, weekRow5, weekRow6, weekRow7;
	TextField weekDay1, weekDay2, weekDay3, weekDay4, weekDay5, weekDay6, weekDay7;

	HBox root, infoAndAdsBox, headerBox, locationSearch,  infoSection, infoFirstSection, infoSecondSection, infoThirdSection, adSplitBoxAnna, adSplitBoxMichelle;
	Image weatherImage,searchImage, homeImage, weekImage, adImage, adImageMichelle, adImageAnna;
	ImageView weatherIcon, searchIcon, homeIcon, weekIcon, adImageViewAnna, adImageViewMichelle;
	HBox weekContent;
	VBox weekHeaderAndInfoBox;

	public static void main(String[] args) {
		launch(args);
	}

	//
	// Page
	//
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Choi's Yeather App");

		navigation();
		header();
		ads();
		weatherInfo();
		weekCode();
		setupLayouts(primaryStage);

		primaryStage.show();
	}

	private void navigation() {
		// Navigation buttons on navigation bar
		// Home button
		homeImage = new Image(getClass().getResource("/icons/Home.png").toExternalForm());
		homeIcon = new ImageView(homeImage);
		homeIcon.setFitWidth(40);
		homeIcon.setFitHeight(40);
		homeIcon.setPreserveRatio(true);
		buttonHome = new Button();
		buttonHome.setGraphic(homeIcon);

		// Week button
		weekImage = new Image(getClass().getResource("/icons/Week.png").toExternalForm());
		weekIcon = new ImageView((weekImage));
		weekIcon.setFitWidth(40);
		weekIcon.setFitHeight(40);
		weekIcon.setPreserveRatio(true);
		buttonWeek = new Button();
		buttonWeek.setGraphic(weekIcon);

		// inside scene create to half one for navigation bar and other half of page
		navigationBarBox = new HBox(10, buttonHome, buttonWeek);
		navigationBarBox.getStyleClass().add("navigationBarBox");
		navigationBarBox.setStyle("-fx-background-color: #6EA2EF;");
	}

	private void header() {
		// Header Box Details
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
	}

	private void ads() {
		// Advertisement Box details
		advertisementAnna = new Label("Advertisement");
		adDetailsAnna = new Label("Looking for a local dental assistant? Look no farther!");
		adImageAnna = new Image(getClass().getResource("/linkedIn/Anna's_LinkedIn.jpg").toExternalForm());
		adImageViewAnna = new ImageView(adImageAnna);
		adImageViewAnna.setFitHeight(100);
		adImageViewAnna.setFitWidth(100);
		linkAnna = new Hyperlink("Visit Anna's LinkedIn");
		linkAnna.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				getHostServices().showDocument("www.linkedin.com/in/annachoi817");
			}
		});

		advertisementMichelle = new Label("Advertisement");
		adDetailsMichelle = new Label("Michelle Ye");
		adImageMichelle = new Image(getClass().getResource("/linkedIn/Michelle's_LinkedIn.jpg").toExternalForm());
		adImageViewMichelle = new ImageView(adImageMichelle);
		adImageViewMichelle.setFitHeight(100);
		adImageViewMichelle.setFitWidth(100);
		linkMichelle = new Hyperlink("Visit Michelle's LinkedIn");
		linkMichelle.setOnAction(new EventHandler() {
			@Override
			public void handle(Event event) {
				getHostServices().showDocument("https://www.linkedin.com/in/michelle-ye-700911299/");
			}
		});
	}

	private void weatherInfo() {
		// info section detail
		weatherImage = new Image(getClass().getResource("/icons/Weather.png").toExternalForm());
		weatherIcon = new ImageView(weatherImage);
		weatherIcon.setFitHeight(50);
		weatherIcon.setFitWidth(50);

		buttonTemperature = new Button("K/F/C");

		// info first section
		todayWeatherText = new Label("Today's Weather");
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
		infoSection = new HBox(10, todayWeatherText, buttonTemperature );
		infoFirstSection = new HBox(10, weatherIcon, temperature, date);
		infoSecondSection = new HBox(10, dayText, dayTemp, nightText, nightTemp);
		infoThirdSection = new HBox(10, windAndDirectionBox, precipAndHumidBox, moodBox);
	}

	private void weekCode() {
		// WEEK CODE
		weekDay1 = new TextField ("Day 1");
		weekDay2 = new TextField ("Day 2");
		weekDay3 = new TextField ("Day 3");
		weekDay4 = new TextField ("Day 4");
		weekDay5 = new TextField ("Day 5");
		weekDay6 = new TextField ("Day 6");
		weekDay7 = new TextField ("Day 7");

		// temperature
		weekRow1 = new HBox(10, weekDay1, new TextField("Temperature"));
		weekRow2 = new HBox(10, weekDay2, new TextField("Temperature"));
		weekRow3 = new HBox(10, weekDay3, new TextField("Temperature"));
		weekRow4 = new HBox(10, weekDay4, new TextField("Temperature"));
		weekRow5 = new HBox(10, weekDay5, new TextField("Temperature"));
		weekRow6 = new HBox(10, weekDay6, new TextField("Temperature"));
		weekRow7 = new HBox(10, weekDay7, new TextField("Temperature"));

		// weekBox
		weekBox = new VBox(10, weekRow1, weekRow2, weekRow3, weekRow4, weekRow5, weekRow6, weekRow7);
	}

	private void setupLayouts(Stage primaryStage) {
		// ad box
		adSplitBoxAnna = new HBox(10, adDetailsAnna, adImageViewAnna);
		adSplitBoxAnna.setAlignment(Pos.CENTER);
		advertisementBoxAnna = new VBox(10, advertisementAnna, adSplitBoxAnna, linkAnna);
		advertisementBoxAnna.getStyleClass().add("advertisementBox");
//     advertisementBox.setStyle ("-fx-background-color: #FFD1DF;");
		// set up info and ads box

//		adSplitBox = new HBox(10, adDetails, adImageView);
//		adSplitBox.setAlignment(Pos.CENTER);

		weatherInformationBox = new VBox(10, infoSection, infoFirstSection, infoSecondSection, infoThirdSection);
		weatherInformationBox.getStyleClass().add("weatherInformationBox");
		// ad box
		advertisementBoxAnna = new VBox(10, advertisementAnna, adSplitBoxAnna, linkAnna);
		advertisementBoxAnna.getStyleClass().add("advertisementBox");
//     advertisementBox.setStyle ("-fx-background-color: #FFD1DF;");
		infoAndAdsBox = new HBox(10, weatherInformationBox, advertisementBoxAnna);

		// set up info and ads box
		infoAndAdsBox = new HBox(10, weatherInformationBox, advertisementBoxAnna);
//     infoAndAdsBox.getStyleClass().add("infoAndAdsBox");
//     infoAndAdsBox.setStyle ("-fx-background-color: grey;");

		locationSearch = new HBox(10, locationInput, buttonSearch);
		locationSearch.getStyleClass().add("locationSearch");

		headerBox = new HBox(10, title, locationSearch);
		headerBox.getStyleClass().add("headerBox");

		headerAndInfoBox = new VBox(10, headerBox, infoAndAdsBox);
		headerAndInfoBox.setStyle ("-fx-background-color: white;");

		// week content
		adSplitBoxMichelle = new HBox(10, adDetailsMichelle, adImageViewMichelle);
		adSplitBoxMichelle.setAlignment(Pos.CENTER);
		advertisementBoxMichelle = new VBox(10, advertisementMichelle, adSplitBoxMichelle, linkMichelle);
		advertisementBoxMichelle.getStyleClass().add("advertisementBox");
		weekContent = new HBox(10, weekBox, advertisementBoxMichelle);

		weekHeaderAndInfoBox = new VBox(10,headerAndInfoBox, weekContent);

		BorderPane mainLayout = new BorderPane();
		mainLayout.setTop(navigationBarBox);
		mainLayout.setCenter(headerAndInfoBox);

		Scene mainScene = new Scene(mainLayout, 900, 600);
		mainScene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

		primaryStage.setScene(mainScene);

		buttonHome.setOnAction(e -> {mainLayout.setCenter(headerAndInfoBox);});
		buttonWeek.setOnAction(e -> {mainLayout.setCenter(weekHeaderAndInfoBox);});
	}
}

