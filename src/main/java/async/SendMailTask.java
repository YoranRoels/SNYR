package async;

import domein.Student;
import java.util.List;
import javafx.concurrent.Task;
import javafx.scene.image.WritableImage;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import json.StudentWriter;

/**
 *
 * @author Yoran
 */
public class SendMailTask extends Task<Void> 
{
    private final WebTarget studentResource;
    private final int studentNr;
    private List<WritableImage> imagesToSend;

    public SendMailTask(int studentNr, List<WritableImage> imagesToSend)
    {
        this.studentNr = studentNr;
        this.imagesToSend = imagesToSend;
        studentResource = ClientBuilder.newClient()
                .target("http://localhost:8080/SNYR-backend/api")
                .path("mail")
                .register(StudentWriter.class);
    }
    
    @Override
    protected Void call() throws Exception {
        Response response = studentResource.request().post(Entity.entity(studentNr, MediaType.APPLICATION_JSON));
        switch (response.getStatus()){
            case 201:
                return null;
            case 400:
                throw new BadRequestException();
            default:
                throw new RuntimeException();
        }
    }
}
