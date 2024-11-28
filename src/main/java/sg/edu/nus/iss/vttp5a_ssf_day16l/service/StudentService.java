package sg.edu.nus.iss.vttp5a_ssf_day16l.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.vttp5a_ssf_day16l.model.Student;
import sg.edu.nus.iss.vttp5a_ssf_day16l.repo.ListRepo;

@Service
public class StudentService {
    
    @Autowired
    ListRepo studentRepo;

    // Write CRUD functions here

    public void add(Student student) {
        studentRepo.rightPush("students", student.toString());
    }

    public List<Student> findAll(String redisKey) {
        List<String> students = studentRepo.getList(redisKey);
        List<Student> studentRecords = new ArrayList<>();

        // Array builder must be outside of the Loop!!
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for(String raw : students) {
            String [] data = raw.split(",");

            // // day 16: slide 12
            // JsonObject jsonObj = Json.createObjectBuilder()
            // .add("id", Integer.parseInt(data[0]))
            // .add("fullName", data[1])
            // .add("email", data[2])
            // .add("phoneNumber", data[3])
            // .build();

            // // Put JsonObject into JsonArray - Reference day 16: slide 7
            // jab.add(jsonObj);

            Student s = new Student(Integer.parseInt(data[0]), data[1], data[2], data[3]);
            studentRecords.add(s);
        }
        return studentRecords;
    }
}
