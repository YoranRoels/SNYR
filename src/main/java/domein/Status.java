package domein;

/**
 *
 * @author Yoran
 */
public class Status 
{
    private Color color;
    private String comment;

    public Status(Color color, String comment) {
        this.color = color;
        this.comment = comment;
    }
    public Status(){
        this.color=color.WHITE;
        this.comment="";
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String commentaar) {
        this.comment = commentaar;
    }
    
    
    
}
