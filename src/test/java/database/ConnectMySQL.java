package database;

import org.testng.annotations.Test;

import java.sql.*;

public class ConnectMySQL {

    @Test
    public void connectMySQLTest(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sql_store", "root", "Smbj6509");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from sql_store.customers");

            while(resultSet.next()){
               String  firstname = resultSet.getString("first_name");
               String lastname = resultSet.getString("last_name");
                System.out.println(firstname + " " + lastname);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
