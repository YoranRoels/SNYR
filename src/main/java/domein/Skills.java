package domein;

/**
 *
 * @author Yoran
 */

public class Skills 
{
    private boolean fueling;
    private boolean gps;
    private boolean tires;
    private boolean emergencystop;
    private boolean citytraffic;
    private boolean doublelane;
    private boolean lights;
    private boolean oilcheck;
    private boolean roundabout;
    private boolean highway;

    public boolean isFueling() {
        return fueling;
    }

    public void setFueling(boolean fueling) {
        this.fueling = fueling;
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public boolean isTires() {
        return tires;
    }

    public void setTires(boolean tires) {
        this.tires = tires;
    }

    public boolean isEmergencystop() {
        return emergencystop;
    }

    public void setEmergencystop(boolean emergencystop) {
        this.emergencystop = emergencystop;
    }

    public boolean isCitytraffic() {
        return citytraffic;
    }

    public void setCitytraffic(boolean citytraffic) {
        this.citytraffic = citytraffic;
    }

    public boolean isDoublelane() {
        return doublelane;
    }

    public void setDoublelane(boolean doublelane) {
        this.doublelane = doublelane;
    }

    public boolean isLights() {
        return lights;
    }

    public void setLights(boolean lights) {
        this.lights = lights;
    }

    public boolean isOilcheck() {
        return oilcheck;
    }

    public void setOilcheck(boolean oilcheck) {
        this.oilcheck = oilcheck;
    }

    public boolean isRoundabout() {
        return roundabout;
    }

    public void setRoundabout(boolean roundabout) {
        this.roundabout = roundabout;
    }

    public boolean isHighway() {
        return highway;
    }

    public void setHighway(boolean highway) {
        this.highway = highway;
    }

    public Skills(boolean fueling, boolean gps, boolean tires, boolean emergencystop, boolean citytraffic, boolean doublelane, boolean lights, boolean oilcheck, boolean roundabout, boolean highway) {
        this.fueling = fueling;
        this.gps = gps;
        this.tires = tires;
        this.emergencystop = emergencystop;
        this.citytraffic = citytraffic;
        this.doublelane = doublelane;
        this.lights = lights;
        this.oilcheck = oilcheck;
        this.roundabout = roundabout;
        this.highway = highway;
    }
    
    public Skills(){
        this.citytraffic=false;
        this.doublelane=false;
        this.emergencystop=false;
        this.fueling=false;
        this.gps=false;
        this.highway=false;
        this.lights=false;
        this.oilcheck=false;
        this.roundabout=false;
        this.tires=false;
    }
    
    
}
