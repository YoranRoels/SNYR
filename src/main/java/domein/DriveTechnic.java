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
public class DriveTechnic {
    
    private Status posture;
    private Status clutch;
    private Status braking;
    private Status steering;
    private Status shifting;
    private Status looking;
    private Status parking;
    private Status turning;
    private Status garage;
    private Status reverse;
    private Status steeringPractice;
    private Status hillHandbrake;
    private Status hillBalancing;
    
    private int driveId;

    public Status getPosture() {
        return posture;
    }

    public void setPosture(Status posture) {
        this.posture = posture;
    }

    public Status getClutch() {
        return clutch;
    }

    public void setClutch(Status clutch) {
        this.clutch = clutch;
    }

    public Status getBraking() {
        return braking;
    }

    public void setBraking(Status braking) {
        this.braking = braking;
    }

    public Status getSteering() {
        return steering;
    }

    public void setSteering(Status steering) {
        this.steering = steering;
    }

    public Status getShifting() {
        return shifting;
    }

    public void setShifting(Status shifting) {
        this.shifting = shifting;
    }

    public Status getLooking() {
        return looking;
    }

    public void setLooking(Status looking) {
        this.looking = looking;
    }

    public Status getParking() {
        return parking;
    }

    public void setParking(Status parking) {
        this.parking = parking;
    }

    public Status getTurning() {
        return turning;
    }

    public void setTurning(Status turning) {
        this.turning = turning;
    }

    public Status getGarage() {
        return garage;
    }

    public void setGarage(Status garage) {
        this.garage = garage;
    }

    public Status getReverse() {
        return reverse;
    }

    public void setReverse(Status reverse) {
        this.reverse = reverse;
    }

    public Status getSteeringPractice() {
        return steeringPractice;
    }

    public void setSteeringPractice(Status steeringExercise) {
        this.steeringPractice = steeringExercise;
    }

    public Status getHillHandbrake() {
        return hillHandbrake;
    }

    public void setHillHandbrake(Status hillHandbrake) {
        this.hillHandbrake = hillHandbrake;
    }

    public Status getHillBalancing() {
        return hillBalancing;
    }

    public void setHillBalancing(Status hillBalancing) {
        this.hillBalancing = hillBalancing;
    }

    public DriveTechnic(Status posture, Status clutch, Status braking, Status steering, Status shifting, Status looking, Status parking, Status turning, Status garage, Status reverse, Status steeringPratice, Status hillHandbrake, Status hillBalancing) {
        this.posture = posture;
        this.clutch = clutch;
        this.braking = braking;
        this.steering = steering;
        this.shifting = shifting;
        this.looking = looking;
        this.parking = parking;
        this.turning = turning;
        this.garage = garage;
        this.reverse = reverse;
        this.steeringPractice = steeringPratice;
        this.hillHandbrake = hillHandbrake;
        this.hillBalancing = hillBalancing;
    }
    /*kopie constructor*/
    public DriveTechnic(DriveTechnic other){
        this.posture = new Status(other.posture);
        this.clutch = new Status(other.clutch);
        this.braking = new Status(other.braking);
        this.steering = new Status(other.steering);
        this.shifting = new Status(other.shifting);
        this.looking = new Status(other.looking);
        this.parking = new Status(other.parking);
        this.turning = new Status(other.turning);
        this.garage = new Status(other.garage);
        this.reverse = new Status(other.reverse);
        this.steeringPractice = new Status(other.steeringPractice);
        this.hillHandbrake = new Status(other.hillHandbrake);
        this.hillBalancing = new Status(other.hillBalancing);
    }
    
    public DriveTechnic(){
        this.posture = new Status();
        this.clutch = new Status();
        this.braking = new Status();
        this.steering = new Status();
        this.shifting = new Status();
        this.looking = new Status();
        this.parking = new Status();
        this.turning = new Status();
        this.garage = new Status();
        this.reverse = new Status();
        this.steeringPractice = new Status();
        this.hillHandbrake = new Status();
        this.hillBalancing = new Status();
    }

    public int getDriveId() {
        return driveId;
    }

    public void setDriveId(int driveId) {
        this.driveId = driveId;
    }
    
    
}
