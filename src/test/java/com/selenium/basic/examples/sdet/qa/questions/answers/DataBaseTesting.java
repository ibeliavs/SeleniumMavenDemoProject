package com.selenium.basic.examples.sdet.qa.questions.answers;

import org.testng.annotations.Test;

import java.sql.*;

public class DataBaseTesting {
    @Test
    public void dataTesting() throws SQLException {
        //Verified from UI
        String firstnameD = "firstname";
        String lastnameD = "lastname";
        String emailD = "email";
        String telephoneD = "111111";//?????

        // Verified Database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/openshop", "root", "");

        Statement statement = con.createStatement();
        String query = "select firstname, lastname, email, telephone from oc_customer;";
        ResultSet resultSet = statement.executeQuery(query);

        boolean status = false;
        while (resultSet.next()) {
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            String email = resultSet.getString("email");
            String telephone = resultSet.getString("telephone");

            if (firstnameD.equals(firstname) && lastnameD.equals(lastname)
                    && emailD.equals(email) && telephoneD.equals(telephone)) {
                System.out.println("Record found in the table. Test passed");
                status = true;
                break;
            }
        }
        if (!status)
            System.out.println("Record not found in the table. Test Failed");
    }
}
