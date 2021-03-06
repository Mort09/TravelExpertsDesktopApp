package Resources.DBClass;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHelper {

    //connect to database and returns connection object

    private  static DBHelper dbHelper;

    private  final String DB_URL = "jdbc:mysql://localhost:3306/travelexperts";

    private  Connection con = null;
    private  Statement stmt = null;

    private DBHelper()  {
        createConnection();
    }

    public static DBHelper getInstance() {

        try {
                    if (dbHelper == null) {
                        dbHelper = new DBHelper();
                    }
        } finally {}

        return dbHelper;
    }

    void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(DB_URL,"root","");
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
            System.exit(0);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public ResultSet executeQuery (String query)  {
        ResultSet resultSet;
        createConnection();
        try{
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(query);
        }
        catch(SQLException ex){
            System.out.println("Execution at executeQuery:DataHandler" + ex.getLocalizedMessage());
            return null;
        }


        return resultSet;
    }

    public boolean execNonQuery(String nonquery) throws SQLException{
        boolean result = true;
        try   {
            stmt = con.createStatement();
            result = stmt.execute(nonquery);
            return result;

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Errror:" + ex.getMessage(),"Error Occured" ,JOptionPane.ERROR_MESSAGE);
            System.out.println("Execution at executeQuery:DataHandler" + ex.getLocalizedMessage());
            return result;

        }
    }

    public boolean deleteAgent(TravelExperts.Agent.Model.Agent agent) {

        try {
            String query = "DELETE FROM agents where agentId = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, agent.getAgentId());
            int res = stmt.executeUpdate();
            if (res == 1) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean updateAgent(TravelExperts.Agent.Model.Agent agent) {
        boolean result=true;

        try {
            String query = "UPDATE agents SET agtFirstName = ?, agtMiddleInitial = ?, agtLastName = ?,agtBusPhone = ?, agtEmail = ?, agtPosition = ?, AgencyId = ? WHERE agentId = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, agent.getAgtFirstName());
            stmt.setString(2, agent.getAgtMiddleInital());
            stmt.setString(3, agent.getAgtLastName());
            stmt.setString(4, agent.getAgtBusPhone());
            stmt.setString(5, agent.getAgtEmail());
            stmt.setString(6, agent.getAgtPosition());
            stmt.setInt(7, agent.getAgencyId());
            stmt.setInt(8, agent.getAgentId());
            int res = stmt.executeUpdate();
            if (res == 1){
                result = true;
            }else result = false;

        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


}//class

