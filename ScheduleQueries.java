
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author endri
 */
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getSchedulebyStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getScheduledStudentsbyCourse;
    private static PreparedStatement getWaitlistedStudentsbyCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropSchedulebyCourse;
    private static PreparedStatement updateScheduleEntry;
    static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.Schedule (Semester, StudentID, CourseCode, Status, Timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1, entry.Semester);
            addScheduleEntry.setString(2, entry.StudentID);
            addScheduleEntry.setString(3, entry.CourseCode);
            addScheduleEntry.setString(4, entry.Status);
            addScheduleEntry.setTimestamp(5, entry.Timestamp);
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getSchedulebyStudent(String Semester, String StudentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> getschedulebystudent = new ArrayList<ScheduleEntry>();
        try
        {
            //getSchedulebyStudent = connection.prepareStatement("select * from app.Schedule where Semester = (?) and StudentID = (?) order by Timestamp");
            getSchedulebyStudent = connection.prepareStatement("select * from app.schedule where Semester = (?) and StudentID = (?) order by Status, Timestamp");
            getSchedulebyStudent.setString(1, Semester);
            getSchedulebyStudent.setString(2, StudentID);
            resultSet = getSchedulebyStudent.executeQuery();
            
            while(resultSet.next())
            {
                //ScheduleEntry currentschedule = new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5));
                ScheduleEntry currentschedule = new ScheduleEntry(resultSet.getString("Semester"), resultSet.getString("CourseCode"), resultSet.getString("StudentID"), resultSet.getString("Status"), resultSet.getTimestamp("Timestamp"));
                getschedulebystudent.add(currentschedule);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return getschedulebystudent;
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode){
        connection = DBConnection.getConnection();
        int studentCount = 0;
        
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select count(StudentID) From app.Schedule where Semester = (?) and CourseCode = (?)");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            
            if(resultSet.next()){
                studentCount = resultSet.getInt(1);
            }
        
        }  
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentCount;
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String Semester, String CourseCode){
        connection = DBConnection.getConnection();
        
        ArrayList<ScheduleEntry> scheduledstudents = new ArrayList<ScheduleEntry>();
        //ArrayList<ScheduleEntry> studentschedules = new ArrayList<ScheduleEntry>();
        try
        {
            //getScheduledStudentsbyCourse = connection.prepareStatement("select StudentID, Status, TimeStamp from app.schedule where Semester = (?) and CourseCode = (?) order by Timestamp");
            
            getScheduledStudentsbyCourse = connection.prepareStatement("select * from app.schedule where Semester = (?) and CourseCode = (?) and Status =(?) order by Timestamp");
            getScheduledStudentsbyCourse.setString(1, Semester);
            getScheduledStudentsbyCourse.setString(2, CourseCode);
            getScheduledStudentsbyCourse.setString(3, "S");
            resultSet = getScheduledStudentsbyCourse.executeQuery();
            
            while(resultSet.next()){
                ScheduleEntry student = new ScheduleEntry(resultSet.getString(1), resultSet.getString(3), resultSet.getString(2), resultSet.getString(4), resultSet.getTimestamp(5));
                scheduledstudents.add(student);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return scheduledstudents;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsbyCourse(String Semester, String CourseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlistedstudents = new ArrayList<ScheduleEntry>();
        try
        {
            
            getWaitlistedStudentsbyCourse = connection.prepareStatement("select * from app.schedule where Semester = (?) and CourseCode = (?) and Status = (?)");
            getWaitlistedStudentsbyCourse.setString(1, Semester);
            getWaitlistedStudentsbyCourse.setString(2, CourseCode);
            getWaitlistedStudentsbyCourse.setString(3, "W");
            resultSet = getWaitlistedStudentsbyCourse.executeQuery();
            
            while(resultSet.next()){
                ScheduleEntry student = new ScheduleEntry(resultSet.getString(1), resultSet.getString(3), resultSet.getString(2), resultSet.getString(4), resultSet.getTimestamp(5));
                waitlistedstudents.add(student);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return waitlistedstudents;
    }
    //deletes student in course
    public static void dropStudentScheduleByCourse(String Semester, String StudentID, String CourseCode){
        connection = DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where Semester = (?) and StudentID = (?) and CourseCode = (?)");
            dropStudentScheduleByCourse.setString(1, Semester);
            dropStudentScheduleByCourse.setString(2, StudentID);
            dropStudentScheduleByCourse.setString(3, CourseCode);
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    //delete course in schedule
    public static void dropSchedulebyCourse(String Semester, String CourseCode){
        connection = DBConnection.getConnection();
        try
        {
            dropSchedulebyCourse = connection.prepareStatement("delete from app.schedule where Semester = (?) and CourseCode = (?)");
            dropSchedulebyCourse.setString(1, Semester);
            dropSchedulebyCourse.setString(2, CourseCode);
            dropSchedulebyCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public static void updateScheduleEntry(String Semester, ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            updateScheduleEntry = connection.prepareStatement("update app.schedule set Status = ? where Semester = ? and StudentID = ? and CourseCode = ?");
            updateScheduleEntry.setString(1, "S");
            updateScheduleEntry.setString(2, Semester);
            updateScheduleEntry.setString(3, entry.getStudentID());
            updateScheduleEntry.setString(4, entry.getCourseCode());
            updateScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
