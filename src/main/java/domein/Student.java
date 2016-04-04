package domein;

/**
 *
 * @author Yoran
 */
public class Student 
{
    private String voornaam;
    private String achternaam;
    private String email;
    private String fotoURL;
    
    
    private final static int AANTALEVALUTIES=3;
    
    /*boolean array die bij houdt of een evaluatie al is gebruitkt*/
    private Boolean[] evasDone = new Boolean[AANTALEVALUTIES];
    /*arrays die de verschillende skills bijhouden*/
    private Skills[] skills = new Skills[AANTALEVALUTIES];
    private DriveTechnic[] drivetechnics = new DriveTechnic[AANTALEVALUTIES];
    private TrafficTechnic[]  traffictechnics = new TrafficTechnic[AANTALEVALUTIES];
    private String[] attitudes = new String[AANTALEVALUTIES];
    /*levels, is de voortgang in de progressbar --> hoe ver staat de student al*/
    private int[] levels = new int[AANTALEVALUTIES];
    /*hoeveeste evaluatie dit is*/
    private int evanumber;
    
    
    
    public Student(String voornaam, String achternaam, String email, String fotoURL)
    {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.fotoURL = fotoURL;
        evanumber=0;
        for(int i=0;i<drivetechnics.length;i++){
            drivetechnics[i]=new DriveTechnic();
        }
        for(int i=0;i<skills.length;i++){
            skills[i]=new Skills();
        }
        for(int i=0;i<traffictechnics.length;i++){
            traffictechnics[i]=new TrafficTechnic();
        }
        for(int i=0;i<attitudes.length;i++){
            attitudes[i]="";
        }
        for(int i=0;i<levels.length;i++){
            levels[i]=0;
        }
        for(int i=0;i<evasDone.length;i++){
            evasDone[i]=false;
        }
        /*eerste eva wordt nu sowieso open gedaan dus true*/
        evasDone[0]=true;
    }

