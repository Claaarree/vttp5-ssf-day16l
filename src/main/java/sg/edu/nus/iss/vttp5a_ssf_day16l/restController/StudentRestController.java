package sg.edu.nus.iss.vttp5a_ssf_day16l.restController;

import java.io.StringReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.vttp5a_ssf_day16l.constant.Constant;
import sg.edu.nus.iss.vttp5a_ssf_day16l.model.Student;
import sg.edu.nus.iss.vttp5a_ssf_day16l.service.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(path = "/api/students", produces = "application/json")
public class StudentRestController {

    @Autowired
    StudentService studentService;

    @PostMapping(path = {"", "/create"})
    public ResponseEntity<String> create(@RequestBody String entity) {
        // Json-P to convert Json String to Student Object
        JsonReader jReader = Json.createReader(new StringReader(entity));
        JsonObject jObject = jReader.readObject();

        Student s = new Student();
        s.setId(jObject.getInt("id"));
        s.setFullName(jObject.getString("fullName"));
        s.setEmail(jObject.getString("email"));
        s.setPhoneNumber(jObject.getString("phoneNumber"));
        
        studentService.add(s);
        
        // 2 different ways to return the data
        // return new ResponseEntity<>("true", HttpStatus.OK);
        return ResponseEntity.ok().body("true");
    }

    @GetMapping("")
    // public ResponseEntity<List<Student>> findAll(){
    public ResponseEntity<String> findAll(){

        List<Student> students = studentService.findAll(Constant.studentKey);

        JsonArrayBuilder jsonArray = Json.createArrayBuilder();

        for (Student s : students) {
            JsonObject jsonObj = Json.createObjectBuilder()
            .add("id", s.getId())
            .add("fullName", s.getFullName())
            .add("email", s.getEmail())
            .add("phoneNumber", s.getPhoneNumber())
            .build();

            jsonArray.add(jsonObj);
        }

        return ResponseEntity.ok().body(jsonArray.build().toString());
    } 
}
