package org.kharj.kursach_db.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

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
		private String navigateTo = "Client.xhtml";
		private Integer selectedId = null;
		private String act = "";
		
		public String Select(){
			//clean
			name = "";
			email = "";
			address = "";
			phone = "";
			clients = new ArrayList<Client>();
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		  String strId = params.get("selectedIdStr");
		  
		//targets
			String act = params.get("act");
			System.out.print("act==="+act);
			navigateTo = "Client.xhtml";
			if(act != null && act.equals("parcelTo")){
				navigateTo = "SelectParcel.xhtml?clientParam=to";
			}else if(act != null && act.equals("parcelFrom")){
				navigateTo = "SelectParcel.xhtml?clientParam=from";
			}else if(act != null && act.equals("parcelChangeTo")){
				navigateTo = "Parcel.xhtml?clientParam=to";
			}else if(act != null && act.equals("parcelChangeFrom")){
				navigateTo = "Parcel.xhtml?clientParam=from";
			}
			
			
			
			
			/*else if(act != null && act.equals("clientChange")){
				navigateTo = "Client.xhtml";
			}else if(act != null && act.equals("vehicleChange")){
				navigateTo = "Vehicle.xhtml";
			}*/
			
			
			Integer id = null;
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {}
			selectedId = id;
			
				String req = "";
				if(navigateTo.contains("?")){
					req = "&selectedClientId="+selectedId;
				}else{
					req = "?selectedClientId="+selectedId;
				}
			try{
				
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigateTo+req);
			} catch (IOException e) {
				System.out.print("Cant redirect to "+navigateTo);
				e.printStackTrace();
			} 
			
			return strId;
		}
		
		
		
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

		public String getNavigateTo() {
			return navigateTo;
		}

		public void setNavigateTo(String navigateTo) {
			this.navigateTo = navigateTo;
		}
		public Integer getSelectedId() {
			return selectedId;
		}
		public void setSelectedId(Integer selectedId) {
			this.selectedId = selectedId;
		}
		public String getAct() {
			return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("act");
		}
}



