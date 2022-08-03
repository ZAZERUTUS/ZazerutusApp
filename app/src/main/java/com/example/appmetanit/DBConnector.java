package com.example.appmetanit;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector extends Thread{

    public Connection connection;
    private String urlDB = "jdbc:mysql://localhost:3307/TestDB";
    ResultSet resultSet;


    DBConnector(String name) {
        super(name);
    }

    public void run() {
        try {
//            String url = "jdbc:mysql://192.168.43.52:3307/Test_Base?autoReconnect=true&useSSL=false";
            String url = "jdbc:mysql://192.168.43.52:3307/Test_Base";
            String user = "root";
            String password_ = "Qaz123wsx@";
            String sql = "SELECT * FROM users;";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            try(Connection conn = DriverManager.getConnection(url, user, password_)) {
                Statement statement = conn.createStatement();
                this.resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    String name = resultSet.getString(1);
                    String password = resultSet.getString(2);
                    System.out.println("User name: " + name + " password: " +password);
                }
//                TableLayout debugView = findViewById(R.id.tableForUsersMySQL);
//                while (resultSet.next()) {
//                    String name = resultSet.getString(1);
//                    String password = resultSet.getString(2);
//                    TableRow row = new TableRow(MainActivity.this);
//
//                    TextView email = new TextView(MainActivity.this);
//                    email.setText(name);
//                    row.addView(email, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
//
//                    TextView passwordF = new TextView(MainActivity.this);
//                    passwordF.setText(password);
//                    row.addView(passwordF, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
//
//                    debugView.addView(row);
////                }
            }
        }catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet returnData() {
        while (this.resultSet == null) {
            System.out.println("LOADING>>>>>>>>>.");
        }
        return resultSet;
    }

    public String SelectResult(String sql) {
        ResultSet resultSet = null;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet.toString();
    }
}
