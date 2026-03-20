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
import java.text.SimpleDateFormat;
import javafx.geometry.Insets;
import java.time.LocalDate;

// use https://api.weather.gov/gridpoints/LOT/77,70/forecast to see where data is
// idk how to do humidity, lets just scratch it.

//adding adaptor class
class Adaptor {
	private Period period;

	public Adaptor(Period p) {
		this.period = p;
	}

	public String getDate(SimpleDateFormat sdf) {
		return sdf.format(period.startTime);
	}
}
//proxy
class Proxy {
	private ArrayList<Period> cache = null;

	public ArrayList<Period> getForecast(String office, int x, int y) {
		if (cache != null) {
			return cache;
		}
		cache = WeatherAPI.getForecast(office, x, y);
		return cache;
	}
}


public class JavaFX extends Application {

	Label day3Date, day3Temp, day3DayTemp, day3NightTemp, day3Wind;
	Label day2Date, day2Temp, day2DayTemp, day2NightTemp, day2Wind;
	Label day1Date, day1Temp, day1DayTemp, day1NightTemp, day1Wind;
	TextField day1Precip, day1Humidity;
	TextField day2Precip, day2Humidity;
	TextField day3Precip, day3Humidity;
	Label title, todayWeatherText, advertisementMaple, advertisementAnna, advertisementMichelle, moodOfDay, dayText, nightText;
	Button buttonHome, day3Button, buttonWeek, buttonSearch, buttonTemperature;
	Hyperlink linkAnna, linkMichelle, linkMaple;
	TextArea precipitation, adDetailsAnna, adDetailsMichelle, adDetailsMaple;
	TextField locationInput, date, temperature, dayTemp, nightTemp, mood, wind, direction;

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

	VBox headerAndInfoBox, weekHeaderAndInfoBox, weatherInformationBox, advertisementBoxAnna, advertisementBoxMichelle, advertisementBoxMaple, windAndDirectionBox, precipAndHumidBox, moodBox;
	HBox weekBox, navigationBarBox, infoAndAdsBox, infoSection, infoFirstSection, infoSecondSection, infoThirdSection, adSplitBoxAnna, adSplitBoxMichelle, adSplitBoxMaple, weekContent;

	ImageView weatherIcon, searchIcon, homeIcon, day3Icon, weekIcon, adImageViewAnna, adImageViewMichelle, adImageViewMaple, pictureOfMood;

	VBox day3HeaderAndInfoBox, day1Info, day2Info, day3Info;
	HBox day3Content, threeDaysInfoSec, day1Temps, day2Temps, day3Temps;

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
		day3Code("LOT", 77, 70);
		setupLayouts(primaryStage);

		loadWeather("LOT", 77, 70); // Default to Chicago

