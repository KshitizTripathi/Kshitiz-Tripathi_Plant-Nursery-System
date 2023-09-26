package com.plantnursery.system;
import com.plantnursery.model.Plant;
import com.plantnursery.dao.PlantDAO;
import com.plantnursery.exp.NurseryException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlantNurserySystem {
    public static void main(String[] args) {

        try {


            Scanner sc = new Scanner(System.in);
            int choice;
            
            
            PlantDAO plantDAO = new PlantDAO();
            
            do {
	        	 System.out.println("Press 1 to add new plant");
		         System.out.println("Press 2 to update plant cost");
		         System.out.println("Press 3 to delete plant");
		         System.out.println("Press 4 to view all plants");
		         System.out.println("Press 5 to find plant by origin country name");
		         System.out.println("Press 6 to find outdoor plants which requires sunlight");
		         System.out.println("Press 7 to count plants by water supply frequency");
		         System.out.println("Press 8 to exit");
		         System.out.println("Please enter your choice");
		         choice=sc.nextInt();
		         switch(choice) {
	         		case 1:
	         			System.out.println("Enter all details");
	         			System.out.print("Enter Plant ID: ");
	         			int id = sc.nextInt();
	         			sc.nextLine(); // Consume the newline character

	         			System.out.print("Enter Plant Name: ");
	         			String pname = sc.nextLine();

	         			System.out.print("Enter Origin Country Name: ");
	         			String cname = sc.nextLine();

	         			System.out.print("Enter Sunlight Required (1 for true, 0 for false): ");
	         			int sun = sc.nextInt();
	         			sc.nextLine(); // Consume the newline character

	         			System.out.print("Enter Water Supply Frequency: ");
	         			String water = sc.nextLine();

	         			System.out.print("Enter Plant Type (indoor/outdoor): ");
	         			String type = sc.nextLine();

	         			System.out.print("Enter Cost: ");
	         			double cost = sc.nextDouble();
	         			sc.nextLine(); // Consume the newline character

	         			Plant p=new Plant(id,pname,type,cname,water,cost,sun);
	         			
	         			plantDAO.addPlant(p);

	         			break;
	         		case 2:
	         			System.out.println("Enter updated cost");
	         			double ucost=sc.nextDouble();
	         			if(ucost<=0) {
	         				throw new NurseryException("Cost should be postive");
	         			}
	         			sc.nextLine();
	         			System.out.println("Enter plant name where updation needs to be done");
	         			int plid=sc.nextInt();
	         			
	         			
	         			boolean b=plantDAO.updatePlantCost(plid, ucost);

	         			break;
	         		case 3:
	         			System.out.println("Enter the id of plant which has to be deleted");
	         			int plid2=sc.nextInt();
	         			
	         			plantDAO.deletePlant(plid2);

	         			break;
	         		case 4:
//	         			
	         			
	         			ArrayList<Plant> arr=plantDAO.showAllPlants();
	        	        for(int i=0;i<arr.size();i++) {
	    	        	Plant t=arr.get(i);
	    	        	System.out.println();
	    	        	System.out.print("ID "+ t.plantId+" ");
	    	        	System.out.print("Plant Name "+ t.plantName+" ");
	    	        	System.out.print("Origin Country Name "+ t.originCountryName+" ");
	    	        	System.out.print("Plant Type "+ t.plantType+" ");
	    	        	System.out.print("Cost "+ t.cost);
	    	        }
	         			break;
	         		case 5:
	         			System.out.println("Enter origin country name");
	         			sc.nextLine();
	         			String cntry=sc.nextLine();
	         			try {
		         			ArrayList<Plant> arr2=plantDAO.searchByOriginCountryName(cntry);
		         			for(int i=0;i<arr2.size();i++) {
		        	        	Plant t=arr2.get(i);
		        	        	System.out.println();
		        	        	System.out.print("ID "+ t.plantId+" ");
		        	        	System.out.print("Plant Name "+ t.plantName+" ");
		        	        	System.out.print("Origin Country Name "+ t.originCountryName+" ");
		        	        	System.out.print("Plant Type "+ t.plantType+" ");
		        	        	System.out.print("Cost "+ t.cost);
		        	        }
	         			} catch(NurseryException e) {System.out.println(e);}
	         			break;
	         		case 6:
	         			
	         			ArrayList<Plant> arr3=plantDAO.searchOutdoorPlantsWithSunlight();
	         			
	         			System.out.println("size"+arr3.size());
	         			for(int i=0;i<arr3.size();i++) {
	        	        	Plant t=arr3.get(i);
	        	        	System.out.println();
	        	        	System.out.print("ID "+ t.plantId+" ");
	        	        	System.out.print("Plant Name "+ t.plantName+" ");
	        	        	System.out.print("Origin Country Name "+ t.originCountryName+" ");
	        	        	System.out.print("Plant Type "+ t.plantType+" ");
	        	        	System.out.print("Cost "+ t.cost);
	        	        }
	         			break;
	         		case 7:
	         			
	         			System.out.println("Enter whether search is to be daily, weekly or monthly ");
	         			sc.nextLine();
	         			String water1=sc.nextLine();
	         			int ans=plantDAO.countPlantsByWaterSupplyFrequency(water1);
	         			System.out.println("Total plants with your condition "+water1+" is "+ans);
	         			break;
	         		case 8:
	         			System.out.println("Exiting from system");
	         			break;
	         		
	         		default:
	         			System.out.println("Invalid choice");
	         			break;
	         }
	         } while(choice!=8);

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

