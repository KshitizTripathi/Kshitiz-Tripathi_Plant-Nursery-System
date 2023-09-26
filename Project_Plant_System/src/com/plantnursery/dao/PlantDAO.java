package com.plantnursery.dao;
import com.plantnursery.exp.*;
import com.plantnursery.exp.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.plantnursery.model.Plant;
public class PlantDAO {
	RetrieveConnection rc=new RetrieveConnection();
	Connection conn=rc.getConnection();
	
	public int addPlant(Plant p) {
		
		String query2;
		
		String insertQuery = "INSERT INTO plant (plantId, plantName, originCountryName, sunlightRequired, waterSupplyFrequency, plantType, cost) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
		    preparedStatement.setInt(1, p.plantId);
		    preparedStatement.setString(2, p.plantName);
		    preparedStatement.setString(3, p.originCountryName);
		    preparedStatement.setInt(4, p.sunLightRequired);
		    preparedStatement.setString(5, p.waterSupplyFrequency);
		    preparedStatement.setString(6, p.plantType);
		    preparedStatement.setDouble(7, p.cost);

		    // Execute the query
		    int rowsInserted = preparedStatement.executeUpdate();
		    
		    if (rowsInserted > 0) {
		        System.out.println("Plant record inserted successfully.");
		    } else {
		        System.out.println("Failed to insert plant record.");
		    }
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		    // Handle the SQL exception appropriately
		}

		return 0;
	}
	
	public boolean deletePlant(int pid) {
		
		
		
		try{
			Statement stmt=conn.createStatement();
			String query2;
			String query1="select * from plant where plantId='"+pid+"'";
			ResultSet rs=stmt.executeQuery(query1);
			if(rs.next()) {
				query2="delete from plant where plantId='"+pid+"'";
				stmt.executeUpdate(query2);
				return true;
			} else {
				System.out.println("Plant not found");
				return false;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	public boolean updatePlantCost(int plid,double newCost) {
		
		try {
			Statement stmt=conn.createStatement();
			String query2 = "UPDATE plant SET cost=" + newCost + " WHERE plantId='" + plid + "'";
			int rows=stmt.executeUpdate(query2);
			if(rows>0) 
				return true;
			return false;	
		} catch(SQLException e) {
			
		}
		return true;
	}
	
	public ArrayList<Plant> showAllPlants(){
		
		ArrayList<Plant> arr=new ArrayList<Plant>();
		try {
			String query1="select * from plant";
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(query1);
	        while (rs.next()) {
	            // Retrieve by column name
	        	int id=rs.getInt("plantId");
	        	String pname=rs.getString("plantName");
	        	String cname=rs.getString("originCountryName");
	        	int sun=rs.getInt("sunlightRequired");
	        	String water=rs.getString("waterSupplyFrequency");
	        	String ptype=rs.getString("plantType");
	        	double cost=rs.getDouble("cost");
	        	Plant p=new Plant(id,pname,ptype,cname,water,cost,sun);
	            arr.add(p);
	         }

		} catch(SQLException e) {
			
		}
		
        return arr;
	}
	public ArrayList<Plant> searchByOriginCountryName(String cntry) throws NurseryException{
		
		ArrayList<Plant> arr=new ArrayList<Plant>();
		try {
			Statement stmt=conn.createStatement();
			String query1="select * from plant where originCountryName='"+cntry+"'";
 			ResultSet rs = stmt.executeQuery(query1);
			while (rs.next()) {
	            // Retrieve by column name
	        	int id=rs.getInt("plantId");
	        	String pname=rs.getString("plantName");
	        	String cname=rs.getString("originCountryName");
	        	int sun=rs.getInt("sunlightRequired");
	        	String water=rs.getString("waterSupplyFrequency");
	        	String ptype=rs.getString("plantType");
	        	double cost=rs.getDouble("cost");
	        	Plant p=new Plant(id,pname,ptype,cname,water,cost,sun);
	            arr.add(p);
	         }
	        
		} catch(SQLException e) {
			
		}
		if(arr.size()<=0) {
			throw new NurseryException("no records found!");
		}
        return arr;
	}
	public ArrayList<Plant> searchOutdoorPlantsWithSunlight(){
	    ArrayList<Plant> arr = new ArrayList<Plant>();
	    try {
	        Statement stmt = conn.createStatement();
	        String query1 = "SELECT * FROM plant WHERE plantType = 'outdoor' AND sunlightRequired = 1";
	        ResultSet rs = stmt.executeQuery(query1);
	        while (rs.next()) {
	            // Retrieve by column name
	            int id = rs.getInt("plantId");
	            String pname = rs.getString("plantName");
	            String cname = rs.getString("originCountryName");
	            int sun = rs.getInt("sunlightRequired");
	            String water = rs.getString("waterSupplyFrequency");
	            String ptype = rs.getString("plantType");
	            double cost = rs.getDouble("cost");
	            Plant p = new Plant(id, pname, ptype, cname, water, cost, sun);
	            arr.add(p);
	        }
	    } catch (SQLException e) {
	        // Handle the exception (e.g., print it for debugging)
	        e.printStackTrace();
	    }

	    return arr;
	}

	public int countPlantsByWaterSupplyFrequency(String waterSupplyFrequency) {
		
		String query = "SELECT COUNT(*) FROM plant WHERE waterSupplyFrequency = '" + waterSupplyFrequency + "'";
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return 0;
	}

}

