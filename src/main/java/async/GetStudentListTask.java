package async;

import domein.Student;
import java.util.List;
import javafx.concurrent.Task;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import json.StudentListReader;

/**
 *
 * @author Yoran
 */
public class GetStudentListTask extends Task<List<Student>>
{
    private final WebTarget userListResource;

    public GetStudentListTask()
    {
        userListResource = ClientBuilder.newClient()
                .target("http://localhost:8080/SNYR-backend/api")
                .path("students")
                .register(StudentListReader.class);
    }
    
    @Override
    protected List<Student> call() throws Exception {
        Response response = userListResource.request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200)
        {
            List<Student> students = response.readEntity(new GenericType<List<Student>>() {});
            return students;
        } else
        {
            throw new RuntimeException();
        }
    }
}
