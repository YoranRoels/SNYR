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
    private Evaluatie[] evaluatie = new Evaluatie[AANTALEVALUTIES];
    private Rijtechniek[] rijtechniek = new Rijtechniek[AANTALEVALUTIES];
    private Verkeerstechniek[]  verkeerstechniek = new Verkeerstechniek[AANTALEVALUTIES];
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
        for(int i=0;i<rijtechniek.length;i++){
            rijtechniek[i]=new Rijtechniek();
        }
        for(int i=0;i<evaluatie.length;i++){
            evaluatie[i]=new Evaluatie();
        }
        for(int i=0;i<verkeerstechniek.length;i++){
            verkeerstechniek[i]=new Verkeerstechniek();
        }
        for(int i=0;i<Attitude.length;i++){
            Attitude[i]="";
        }
        for(int i=0;i<level.length;i++){
            level[i]=0;
        }
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
}
