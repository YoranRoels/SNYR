/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package async;

import domein.Student;
import java.util.List;
import javafx.concurrent.Task;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import json.StudentListWriter;
import json.StudentWriter;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author sande
 */
public class UpdateStudentTask extends Task<Void> {
    
     private final WebTarget studentListResource;
    
    private final Student student;
    
    public UpdateStudentTask(Student student){
        this.student=student;
        studentListResource = ClientBuilder.newClient()
                .target("http://localhost:8080/SNYR-backend/api")
                .path("students")
              //  .path(Integer.toString(student.getStudentnr()))
                .register(StudentWriter.class)
                .register(HttpAuthenticationFeature.basic("rijschoolevauser", "user"));;
                
    }

    @Override
    protected Void call() throws Exception {
        Response response = studentListResource.request().put(Entity.entity(student, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
        switch (response.getStatus()){
            case 204:
                return null;
            case 400:
                throw new BadRequestException();
            default:
                throw new RuntimeException();
        }
    }
    
    
}
