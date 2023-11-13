package frostybee.snowfall.controllers;

import frostybee.snowfall.helpers.AppHelper;
import frostybee.snowfall.models.SnowFlake;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
    private VBox vbSettings;
    private final Random random = new Random();
    private int numberOfSnowFlakes = 1000;
    private Canvas canvas = new Canvas(1000, 1000);
    private List<SnowFlake> snowflakes = new ArrayList<>();
    private double angle = Math.random();
    private AnimationTimer animation;

    @FXML
    public void initialize() {
        initComponents();
    }

    private void initComponents() {
        vbSettings.setStyle(
                "-fx-border-color:#424242; -fx-border-width:1px;-fx-background-color:rgba(255, 255, 255, 0.87);");
        renderingPane.setStyle("-fx-border-color:#424242; -fx-border-width:1px;-fx-background-color:rgba(5, 5, 5, 0.97);");
        ImageView background = new ImageView(new Image(getClass().getResource("/images/montreal.jpg").toString()));
        renderingPane.getChildren().add(background);
        renderingPane.setBackground(Background.fill(Color.BLACK));
        renderingPane.getChildren().addAll(canvas);
        canvas.widthProperty().bind(renderingPane.widthProperty());
        canvas.heightProperty().bind(renderingPane.heightProperty());
        //
        createAnimationLoop();
        //
        btnStart.setOnAction((event) -> {
            handleStartAnimation();
            disableSimulationButtons(true, false, false);
        });
        btnStop.setOnAction((event) -> {
            stopAnimation();
            disableSimulationButtons(false, true, true);
        });
        btnReset.setOnAction((event) -> {
            resetSimulation();
            disableSimulationButtons(false, true, true);
        });
    }

    private void createAnimationLoop() {
        // Create the animation loop.
        animation = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                if (now - lastUpdate >= 27000000) {
                    lastUpdate = now;
                    //update();                                                                                
                    clearCanvas(gc);
                    render(gc);
                }
            }
        };
    }

    private void handleStartAnimation() {
        createSnowFlakes();
        animation.start();
    }

    private void createSnowFlakes() {
        // Create and initialize the snowflakes.
        snowflakes.clear();
        for (int i = 0; i < numberOfSnowFlakes; i++) {
            double radius = AppHelper.getRandomDouble(2.25, 8);
            double windSpeed = AppHelper.getRandomDouble(-0.3, 0.7);
            double dropSpeed = AppHelper.getRandomDouble(-0.5, 0.5);
            double x = AppHelper.getRandomDouble(110, canvas.getWidth());
            double y = AppHelper.getRandomDouble(110, canvas.getHeight());
            // Generate a random white'ish color... 
            Color color = Color.rgb(255, 255, 255, random.nextDouble(0.3, .8));
            snowflakes.add(new SnowFlake(x, y, radius, windSpeed, dropSpeed, color));
        }
    }

    public void render(GraphicsContext gc) {
        angle = this.angle + 0.007;
        snowflakes.forEach((SnowFlake flake) -> {
            flake.draw(gc);
            // Update the flake's next x/y.            
            flake.update(canvas.getWidth(), canvas.getHeight(), angle);
        });
    }

    private void resetSimulation() {
        stopAnimation();
        clearCanvas(canvas.getGraphicsContext2D());
        createSnowFlakes();
        disableSimulationButtons(false, true, true);
    }

    public void clearCanvas(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void disableSimulationButtons(boolean start, boolean stop, boolean reset) {
        btnStart.setDisable(start);
        btnStop.setDisable(stop);
        btnReset.setDisable(reset);
    }

    public void stopAnimation() {
        if (animation != null) {
            animation.stop();
        }
    }
}
