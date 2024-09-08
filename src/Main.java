
// program for Batch Proceessing...

import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded succesfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connection established succesfully");
            // using simpl statement
           // con.setAutoCommit(false);
           // Statement statement = con.createStatement();
           // statement.addBatch("INSERT INTO employees(name,job_title,salary)VALUES ('Vashu','HR manager',65000.0)");
            //statement.addBatch("INSERT INTO employees(name,job_title,salary)VALUES ('Karan','Cyber security anaylst',55000.0)");
            //statement.addBatch("INSERT INTO employees(name,job_title,salary)VALUES ('Vipul','Devops eng',75000.0)");
           // int[] batchResult = statement.executeBatch();
            //con.commit();
           // System.out.println("Batch excuted succesfully");

            // using p statement
            con.setAutoCommit(false);
            String query = "INSERT INTO employees(name,job_title,salary)VALUES(?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Name:");
                String name = scanner.nextLine();
                System.out.print("Job Title:");
                String job_title = scanner.nextLine();
                System.out.println("Salary:");
                double salary = scanner.nextDouble();
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,job_title);
                preparedStatement.setDouble(3,salary);
                preparedStatement.addBatch();
                System.out.println("Add more values Y/N");
                String decision = scanner.nextLine();
                if(decision.toUpperCase().equals("N")) {
                    break;
                }
            }
            int[] batchResult= preparedStatement.executeBatch();
            con.commit();
            System.out.println("Batch execute succesfull");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        }
    }
