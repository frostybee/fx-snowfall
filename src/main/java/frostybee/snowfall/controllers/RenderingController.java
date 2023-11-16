package frostybee.snowfall.controllers;

import frostybee.snowfall.helpers.AppHelper;
import frostybee.snowfall.helpers.SimulationSettings;
import frostybee.snowfall.models.SnowFlake;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Rendering controller of the snowfall simulation.
 *
 * @author frostybee
 */
public class RenderingController {

    private final Random random = new Random();
    private int nbrOfSnowflakes = 400;
    private List<SnowFlake> snowflakes = new ArrayList<>();
    private double angle = Math.random();
    private AnimationTimer animation;
    private SimulationSettings settings;
    private Canvas canvas;

    public RenderingController(Canvas canvas, SimulationSettings settings) {
        this.settings = settings;
        this.canvas = canvas;
        createAnimationLoop();
        //createSnowFlakes();
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

    public void createSnowFlakes() {
        clearCanvas(canvas.getGraphicsContext2D());
        // Create and initialize the snowflakes.
        snowflakes.clear();
        for (int i = 0; i < settings.getSnowflakesNbr(); i++) {
            //double radius = AppHelper.getRandomDouble(2.25, settings.getMaxRadius());                        
            double radius = random.nextDouble(2.25, settings.getMaxRadius());
            double dropSpeed = AppHelper.getRandomDouble(-0.5, settings.getMaxDropSpeed());
            double windSpeed = AppHelper.getRandomDouble(-0.3, settings.getMaxWindSpeed());            
            windSpeed = (settings.getWindDirection() == SimulationSettings.WindDirection.RIGHT)
                    ? windSpeed * -1 : windSpeed;
            //Invert the wind direction: windSpeed = windSpeed * -1;
            double x = AppHelper.getRandomDouble(0, canvas.getWidth());
            double y = AppHelper.getRandomDouble(0, 100);
            //double y = AppHelper.getRandomDouble(0, canvas.getHeight());
            // Generate a random white'ish color... 
            Color color = Color.rgb(255, 255, 255, random.nextDouble(0.3, 0.9));
            snowflakes.add(new SnowFlake(x, y, radius, windSpeed, dropSpeed, color));
        }
    }

    public void render(GraphicsContext gc) {
        angle += 0.002;
        snowflakes.forEach((SnowFlake flake) -> {
            flake.draw(gc);
            // Update the flake's next x/y.            
            flake.update(canvas.getWidth(), canvas.getHeight(),
                    angle, settings.isWindy());
        });
    }

    public void clearCanvas(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void startSimulation() {
        if (snowflakes.isEmpty()) {
            createSnowFlakes();
        }
        if (animation != null) {
            animation.start();
        }
    }

    public void stopSimulation() {
        if (animation != null) {
            animation.stop();
        }
    }

    void resetSimulation() {
        stopSimulation();
        createSnowFlakes();
    }
}
