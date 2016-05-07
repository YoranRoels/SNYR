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
    private int studentnr;
    
    
    private final static int AANTALEVALUTIES=3;
    
    /*boolean array die bij houdt of een evaluatie al is gebruitkt*/
    private Boolean[] evasDone = new Boolean[AANTALEVALUTIES];
    /*arrays die de verschillende skills bijhouden*/
    private Skills[] skills = new Skills[AANTALEVALUTIES];
    private DriveTechnic[] drivetechnics = new DriveTechnic[AANTALEVALUTIES];
    private TrafficTechnic[]  traffictechnics = new TrafficTechnic[AANTALEVALUTIES];
    private String[] attitudes = new String[AANTALEVALUTIES];
    /*progress van de progressbar/abx level*/
    private Double[] progreses = new Double[AANTALEVALUTIES];
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
        for(int i=0;i<progreses.length;i++){
            progreses[i]=0.0;
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

    public int getStudentnr() {
        return studentnr;
    }

    public void setStudentnr(int studentnr) {
        this.studentnr = studentnr;
    }
    
    
    
    public Double[] getProgreses() {
        return progreses;
    }

    public void setProgreses(Double[] progreses) {
        this.progreses = progreses;
    }
    
    public double getCurrentProgres(){
        return progreses[evanumber];
    }
    
    public void setCurrentProgres(double i){
        progreses[evanumber]=i;
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
    
    public Skills getCurrentSkills(){
        return skills[evanumber];
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

    public Boolean[] getEvasDone() {
        return evasDone;
    }

    public void setEvasDone(Boolean[] evasDone) {
        this.evasDone = evasDone;
    }
    

    public void setEvanumber(int newevanumber) {
        /*bij setten kijken of de evaluatie al gedaan is, indien nog niet huidig proces kopieren*/
        if(!evasDone[newevanumber]){
           /*kopieren door copy constructor*/
           drivetechnics[newevanumber]=new DriveTechnic(drivetechnics[evanumber]);
           traffictechnics[newevanumber]=new TrafficTechnic(traffictechnics[evanumber]);
           skills[newevanumber]=new Skills(skills[evanumber]);
           attitudes[newevanumber]=attitudes[evanumber];
           progreses[newevanumber]=progreses[evanumber];
           /*we beginnen aan de nieuwe dus op true*/
           evasDone[newevanumber]=true;
        }
        evanumber = newevanumber;
    }
    
    @Override
    public String toString(){
        return achternaam+" "+voornaam;
    }
}
