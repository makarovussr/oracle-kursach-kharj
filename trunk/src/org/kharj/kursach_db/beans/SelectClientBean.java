package org.kharj.kursach_db.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;

import org.kharj.kursach_db.Client;
import org.kharj.kursach_db.DBConnector;
@ManagedBean(name = "selectClientBean", eager = true)
@SessionScoped
public class SelectClientBean {

		private List<Client> clients = new ArrayList<Client>();
		private String name = "";
		private String email = "";
		private String address = "";
		private String phone = "";
		public List<Client> getClients() {
			DBConnector connector = new DBConnector();
			if(name != null && name.length()>0)
				clients = connector.GetClientsByName(name);
			else if(name != null && email.length()>0)
				clients = connector.GetClientsByEmail(email);
			else if(name != null && address.length()>0)
				clients = connector.GetClientsByAddress(address);
			else if(name != null && phone.length()>0)
				clients = connector.GetClientsByPhone(phone);
			
			connector.Close();
			return clients;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
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
}
