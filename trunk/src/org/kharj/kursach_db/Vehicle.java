package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name="vehicles")
public class Vehicle {	
	public Vehicle(String registrationNumber, Float maxLoad, City homeCity,
			String model) {
		this.registrationNumber = registrationNumber;
		this.maxLoad = maxLoad;
		this.homeCity = homeCity;
		this.model = model;
	}

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@Column(name = "registration_number")
	public String registrationNumber;
	
	@Column(name="max_load", nullable = false)
   	public Float maxLoad;
	
	@ManyToOne
    @JoinColumn(name = "home_city", nullable = true)
	public City homeCity;
	
	@Column(name = "model")
	public String model;
    
    public Vehicle(){
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Float getMaxLoad() {
		return maxLoad;
	}

	public void setMaxLoad(Float maxLoad) {
		this.maxLoad = maxLoad;
	}

	public City getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(City homeCity) {
		this.homeCity = homeCity;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", registrationNumber="
				+ registrationNumber + ", maxLoad=" + maxLoad + ", homeCity="
				+ homeCity + ", model=" + model + "]";
	}
}