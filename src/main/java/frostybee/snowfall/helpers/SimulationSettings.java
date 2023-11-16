package frostybee.snowfall.helpers;

/**
 * Holds the settings of the snowfall simulation.
 *
 * @author frostybee
 */
public class SimulationSettings {

    public enum WindDirection {
        RIGHT,
        LEFT
    }

    private int snowflakesNbr = 400;
    private double maxDropSpeed = 2.5;
    private double maxRadius = 7;
    private double maxWindSpeed = 2.5;
    private boolean isWindy = true;
    private WindDirection windDirection = WindDirection.RIGHT;

    public SimulationSettings() {
    }

    public int getSnowflakesNbr() {
        return snowflakesNbr;
    }

    public void setSnowflakesNbr(int snowflakesNbr) {
        this.snowflakesNbr = snowflakesNbr;
    }

    public double getMaxDropSpeed() {
        return maxDropSpeed;
    }

    public void setMaxDropSpeed(double maxDropSpeed) {
        this.maxDropSpeed = maxDropSpeed;
    }

    public double getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(double maxRadius) {
        this.maxRadius = maxRadius;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public void setMaxWindSpeed(double maxWindSpeed) {
        this.maxWindSpeed = maxWindSpeed;
    }

    public boolean isWindy() {
        return isWindy;
    }

    public void setIsWindy(boolean isWindy) {
        this.isWindy = isWindy;
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }
}
