import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;
import weather.Period;
import weather.WeatherAPI;

public class JavaFX extends Application {

	Label title, todayWeatherText, advertisementAnna, advertisementMichelle, adDetailsAnna, adDetailsMichelle;
	Button buttonHome, buttonWeek, buttonSearch, buttonTemperature;
	Hyperlink linkAnna, linkMichelle;

	TextField locationInput, date, temperature, dayText, dayTemp, nightText, nightTemp, moodOfDay, mood, pictureOfMood, wind, direction, precipitation, humidity;

	// Day 1
	TextField weekDay1, weekDay1DayTemp, weekDay1NightTemp, weekDay1Precip, weekDay1Wind, weekDay1Direction, weekDay1Humidity;
	// Day 2
	TextField weekDay2, weekDay2DayTemp, weekDay2NightTemp, weekDay2Precip, weekDay2Wind, weekDay2Direction, weekDay2Humidity;
	// Day 3
	TextField weekDay3, weekDay3DayTemp, weekDay3NightTemp, weekDay3Precip, weekDay3Wind, weekDay3Direction, weekDay3Humidity;
	// Day 4
	TextField weekDay4, weekDay4DayTemp, weekDay4NightTemp, weekDay4Precip, weekDay4Wind, weekDay4Direction, weekDay4Humidity;
	// Day 5
	TextField weekDay5, weekDay5DayTemp, weekDay5NightTemp, weekDay5Precip, weekDay5Wind, weekDay5Direction, weekDay5Humidity;
	// Day 6
	TextField weekDay6, weekDay6DayTemp, weekDay6NightTemp, weekDay6Precip, weekDay6Wind, weekDay6Direction, weekDay6Humidity;
	// Day 7
	TextField weekDay7, weekDay7DayTemp, weekDay7NightTemp, weekDay7Precip, weekDay7Wind, weekDay7Direction, weekDay7Humidity;

	VBox headerAndInfoBox, weekHeaderAndInfoBox, weatherInformationBox, advertisementBoxAnna, advertisementBoxMichelle, windAndDirectionBox, precipAndHumidBox, moodBox;
	HBox weekBox, navigationBarBox, infoAndAdsBox, infoSection, infoFirstSection, infoSecondSection, infoThirdSection, adSplitBoxAnna, adSplitBoxMichelle, weekContent;

	ImageView weatherIcon, searchIcon, homeIcon, weekIcon, adImageViewAnna, adImageViewMichelle;

	double currentTempF;
	String currentUnit = "F";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Choi's Yeather App");

		navigation();
		ads();
		weatherInfo();
		weekCode();
		setupLayouts(primaryStage);

		loadWeather("LOT", 77, 70); // Default to Chicago

