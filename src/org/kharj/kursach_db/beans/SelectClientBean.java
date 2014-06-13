package org.kharj.kursach_db.beans;

import java.util.ArrayList;
import java.util.List;

import org.kharj.kursach_db.Client;
import org.kharj.kursach_db.DBConnector;

public class SelectClientBean {

		private List<Client> clients = new ArrayList<Client>();
		private String name;
		private String email;
		private String address;
		private String phone;
		public List<Client> getClients() {
			System.out.print("getclient");
			DBConnector connector = new DBConnector();
			if(name.length()>0)
			clients = connector.GetClientsByName(name);
			else if(email.length()>0)
				clients = connector.GetClientsByEmail(email);
			else if(address.length()>0)
				clients = connector.GetClientsByAddress(address);
			else if(phone.length()>0)
				clients = connector.GetClientsByPhone(phone);
			return clients;
		}
		public void setClients(List<Client> clients) {
			//readonly
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
