/**
 * Created by Ben_Big on 3/5/16.
 */

import java.sql.*;


/** * List the foods that one can drink with a beverage.
 *  * The beverages are listed in order by beverage size.
 *  * The foods for one beverage and size are listed by price.
 *  * @param connection The connection to use for the method.
 *  * @param beverage The name of the beverage.
 *  * @throws SQLException if a database error occurs. */


public class solution_to_263_3 {

    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    static final String DB_URL =   "JDBC:MYSQL://LOCALHOST:3306/JDBC_Food";

    static final String USER="root";
    static final String PASS="100200";

    public void foodsWithBeverage(Connection connection,
                                  String beverage)
            throws SQLException {

        PreparedStatement beverageQuery = connection.prepareStatement
                ("SELECT b.id FROM Beverage b WHERE b.name = ? AND b.size = ?");


        PreparedStatement foodQuery = connection.prepareStatement
                ("SELECT f.name, f.price FROM Food f,Recommendation r WHERE f.id=r.drinkWith AND r.complementedBy = ? ORDER BY f.price");


        for (String size : new String[]{"small", "medium", "large"}) {
            beverageQuery.setString(1, beverage);
            beverageQuery.setString(2, size);
            ResultSet beverages = beverageQuery.executeQuery();
            while (beverages.next()) {
                System.out.println("Foods complemented by " + beverage + " at size " + size);

                foodQuery.setInt(1, beverages.getInt(1));
                ResultSet foods = foodQuery.executeQuery();

                while (foods.next()) {
                    String foodName = foods.getString(1);
                    double foodPrice = foods.getDouble(2);
                    System.out.println("Foods: " + foodName + ", price: "+foodPrice);
                }


                foods.close();

            }
            beverages.close();

        }
        foodQuery.close();
        beverageQuery.close();
    }

    public static void main(String[] args){
        solution_to_263_3 s=new solution_to_263_3();


        try{
            Class.forName(JDBC_DRIVER);
            Connection connection=DriverManager.getConnection(DB_URL,USER,PASS);

            s.foodsWithBeverage(connection,"Coke");

        }catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }


}