		primaryStage.show();
	}

	private void navigation() {
		homeIcon = new ImageView(new Image(getClass().getResource("/icons/Home.png").toExternalForm()));
		homeIcon.setFitWidth(40); homeIcon.setFitHeight(40); homeIcon.setPreserveRatio(true);
		buttonHome = new Button();
		buttonHome.setGraphic(homeIcon);

		weekIcon = new ImageView(new Image(getClass().getResource("/icons/Week.png").toExternalForm()));
		weekIcon.setFitWidth(40); weekIcon.setFitHeight(40); weekIcon.setPreserveRatio(true);
		buttonWeek = new Button();
		buttonWeek.setGraphic(weekIcon);

		title = new Label("Choi's Yeather App");
		title.getStyleClass().add("title");

		locationInput = new TextField("Chicago");
		searchIcon = new ImageView(new Image(getClass().getResource("/icons/Search.png").toExternalForm()));
		searchIcon.setFitWidth(16); searchIcon.setFitHeight(16); searchIcon.setPreserveRatio(true);
		buttonSearch = new Button();
		buttonSearch.setGraphic(searchIcon);

		HBox locationSearch = new HBox(10, locationInput, buttonSearch);
		locationSearch.getStyleClass().add("locationSearch");

		HBox headerBox = new HBox(20, title, locationSearch);
		headerBox.getStyleClass().add("headerBox");

		navigationBarBox = new HBox(10, buttonHome, buttonWeek, headerBox);
		navigationBarBox.getStyleClass().add("navigationBarBox");
	}

	private void ads() {
		advertisementAnna = new Label("Advertisement");
		advertisementAnna.getStyleClass().add("ad-details");
		adDetailsAnna = new Label("Looking for a local dental assistant?");
		adImageViewAnna = new ImageView(new Image(getClass().getResource("/linkedIn/Anna's_LinkedIn.jpg").toExternalForm()));
		adImageViewAnna.setFitHeight(100); adImageViewAnna.setFitWidth(100);
		linkAnna = new Hyperlink("Visit Anna's LinkedIn");
		linkAnna.setOnAction(e -> getHostServices().showDocument("https://www.linkedin.com/in/annachoi817"));

		advertisementMichelle = new Label("Advertisement");
		advertisementMichelle.getStyleClass().add("ad-details");
		adDetailsMichelle = new Label("Looking for a therapist?");
		adImageViewMichelle = new ImageView(new Image(getClass().getResource("/linkedIn/Michelle's_LinkedIn.jpg").toExternalForm()));
		adImageViewMichelle.setFitHeight(100); adImageViewMichelle.setFitWidth(100);
		linkMichelle = new Hyperlink("Visit Michelle's LinkedIn");
		linkMichelle.setOnAction(e -> getHostServices().showDocument("https://www.linkedin.com/in/michelle-ye-700911299/"));
	}

	private void weatherInfo() {
		weatherIcon = new ImageView(new Image(getClass().getResource("/icons/Weather.png").toExternalForm()));
		weatherIcon.setFitHeight(50); weatherIcon.setFitWidth(50);
		buttonTemperature = new Button("K/F/C");

		todayWeatherText = new Label("Today's Weather");
		temperature = new TextField();
		date = new TextField();
		dayText = new TextField();
		dayTemp = new TextField();
		nightText = new TextField();
		nightTemp = new TextField();
		wind = new TextField();
		direction = new TextField();
		precipitation = new TextField();
		humidity = new TextField();
		moodOfDay = new TextField("Mood of the Day:");
		mood = new TextField();
		pictureOfMood = new TextField();

		windAndDirectionBox = new VBox(10, new Label("Wind Info"), wind, direction);
		precipAndHumidBox = new VBox(10, new Label("Stats"), precipitation, humidity);
		moodBox = new VBox(10, moodOfDay, mood);

		infoSection = new HBox(10, weatherIcon, todayWeatherText, buttonTemperature);
		infoFirstSection = new HBox(10, new Label("Temp:"), temperature, new Label("Date:"), date);
		infoSecondSection = new HBox(10, dayText, dayTemp, nightText, nightTemp);
		infoThirdSection = new HBox(10, windAndDirectionBox, precipAndHumidBox, moodBox);
	}

	private void weekCode() {
		weekDay1 = new TextField();
		weekDay1DayTemp = new TextField();
		weekDay1NightTemp = new TextField();
		weekDay1Precip = new TextField();
		weekDay1Wind = new TextField();
		weekDay1Direction = new TextField();
		weekDay1Humidity = new TextField();
		VBox weekRow1 = new VBox(5, new Label("Day 1"), weekDay1, weekDay1DayTemp, weekDay1NightTemp, weekDay1Wind);

		weekDay2 = new TextField();
		weekDay2DayTemp = new TextField();
		weekDay2NightTemp = new TextField();
		weekDay2Precip = new TextField();
		weekDay2Wind = new TextField();
		weekDay2Direction = new TextField();
		weekDay2Humidity = new TextField();
		VBox weekRow2 = new VBox(5, new Label("Day 2"), weekDay2, weekDay2DayTemp, weekDay2NightTemp, weekDay2Wind);

		weekDay3 = new TextField();
		weekDay3DayTemp = new TextField();
		weekDay3NightTemp = new TextField();
		weekDay3Precip = new TextField();
		weekDay3Wind = new TextField();
		weekDay3Direction = new TextField();
		weekDay3Humidity = new TextField();
		VBox weekRow3 = new VBox(5, new Label("Day 3"), weekDay3, weekDay3DayTemp, weekDay3NightTemp, weekDay3Wind);

		weekDay4 = new TextField();
		weekDay4DayTemp = new TextField();
		weekDay4NightTemp = new TextField();
		weekDay4Precip = new TextField();
		weekDay4Wind = new TextField();
		weekDay4Direction = new TextField();
		weekDay4Humidity = new TextField();
		VBox weekRow4 = new VBox(5, new Label("Day 4"), weekDay4, weekDay4DayTemp, weekDay4NightTemp, weekDay4Wind);

		weekDay5 = new TextField();
		weekDay5DayTemp = new TextField();
		weekDay5NightTemp = new TextField();
		weekDay5Precip = new TextField();
		weekDay5Wind = new TextField();
		weekDay5Direction = new TextField();
		weekDay5Humidity = new TextField();
		VBox weekRow5 = new VBox(5, new Label("Day 5"), weekDay5, weekDay5DayTemp, weekDay5NightTemp, weekDay5Wind);

		weekDay6 = new TextField();
		weekDay6DayTemp = new TextField();
		weekDay6NightTemp = new TextField();
		weekDay6Precip = new TextField();
		weekDay6Wind = new TextField();
		weekDay6Direction = new TextField();
		weekDay6Humidity = new TextField();
		VBox weekRow6 = new VBox(5, new Label("Day 6"), weekDay6, weekDay6DayTemp, weekDay6NightTemp, weekDay6Wind);

		weekDay7 = new TextField();
		weekDay7DayTemp = new TextField();
		weekDay7NightTemp = new TextField();
		weekDay7Precip = new TextField();
		weekDay7Wind = new TextField();
		weekDay7Direction = new TextField();
		weekDay7Humidity = new TextField();
		VBox weekRow7 = new VBox(5, new Label("Day 7"), weekDay7, weekDay7DayTemp, weekDay7NightTemp, weekDay7Wind);

		weekBox = new HBox(10, weekRow1, weekRow2, weekRow3, weekRow4, weekRow5, weekRow6, weekRow7);
		weekBox.getStyleClass().add("weekBox");
	}

	private void setupLayouts(Stage primaryStage) {
		adSplitBoxAnna = new HBox(10, adDetailsAnna, adImageViewAnna);
		advertisementBoxAnna = new VBox(10, advertisementAnna, adSplitBoxAnna, linkAnna);
		advertisementBoxAnna.getStyleClass().add("advertisementBox");

		weatherInformationBox = new VBox(10, infoSection, infoFirstSection, infoSecondSection, infoThirdSection);
		weatherInformationBox.getStyleClass().add("weatherInformationBox");

		infoAndAdsBox = new HBox(10, weatherInformationBox, advertisementBoxAnna);
		headerAndInfoBox = new VBox(10, infoAndAdsBox);

		adSplitBoxMichelle = new HBox(10, adDetailsMichelle, adImageViewMichelle);
		advertisementBoxMichelle = new VBox(10, advertisementMichelle, adSplitBoxMichelle, linkMichelle);
		advertisementBoxMichelle.getStyleClass().add("advertisementBox");

		weekContent = new HBox(10, weekBox, advertisementBoxMichelle);
		weekHeaderAndInfoBox = new VBox(10, weekContent);

		BorderPane mainLayout = new BorderPane();
		mainLayout.getStyleClass().add("mainLayout");
		mainLayout.setTop(navigationBarBox);
		mainLayout.setCenter(headerAndInfoBox);

		Scene mainScene = new Scene(mainLayout, 950, 650);
		mainScene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
		primaryStage.setScene(mainScene);

		buttonHome.setOnAction(e -> mainLayout.setCenter(headerAndInfoBox));
		buttonWeek.setOnAction(e -> mainLayout.setCenter(weekHeaderAndInfoBox));

		buttonSearch.setOnAction(e -> {
			String loc = locationInput.getText().toLowerCase();
			if(loc.contains("chicago")) loadWeather("LOT", 77, 70);
			else if(loc.contains("new york")) loadWeather("OKX", 33, 37);
			else if(loc.contains("los angeles")) loadWeather("LOX", 154, 44);
		});

		buttonTemperature.setOnAction(e -> {
			if (currentUnit.equals("F")) currentUnit = "C";
			else if (currentUnit.equals("C")) currentUnit = "K";
			else currentUnit = "F";
			updateAllTemps();
		});
	}

	private void updateAllTemps() {
		temperature.setText(convert(currentTempF));
	}

	private String convert(double f) {
		if (currentUnit.equals("C")) return String.format("%.1f °C", (f - 32) * 5/9);
		if (currentUnit.equals("K")) return String.format("%.1f K", (f - 32) * 5/9 + 273.15);
		return String.format("%.0f °F", f);
	}

	private void loadWeather(String office, int gridX, int gridY) {
		ArrayList<Period> forecast = WeatherAPI.getForecast(office, gridX, gridY);
		if (forecast == null) return;

		Period today = forecast.get(0);
		Period tonight = forecast.get(1);
		currentTempF = today.temperature;

		temperature.setText(today.temperature + "°F");
		date.setText(today.name);
		dayTemp.setText("Day: " + today.temperature + "°F");
		nightTemp.setText("Night: " + tonight.temperature + "°F");
		wind.setText(today.windSpeed);
		direction.setText(today.windDirection);
		precipitation.setText(today.shortForecast);

		if(currentTempF < 50) mood.setText("Sad");
		else if(currentTempF < 75) mood.setText("Chill");
		else mood.setText("Happy");

		weekDay1.setText(forecast.get(0).name);
		weekDay1DayTemp.setText(forecast.get(0).temperature + "°F");

		weekDay2.setText(forecast.get(2).name);
		weekDay2DayTemp.setText(forecast.get(2).temperature + "°F");

		weekDay3.setText(forecast.get(4).name);
		weekDay3DayTemp.setText(forecast.get(4).temperature + "°F");

		weekDay4.setText(forecast.get(6).name);
		weekDay4DayTemp.setText(forecast.get(6).temperature + "°F");

		weekDay5.setText(forecast.get(8).name);
		weekDay5DayTemp.setText(forecast.get(8).temperature + "°F");

		weekDay6.setText(forecast.get(10).name);
		weekDay6DayTemp.setText(forecast.get(10).temperature + "°F");

		weekDay7.setText(forecast.get(12).name);
		weekDay7DayTemp.setText(forecast.get(12).temperature + "°F");
	}
}