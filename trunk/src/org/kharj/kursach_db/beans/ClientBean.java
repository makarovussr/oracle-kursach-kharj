package org.kharj.kursach_db.beans;

import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.hibernate.Transaction;
import org.kharj.kursach_db.*;
@ManagedBean(name = "clientBean", eager = true)
@SessionScoped
public class ClientBean {
	private String firstName = "";
	private String lastName = "";
	private String middleName = "";
	private String email = "";
	private String address = "";
	private String phone = "";
	private Integer id = null;
	private City city = null;
	private Client client = null;

	public String Update(){
		if(id == null) return "";
		DBConnector connector = new DBConnector();
		client = connector.GetClientById(id);
		if(client != null){
			client.firstName = firstName;
			client.middleName = middleName;
			client.lastName = lastName;
			client.address = address;
			client.phone = phone;
			client.email = email;
			connector.UpdateClient(client);
		}
		connector.Close();
		return "";
	}
	private void LoadClient(){
		if(id == null){
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strId = params.get("selectedClientId");
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {
				System.out.print("Client not found by id "+id);
			}
		}

		if(client == null){
			DBConnector connector = new DBConnector();
			client = connector.GetClientById(id);
			connector.Close();

			if(client == null){
				System.out.print("Client not found by id "+id);
				return;
			}
			firstName = client.firstName;
			middleName = client.middleName;
			lastName = client.lastName;
			email = client.lastName;
			phone = client.phone;
			city = client.city;
		}
	}

	public String getFirstName() {
		LoadClient();
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		LoadClient();
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		LoadClient();
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmail() {
		LoadClient();
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		LoadClient();
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		LoadClient();
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public City getCity() {
		LoadClient();
		return city;
	}
	public Client getClient() {
		LoadClient();
		return client;
	}

}
