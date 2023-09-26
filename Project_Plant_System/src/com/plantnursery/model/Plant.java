package com.plantnursery.model;

public class Plant {
	public int plantId;
	public String plantName;
	public String plantType;
	public String originCountryName;
	public String waterSupplyFrequency;
	public double cost;
	public int sunLightRequired;

    public Plant() {
    }

    public Plant(int pid, String pname, String type, String cname, String wfreq, double cost, int sun) {
        this.plantId = pid;
        this.plantName = pname;
        this.plantType = type;
        this.originCountryName = cname;
        this.waterSupplyFrequency = wfreq;
        this.cost = cost;
        this.sunLightRequired = sun;
    }

    // Getters and setters for all fields

    // Other methods as needed
}

