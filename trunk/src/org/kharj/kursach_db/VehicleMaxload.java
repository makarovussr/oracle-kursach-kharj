package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name="vehicle_maxloads")
public class VehicleMaxload {	
	public VehicleMaxload(Vehicle vehicle, ParcelType parcelType,
			Float maxWeight, Integer maxCount) {
		this.vehicle = vehicle;
		this.parcelType = parcelType;
		this.maxWeight = maxWeight;
		this.maxCount = maxCount;
	}

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@ManyToOne
    @JoinColumn(name = "vehicle", nullable = false)
	public Vehicle vehicle;

	@ManyToOne
    @JoinColumn(name = "parcel_type", nullable = false)
	public ParcelType parcelType;
	
	@Column(name="max_weight", nullable = false)
   	public Float maxWeight;
	
	@Column(name="max_count")
   	public Integer maxCount;
	
	public VehicleMaxload(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ParcelType getParcelType() {
		return parcelType;
	}

	public void setParcelType(ParcelType parcelType) {
		this.parcelType = parcelType;
	}

	public Float getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Float maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	@Override
	public String toString() {
		return "VehicleMaxload [id=" + id + ", vehicle=" + vehicle
				+ ", parcelType=" + parcelType + ", maxWeight=" + maxWeight
				+ ", maxCount=" + maxCount + "]";
	}
}