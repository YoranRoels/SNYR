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
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;


/**
 *
 * @author sande
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
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
            jsonStudent.add("voornaam", student.getVoornaam());
            jsonStudent.add("achternaam", student.getAchternaam());
            jsonStudent.add("email", student.getEmail());
            /*current eva number, waar hervatten*/
            jsonStudent.add("currenteva", student.getEvanumber());
            /*alle evaluatie gegevens*/
            /*evas done*/
            JsonArray array = Json.createArrayBuilder().
                    add(student.getEvasDone()[0]).add(student.getEvasDone()[1]).add(student.getEvasDone()[2]).build();
            jsonStudent.add("evasdone", array);
            /*skills toevoegen*/
            jsonStudent.add("skills1", getJsonSkillObject(student.getSkills()[0]));
            jsonStudent.add("skills2", getJsonSkillObject(student.getSkills()[1]));
            jsonStudent.add("skills3", getJsonSkillObject(student.getSkills()[2]));
            
            /*drivetechnics*/
            jsonStudent.add("drive1", getJsonDriveTechnichObject(student.getDriveTechnics()[0]));
            jsonStudent.add("drive2", getJsonDriveTechnichObject(student.getDriveTechnics()[1]));
            jsonStudent.add("drive3", getJsonDriveTechnichObject(student.getDriveTechnics()[2]));
            /*traffictechnis*/
            jsonStudent.add("traffic1", getJsonTrafficTechnicObject(student.getTrafficTechnics()[0]));
            jsonStudent.add("traffic2", getJsonTrafficTechnicObject(student.getTrafficTechnics()[1]));
            jsonStudent.add("traffic3", getJsonTrafficTechnicObject(student.getTrafficTechnics()[2]));
            
            /*atitudes*/
            jsonStudent.add("attitudes", Json.createArrayBuilder()
                                            .add(student.getAttitudes()[0])
                                            .add(student.getAttitudes()[1])
                                            .add(student.getAttitudes()[2]).build());
            jsonStudent.add("progress", Json.createArrayBuilder()
                                        .add(student.getProgreses()[0])
                                        .add(student.getProgreses()[1])
                                        .add(student.getProgreses()[2])
                                        .build());
            
            out.writeObject(jsonStudent.build());
        }
    }
    
    
    public JsonObject getJsonSkillObject(Skills skills){
        return Json.createObjectBuilder()
                .add("fueling", Json.createObjectBuilder()
                        .add("comment", skills.getFueling().getComment())
                        .add("color", skills.getFueling().getColor().toString()))
                .add("gps", Json.createObjectBuilder()
                        .add("comment", skills.getGps().getComment())
                        .add("color", skills.getGps().getColor().toString()))
                .add("tires", Json.createObjectBuilder()
                        .add("comment", skills.getTires().getComment())
                        .add("color", skills.getTires().getColor().toString()))
                .add("emergencystop", Json.createObjectBuilder()
                        .add("comment", skills.getEmergencystop().getComment())
                        .add("color", skills.getEmergencystop().getColor().toString()))
                .add("city", Json.createObjectBuilder()
                        .add("comment", skills.getCity().getComment())
                        .add("color", skills.getCity().getColor().toString()))
                .add("doublelane", Json.createObjectBuilder()
                        .add("comment", skills.getDoublelane().getComment())
                        .add("color", skills.getDoublelane().getColor().toString()))
                .add("lights", Json.createObjectBuilder()
                        .add("comment", skills.getLights().getComment())
                        .add("color", skills.getLights().getColor().toString()))
                .add("oilcheck", Json.createObjectBuilder()
                        .add("comment", skills.getOilcheck().getComment())
                        .add("color", skills.getOilcheck().getColor().toString()))
                .add("roundabout", Json.createObjectBuilder()
                        .add("comment", skills.getRoundabout().getComment())
                        .add("color", skills.getRoundabout().getColor().toString()))
                .add("highway", Json.createObjectBuilder()
                        .add("comment", skills.getHighway().getComment())
                        .add("color", skills.getHighway().getColor().toString()))
                .build();
 
    }
    
    public JsonObject getJsonDriveTechnichObject(DriveTechnic drivetechnic){
        return Json.createObjectBuilder()
                .add("posture", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getPosture().getComment())
                        .add("color", drivetechnic.getPosture().getColor().toString()))
                .add("clutch", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getClutch().getComment())
                        .add("color", drivetechnic.getClutch().getColor().toString()))
                .add("braking", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getBraking().getComment())
                        .add("color", drivetechnic.getBraking().getColor().toString()))
                .add("steering", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getSteering().getComment())
                        .add("color", drivetechnic.getSteering().getColor().toString()))
                .add("shifting", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getShifting().getComment())
                        .add("color", drivetechnic.getShifting().getColor().toString()))
                .add("looking", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getLooking().getComment())
                        .add("color", drivetechnic.getLooking().getColor().toString()))
                .add("parking", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getParking().getComment())
                        .add("color", drivetechnic.getParking().getColor().toString()))
                .add("turning", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getTurning().getComment())
                        .add("color", drivetechnic.getTurning().getColor().toString()))
                .add("garage", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getGarage().getComment())
                        .add("color", drivetechnic.getGarage().getColor().toString()))
                .add("reverse", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getReverse().getComment())
                        .add("color", drivetechnic.getReverse().getColor().toString()))
                .add("steeringpractice", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getSteeringPractice().getComment())
                        .add("color", drivetechnic.getSteeringPractice().getColor().toString()))
                .add("hillhandbrake", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getHillHandbrake().getComment())
                        .add("color", drivetechnic.getHillHandbrake().getColor().toString()))
                .add("hillbalancing", Json.createObjectBuilder()
                        .add("comment", drivetechnic.getHillBalancing().getComment())
                        .add("color", drivetechnic.getHillBalancing().getColor().toString()))
                
                .build();
    }
    
    public JsonObject getJsonTrafficTechnicObject(TrafficTechnic trafficTechnic){
        return Json.createObjectBuilder()
                .add("indicators", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getIndicators().getComment())
                        .add("color", trafficTechnic.getIndicators().getColor().toString()))
                .add("publicroad", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getPublicroad().getComment())
                        .add("color", trafficTechnic.getPublicroad().getColor().toString()))
                .add("priority", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getPriority().getComment())
                        .add("color", trafficTechnic.getPriority().getColor().toString()))
                .add("sign", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getSign().getComment())
                        .add("color", trafficTechnic.getSign().getColor().toString()))
                .add("speed", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getSpeed().getComment())
                        .add("color", trafficTechnic.getSpeed().getColor().toString()))
                .add("distance", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getDistance().getComment())
                        .add("color", trafficTechnic.getDistance().getColor().toString()))
                .add("overtaking", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getOvertaking().getComment())
                        .add("color", trafficTechnic.getOvertaking().getColor().toString()))
                .add("crossing", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getCrossing().getComment())
                        .add("color", trafficTechnic.getCrossing().getColor().toString()))
                .add("turningleft", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getTurningleft().getComment())
                        .add("color", trafficTechnic.getTurningleft().getColor().toString()))
                .add("turningright", Json.createObjectBuilder()
                        .add("comment", trafficTechnic.getTurningright().getComment())
                        .add("color", trafficTechnic.getTurningright().getColor().toString()))
                
                .build();
    }
    
}