		primaryStage.show();
	}

	private void navigation() {
		homeIcon = new ImageView(new Image(getClass().getResource("/icons/Home.png").toExternalForm()));
		homeIcon.setFitWidth(40); homeIcon.setFitHeight(40); homeIcon.setPreserveRatio(true);
		buttonHome = new Button();
		buttonHome.setGraphic(homeIcon);

		day3Icon = new ImageView(new Image(getClass().getResource("/icons/3-Day.png").toExternalForm()));
		day3Icon.setFitWidth(40); day3Icon.setFitHeight(40); day3Icon.setPreserveRatio(true);
		day3Button = new Button();
		day3Button.setGraphic(day3Icon);

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

		navigationBarBox = new HBox(10, buttonHome, day3Button, buttonWeek, headerBox);
		navigationBarBox.getStyleClass().add("navigationBarBox");
	}

	private void ads() {
		advertisementAnna = new Label("Advertisement");
		advertisementAnna.getStyleClass().add("ad-details");
		adDetailsAnna = new TextArea("Looking for a local dental assistant? She lived on Monkey Jungle Road and that seemed to explain all of her strangeness. She says she has the ability to hear the soundtrack of your life. There's a growing trend among teenagers of using frisbees as go-cart wheels. Note: this was generated by https://randomwordgenerator.com/sentence.php");
		adDetailsAnna.setWrapText(true);
		adDetailsAnna.setEditable(false);
		adDetailsAnna.setPrefRowCount(3);
		adImageViewAnna = new ImageView(new Image(getClass().getResource("/linkedIn/Anna's_LinkedIn.jpg").toExternalForm()));
		adImageViewAnna.setFitHeight(100); adImageViewAnna.setFitWidth(100);
		linkAnna = new Hyperlink("Visit Anna's LinkedIn");
		linkAnna.setOnAction(e -> getHostServices().showDocument("https://www.linkedin.com/in/annachoi817"));

		advertisementMichelle = new Label("Advertisement");
		advertisementMichelle.getStyleClass().add("ad-details");
		adDetailsMichelle = new TextArea("Looking for a therapist? Waffles are always better without fire ants and fleas.\n" +
				"Combines are no longer just for farms.\n" +
				"She did her best to help him.\n" +
				"She works two jobs to make ends meet; at least, that was her reason for not having time to join us.");
		adDetailsMichelle.setWrapText(true);
		adDetailsMichelle.setEditable(false);
		adDetailsMichelle.setPrefRowCount(3);
		adImageViewMichelle = new ImageView(new Image(getClass().getResource("/linkedIn/Michelle's_LinkedIn.png").toExternalForm()));
		adImageViewMichelle.setFitHeight(100); adImageViewMichelle.setFitWidth(100);
		linkMichelle = new Hyperlink("Visit Michelle's LinkedIn");
		linkMichelle.setOnAction(e -> getHostServices().showDocument("https://www.linkedin.com/in/michelle-ye-700911299/"));

		advertisementMaple = new Label("Advertisement");
		advertisementMaple.getStyleClass().add("ad-details");
		adDetailsMaple = new TextArea("Look at this DIFFERENT and \nUNLIKE the other girls cat in the world");
		adImageViewMaple = new ImageView(new Image(getClass().getResource("/linkedIn/Maple_LinkedIn.jpg").toExternalForm()));
		adDetailsMaple.setWrapText(true);
		adDetailsMaple.setEditable(false);
		adDetailsMaple.setPrefRowCount(3);
		adImageViewMaple.setFitHeight(100); adImageViewMaple.setFitWidth(100);
		linkMaple = new Hyperlink("Adopt a cat!");
		linkMaple.setOnAction(e -> getHostServices().showDocument("https://www.pawschicago.org/"));

	}

	private void weatherInfo() {
		weatherIcon = new ImageView(new Image(getClass().getResource("/icons/Weather.png").toExternalForm()));
		weatherIcon.setFitHeight(70); weatherIcon.setFitWidth(70);
		buttonTemperature = new Button("K/F/C");

		todayWeatherText = new Label("Today's Weather");
		temperature = new TextField();
		temperature.setEditable(false);
		date = new TextField();
		date.setEditable(false);
		dayText = new Label("Day: ");
		dayTemp = new TextField();
		dayTemp.setEditable(false);
		nightText = new Label("Night: ");
		nightTemp = new TextField();
		nightTemp.setEditable(false);
		wind = new TextField();
		wind.setEditable(false);
		direction = new TextField();
		direction.setEditable(false);
		precipitation = new TextArea();
		precipitation.setEditable(false);
		precipitation.setWrapText(true);
		precipitation.setPrefRowCount(3);
		precipitation.setPrefWidth(250);
		precipitation.setPrefHeight(150);
//		humidity = new TextField("Humidity");
//		humidity.setEditable(false);
		moodOfDay = new Label("Mood of the Day:");
		mood = new TextField();
		mood.setEditable(false);
		pictureOfMood = new ImageView();

		windAndDirectionBox = new VBox(10, new Label("Wind Information"), wind, direction);
		precipAndHumidBox = new VBox(10, new Label("Day Overview"), precipitation);
		moodBox = new VBox(10, moodOfDay, mood, pictureOfMood);
		moodBox.setAlignment(Pos.CENTER);

		infoSection = new HBox(10, weatherIcon, todayWeatherText, buttonTemperature);
		infoSection.setAlignment(Pos.CENTER_LEFT);
		infoFirstSection = new HBox(10, new Label("Temp:"), temperature, new Label("Date:"), date);
		infoFirstSection.setAlignment(Pos.CENTER_LEFT);
		infoSecondSection = new HBox(10, dayText, dayTemp, nightText, nightTemp);
		infoSecondSection.setAlignment(Pos.CENTER_LEFT);
		infoThirdSection = new HBox(10, windAndDirectionBox, precipAndHumidBox, moodBox);
	}

	private void weekCode() {
		weekDay1 = new TextField();
		weekDay1.setEditable(false);
		weekDay1DayTemp = new TextField();
		weekDay1DayTemp.setEditable(false);
		weekDay1NightTemp = new TextField();
		weekDay1NightTemp.setEditable(false);
		weekDay1Precip = new TextField();
		weekDay1Precip.setEditable(false);
		weekDay1Wind = new TextField();
		weekDay1Wind.setEditable(false);
		weekDay1Direction = new TextField();
		weekDay1Direction.setEditable(false);
//		weekDay1Humidity = new TextField();
//		weekDay1Humidity.setEditable(false);
		VBox weekRow1 = new VBox(5, new Label("Day 1"), weekDay1, weekDay1DayTemp, weekDay1NightTemp, weekDay1Wind);

		weekDay2 = new TextField();
		weekDay2.setEditable(false);
		weekDay2DayTemp = new TextField();
		weekDay2DayTemp.setEditable(false);
		weekDay2NightTemp = new TextField();
		weekDay2NightTemp.setEditable(false);
		weekDay2Precip = new TextField();
		weekDay2Precip.setEditable(false);
		weekDay2Wind = new TextField();
		weekDay2Wind.setEditable(false);
		weekDay2Direction = new TextField();
		weekDay2Direction.setEditable(false);
//		weekDay2Humidity = new TextField();
//		weekDay2Humidity.setEditable(false);
		VBox weekRow2 = new VBox(5, new Label("Day 2"), weekDay2, weekDay2DayTemp, weekDay2NightTemp, weekDay2Wind);

		weekDay3 = new TextField();
		weekDay3.setEditable(false);
		weekDay3DayTemp = new TextField();
		weekDay3DayTemp.setEditable(false);
		weekDay3NightTemp = new TextField();
		weekDay3NightTemp.setEditable(false);
		weekDay3Precip = new TextField();
		weekDay3Precip.setEditable(false);
		weekDay3Wind = new TextField();
		weekDay3Wind.setEditable(false);
		weekDay3Direction = new TextField();
		weekDay3Direction.setEditable(false);
//		weekDay3Humidity = new TextField();
//		weekDay3Humidity.setEditable(false);
		VBox weekRow3 = new VBox(5, new Label("Day 3"), weekDay3, weekDay3DayTemp, weekDay3NightTemp, weekDay3Wind);

		weekDay4 = new TextField();
		weekDay4.setEditable(false);
		weekDay4DayTemp = new TextField();
		weekDay4DayTemp.setEditable(false);
		weekDay4NightTemp = new TextField();
		weekDay4NightTemp.setEditable(false);
		weekDay4Precip = new TextField();
		weekDay4Precip.setEditable(false);
		weekDay4Wind = new TextField();
		weekDay4Wind.setEditable(false);
		weekDay4Direction = new TextField();
		weekDay4Direction.setEditable(false);
//		weekDay4Humidity = new TextField();
//		weekDay4Humidity.setEditable(false);
		VBox weekRow4 = new VBox(5, new Label("Day 4"), weekDay4, weekDay4DayTemp, weekDay4NightTemp, weekDay4Wind);

		weekDay5 = new TextField();
		weekDay5.setEditable(false);
		weekDay5DayTemp = new TextField();
		weekDay5DayTemp.setEditable(false);
		weekDay5NightTemp = new TextField();
		weekDay5NightTemp.setEditable(false);
		weekDay5Precip = new TextField();
		weekDay5Precip.setEditable(false);
		weekDay5Wind = new TextField();
		weekDay5Wind.setEditable(false);
		weekDay5Direction = new TextField();
		weekDay5Direction.setEditable(false);
//		weekDay5Humidity = new TextField();
//		weekDay5Humidity.setEditable(false);
		VBox weekRow5 = new VBox(5, new Label("Day 5"), weekDay5, weekDay5DayTemp, weekDay5NightTemp, weekDay5Wind);

		weekDay6 = new TextField();
		weekDay6.setEditable(false);
		weekDay6DayTemp = new TextField();
		weekDay6DayTemp.setEditable(false);
		weekDay6NightTemp = new TextField();
		weekDay6NightTemp.setEditable(false);
		weekDay6Precip = new TextField();
		weekDay6Precip.setEditable(false);
		weekDay6Wind = new TextField();
		weekDay6Wind.setEditable(false);
		weekDay6Direction = new TextField();
		weekDay6Direction.setEditable(false);
//		weekDay6Humidity = new TextField();
//		weekDay6Humidity.setEditable(false);
		VBox weekRow6 = new VBox(5, new Label("Day 6"), weekDay6, weekDay6DayTemp, weekDay6NightTemp, weekDay6Wind);

		weekDay7 = new TextField();
		weekDay7.setEditable(false);
		weekDay7DayTemp = new TextField();
		weekDay7DayTemp.setEditable(false);
		weekDay7NightTemp = new TextField();
		weekDay7NightTemp.setEditable(false);
		weekDay7Precip = new TextField();
		weekDay7Precip.setEditable(false);
		weekDay7Wind = new TextField();
		weekDay7Wind.setEditable(false);
		weekDay7Direction = new TextField();
		weekDay7Direction.setEditable(false);
//		weekDay7Humidity = new TextField();
//		weekDay7Humidity.setEditable(false);
		VBox weekRow7 = new VBox(5, new Label("Day 7"), weekDay7, weekDay7DayTemp, weekDay7NightTemp, weekDay7Wind);

		weekBox = new HBox(10, weekRow1, weekRow2, weekRow3, weekRow4, weekRow5, weekRow6, weekRow7);
		weekBox.getStyleClass().add("weekBox");
	}

	private void setupLayouts(Stage primaryStage) {
		adSplitBoxAnna = new HBox(10, adDetailsAnna, adImageViewAnna);
		adSplitBoxAnna.setPrefSize(300, 150);
		adSplitBoxAnna.setMinSize(200, 150);
		adSplitBoxAnna.setMaxSize(400, 150);
		advertisementBoxAnna = new VBox(10, advertisementAnna, adSplitBoxAnna, linkAnna);
		advertisementBoxAnna.getStyleClass().add("advertisementBox");

		weatherInformationBox = new VBox(10, infoSection, infoFirstSection, infoSecondSection, infoThirdSection);
		weatherInformationBox.getStyleClass().add("weatherInformationBox");

		infoAndAdsBox = new HBox(10, weatherInformationBox, advertisementBoxAnna);
		headerAndInfoBox = new VBox(10, infoAndAdsBox);

		adSplitBoxMichelle = new HBox(10, adDetailsMichelle, adImageViewMichelle);

		adSplitBoxMichelle.setPrefSize(300, 150);
		adSplitBoxMichelle.setMinSize(200, 150);
		adSplitBoxMichelle.setMaxSize(400, 150);
		advertisementBoxMichelle = new VBox(10, advertisementMichelle, adSplitBoxMichelle, linkMichelle);
		advertisementBoxMichelle.getStyleClass().add("advertisementBox");

		weekContent = new HBox(10, weekBox, advertisementBoxMichelle);
		weekHeaderAndInfoBox = new VBox(10, weekContent);

		adSplitBoxMaple = new HBox(10, adDetailsMaple, adImageViewMaple);
		advertisementBoxMaple = new VBox(10, advertisementMaple, adSplitBoxMaple, linkMaple);
		advertisementBoxMaple.getStyleClass().add("advertisementBox");

		threeDaysInfoSec.getStyleClass().add("weatherInformationBox");
		day3Content = new HBox(10, threeDaysInfoSec, advertisementBoxMaple);
		day3HeaderAndInfoBox = new VBox(10, day3Content);

		threeDaysInfoSec.getStyleClass().add("weatherInformationBox");
		day3Content = new HBox(10, threeDaysInfoSec, advertisementBoxMaple);
		day3HeaderAndInfoBox = new VBox(10, day3Content);

		BorderPane mainLayout = new BorderPane();
		mainLayout.getStyleClass().add("mainLayout");
		mainLayout.setTop(navigationBarBox);
		mainLayout.setCenter(headerAndInfoBox);
		BorderPane.setMargin(headerAndInfoBox, new Insets(10, 0, 0, 0));
		BorderPane.setMargin(day3HeaderAndInfoBox, new Insets(10, 0, 0, 0));
		BorderPane.setMargin(weekHeaderAndInfoBox, new Insets(10, 0, 0, 0));

		Scene mainScene = new Scene(mainLayout, 950, 500);
		mainScene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
		primaryStage.setScene(mainScene);

		buttonHome.setOnAction(e -> mainLayout.setCenter(headerAndInfoBox));
		day3Button.setOnAction(e -> {mainLayout.setCenter(day3HeaderAndInfoBox);});
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
//		Proxy proxy1 = new Proxy();
//		ArrayList<Period> forecast = proxy1.getForecast("LOT", 77, 70);
		ArrayList<Period> forecast = WeatherAPI.getForecast(office, gridX, gridY);
		if (forecast == null) return;

		Period today = forecast.get(0);
		Period tonight = forecast.get(1);
		currentTempF = today.temperature;

		temperature.setText(today.temperature + "°F");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		date.setText(sdf.format(today.startTime));
//		date.setText(today.name);
		dayTemp.setText(today.temperature + "°F");
		nightTemp.setText(tonight.temperature + "°F");
		wind.setText("Speed: " + today.windSpeed);
		direction.setText("Direction: " + today.windDirection);
		precipitation.setText(today.detailedForecast);
		pictureOfMood.getStyleClass().add("image-view");
		if (currentTempF < 30) {
			mood.setText("Erm, what is dis!?");
			pictureOfMood.setImage(new Image(getClass().getResource("/images/whatIsThisWeather.jpg").toString()));
//			pictureOfMood.getStyleClass().add("image-view");
			pictureOfMood.setFitHeight(90);
			pictureOfMood.setFitWidth(90);
		} else if (currentTempF < 50) {
			mood.setText("Sadge (◞ ‸ ◟)");
			pictureOfMood.setImage(new Image(getClass().getResource("/images/sadgeCat.jpg").toExternalForm()));
			pictureOfMood.setFitHeight(90);
			pictureOfMood.setFitWidth(90);
		} else if (currentTempF < 75) {
			mood.setText("Chilling (─ ‿ ─)");
			pictureOfMood.setImage(new Image(getClass().getResource("/images/chillingCat.jpg").toExternalForm()));
			pictureOfMood.setFitHeight(70);
			pictureOfMood.setFitWidth(70);
		} else if (currentTempF < 100) {
			mood.setText("A Bit Hot (◞ ‸ ◟)");
			pictureOfMood.setImage(new Image(getClass().getResource("/images/aBitHot.jpg").toExternalForm()));
			pictureOfMood.setFitHeight(70);
			pictureOfMood.setFitWidth(70);
		} else {
			mood.setText("Happy ◝(ᵔᗜᵔ)◜");
			pictureOfMood.setImage(new Image(getClass().getResource("/images/sunnyDay.jpg").toExternalForm()));
			pictureOfMood.setFitHeight(70);
			pictureOfMood.setFitWidth(70);
		}

		Period day1 = forecast.get(0);
		Period day2 = forecast.get(1);
		Period day3 = forecast.get(2);
		Period day4 = forecast.get(3);
		Period day5 = forecast.get(4);
		Period day6 = forecast.get(5);
		Period day7 = forecast.get(6);

		day1Temp.setText(forecast.get(0).name);
		day1DayTemp.setText(forecast.get(0).temperature + "°F");
		day1NightTemp.setText(forecast.get(1).temperature + "°F");
		day1Wind.setText("Wind: " + forecast.get(0).windDirection + " " + forecast.get(0).windSpeed);
		day1Precip.setText(forecast.get(0).shortForecast);


		day2Temp.setText(forecast.get(2).name);
		day2DayTemp.setText(forecast.get(2).temperature + "°F");
		day2NightTemp.setText(forecast.get(3).temperature + "°F");
		day2Wind.setText("Wind: " + forecast.get(2).windDirection + " " + forecast.get(2).windSpeed);
		day2Precip.setText(forecast.get(2).shortForecast);

		day3Temp.setText(forecast.get(4).name);
		day3DayTemp.setText(forecast.get(4).temperature + "°F");
		day3NightTemp.setText(forecast.get(5).temperature + "°F");
		day3Wind.setText("Wind: " + forecast.get(4).windDirection + " " + forecast.get(4).windSpeed);
		day3Precip.setText(forecast.get(4).shortForecast);

		Adaptor daya = new Adaptor(forecast.get(0));
		weekDay1.setText(daya.getDate(sdf));
		weekDay1.setText(sdf.format(day1.startTime));
		weekDay1DayTemp.setText(day1.temperature + "°F");
		weekDay1Wind.setText(day1.windSpeed);
		weekDay1NightTemp.setText(forecast.get(1).temperature + "°F");


		weekDay2.setText(sdf.format(day2.startTime));
		weekDay2DayTemp.setText(day2.temperature + "°F");
		weekDay2Wind.setText(day2.windSpeed);
		weekDay2NightTemp.setText(forecast.get(2).temperature + "°F");

		weekDay3.setText(sdf.format(day3.startTime));
		weekDay3DayTemp.setText(day3.temperature + "°F");
		weekDay3Wind.setText(day3.windSpeed);
		weekDay3NightTemp.setText(forecast.get(3).temperature + "°F");

		weekDay4.setText(sdf.format(day4.startTime));
		weekDay4DayTemp.setText(day4.temperature + "°F");
		weekDay4Wind.setText(day4.windSpeed);
		weekDay4NightTemp.setText(forecast.get(4).temperature + "°F");

		weekDay5.setText(sdf.format(day5.startTime));
		weekDay5DayTemp.setText(day5.temperature + "°F");
		weekDay5Wind.setText(day5.windSpeed);
		weekDay5NightTemp.setText(forecast.get(5).temperature + "°F");

		weekDay6.setText(sdf.format(day6.startTime));
		weekDay6DayTemp.setText(day6.temperature + "°F");
		weekDay6Wind.setText(day6.windSpeed);
		weekDay6NightTemp.setText(forecast.get(6).temperature + "°F");

		weekDay7.setText(sdf.format(day7.startTime));
		weekDay7DayTemp.setText(day7.temperature + "°F");
		weekDay7Wind.setText(day7.windSpeed);
		weekDay7NightTemp.setText(forecast.get(6).temperature + "°F");

//		weekDay2.setText(forecast.get(2).name);
//		weekDay2DayTemp.setText(forecast.get(2).temperature + "°F");
//
//		weekDay3.setText(forecast.get(4).name);
//		weekDay3DayTemp.setText(forecast.get(4).temperature + "°F");
//
//		weekDay4.setText(forecast.get(6).name);
//		weekDay4DayTemp.setText(forecast.get(6).temperature + "°F");
//
//		weekDay5.setText(forecast.get(8).name);
//		weekDay5DayTemp.setText(forecast.get(8).temperature + "°F");
//
//		weekDay6.setText(forecast.get(10).name);
//		weekDay6DayTemp.setText(forecast.get(10).temperature + "°F");
//
//		weekDay7.setText(forecast.get(12).name);
//		weekDay7DayTemp.setText(forecast.get(12).temperature + "°F");
	}

	private void day3Code(String office, int gridX, int gridY) {
		Proxy proxy1 = new Proxy();
		ArrayList<Period> forecast = proxy1.getForecast("LOT", 77, 70);
//		ArrayList<Period> forecast = WeatherAPI.getForecast(office, gridX, gridY);
		if (forecast == null) return;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Period day1 = forecast.get(0);
		Period day2 = forecast.get(1);
		Period day3 = forecast.get(2);

		day1Date = new Label();
		day1Date.setText(sdf.format(day1.startTime));
//		day1Date.setText(day3.startTime.format(sdf));
//		LocalDate today = LocalDate.now();
//		day1Date = new Label(today.toString());
		day1Temp = new Label();
		day1DayTemp = new Label();
		day1NightTemp = new Label();
		day1Precip = new TextField();
		day1Wind = new Label();
//		day1Humidity = new TextField();

//		LocalDate tomorrow = today.plusDays(1);
//		day2Date = new Label(tomorrow.toString());
//		day2Date.setText(day3.startTime.format(sdf));
		day2Date = new Label();
		day2Date.setText(sdf.format(day2.startTime));
		day2Temp = new Label();
		day2DayTemp = new Label();
		day2NightTemp = new Label();
		day2Precip = new TextField();
		day2Wind = new Label();
//		day2Humidity = new TextField();

//		LocalDate overmorrow = today.plusDays(2);
//		day3Date = new Label(overmorrow.toString());
//		day3Date.setText(sdf.format(day3.startTime));
//		day3Date.setText(day3.startTime.format(sdf));
		day3Date = new Label();
		day3Date.setText(sdf.format(day3.startTime));
		day3Temp = new Label();
		day3DayTemp = new Label();
		day3NightTemp = new Label();
		day3Precip = new TextField();
		day3Wind = new Label();
//		day3Humidity = new TextField();

		day1Temps = new HBox(10, day1DayTemp, day1NightTemp);
		day1Temps.setAlignment(Pos.CENTER);
		day1Info = new VBox(10, day1Date, day1Temp, day1Temps, day1Wind, day1Precip);
		day1Info.getStyleClass().add("day");

		day2Temps = new HBox(10, day2DayTemp, day2NightTemp);
		day2Temps.setAlignment(Pos.CENTER);
		day2Info = new VBox(10, day2Date, day2Temp, day2Temps, day2Wind, day2Precip);
		day2Info.getStyleClass().add("day");

		day3Temps = new HBox(10 ,day3DayTemp, day3NightTemp);
		day3Temps.setAlignment(Pos.CENTER);
		day3Info = new VBox(10, day3Date, day3Temp, day3Temps, day3Wind, day3Precip);
		day3Info.getStyleClass().add("day");

		threeDaysInfoSec = new HBox(10, day1Info, day2Info, day3Info);
	}
}