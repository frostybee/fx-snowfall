package frostybee.snowfall.models;

import frostybee.snowfall.helpers.AppHelper;
import frostybee.snowfall.helpers.SimulationSettings;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Represents a snowflake to be rendered.
 *
 * @author frostybee
 */
public class SnowFlake {

    private double x = 0;
    private double y = 0;
    private double radius = 0;
    private double windSpeed = 0;
    private double dropSpeed = 0;
    private Color color;

    public SnowFlake(double x, double y, double radius, double windSpeed, double dropSpeed, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.windSpeed = windSpeed;
        this.dropSpeed = dropSpeed;
    }

    public void update(double canvasWidth, double canvasHeight, double angle, boolean isWindy) {
        y += 1 + dropSpeed;        
        x += (isWindy) ? (Math.sin(angle) * 2) + this.windSpeed : (Math.sin(angle) * 2);
        //x += (Math.sin(angle) * 2);        
        if (x > canvasWidth + (radius * 2)) {
            x = AppHelper.getRandomDouble(-25, (radius * -2));
        } else if (x < (radius * -2)) {
            x = canvasWidth + AppHelper.getRandomDouble(radius * 2, 25);
        } else if (y > canvasHeight) {
            x = AppHelper.getRandomDouble(0, canvasWidth);
            y = radius * -2;
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x, y, radius, radius);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
