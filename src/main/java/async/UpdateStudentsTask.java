/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package async;

import domein.Student;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import json.StudentListWriter;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 *
 * @author sande
 */
public class UpdateStudentsTask extends Task<Void> {
    
    private final WebTarget studentListResource;
    
    private final List<Student> students;
    
    public UpdateStudentsTask(List<Student> students){
        this.students=students;
        Client client = ClientBuilder.newBuilder().register(HttpAuthenticationFeature.basic("rijschoolevauser", "user")).build();
        studentListResource = client
                .target("http://localhost:8080/SNYR-backend/api")
                .path("students")
                //.path("updateStudents")
                //.register(HttpAuthenticationFeature.basic("rijschoolevauser", "user"))
                                .register(StudentListWriter.class);
    }

    @Override
    protected Void call() throws Exception {
        GenericEntity<List<Student>> l = new GenericEntity<List<Student>>(students) {};
        Response response = studentListResource.request().put(Entity.entity(l, MediaType.APPLICATION_JSON));
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
