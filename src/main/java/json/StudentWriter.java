/*

 */
package json;

import domein.DriveTechnic;
import domein.Skills;
import domein.Student;
import domein.TrafficTechnic;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
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
            /*skills toevoegen*/
            jsonStudent.add("skills1", getJsonSkillObject(student.getSkills()[0]));
            jsonStudent.add("skills2", getJsonSkillObject(student.getSkills()[1]));
            jsonStudent.add("skills3", getJsonSkillObject(student.getSkills()[2]));
            
        }
    }
    
    
    public JsonObject getJsonSkillObject(Skills skills){
        return Json.createObjectBuilder()
                .add("fueling", Json.createObjectBuilder()
                        .add("comment", skills.getFueling().getComment())
                        .add("Color", skills.getFueling().getColor().ordinal()))
                .add("gps", Json.createObjectBuilder()
                        .add("comment", skills.getGps().getComment())
                        .add("Color", skills.getGps().getColor().ordinal()))
                .add("tires", Json.createObjectBuilder()
                        .add("comment", skills.getTires().getComment())
                        .add("Color", skills.getTires().getColor().ordinal()))
                .add("emergencystop", Json.createObjectBuilder()
                        .add("comment", skills.getEmergencystop().getComment())
                        .add("Color", skills.getEmergencystop().getColor().ordinal()))
                .add("city", Json.createObjectBuilder()
                        .add("comment", skills.getCity().getComment())
                        .add("Color", skills.getCity().getColor().ordinal()))
                .add("doublelane", Json.createObjectBuilder()
                        .add("comment", skills.getDoublelane().getComment())
                        .add("Color", skills.getDoublelane().getColor().ordinal()))
                .add("lights", Json.createObjectBuilder()
                        .add("comment", skills.getLights().getComment())
                        .add("Color", skills.getLights().getColor().ordinal()))
                .add("oilcheck", Json.createObjectBuilder()
                        .add("comment", skills.getOilcheck().getComment())
                        .add("Color", skills.getOilcheck().getColor().ordinal()))
                .add("roundabout", Json.createObjectBuilder()
                        .add("comment", skills.getRoundabout().getComment())
                        .add("Color", skills.getRoundabout().getColor().ordinal()))
                .add("highway", Json.createObjectBuilder()
                        .add("comment", skills.getHighway().getComment())
                        .add("Color", skills.getHighway().getColor().ordinal()))
                .build();
 
    }
    
    public JsonObject getJsonDriveTechnichObject(DriveTechnic drivetechnic){
        return Json.createObjectBuilder()
                .add("posture", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getPosture().getComment())
                        .add("Color", drivetechnic.getPosture().getColor().ordinal()))
                .add("clutch", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getClutch().getComment())
                        .add("Color", drivetechnic.getClutch().getColor().ordinal()))
                .add("braking", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getBraking().getComment())
                        .add("Color", drivetechnic.getBraking().getColor().ordinal()))
                .add("steering", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getSteering().getComment())
                        .add("Color", drivetechnic.getSteering().getColor().ordinal()))
                .add("shifting", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getShifting().getComment())
                        .add("Color", drivetechnic.getShifting().getColor().ordinal()))
                .add("looking", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getLooking().getComment())
                        .add("Color", drivetechnic.getLooking().getColor().ordinal()))
                .add("parking", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getParking().getComment())
                        .add("Color", drivetechnic.getParking().getColor().ordinal()))
                .add("turning", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getTurning().getComment())
                        .add("Color", drivetechnic.getTurning().getColor().ordinal()))
                .add("garage", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getGarage().getComment())
                        .add("Color", drivetechnic.getGarage().getColor().ordinal()))
                .add("reverse", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getReverse().getComment())
                        .add("Color", drivetechnic.getReverse().getColor().ordinal()))
                .add("steeringpractice", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getSteeringPractice().getComment())
                        .add("Color", drivetechnic.getSteeringPractice().getColor().ordinal()))
                .add("hillhandbrake", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getHillHandbrake().getComment())
                        .add("Color", drivetechnic.getHillHandbrake().getColor().ordinal()))
                .add("hillbalancing", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getHillBalancing().getComment())
                        .add("Color", drivetechnic.getHillBalancing().getColor().ordinal()))
                
                .build();
    }
    
    public JsonObject getJsonTrafficTechnicObject(TrafficTechnic trafficTechnic){
        return Json.createObjectBuilder()
                .add("indicators", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getIndicators().getComment())
                        .add("Color", trafficTechnic.getIndicators().getColor().ordinal()))
                .add("publicroad", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getPublicroad().getComment())
                        .add("Color", trafficTechnic.getPublicroad().getColor().ordinal()))
                .add("priority", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getPriority().getComment())
                        .add("Color", trafficTechnic.getPriority().getColor().ordinal()))
                .add("sign", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getSign().getComment())
                        .add("Color", trafficTechnic.getSign().getColor().ordinal()))
                .add("speed", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getSpeed().getComment())
                        .add("Color", trafficTechnic.getSpeed().getColor().ordinal()))
                .add("distance", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getDistance().getComment())
                        .add("Color", trafficTechnic.getDistance().getColor().ordinal()))
                .add("overtaking", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getOvertaking().getComment())
                        .add("Color", trafficTechnic.getOvertaking().getColor().ordinal()))
                .add("crossing", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getCrossing().getComment())
                        .add("Color", trafficTechnic.getCrossing().getColor().ordinal()))
                .add("turningleft", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getTurningleft().getComment())
                        .add("Color", trafficTechnic.getTurningleft().getColor().ordinal()))
                .add("turningright", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getTurningright().getComment())
                        .add("Color", trafficTechnic.getTurningright().getColor().ordinal()))
                
                .build();
    }
    
}
