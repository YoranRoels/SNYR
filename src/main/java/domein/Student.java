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
    /*arrays die de verschillende evaluaties bijhouden*/
    private Evaluatie[] evaluaties = new Evaluatie[AANTALEVALUTIES];
    private DriveTechnic[] drivetechnics = new DriveTechnic[AANTALEVALUTIES];
    private TrafficTechnic[]  traffictechnics = new TrafficTechnic[AANTALEVALUTIES];
    private String[] Attitudes = new String[AANTALEVALUTIES];
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
        for(int i=0;i<evaluaties.length;i++){
            evaluaties[i]=new Evaluatie();
        }
        for(int i=0;i<traffictechnics.length;i++){
            traffictechnics[i]=new TrafficTechnic();
        }
        for(int i=0;i<Attitudes.length;i++){
            Attitudes[i]="";
        }
        for(int i=0;i<levels.length;i++){
            levels[i]=0;
        }
    }

    public Student(String voornaam, String achternaam, String email, String fotoURL, int evanumber, Evaluatie[] evaluaties,DriveTechnic[] drivetechnics,TrafficTechnic[] traffictechnics) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.fotoURL = fotoURL;
        this.evanumber = evanumber;
        this.traffictechnics=traffictechnics;
        this.drivetechnics=drivetechnics;
        this.evaluaties=evaluaties;
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

    public Evaluatie[] getEvaluaties() {
        return evaluaties;
    }
    public Evaluatie getCurrentEvalutie(){
        return evaluaties[evanumber];
    }

    public void setEvaluaties(Evaluatie[] evaluatie) {
        this.evaluaties = evaluatie;
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
        return Attitudes;
    }
    
    public String getCurrentAttitude(){
        return Attitudes[evanumber];
    }

    public void setAttitude(String[] Attitudes) {
        this.Attitudes = Attitudes;
    }
    public void setCurrentAttitude(String attitude){
        Attitudes[evanumber]=attitude;
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

    public void setEvanumber(int evanumber) {
        this.evanumber = evanumber;
    }
    
    @Override
    public String toString(){
        return achternaam+" "+voornaam;
    }
    
}
