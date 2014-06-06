package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name="parcel_types")
public class ParcelType {	
	public ParcelType(String name, Float maxWeight, Float minWeight) {
		this.name = name;
		this.maxWeight = maxWeight;
		this.minWeight = minWeight;
	}

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
    @Column(name="name", nullable = false)
	public String name;
    
    @Column(name="max_weight", nullable = false)
   	public Float maxWeight;
    
    @Column(name="min_weight", nullable = false)
   	public Float minWeight;
    
    public ParcelType(){
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Float maxWeight) {
		this.maxWeight = maxWeight;
	}

	public Float getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(Float minWeight) {
		this.minWeight = minWeight;
	}

	@Override
	public String toString() {
		return "ParcelType [id=" + id + ", name=" + name + ", maxWeight="
				+ maxWeight + ", minWeight=" + minWeight + "]";
	}
	
	
}