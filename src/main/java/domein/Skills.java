package domein;

/**
 *
 * @author Yoran
 */

public class Skills 
{
    private Status fueling;
    private Status gps;
    private Status tires;
    private Status emergencystop;
    private Status city;
    private Status doublelane;
    private Status lights;
    private Status oilcheck;
    private Status roundabout;
    private Status highway;

    public Status getFueling() {
        return fueling;
    }

    public void setFueling(Status fueling) {
        this.fueling = fueling;
    }

    public Status getGps() {
        return gps;
    }

    public void setGps(Status gps) {
        this.gps = gps;
    }

    public Status getTires() {
        return tires;
    }

    public void setTires(Status tires) {
        this.tires = tires;
    }

    public Status getEmergencystop() {
        return emergencystop;
    }

    public void setEmergencystop(Status emergencystop) {
        this.emergencystop = emergencystop;
    }

    public Status getCity() {
        return city;
    }

    public void setCity(Status city) {
        this.city = city;
    }

    public Status getDoublelane() {
        return doublelane;
    }

    public void setDoublelane(Status doublelane) {
        this.doublelane = doublelane;
    }

    public Status getLights() {
        return lights;
    }

    public void setLights(Status lights) {
        this.lights = lights;
    }

    public Status getOilcheck() {
        return oilcheck;
    }

    public void setOilcheck(Status oilcheck) {
        this.oilcheck = oilcheck;
    }

    public Status getRoundabout() {
        return roundabout;
    }

    public void setRoundabout(Status roundabout) {
        this.roundabout = roundabout;
    }

    public Status getHighway() {
        return highway;
    }

    public void setHighway(Status highway) {
        this.highway = highway;
    }

    public Skills(Status fueling, Status gps, Status tires, Status emergencystop, Status citytraffic, Status doublelane, Status lights, Status oilcheck, Status roundabout, Status highway) {
        this.fueling = fueling;
        this.gps = gps;
        this.tires = tires;
        this.emergencystop = emergencystop;
        this.city = citytraffic;
        this.doublelane = doublelane;
        this.lights = lights;
        this.oilcheck = oilcheck;
        this.roundabout = roundabout;
        this.highway = highway;
    }
    
    public Skills(Skills other){ // deep copy constructor
        this.city = new Status(other.city);
        this.doublelane = new Status(other.doublelane);
        this.emergencystop = new Status(other.emergencystop);
        this.fueling = new Status(other.fueling);
        this.gps = new Status(other.gps);
        this.highway = new Status(other.highway);
        this.lights = new Status(other.lights);
        this.oilcheck = new Status(other.oilcheck);
        this.roundabout = new Status(other.roundabout);
        this.tires = new Status(other.tires);
    }
    
    public Skills(){
        this.city = new Status();
        this.doublelane = new Status();
        this.emergencystop = new Status();
        this.fueling = new Status();
        this.gps = new Status();
        this.highway = new Status();
        this.lights = new Status();
        this.oilcheck = new Status();
        this.roundabout = new Status();
        this.tires = new Status();
    }
    
    
    
    
}
