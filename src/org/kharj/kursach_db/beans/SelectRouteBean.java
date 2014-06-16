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
		private ParcelType parcelType = null;
		private String navigateTo = "Vehicle.xhtml";
		private Integer selectedId = null;
		private Parcel parcel = null;
		private List<Route> routes = new ArrayList<Route>();
		private String act = "";
		
		public String Select(){
			//clean
		
			vehicle = null;
			maxLoad = 0.0f;
			cityFrom = null;
			cityTo = null;
			dateFrom = null;
			dateTo = null;
			parcelType = null;
			navigateTo = "Route.xhtml";
			selectedId = null;
			routes = new ArrayList<Route>();
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		  String strId = params.get("selectedIdStr");

		  String act = params.get("act");
			if(act != null && act.equals("parcelSearchRoute")){
				navigateTo = "SelectParcel.xhtml";
			}else if(act != null && act.equals("parcelChangeRoute")){
				navigateTo = "Parcel.xhtml";
			}
		  
			Integer id = null;
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {}
			selectedId = id;
			
				String req = "";
				if(navigateTo.contains("?")){
					req = "&selectedRouteId="+selectedId;
				}else{
					req = "?selectedRouteId="+selectedId;
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


		public Vehicle getVehicle() {
			return vehicle;
		}


		public void setVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
		}


		public Float getMaxLoad() {
			return maxLoad;
		}


		public void setMaxLoad(Float maxLoad) {
			this.maxLoad = maxLoad;
		}


		public City getCityFrom() {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strCityParam = params.get("cityParam");
			String strCityId = params.get("selectedCityId");

			System.out.print(params.toString()+strCityParam+strCityId);
			if(strCityId != null && strCityParam != null && strCityParam.equals("from")){
				Integer cityId = null;
				try {
					cityId = Integer.parseInt(strCityId);
				} catch (Exception e) {}
				System.out.print("cityid="+cityId);
				DBConnector connector = new DBConnector();
				cityFrom = connector.GetCityById(cityId);
				connector.Close();
			}
			System.out.print("cityfrom="+cityFrom);
			return cityFrom;
		}


		public void setCityFrom(City cityFrom) {
			this.cityFrom = cityFrom;
		}


		public City getCityTo() {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strCityParam = params.get("cityParam");
			String strCityId = params.get("selectedCityId");
			if(strCityId != null && strCityParam != null && strCityParam.equals("to")){
				Integer cityId = null;
				try {
					cityId = Integer.parseInt(strCityId);
				} catch (Exception e) {}
				DBConnector connector = new DBConnector();
				cityTo = connector.GetCityById(cityId);
				connector.Close();
			}
			return cityTo;
		}


		public void setCityTo(City cityTo) {
			this.cityTo = cityTo;
		}


		public Date getDateFrom() {
			return dateFrom;
		}


		public void setDateFrom(Date dateFrom) {
			this.dateFrom = dateFrom;
		}


		public Date getDateTo() {
			return dateTo;
		}


		public void setDateTo(Date dateTo) {
			this.dateTo = dateTo;
		}


		public ParcelType getParcelType() {
			return parcelType;
		}


		public void setParcelType(ParcelType parcelType) {
			this.parcelType = parcelType;
		}


		public List<Route> getRoutes() {
			DBConnector connector = new DBConnector();
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strPId = params.get("selectedParcelId");
			String strVId = params.get("selectedVehicleId");
			System.out.print(strPId);

			Integer parcelId = null;
			Integer vehicleId = null;
			try {
				parcelId = Integer.parseInt(strPId);
			} catch (Exception e) {}
			try {
				vehicleId = Integer.parseInt(strVId);
			} catch (Exception e) {}
			if(parcelId!=null){
				Parcel pa = connector.GetParcelById(parcelId);
				routes = connector.GetRoutesForParcel(pa);
			}else if(vehicleId!=null){
				Vehicle va = connector.GetVehicleById(vehicleId);
				routes = connector.GetRoutesByVehicle(va);
			}else if(dateFrom != null && dateTo != null && cityFrom!=null && cityTo !=null){
				routes = connector.GetRoutesFromCityToCityBetweenDate(cityFrom, cityTo, new java.sql.Date(dateFrom.getTime()), new java.sql.Date(dateTo.getTime()));
			}else if(cityFrom!=null && cityTo !=null){
				routes = connector.GetRoutesFromCityToCity(cityFrom, cityTo);
			}
			connector.Close();
			
			
			return routes;
		}


		public Parcel getParcel() {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strCityId = params.get("selectedParcelId");
			if(strCityId != null){
				Integer cityId = null;
				try {
					cityId = Integer.parseInt(strCityId);
				} catch (Exception e) {}
				DBConnector connector = new DBConnector();
				parcel = connector.GetParcelById(cityId);
				connector.Close();
			}
			return parcel;
		}


		public void setParcel(Parcel parcel) {
			this.parcel = parcel;
		}


	
		public String getAct() {
			return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("act");
		}




}



