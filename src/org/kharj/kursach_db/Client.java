package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;


@Entity
@Table(name="clients")
public class Client {	


	public Client(String firstName, String lastName, String middleName,
			City city, String address, String phone, String email,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.city = city;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
		java.util.Date d = new java.util.Date();//today
		this.registrationDate = new Date(d.getTime());
	}

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@Column(name="first_name")
	public String firstName;
	
	@Column(name="last_name")
	public String lastName;
	
	@Column(name="middle_name")
	public String middleName;
	
	@ManyToOne
    @JoinColumn(name = "city", nullable = true)
	public City city;
	
	@Column(name="registration_date")
	public Date registrationDate;
	
	@Column(name="address")
	public String address;
	
	@Column(name="phone")
	public String phone;
	
	@Column(name="email")
	public String email;
	
	@Column(name="password")
	public String password;

	public Client(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
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
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", middleName=" + middleName + ", city=" + city
				+ ", registrationDate=" + registrationDate + ", address="
				+ address + ", phone=" + phone + ", email=" + email
				+ ", password=" + password + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}