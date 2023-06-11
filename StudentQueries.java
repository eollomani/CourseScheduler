
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
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    //private static int index;
    
    public static void addStudent(StudentEntry student){
        connection = DBConnection.getConnection();
        //String newstudent = student.LastName +"," + student.FirstName; 
        try
        {
            addStudent = connection.prepareStatement("insert into app.student (StudentID, FirstName, LastName) values (?,?,?)");
            addStudent.setString(1, student.StudentID);
            addStudent.setString(2, student.FirstName);
            addStudent.setString(3, student.LastName);
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<StudentEntry> getAllStudents(){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> getallstudents = new ArrayList<StudentEntry>();
        try
        {
            getAllStudents = connection.prepareStatement("select * from app.student order by StudentID");
            resultSet = getAllStudents.executeQuery();
            
            
            while(resultSet.next())
            {
                StudentEntry currentstudent = new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                getallstudents.add(currentstudent);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return getallstudents;
    }
    
    public static StudentEntry getStudent(String StudentID){
        connection = DBConnection.getConnection();
        StudentEntry student = null;
        try{
            getStudent = connection.prepareStatement("select * from app.student where StudentID = (?)");
            getStudent.setString(1, StudentID);
            resultSet = getStudent.executeQuery();
            
            while(resultSet.next()){
                student = new StudentEntry(resultSet.getString("StudentID"), resultSet.getString("FirstName"), resultSet.getString("LastName"));
            } 
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
    }
    //    connection = DBConnection.getConnection();
    //    StudentEntry student = new StudentEntry("error", "error", "error");
    //    try
     //   {
       //     getStudent = connection.prepareStatement("select * from app.student where StudentID = (?)");
         //   getStudent.setString(1, StudentID);
           // resultSet = getStudent.executeQuery();
            //System.out.println(resultSet);
            //System.out.println(resultSet.next());
            //resultSet = getStudent.executeQuery();
            //resultSet.next();
            //System.out.println("5" + resultSet.next());
            //student = new StudentEntry("error2", "error2", "error2");
            //while(resultSet.next()){
            //    student = new StudentEntry(resultSet.getString("StudentID"), resultSet.getString("FirstName"), resultSet.getString("LastName"));
            //}    
            
            
            //for(StudentEntry student: getstudent){
            //    if (student.getStudentID() == StudentID);
            //        index = getstudent.indexOf(student);

                    //return student;
            //}

            //if(resultSet.getString(1) == StudentID){
            //    StudentEntry getStudent = new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));   
            //    return getStudent;
            //}
            
        //}
        //catch(SQLException sqlException)
        //{
        //    sqlException.printStackTrace();
        //}
        
        //return getstudent.get(index);
        //return student;
    //}
    
    public static void dropStudent(String StudentID){
        connection = DBConnection.getConnection();
        try
        {
            dropStudent = connection.prepareStatement("delete from app.student where StudentID = (?)");
            dropStudent.setString(1, StudentID);
            dropStudent.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

        
}

    
