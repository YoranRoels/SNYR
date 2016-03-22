/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author sande
 */
public class Verkeerstechniek {
    
    private Status indicators;
    private Status publicroad;
    private Status priority;
    private Status trafficsignals;
    private Status speed;
    private Status distance;
    private Status overtaking;
    private Status crossing;
    private Status turningleft;
    private Status turningright;

    public Status getIndicators() {
        return indicators;
    }

    public void setIndicators(Status indicators) {
        this.indicators = indicators;
    }

    public Status getPublicroad() {
        return publicroad;
    }

    public void setPublicroad(Status publicroad) {
        this.publicroad = publicroad;
    }

    public Status getPriority() {
        return priority;
    }

    public void setPriority(Status priority) {
        this.priority = priority;
    }

    public Status getTrafficsignals() {
        return trafficsignals;
    }

    public void setTrafficsignals(Status trafficsignals) {
        this.trafficsignals = trafficsignals;
    }

    public Status getSpeed() {
        return speed;
    }

    public void setSpeed(Status speed) {
        this.speed = speed;
    }

    public Status getDistance() {
        return distance;
    }

    public void setDistance(Status distance) {
        this.distance = distance;
    }

    public Status getOvertaking() {
        return overtaking;
    }

    public void setOvertaking(Status overtaking) {
        this.overtaking = overtaking;
    }

    public Status getCrossing() {
        return crossing;
    }

    public void setCrossing(Status crossing) {
        this.crossing = crossing;
    }

    public Status getTurningleft() {
        return turningleft;
    }

    public void setTurningleft(Status turningleft) {
        this.turningleft = turningleft;
    }

    public Status getTurningright() {
        return turningright;
    }

    public void setTurningright(Status turningright) {
        this.turningright = turningright;
    }

    public Verkeerstechniek(Status indicators, Status publicroad, Status priority, Status trafficsignals, Status speed, Status distance, Status overtaking, Status crossing, Status turningleft, Status turningright) {
        this.indicators = indicators;
        this.publicroad = publicroad;
        this.priority = priority;
        this.trafficsignals = trafficsignals;
        this.speed = speed;
        this.distance = distance;
        this.overtaking = overtaking;
        this.crossing = crossing;
        this.turningleft = turningleft;
        this.turningright = turningright;
    }
    
    public Verkeerstechniek(){
        this.indicators = new Status();
        this.publicroad = new Status();
        this.priority = new Status();
        this.trafficsignals = new Status();
        this.speed = new Status();
        this.distance = new Status();
        this.overtaking = new Status();
        this.crossing = new Status();
        this.turningleft = new Status();
        this.turningright = new Status();
    }
    
}