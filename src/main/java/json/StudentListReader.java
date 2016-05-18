/*
 * To change this license header, choose License Headers in Project Properties.

 */
package json;

import domein.Color;
import domein.DriveTechnic;
import domein.Skills;
import domein.Status;
import domein.Student;
import domein.TrafficTechnic;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author sande
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class StudentListReader implements MessageBodyReader <List<Student>> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        if (!List.class.isAssignableFrom(type)) {
            return false;
        }

        if (genericType instanceof ParameterizedType) {
            Type[] arguments = ((ParameterizedType) genericType).getActualTypeArguments();
            return arguments.length == 1 && arguments[0].equals(Student.class);
        } else {
            return false;
        }
    }

     @Override
    public List<Student> readFrom(Class<List<Student>> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
            try (JsonReader in = Json.createReader(entityStream)) {
            JsonArray jsonStudents = in.readArray();
            List<Student> students = new ArrayList<>();
            for (JsonObject jsonStudent: jsonStudents.getValuesAs(JsonObject.class)) {
                Student student = new Student();
                student.setVoornaam(jsonStudent.getString("surname"));
                student.setAchternaam(jsonStudent.getString("firstname"));
                student.setEvanumber(jsonStudent.getInt("currenteva"));
                
                student.setAttitudes((String[]) jsonStudent.getJsonArray("attitudes").toArray());
                student.setProgreses((Double[]) jsonStudent.getJsonArray("progress").toArray());
                
                Skills[] skills = {studentAddSkill(jsonStudent.getJsonObject("skills1")),studentAddSkill(jsonStudent.getJsonObject("skills2")),studentAddSkill(jsonStudent.getJsonObject("skills3"))};
                student.setSkills(skills);
                
                DriveTechnic[] drivetechnic = {studentAddDriveTechnic(jsonStudent.getJsonObject("drive1")),studentAddDriveTechnic(jsonStudent.getJsonObject("drive2")),studentAddDriveTechnic(jsonStudent.getJsonObject("drive3"))};
                student.setDriveTechnics(drivetechnic);
                
                TrafficTechnic[] trafficTechnics = {studentAddTrafficTechnic(jsonStudent.getJsonObject("traffic1")),studentAddTrafficTechnic(jsonStudent.getJsonObject("traffic2")),studentAddTrafficTechnic(jsonStudent.getJsonObject("traffic3"))};
                student.setTrafficTechnics(trafficTechnics);
                
                students.add(student);
            }
            return students;
        } catch (JsonException | ClassCastException ex) {
            return new ArrayList();
        }
    }
    
    public Skills studentAddSkill(JsonObject jsonSkills){
        Skills skills = new Skills();
        skills.setFueling(new Status(Color.valueOf(jsonSkills.getJsonObject("fueling").getString("color")), jsonSkills.getJsonObject("fueling").getString("comment")));
        skills.setGps(new Status(Color.valueOf(jsonSkills.getJsonObject("gps").getString("color")), jsonSkills.getJsonObject("gps").getString("comment")));
        skills.setTires(new Status(Color.valueOf(jsonSkills.getJsonObject("tires").getString("color")), jsonSkills.getJsonObject("tires").getString("comment")));
        skills.setEmergencystop(new Status(Color.valueOf(jsonSkills.getJsonObject("emergencystop").getString("color")), jsonSkills.getJsonObject("emergencystop").getString("comment")));
        skills.setCity(new Status(Color.valueOf(jsonSkills.getJsonObject("city").getString("color")), jsonSkills.getJsonObject("city").getString("comment")));
        skills.setDoublelane(new Status(Color.valueOf(jsonSkills.getJsonObject("doublelane").getString("color")), jsonSkills.getJsonObject("doublelane").getString("comment")));
        skills.setLights(new Status(Color.valueOf(jsonSkills.getJsonObject("lights").getString("color")), jsonSkills.getJsonObject("lights").getString("comment")));
        skills.setOilcheck(new Status(Color.valueOf(jsonSkills.getJsonObject("oilcheck").getString("color")), jsonSkills.getJsonObject("oilcheck").getString("comment"))); 
        skills.setRoundabout(new Status(Color.valueOf(jsonSkills.getJsonObject("roundabout").getString("color")), jsonSkills.getJsonObject("roundabout").getString("comment")));
        skills.setHighway(new Status(Color.valueOf(jsonSkills.getJsonObject("highway").getString("color")), jsonSkills.getJsonObject("highway").getString("comment")));
        
        return skills;
    }
    
    public DriveTechnic studentAddDriveTechnic( JsonObject jsonDrivetechnic){
        DriveTechnic drivetechnic = new DriveTechnic();
        drivetechnic.setPosture(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("posture").getString("color")), jsonDrivetechnic.getJsonObject("posture").getString("comment")));
        drivetechnic.setClutch(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("clutch").getString("color")), jsonDrivetechnic.getJsonObject("clutch").getString("comment")));
        drivetechnic.setBraking(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("braking").getString("color")), jsonDrivetechnic.getJsonObject("braking").getString("comment")));
        drivetechnic.setSteering(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("steering").getString("color")), jsonDrivetechnic.getJsonObject("steering").getString("comment")));
        drivetechnic.setShifting(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("shifting").getString("color")), jsonDrivetechnic.getJsonObject("shifting").getString("comment")));
        drivetechnic.setLooking(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("looking").getString("color")), jsonDrivetechnic.getJsonObject("looking").getString("comment")));
        drivetechnic.setParking(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("parking").getString("color")), jsonDrivetechnic.getJsonObject("parking").getString("comment")));
        drivetechnic.setTurning(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("turning").getString("color")), jsonDrivetechnic.getJsonObject("turning").getString("comment")));
        drivetechnic.setGarage(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("garage").getString("color")), jsonDrivetechnic.getJsonObject("garage").getString("comment")));
        drivetechnic.setReverse(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("reverse").getString("color")), jsonDrivetechnic.getJsonObject("reverse").getString("comment")));
        drivetechnic.setSteeringPractice(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("steeringpractice").getString("color")), jsonDrivetechnic.getJsonObject("steeringpractice").getString("comment")));
        drivetechnic.setHillHandbrake(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("hillhandbrake").getString("color")), jsonDrivetechnic.getJsonObject("hillhandbrake").getString("comment")));
        drivetechnic.setHillBalancing(new Status(Color.valueOf(jsonDrivetechnic.getJsonObject("hillbalancing").getString("color")), jsonDrivetechnic.getJsonObject("hillbalancing").getString("comment")));
    
        return drivetechnic;
    }
    
    public TrafficTechnic studentAddTrafficTechnic(JsonObject jsonTraffictechnic){
        TrafficTechnic trafficTechnic=new TrafficTechnic();
        trafficTechnic.setIndicators(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("indicators").getString("color")), jsonTraffictechnic.getJsonObject("indicators").getString("comment")));
        trafficTechnic.setPublicroad(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("publicroad").getString("color")), jsonTraffictechnic.getJsonObject("publicroad").getString("comment")));
        trafficTechnic.setPriority(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("priority").getString("color")), jsonTraffictechnic.getJsonObject("priority").getString("comment")));
        trafficTechnic.setSign(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("sign").getString("color")), jsonTraffictechnic.getJsonObject("sign").getString("comment")));
        trafficTechnic.setSpeed(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("speed").getString("color")), jsonTraffictechnic.getJsonObject("speed").getString("comment")));
        trafficTechnic.setDistance(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("distance").getString("color")), jsonTraffictechnic.getJsonObject("distance").getString("comment")));
        trafficTechnic.setOvertaking(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("overtaking").getString("color")), jsonTraffictechnic.getJsonObject("overtaking").getString("comment")));
        trafficTechnic.setCrossing(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("crossing").getString("color")), jsonTraffictechnic.getJsonObject("crossing").getString("comment")));
        trafficTechnic.setTurningleft(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("turningleft").getString("color")), jsonTraffictechnic.getJsonObject("turningleft").getString("comment")));
        trafficTechnic.setTurningright(new Status(Color.valueOf(jsonTraffictechnic.getJsonObject("turningright").getString("color")), jsonTraffictechnic.getJsonObject("turningright").getString("comment")));
        return trafficTechnic;
        
        
    }
    
    }
