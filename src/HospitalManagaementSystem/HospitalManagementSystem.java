package HospitalManagaementSystem;

import javax.print.Doc;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class HospitalManagementSystem {
    public static void main(String[] args){

        /// Create a Properties object to hold config values
        Properties prop = new Properties();

        try {
            /// Load database credentials from config.properties
             prop.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            /// Stop if file not found or cannot read
            System.out.println("Failed to load config.properties");
            e.printStackTrace();
            return;
        }

        /// Fetch credentials from the Properties object
        final String url = prop.getProperty("db.url");
        final String username = prop.getProperty("db.username");
        final String password = prop.getProperty("db.password");


     try {
            Class.forName("com.mysql.cj.jdbc.Driver"); /// Load...
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            /// Constructor Injections
            Patient pt = new Patient(con, sc);
            Doctor dr = new Doctor(con);

            while (true) {
                System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter Your Choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        /// add patient
                        pt.addpatient();
                        System.out.println();
                        break;
                    case 2:
                        /// view patient
                        pt.viewPateint();
                        System.out.println();
                        break;
                    case 3:
                        ///view doctor
                        dr.viewDoctor();
                        System.out.println();
                        break;
                    case 4:
                        ///view appointment
                        bookAppointment(pt, dr, con, sc);
                        System.out.println();
                        break;
                    case 5:
                        System.out.println("THANK YOU FOR USING HOSPITAL MANAGEMENT SYSTEM!!!");
                        return;
                    default:
                        System.out.println("Enter valid choice!!!");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /// Appointments Booking Process
    public static void bookAppointment(Patient pt, Doctor dr, Connection con, Scanner sc) {

        System.out.println("Enter Patient Id: ");
        int patientId = sc.nextInt();

        System.out.println("Enter Doctor Id: ");
        int doctorId = sc.nextInt();

        System.out.println("Enter Appointment Date (YYYY-MM-DD): ");
        String appointmentDate = sc.next();

        /// checking for patient & doctor...
        if (pt.getPatientById(patientId) && dr.getDoctorById(doctorId)) {
           /// checking whether doctor bhai is free on selected date
            if (checkDoctorAvailability(doctorId, appointmentDate, con)) {
                String appointmentQuery = " INSERT INTO appointments(patient_id, doctor_id, appointment_date)VALUES(?,?,?)";

                try {
                    PreparedStatement ps = con.prepareStatement(appointmentQuery);
                    ps.setInt(1, patientId);
                    ps.setInt(2, doctorId);
                    ps.setString(3, appointmentDate);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Appointment Booked!!");
                    } else {
                        System.out.println("Failed to Book Appointment!!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Doctor is not available on this date!!");
            }
        } else {
            System.out.println("Either doctor or patient doesn't exist!!!");
        }
    }
    /// Checking whether doctor is free or already appointed on the selected date....
    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection con) {

        String query = "SELECT COUNT(*) FROM  appointments WHERE doctor_id=? AND appointment_date=?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doctorId);
            ps.setString(2, appointmentDate);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true; /// returns true if no appointment was booked on that selected date...
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}