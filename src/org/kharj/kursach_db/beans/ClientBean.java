package org.kharj.kursach_db.beans;

import java.util.List;

import org.kharj.kursach_db.Client;
import org.kharj.kursach_db.DBConnector;

public class ClientBean {
	private List<Client> clients;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientBean(){
		
	}
	
	public List<Client> getClients() {
		DBConnector connector = new DBConnector();
		clients = connector.GetClientsByName(name);
		return clients;
	}
	public void setClients(List<Client> l) {
		//readonly
	}

}
