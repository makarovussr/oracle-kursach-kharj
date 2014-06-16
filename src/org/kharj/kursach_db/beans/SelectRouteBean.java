package org.kharj.kursach_db.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.kharj.kursach_db.*;
@ManagedBean(name = "selectRouteBean", eager = true)
@SessionScoped
public class SelectRouteBean {

		private Vehicle vehicle = null;
		private Float maxLoad = 0.0f;
		private City cityFrom = null;
		private City cityTo = null;
		private Date dateFrom = null;
		private Date dateTo = null;
		private Float weight = 0.0f;
		private ParcelType parcelType = null;
		private String navigateTo = "Vehicle.xhtml";
		private Integer selectedId = null;
		private List<Vehicle> vehicles = new ArrayList<Vehicle>();
		public String Select(){
			//clean
		
			vehicles = new ArrayList<Vehicle>();
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		  String strId = params.get("selectedIdStr");

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

		


		public Float getMaxLoad() {
			return maxLoad;
		}


		public void setMaxLoad(Float maxLoad) {
			this.maxLoad = maxLoad;
		}


		


}



