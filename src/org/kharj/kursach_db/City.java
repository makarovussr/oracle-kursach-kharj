package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;


@Entity
@Table(name="cities")
public class City {	
	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@Column(name="name", nullable = false)
	public String name;
	
	@Column(name="location")
	public String location;

	public City(){
		
	}
	
	public City(String name, String location){
		this.name = name;
		this.location = location;		
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", location=" + location
				+ "]";
	}
}