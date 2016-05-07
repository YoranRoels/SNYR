/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import domein.Student;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;


/**
 *
 * @author sande
 */
public class StudentWriter implements MessageBodyWriter<Student> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Student.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Student t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
       return -1;
               }

    @Override
    public void writeTo(Student student, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        try(JsonWriter out = Json.createWriter(entityStream)  ){
            JsonObjectBuilder jsonStudent = Json.createObjectBuilder();
            /*algemene gegevens*/
            jsonStudent.add("surname", student.getVoornaam());
            jsonStudent.add("lastname", student.getAchternaam());
            jsonStudent.add("email", student.getEmail());
            jsonStudent.add("studnr", student.getStudentnr());
            /*current eva number, waar hervatten*/
            jsonStudent.add("curenteva", student.getEvanumber());
            /*alle evaluatie gegevens*/
            /*evas done*/
            JsonArray array = Json.createArrayBuilder().
                    add(student.getEvasDone()[0]).add(student.getEvasDone()[1]).add(student.getEvasDone()[2]).build();
            jsonStudent.add("evasdone", array);
            array.clear();
            /**/
            
        }
    }
    
}
