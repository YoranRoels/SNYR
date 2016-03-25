package main.java.domein;

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
        this.color=color.WHITE;
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
