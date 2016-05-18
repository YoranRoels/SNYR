package async;

import domein.Student;
import javafx.concurrent.Task;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author Yoran
 */
public class DeleteStudentTask extends Task<Void> 
{
    private final WebTarget studentListResource;

    public DeleteStudentTask(Student student)
    {
        studentListResource = ClientBuilder.newClient()
                .target("http://localhost:8080/rijschool/api")
                .path("students")
                .path(Integer.toString(student.getStudentnr()))
                .register(HttpAuthenticationFeature.basic("rijschoolevauser", "rijschoolevauser"));
    }
    
    @Override
    protected Void call() throws Exception {
        Response response = studentListResource.request().delete();
        switch (response.getStatus()){
            case 204:
                return null;
            case 401:
            case 403:
                throw new RuntimeException();
            case 404:
                throw new NotFoundException();
            default:
                throw new RuntimeException();
        }
    }
}
