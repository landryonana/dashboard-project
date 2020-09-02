/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailsfinal1.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.SQLiteException;

/**
 *
 * @author JESUS-CHRIST
 */
public class SqlConnection {

    public static Connection DbConnector() throws SQLException {

        try {
            Connection con = null;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:db.sqlite3");

            return con;
        } catch (ClassNotFoundException | SQLiteException e) {
            System.out.print(e.getMessage());

        }
        return null;

    }
}

