/**
 * Created by Ben_Big on 3/4/16.
 */
/** * Enroll a student in a course.
 * * @param driver The driver class name.
 * * @param url The database URL.
 * * @param user The database user.
 * * @param password The database password.
 * * @param student The student name.
 * * @param course The course title.
 * * @return true if the student was enrolled in the course,
 * * false if the student was already enrolled in the course or
 * * could not be enrolled in the course.
 * * @throws SQLException if a database error occurred.
 * * @throws ClassNotFoundException if the driver could not be loaded. */

import java.sql.*;


public class solution_to_037 {


    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL =   "JDBC:MYSQL://LOCALHOST:3306/JDBC_Student";

    static final String USER="root";
    static final String PASS="100200";


    public boolean enrollStudent(String driver, String url,
                                 String user, String password,
                                 String student, String course)
            throws SQLException, ClassNotFoundException {

        Connection connection=null;

        Class.forName(JDBC_DRIVER);
        connection=DriverManager.getConnection(url,user,password);

        PreparedStatement statement = connection.prepareStatement("insert ignore into Enroll(student, course)" +
                " select s.id, c.code " + "from Student s, Course c where s.name=? and c.title=?");

        statement.setString(1,student);
        statement.setString(2,course);

        int count=statement.executeUpdate();

        System.out.println("count="+count);

        statement.close();
        connection.close();

        return (count == 1);

    }


    public static void main(String[] args){

    solution_to_037 s=new solution_to_037();

        try {

            s.enrollStudent(JDBC_DRIVER,DB_URL,USER,PASS,"Tom","Database");

        }catch(SQLException se){
            se.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



}
