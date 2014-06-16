package org.kharj.kursach_db.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.kharj.kursach_db.*;
@ManagedBean(name = "selectVehicleBean", eager = true)
@SessionScoped
public class SelectVehicleBean {

		private String registrationNumber = "";
		private Float maxLoad = 0.0f;
		private City homeCity = null;
		private String model = "";
		private String act = "";
		private String navigateTo = "Vehicle.xhtml";
		private Integer selectedId = null;
		private List<Vehicle> vehicles = new ArrayList<Vehicle>();
		public String Select(){
			//clean
			setRegistrationNumber("");
			setMaxLoad(0.0f);
			setHomeCity(null);
			setModel("");
			vehicles = new ArrayList<Vehicle>();
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		  String strId = params.get("selectedIdStr");
		  String strAct = params.get("act");
		  navigateTo = "Vehicle.xhtml";
		  if(strAct.equals("routeChange")){
			  navigateTo = "Route.xhtml";
		  }
			Integer id = null;
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {}
			selectedId = id;
			
				String req = "";
				if(navigateTo.contains("?")){
					req = "&selectedVehicleId="+selectedId;
				}else{
					req = "?selectedVehicleId="+selectedId;
				}
			try{
				
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigateTo+req);
			} catch (IOException e) {
				System.out.print("Cant redirect to "+navigateTo);
				e.printStackTrace();
			} 
			
			return strId;
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

		public String getRegistrationNumber() {
			return registrationNumber;
		}


		public void setRegistrationNumber(String registrationNumber) {
			this.registrationNumber = registrationNumber;
		}


		public Float getMaxLoad() {
			return maxLoad;
		}


		public void setMaxLoad(Float maxLoad) {
			this.maxLoad = maxLoad;
		}


		public City getHomeCity() {
			return homeCity;
		}


		public void setHomeCity(City homeCity) {
			this.homeCity = homeCity;
		}


		public String getModel() {
			return model;
		}


		public void setModel(String model) {
			this.model = model;
		}


		public List<Vehicle> getVehicles() {
			DBConnector connector = new DBConnector();
			if(registrationNumber != null && registrationNumber.length()>0)
				vehicles = connector.GetVehiclesByRegNumber(registrationNumber);
			else if(model != null && model.length()>0)
				vehicles = connector.GetVehiclesByModel(model);
			connector.Close();
			return vehicles;
		}
		public String getAct() {
			return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("act");
		}

}



