package HospitalManagaementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor{    /// handles operations like: view, check...

    private Connection con; /// Database Connection

    public Doctor(Connection con) { /// //first of all create a constructor because, I will create the connection in the main class and pass it to other classes using constructor injection. This ensures better dependency management and follows object-oriented design principles...
        this.con = con;
    }

    ///1st method viewDoctors()
    public void viewDoctor(){
        String query="select * from Doctors";

        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+----------------------+--------------------+");
            System.out.println("| Doctor Id  | Name                 | Specialization     |");
            System.out.println("+------------+----------------------+--------------------+");

            while(rs.next()){
                int id =rs.getInt("id");
                String name=rs.getString("name");
                String specialization =rs.getString("specialization");
                System.out.printf("| %-10s | %-20s | %-18s |\n",id , name, specialization);
                System.out.println("+------------+----------------------+--------------------+");

            }  ///while loop close
        }catch (SQLException e){
            e.printStackTrace();
        }}

    ///2nd method getDoctorById()
    public boolean getDoctorById(int id){
        String query ="SELECT * FROM doctors WHERE id=?";

        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,id);

            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                return true; ///returns true if doctor exists....
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }}
