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
    private Rijtechniek[] rijtechnieks = new Rijtechniek[AANTALEVALUTIES];
    private Verkeerstechniek[]  verkeerstechnieks = new Verkeerstechniek[AANTALEVALUTIES];
    private String[] Attitude = new String[AANTALEVALUTIES];
    /*level, is de voortgang in de progressbar --> hoe ver staat de student al*/
    private int[] level = new int[AANTALEVALUTIES];
    /*hoeveeste evaluatie dit is*/
    private int evanumber;
    
    
    
    public Student(String voornaam, String achternaam, String email, String fotoURL)
    {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.fotoURL = fotoURL;
        for(int i=0;i<rijtechnieks.length;i++){
            rijtechnieks[i]=new Rijtechniek();
        }
        for(int i=0;i<evaluaties.length;i++){
            evaluaties[i]=new Evaluatie();
        }
        for(int i=0;i<verkeerstechnieks.length;i++){
            verkeerstechnieks[i]=new Verkeerstechniek();
        }
        for(int i=0;i<Attitude.length;i++){
            Attitude[i]="";
        }
        for(int i=0;i<level.length;i++){
            level[i]=0;
        }
    }

    public Student(String voornaam, String achternaam, String email, String fotoURL, int evanumber, Evaluatie[] evaluaties,Rijtechniek[] rijtechnieks,Verkeerstechniek[] verkeerstechnieks) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.fotoURL = fotoURL;
        this.evanumber = evanumber;
        this.verkeerstechnieks=verkeerstechnieks;
        this.rijtechnieks=rijtechnieks;
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

    public void setEvaluaties(Evaluatie[] evaluatie) {
        this.evaluaties = evaluatie;
    }

    public Rijtechniek[] getRijtechnieks() {
        return rijtechnieks;
    }

    public void setRijtechnieks(Rijtechniek[] rijtechniek) {
        this.rijtechnieks = rijtechniek;
    }

    public Verkeerstechniek[] getVerkeerstechnieks() {
        return verkeerstechnieks;
    }

    public void setVerkeerstechnieks(Verkeerstechniek[] verkeerstechniek) {
        this.verkeerstechnieks = verkeerstechniek;
    }

    public String[] getAttitude() {
        return Attitude;
    }

    public void setAttitude(String[] Attitude) {
        this.Attitude = Attitude;
    }

    public int[] getLevel() {
        return level;
    }

    public void setLevel(int[] level) {
        this.level = level;
    }

    public int getEvanumber() {
        return evanumber;
    }

    public void setEvanumber(int evanumber) {
        this.evanumber = evanumber;
    }
    
    @Override
    public String toString(){
        return voornaam+" "+achternaam;
    }
    
}
