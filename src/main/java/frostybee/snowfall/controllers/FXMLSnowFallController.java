package frostybee.snowfall.controllers;

import frostybee.snowfall.helpers.SimulationSettings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXMLL controller class for the snowfall app's main stage.
 *
 * @author frostybee
 */
public class FXMLSnowFallController {

    @FXML
    private Button btnStart;
    @FXML
    private Button btnStop;
    @FXML
    private Button btnReset;
    @FXML
    private Pane renderingPane;
    @FXML
    private Slider sldSnowflakesNbr;
    @FXML
    private Slider sldMaxDropSpeed;
    @FXML
    private VBox vbSettings;
    @FXML
    private Spinner spMaxFlakeRadius;
    @FXML
    private ToggleButton tgbtnWindON;
    @FXML
    private ToggleButton tgbtnWindOFF;
    @FXML
    private ToggleButton tgbtnWindDirectionRight;
    @FXML
    private ToggleButton tgbtnWindDirectionLeft;
    @FXML
    private Slider sldWindSpeed;
    @FXML
    private CheckBox chbBackground;

    //
    private RenderingController renderer;
    private SimulationSettings settings;
    private ImageView background;
    private Canvas canvas = new Canvas(1000, 1000);

    @FXML
    public void initialize() {
        settings = new SimulationSettings();
        renderer = new RenderingController(canvas, settings);
        initUiComponents();
    }

    private void initUiComponents() {
        vbSettings.setStyle(
                "-fx-border-color:#424242; -fx-border-width:1px;-fx-background-color:rgba(255, 255, 255, 0.87);");
        renderingPane.setStyle("-fx-border-color:#424242; -fx-border-width:1px;-fx-background-color:rgba(5, 5, 5, 0.97);");
        background = new ImageView(new Image(getClass().getResource("/images/montreal.jpg").toString()));
        renderingPane.getChildren().add(background);
        renderingPane.setBackground(Background.fill(Color.BLACK));
        renderingPane.getChildren().addAll(canvas);
        canvas.widthProperty().bind(renderingPane.widthProperty());
        canvas.heightProperty().bind(renderingPane.heightProperty());
        //-- Group the wind toggle buttons.         
        ToggleGroup tgWind = new ToggleGroup();
        this.tgbtnWindOFF.setToggleGroup(tgWind);
        this.tgbtnWindON.setToggleGroup(tgWind);
        //-- Group the wind direction toggle buttons.      
        ToggleGroup tgWindDirection = new ToggleGroup();
        this.tgbtnWindDirectionRight.setToggleGroup(tgWindDirection);
        this.tgbtnWindDirectionLeft.setToggleGroup(tgWindDirection);

        initEventHandlers();
    }

    private void initEventHandlers() {
        chbBackground.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                background.setVisible(false);
            } else {
                background.setVisible(true);
            }
        });
        sldSnowflakesNbr.valueProperty().addListener((observable, oldValue, newValue) -> {
            settings.setSnowflakesNbr(newValue.intValue());
            enableResetButton();
        });
        sldMaxDropSpeed.valueProperty().addListener((observable, oldValue, newValue) -> {
            settings.setMaxDropSpeed(newValue.doubleValue());
            enableResetButton();
        });
        spMaxFlakeRadius.valueProperty().addListener((observable, oldValue, newValue) -> {
            settings.setMaxRadius(Double.parseDouble(newValue.toString()));
            enableResetButton();
        });
        sldWindSpeed.valueProperty().addListener((observable, oldValue, newValue) -> {
            settings.setMaxWindSpeed(newValue.doubleValue());
            enableResetButton();
        });
        btnStart.setOnAction((event) -> {
            handleStartAnimation();
            disableSimulationButtons(true, false, false);
        });
        btnStop.setOnAction((event) -> {
            renderer.stopSimulation();
            disableSimulationButtons(false, true, true);
        });
        btnReset.setOnAction((event) -> {
            resetSimulation();
            disableSimulationButtons(false, true, true);
        });
        tgbtnWindOFF.setOnAction((event) -> {
            settings.setIsWindy(false);
        });
        tgbtnWindON.setOnAction((event) -> {
            settings.setIsWindy(true);
        });
        tgbtnWindDirectionRight.setOnAction((event) -> {
            settings.setWindDirection(SimulationSettings.WindDirection.RIGHT);
            enableResetButton();
        });
        tgbtnWindDirectionLeft.setOnAction((event) -> {
            settings.setWindDirection(SimulationSettings.WindDirection.LEFT);
            enableResetButton();
        });
    }

    private void handleStartAnimation() {
        renderer.startSimulation();
    }

    private void resetSimulation() {
        renderer.resetSimulation();
        disableSimulationButtons(false, true, true);
    }

    private void disableSimulationButtons(boolean start, boolean stop, boolean reset) {
        btnStart.setDisable(start);
        btnStop.setDisable(stop);
        btnReset.setDisable(reset);
    }

    public void stopAnimation() {
        renderer.stopSimulation();
    }

    private void enableResetButton() {
        disableSimulationButtons(true, true, false);
    }
}
