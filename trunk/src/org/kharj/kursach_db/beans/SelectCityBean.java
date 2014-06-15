package org.kharj.kursach_db.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.kharj.kursach_db.*;
@ManagedBean(name = "selectCityBean", eager = true)
@SessionScoped
public class SelectCityBean {

		private List<City> cities = new ArrayList<City>();
		private String name = "";
		private String address = "";
		private String phone = "";
		private String navigateTo = "City.xhtml";
		private Integer selectedId = null;
		
		public String Select(){
			//clean
			name = "";
			address = "";
			phone = "";
			cities = new ArrayList<City>();
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		  String strId = params.get("selectedIdStr");

			Integer id = null;
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {}
			selectedId = id;
			
				String req = "";
				if(navigateTo.contains("?")){
					req = "&selectedCityId="+selectedId;
				}else{
					req = "?selectedCityId="+selectedId;
				}
			try{
				
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigateTo+req);
			} catch (IOException e) {
				System.out.print("Cant redirect to "+navigateTo);
				e.printStackTrace();
			} 
			
			return strId;
		}
		
		public List<City> getCities() {
			DBConnector connector = new DBConnector();
			if(name != null && name.length()>0)
				cities = connector.GetCitiesByName("K");
			connector.Close();
			return cities;
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
}



