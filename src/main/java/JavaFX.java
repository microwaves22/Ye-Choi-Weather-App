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

import java.util.ArrayList;
import weather.Period;
import weather.WeatherAPI;

public class JavaFX extends Application {
	//
	// INITIALIZING
	//
	Hyperlink linkAnna, linkMichelle;
	Label title, advertisement, advertisementAnna, advertisementMichelle, adDetailsAnna, adDetailsMichelle, todayWeatherText;
	Button buttonHome, buttonWeek, buttonSearch, buttonTemperature;
	TextField locationInput, date, temperature, dayText, dayTemp, nightText, nightTemp, moodOfDay, mood, pictureOfMood, wind, direction, precipitation, humidity;
	VBox headerAndInfoBox, weatherInformationBox, advertisementBoxAnna, advertisementBoxMichelle, windAndDirectionBox, precipAndHumidBox, moodBox;

	VBox weekDay1Info, weekDay2Info, weekDay3Info, weekDay4Info, weekDay5Info, weekDay6Info, weekDay7Info;
	HBox weekBox, navigationBarBox;
	VBox weekRow1, weekRow2, weekRow3, weekRow4, weekRow5, weekRow6, weekRow7;
	TextField weekDay1, weekDay1DayTemp, weekDay1NightTemp, weekDay1Precip, weekDay1Wind, weekDay1Direction, weekDay1Humidity;
	TextField weekDay2, weekDay2DayTemp, weekDay2NightTemp, weekDay2Precip, weekDay2Wind, weekDay2Direction, weekDay2Humidity;
	TextField weekDay3, weekDay3DayTemp, weekDay3NightTemp, weekDay3Precip, weekDay3Wind, weekDay3Direction, weekDay3Humidity;
	TextField weekDay4, weekDay4DayTemp, weekDay4NightTemp, weekDay4Precip, weekDay4Wind, weekDay4Direction, weekDay4Humidity;
	TextField weekDay5, weekDay5DayTemp, weekDay5NightTemp, weekDay5Precip, weekDay5Wind, weekDay5Direction, weekDay5Humidity;
	TextField weekDay6, weekDay6DayTemp, weekDay6NightTemp, weekDay6Precip, weekDay6Wind, weekDay6Direction, weekDay6Humidity;
	TextField weekDay7, weekDay7DayTemp, weekDay7NightTemp, weekDay7Precip, weekDay7Wind, weekDay7Direction, weekDay7Humidity;

	HBox root, infoAndAdsBox, headerBox, locationSearch,  infoSection, infoFirstSection, infoSecondSection, infoThirdSection, adSplitBoxAnna, adSplitBoxMichelle;
	Image weatherImage,searchImage, homeImage, weekImage, adImage, adImageMichelle, adImageAnna;
	ImageView weatherIcon, searchIcon, homeIcon, weekIcon, adImageViewAnna, adImageViewMichelle;
	HBox weekContent;
	VBox weekHeaderAndInfoBox;
	double currentTempF;
	String currentUnit = "F";

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
		ads();
		weatherInfo();
		weekCode();
		setupLayouts(primaryStage);

		loadWeather("LOT",77,70);;

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

		locationSearch = new HBox(10, locationInput, buttonSearch);
		locationSearch.getStyleClass().add("locationSearch");

		headerBox = new HBox(20, title, locationSearch);
		headerBox.getStyleClass().add("headerBox");

