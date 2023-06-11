/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author endri
 */
public class CourseEntry {
    String Semester;
    String CourseCode;
    String Description;
    int Seats;


    public CourseEntry (String Semester, String CourseCode, String Description, int Seats) {
        this.Semester = Semester;
        this.CourseCode = CourseCode;
        this.Description = Description;
        this.Seats = Seats;
    }
    
    public String getSemester(){
        return Semester;
    }
    
    public String getCourseCode(){
        return CourseCode;
    }
    
    public String getDescription(){
        return Description;
    }
    
    public int getSeats(){
        return Seats;
    }
    
    public String toString(){
        return CourseCode;
    }
    
}