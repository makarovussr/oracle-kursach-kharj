package org.kharj.kursach_db.beans;

import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.hibernate.Transaction;
import org.kharj.kursach_db.*;
@ManagedBean(name = "vehicleBean", eager = true)
@SessionScoped
public class VehicleBean {
	private String registrationNumber = "";
	private Float maxLoad = 0.0f;
	private City homeCity = null;
	private String model = "";
	private Integer id = null;
	private Vehicle vehicle = null;
	private RouteMap currentLocation = null;
	private RouteMap lastLocation = null;
	
	public String Update(){
		System.out.println("Update");
		if(id == null) return "";
		DBConnector connector = new DBConnector();
		vehicle = connector.GetVehicleById(id);
		if(vehicle != null){
			vehicle.registrationNumber = registrationNumber;
			vehicle.model = model;
			vehicle.maxLoad = maxLoad;
			connector.UpdateVehicle(vehicle);
		}
		connector.Close();
		return "";
	}
	private void LoadVehicle(){
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strId = params.get("selectedVehicleId");
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {
				//System.out.print("error parse id = "+id);
			}


			if(vehicle == null || !(id.equals(vehicle.id))){
			DBConnector connector = new DBConnector();
			vehicle = connector.GetVehicleById(id);
			currentLocation = connector.GetVehicleCurrentLocation(vehicle);
			lastLocation = connector.GetVehicleLastLocation(vehicle);		
			
			connector.Close();

			if(vehicle == null){
				System.out.print("Client not found by id "+id);
				return;
			}
			registrationNumber = vehicle.registrationNumber;
			model = vehicle.model;
			homeCity = vehicle.homeCity;
			maxLoad = vehicle.maxLoad;
		}
	}

	public Vehicle getVehicle() {
		LoadVehicle();
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public String getModel() {
		LoadVehicle();
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public City getHomeCity() {
		LoadVehicle();
		return homeCity;
	}
	public void setHomeCity(City homeCity) {
		this.homeCity = homeCity;
	}
	public Float getMaxLoad() {
		LoadVehicle();
		return maxLoad;
	}
	public void setMaxLoad(Float maxLoad) {
		this.maxLoad = maxLoad;
	}
	public String getRegistrationNumber() {
		LoadVehicle();
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public RouteMap getCurrentLocation() {
		LoadVehicle();
		return currentLocation;
	}

	public RouteMap getLastLocation() {
		LoadVehicle();
		return lastLocation;
	}
	public Integer getId() {
		return id;
	}

}
