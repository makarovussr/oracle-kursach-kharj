package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;


@Entity
@Table(name="cities")
public class City {	
	public City(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@Column(name="name", nullable = false)
	public String name;
	
	@Column(name="address")
	public String address;
	
	@Column(name="phone")
	public String phone;

	public City(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}