package HospitalManagaementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient { ///handles patient operations like : add, view,check....

    private Connection con;    /// Database Connection
    private Scanner sc;       ///User Input
    public Patient(Connection con, Scanner sc) { ////first of all create a constructor because, I will create the connection in the main class and pass it to other classes using constructor injection. This ensures better dependency management and follows object-oriented design principles...
        this.con = con;
        this.sc = sc;
    }

    ///1st method addpatient()
    public void addpatient() {
        System.out.print("Enter Patient Name: ");
        String name = sc.next();
        System.out.println("Enter Patient Age: ");
        int age = sc.nextInt();
        System.out.println("Enter Patient Gender: ");
        String gender = sc.next();

        try {
            String query = "INSERT INTO patients(name,age,gender) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query); //// using preparedstmts to prevent SQL Injection...
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient Added Successfully!!");
            } else {
                System.out.println("Failed to add Patient");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    ///2nd method viewpatient()
    public void viewPateint() {
        String query = "select * from Patients";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+----------------------+---------+---------------+");
            System.out.println("| Patient Id | Name                 | Age     | Gender        |");
            System.out.println("+------------+----------------------+---------+---------------+");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                System.out.printf("| %-10s | %-20s | %-7s | %-13s |\n", id, name, age, gender);
                System.out.println("+------------+----------------------+---------+---------------+");
            }//while loop close
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ////3rd method getPatientById()
    public boolean getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true; /// if data exits returns true....
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}