		navigationBarBox = new HBox(10, buttonHome, buttonWeek, headerBox);
		navigationBarBox.getStyleClass().add("navigationBarBox");
	}

	private void ads() {
		// Advertisement Box details
		advertisementAnna = new Label("Advertisement");
		advertisementAnna.getStyleClass().add("ad-details");
		adDetailsAnna = new Label("Looking for a local dental assistant? \nLook no further!");
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
		advertisementMichelle.getStyleClass().add("ad-details");
		adDetailsMichelle = new Label("Looking for a therapist? Look no further! \nYour own therapist with a B.S. in Psychology and hasn't finished her degree yet.");
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
		infoSection = new HBox(10, weatherIcon, todayWeatherText, buttonTemperature );
		infoFirstSection = new HBox(10, temperature, date);
		infoSecondSection = new HBox(10, dayText, dayTemp, nightText, nightTemp);
		infoThirdSection = new HBox(10, windAndDirectionBox, precipAndHumidBox, moodBox);
	}

	private void weekCode() {

		// DAY 1
		weekDay1 = new TextField("Day");
		weekDay1DayTemp = new TextField("Day Temp");
		weekDay1NightTemp = new TextField("Night Temp");
		weekDay1Precip = new TextField("Precip");
		weekDay1Wind = new TextField("Wind");
		weekDay1Direction = new TextField("Direction");
		weekDay1Humidity = new TextField("Humidity");

		weekRow1 = new VBox(5,
				weekDay1,
				weekDay1DayTemp,
				weekDay1NightTemp,
				weekDay1Precip,
				weekDay1Wind,
				weekDay1Direction,
				weekDay1Humidity
		);

		// DAY 2
		weekDay2 = new TextField("Day");
		weekDay2DayTemp = new TextField("Day Temp");
		weekDay2NightTemp = new TextField("Night Temp");
		weekDay2Precip = new TextField("Precip");
		weekDay2Wind = new TextField("Wind");
		weekDay2Direction = new TextField("Direction");
		weekDay2Humidity = new TextField("Humidity");

		weekRow2 = new VBox(5,
				weekDay2,
				weekDay2DayTemp,
				weekDay2NightTemp,
				weekDay2Precip,
				weekDay2Wind,
				weekDay2Direction,
				weekDay2Humidity
		);

		// repeat pattern for remaining days

		weekRow3 = new VBox(5,
				weekDay3 = new TextField("Day"),
				weekDay3DayTemp = new TextField("Day Temp"),
				weekDay3NightTemp = new TextField("Night Temp"),
				weekDay3Precip = new TextField("Precip"),
				weekDay3Wind = new TextField("Wind"),
				weekDay3Direction = new TextField("Direction"),
				weekDay3Humidity = new TextField("Humidity")
		);

		weekRow4 = new VBox(5,
				weekDay4 = new TextField("Day"),
				weekDay4DayTemp = new TextField("Day Temp"),
				weekDay4NightTemp = new TextField("Night Temp"),
				weekDay4Precip = new TextField("Precip"),
				weekDay4Wind = new TextField("Wind"),
				weekDay4Direction = new TextField("Direction"),
				weekDay4Humidity = new TextField("Humidity")
		);

		weekRow5 = new VBox(5,
				weekDay5 = new TextField("Day"),
				weekDay5DayTemp = new TextField("Day Temp"),
				weekDay5NightTemp = new TextField("Night Temp"),
				weekDay5Precip = new TextField("Precip"),
				weekDay5Wind = new TextField("Wind"),
				weekDay5Direction = new TextField("Direction"),
				weekDay5Humidity = new TextField("Humidity")
		);

		weekRow6 = new VBox(5,
				weekDay6 = new TextField("Day"),
				weekDay6DayTemp = new TextField("Day Temp"),
				weekDay6NightTemp = new TextField("Night Temp"),
				weekDay6Precip = new TextField("Precip"),
				weekDay6Wind = new TextField("Wind"),
				weekDay6Direction = new TextField("Direction"),
				weekDay6Humidity = new TextField("Humidity")
		);

		weekRow7 = new VBox(5,
				weekDay7 = new TextField("Day"),
				weekDay7DayTemp = new TextField("Day Temp"),
				weekDay7NightTemp = new TextField("Night Temp"),
				weekDay7Precip = new TextField("Precip"),
				weekDay7Wind = new TextField("Wind"),
				weekDay7Direction = new TextField("Direction"),
				weekDay7Humidity = new TextField("Humidity")
		);

		weekBox = new HBox(10,
				weekRow1,
				weekRow2,
				weekRow3,
				weekRow4,
				weekRow5,
				weekRow6,
				weekRow7
		);

		weekBox.getStyleClass().add("weekBox");
	}

	private void setupLayouts(Stage primaryStage) {
		// ad box
		adSplitBoxAnna = new HBox(10, adDetailsAnna, adImageViewAnna);
		adSplitBoxAnna.setAlignment(Pos.CENTER);
		advertisementBoxAnna = new VBox(10, advertisementAnna, adSplitBoxAnna, linkAnna);
		advertisementBoxAnna.getStyleClass().add("advertisementBox");

		// set up info and ads box
		weatherInformationBox = new VBox(10, infoSection, infoFirstSection, infoSecondSection, infoThirdSection);
		weatherInformationBox.getStyleClass().add("weatherInformationBox");
		infoAndAdsBox = new HBox(10, weatherInformationBox, advertisementBoxAnna);

		// set up info and ads box
		infoAndAdsBox = new HBox(10, weatherInformationBox, advertisementBoxAnna);
//     infoAndAdsBox.getStyleClass().add("infoAndAdsBox");
//     infoAndAdsBox.setStyle ("-fx-background-color: grey;");


		// week content
		adSplitBoxMichelle = new HBox(10, adDetailsMichelle, adImageViewMichelle);
		adSplitBoxMichelle.setAlignment(Pos.CENTER);
		advertisementBoxMichelle = new VBox(10, advertisementMichelle, adSplitBoxMichelle, linkMichelle);
		advertisementBoxMichelle.getStyleClass().add("advertisementBox");
		weekContent = new HBox(10, weekBox, advertisementBoxMichelle);

		headerAndInfoBox = new VBox(10, infoAndAdsBox);
		weekHeaderAndInfoBox = new VBox(10, weekContent);

		BorderPane mainLayout = new BorderPane();
		mainLayout.getStyleClass().add("mainLayout");
		mainLayout.setTop(navigationBarBox);
		mainLayout.setCenter(headerAndInfoBox);

		Scene mainScene = new Scene(mainLayout, 900, 600);
		mainScene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

		primaryStage.setScene(mainScene);

		buttonHome.setOnAction(e -> {mainLayout.setCenter(headerAndInfoBox);});
		buttonWeek.setOnAction(e -> {mainLayout.setCenter(weekHeaderAndInfoBox);});
		buttonSearch.setOnAction(e -> {

			String location = locationInput.getText().toLowerCase();

			if(location.contains("chicago")){
				loadWeather("LOT",77,70);
			}
			else if(location.contains("new york")){
				loadWeather("OKX",33,37);
			}
			else if(location.contains("los angeles")){
				loadWeather("LOX",154,44);
			}
		});

		buttonTemperature.setOnAction(e -> {

			if(currentUnit.equals("F")){

				double c = (currentTempF - 32) * 5/9;
				temperature.setText(String.format("%.1f °C", c));

				currentUnit = "C";
			}
			else if(currentUnit.equals("C")){

				double k = (currentTempF - 32) * 5/9 + 273.15;
				temperature.setText(String.format("%.1f K", k));

				currentUnit = "K";
			}
			else{

				temperature.setText(currentTempF + " °F");

				currentUnit = "F";
			}

		});
	}

	private void loadWeather(String office, int gridX, int gridY) {

		ArrayList<Period> forecast = WeatherAPI.getForecast(office, gridX, gridY);

		if (forecast == null) {
			throw new RuntimeException("Forecast did not load");
		}

		// TODAY
		Period tonight = forecast.get(1);
		Period today = forecast.get(0);

		currentTempF = today.temperature;

		temperature.setText(today.temperature + "°F");
		dayText.setText(today.name);

		// day / night temps
		dayTemp.setText("Day: " + today.temperature + "°F");
		nightText.setText(tonight.name);
		nightTemp.setText("Night: " + tonight.temperature + "°F");

		// wind
		wind.setText(today.windSpeed);
		direction.setText(today.windDirection);

		// precipitation (from forecast text)
		precipitation.setText(today.shortForecast);

		// humidity (NWS API usually doesn't give this easily)
		humidity.setText("Humidity: N/A");

		// mood logic
		int temp = today.temperature;

		if(temp < 50){
			moodOfDay.setText("Mood of the Day:");
			mood.setText("Sad");
//			pictureOfMood.setText("Cold Day");
		}
		else if(temp < 70){
			moodOfDay.setText("Mood of the Day:");
			mood.setText("Chill");
//			pictureOfMood.setText("Cool Weather");
		}
		else if(temp < 85){
			moodOfDay.setText("Mood of the Day:");
			mood.setText("Happy");
//			pictureOfMood.setText("Nice Day");
		}
		else{
			moodOfDay.setText("Mood of the Day:");
			mood.setText("Hot");
//			pictureOfMood.setText("Too Hot");
		}

		// WEEK
		weekDay1.setText(forecast.get(0).name);
		weekDay1DayTemp.setText("Day: " + forecast.get(0).temperature + "°F");
		weekDay1NightTemp.setText("Night: " + forecast.get(1).temperature + "°F");
		weekDay1Wind.setText("Wind: " + forecast.get(0).windSpeed);
		weekDay1Direction.setText("Dir: " + forecast.get(0).windDirection);
		weekDay1Humidity.setText("Humidity: N/A");
		weekDay1Precip.setText("Precip: N/A");
	}
}

