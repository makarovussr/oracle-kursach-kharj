package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name="routes")
public class Route {	
	public Route(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@ManyToOne
    @JoinColumn(name = "vehicle", nullable = true)
	public Vehicle vehicle;
	
	public Route(){
		
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

	@Override
	public String toString() {
		return "Route [id=" + id + ", vehicle=" + vehicle + "]";
	}
}
