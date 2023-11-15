package frostybee.snowfall.helpers;

/**
 *
 * @author frostybee
 */
public class SimulationSettings {

    private int snowflakesNbr = 400;
    private double maxDropSpeed = 2.5;
    private double maxRadius = 7;
    private double maxWindSpeed = 7;
    private boolean isWindy = true;

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

    public boolean isIsWindy() {
        return isWindy;
    }

    public void setIsWindy(boolean isWindy) {
        this.isWindy = isWindy;
    }

}
