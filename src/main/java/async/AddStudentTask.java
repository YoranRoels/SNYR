package async;

import domein.Student;
import javafx.concurrent.Task;
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
public class AddStudentTask extends Task<Void> 
{
    private final WebTarget studentListResource;
    private final Student student;

    public AddStudentTask(Student student)
    {
        this.student = student;
        studentListResource = ClientBuilder.newClient()
                .target("http://localhost:8080/rijschool/api/")
                .path("students")
                .register(StudentWriter.class);
    }
    
    @Override
    protected Void call() throws Exception {
        Response response = studentListResource.request().post(Entity.entity(student, MediaType.APPLICATION_JSON));
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
