/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author endri
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement getAllCourses; 
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement dropCourses;
    private static ResultSet resultSet;
    
    
    public static ArrayList<CourseEntry> getAllCourses(String Semester){
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> getallcourses = new ArrayList<CourseEntry>();
        try
        {
            getAllCourses = connection.prepareStatement("select * from app.course where Semester = (?)");
            getAllCourses.setString(1, Semester);
            resultSet = getAllCourses.executeQuery();
            
            
            while(resultSet.next())
            {
                CourseEntry currentcourse = new CourseEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                getallcourses.add(currentcourse);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return getallcourses;
    }
    
    public static void addCourse(CourseEntry course) throws SQLException{
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.course (Semester, CourseCode, Description, Seats) values (?,?,?,?)");
            addCourse.setString(1, course.Semester);
            addCourse.setString(2, course.CourseCode);
            addCourse.setString(3, course.Description);
            addCourse.setInt(4, course.Seats);
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String Semester){
        connection = DBConnection.getConnection();
        ArrayList<String> getallcoursecodes = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select CourseCode from app.course where Semester = (?)");
            getAllCourseCodes.setString(1, Semester);
            resultSet = getAllCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                getallcoursecodes.add(resultSet.getString(1));
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return getallcoursecodes;
    }
    
    public static int getCourseSeats(String Semester, String CourseCode){
        connection = DBConnection.getConnection();
        int seats = 0;
        try
        {
            getCourseSeats = connection.prepareStatement("select Seats from app.course where Semester = (?) and CourseCode = (?) ");
            getCourseSeats.setString(1, Semester);
            getCourseSeats.setString(2, CourseCode);
            resultSet = getCourseSeats.executeQuery();  
            
            if(resultSet.next()){
                seats = resultSet.getInt(1);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
        return seats;
    }
    
    public static void dropCourse(String Semester, String CourseCode){
        connection = DBConnection.getConnection();
        
        try
        {
            dropCourses = connection.prepareStatement("delete from app.course where Semester = (?) and CourseCode = (?)");
            dropCourses.setString(1, Semester);
            dropCourses.setString(2, CourseCode);
            dropCourses.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