    public Student(String voornaam, String achternaam, String email, String fotoURL, int evanumber, Skills[] skills,DriveTechnic[] drivetechnics,TrafficTechnic[] traffictechnics,Boolean[] evasDone) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.fotoURL = fotoURL;
        this.evanumber = evanumber;
        this.traffictechnics=traffictechnics;
        this.drivetechnics=drivetechnics;
        this.skills=skills;
        this.evasDone=evasDone;
    }
    

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }

    public Skills[] getSkills() {
        return skills;
    }
    public Skills getCurrentEvalutie(){
        return skills[evanumber];
    }

    public void setSkills(Skills[] evaluatie) {
        this.skills = evaluatie;
    }

    public DriveTechnic[] getDriveTechnics() {
        return drivetechnics;
    }
    public DriveTechnic getCurrentDriveTechnic(){
        return drivetechnics[evanumber];
    }

    public void setDriveTechnics(DriveTechnic[] rijtechniek) {
        this.drivetechnics = rijtechniek;
    }

    public TrafficTechnic[] getTrafficTechnics() {
        return traffictechnics;
    }
    public TrafficTechnic getCurrentTrafficTechnic(){
        return traffictechnics[evanumber];
    }

    public void setTrafficTechnics(TrafficTechnic[] verkeerstechniek) {
        this.traffictechnics = verkeerstechniek;
    }

    public String[] getAttitude() {
        return attitudes;
    }
    
    public String getCurrentAttitude(){
        return attitudes[evanumber];
    }

    public void setAttitude(String[] Attitudes) {
        this.attitudes = Attitudes;
    }
    public void setCurrentAttitude(String attitude){
        attitudes[evanumber]=attitude;
    }

    public int[] getLevel() {
        return levels;
    }
    
    public int getCurrentLevel(){
        return levels[evanumber];
    }

    public void setLevel(int[] levels) {
        this.levels = levels;
    }

    public int getEvanumber() {
        return evanumber;
    }

    public void setEvanumber(int newevanumber) {
        /*bij setten kijken of de evaluatie al gedaan is, indien nog niet huidig proces kopieren*/
        if(!evasDone[newevanumber]){
           /*kopieren*/
            System.out.println("kopieren");
            kopierSkills(newevanumber);
            kopierDriveTechnic(newevanumber);
            kopierTrafficTechnic(newevanumber);
            attitudes[newevanumber]=attitudes[evanumber].toUpperCase();
            /*we beginnen aan de nieuwe dus op true*/
            evasDone[newevanumber]=true;
        }
        evanumber = newevanumber;
        System.out.println(evanumber);
    }
    
    @Override
    public String toString(){
        return achternaam+" "+voornaam;
    }
    
    public void kopierSkills(int newevanumber){
        skills[newevanumber].setCitytraffic(skills[evanumber].isCitytraffic());
        skills[newevanumber].setFueling(skills[evanumber].isFueling());
        skills[newevanumber].setGps(skills[evanumber].isGps());
        skills[newevanumber].setTires(skills[evanumber].isTires());
        skills[newevanumber].setEmergencystop(skills[evanumber].isEmergencystop());
        skills[newevanumber].setDoublelane(skills[evanumber].isDoublelane());
        skills[newevanumber].setLights(skills[evanumber].isLights());
        skills[newevanumber].setOilcheck(skills[evanumber].isOilcheck());
        skills[newevanumber].setRoundabout(skills[evanumber].isRoundabout());
        skills[newevanumber].setHighway(skills[evanumber].isHighway());
    }
    
    public void kopierDriveTechnic(int newevanumber){
        drivetechnics[newevanumber].setPosture(drivetechnics[evanumber].getPosture());
        drivetechnics[newevanumber].setClutch(drivetechnics[evanumber].getClutch());
        drivetechnics[newevanumber].setBraking(drivetechnics[evanumber].getBraking());
        drivetechnics[newevanumber].setSteering(drivetechnics[evanumber].getSteering());
        drivetechnics[newevanumber].setShifting(drivetechnics[evanumber].getShifting());
        drivetechnics[newevanumber].setLooking(drivetechnics[evanumber].getLooking());
        drivetechnics[newevanumber].setParking(drivetechnics[evanumber].getParking());
        drivetechnics[newevanumber].setTurning(drivetechnics[evanumber].getTurning());
        drivetechnics[newevanumber].setGarage(drivetechnics[evanumber].getGarage());
        drivetechnics[newevanumber].setReverse(drivetechnics[evanumber].getReverse());
        drivetechnics[newevanumber].setSteeringPractice(drivetechnics[evanumber].getSteeringPractice());
        drivetechnics[newevanumber].setHillHandbrake(drivetechnics[evanumber].getHillHandbrake());
        drivetechnics[newevanumber].setHillBalancing(drivetechnics[evanumber].getHillBalancing());
    }
    
    public void kopierTrafficTechnic(int newevanumber){
        traffictechnics[newevanumber].setIndicators(traffictechnics[evanumber].getIndicators());
        traffictechnics[newevanumber].setPublicroad(traffictechnics[evanumber].getPublicroad());
        traffictechnics[newevanumber].setPriority(traffictechnics[evanumber].getPriority());
        traffictechnics[newevanumber].setSign(traffictechnics[evanumber].getSign());
        traffictechnics[newevanumber].setSpeed(traffictechnics[evanumber].getSpeed());
        traffictechnics[newevanumber].setDistance(traffictechnics[evanumber].getDistance());
        traffictechnics[newevanumber].setOvertaking(traffictechnics[evanumber].getOvertaking());
        traffictechnics[newevanumber].setCrossing(traffictechnics[evanumber].getCrossing());
        traffictechnics[newevanumber].setTurningleft(traffictechnics[evanumber].getTurningleft());
        traffictechnics[newevanumber].setTurningright(traffictechnics[evanumber].getTurningright());
    }
    
}
