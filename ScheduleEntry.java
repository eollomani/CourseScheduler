/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author endri
 */
import java.sql.Timestamp;

public class ScheduleEntry {
     String Semester;
     String CourseCode;
     String StudentID;
     String Status;
     Timestamp Timestamp;

    
    public ScheduleEntry (String Semester, String CourseCode, String StudentID, String Status, Timestamp Timestamp){
        this.Semester = Semester;
        this.CourseCode = CourseCode;
        this.StudentID = StudentID;
        this.Status = Status;
        this.Timestamp = Timestamp;
    }
    
    public String getSemester(){
        return Semester;
    }
    
    public String getCourseCode(){
        return CourseCode;
    }
    
    public String getStudentID(){
        return StudentID;
    }
    
    public String getStatus(){
        return Status;
    }
    
    public Timestamp getTimestamp(){
        return Timestamp;
    }
    
}
