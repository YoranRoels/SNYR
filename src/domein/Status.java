package domein;

/**
 *
 * @author Yoran
 */
public class Status 
{
    private Color color;
    private String commentaar;

    public Status(Color color, String commentaar) {
        this.color = color;
        this.commentaar = commentaar;
    }
    public Status(){
        this.color=color.GRAY;
        this.commentaar="";
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getCommentaar() {
        return commentaar;
    }

    public void setCommentaar(String commentaar) {
        this.commentaar = commentaar;
    }
    
    
    
}
