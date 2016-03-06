/**
 * Created by Ben_Big on 3/5/16.
 */

/**
 * create table Building ( id int primary key,
 * address varchar(5000) not null );
 *
 * create table Apartment (
 * id int primary key,
 * number varchar(31) not null,
 * building int not null references Building(id) on update cascade on delete cascade );
 *
 * create table Person ( id int primary key, name varchar(5000) not null );
 *
 * create table Owner ( person int references Person(id)

 on update cascade on delete cascade,

 apartment int references Apartment(id)

 on update cascade on delete cascade, primary key(person, apartment)
 *
 *
 *
 */



import java.sql.*;

public class solution_to_020 {
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL =   "JDBC:MYSQL://LOCALHOST:3306/JDBC_Building";

    static final String USER="root";
    static final String PASS="100200";


    void question1(Connection connection, String address, String[] apartments )
            throws SQLException, ClassNotFoundException
    {


        PreparedStatement buildingInsert=connection.prepareStatement
                ("insert into Building values (1, address)");

        buildingInsert.executeUpdate();

        PreparedStatement apartmentInsert=connection.prepareStatement
                ("insert into Apartment  (id,number,building) values (?,?,1)");

        int id=2;

        for(String a:apartments){
            apartmentInsert.setInt(1,id);
            apartmentInsert.setString(2,"A"+a);
            apartmentInsert.executeUpdate();
            System.out.println("Apartment "+a+" is inserted");
            id++;
        }

        buildingInsert.close();
        apartmentInsert.close();
    }





    public static void main(String[] args){

        solution_to_020 s=new solution_to_020();

        try{
            Class.forName(JDBC_DRIVER);
            Connection connection=DriverManager.getConnection(DB_URL,USER,PASS);
            String[] apartments=new String[]{"102","103","201","202","203"};
            s.question1(connection,"Washington Street 100",apartments);
            connection.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

}
