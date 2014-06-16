package org.kharj.kursach_db.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.kharj.kursach_db.*;
@ManagedBean(name = "selectParcelBean", eager = true)
@SessionScoped
public class SelectParcelBean {

		private Route route = null;
		private City cityFrom = null;
		private City cityTo = null;
		private Client clientFrom = null;
		private Client clientTo = null;
		private ParcelType parcelType = null;
		private Float weight = 0.0f;
		private Float price = 0.0f;
		private String description = "";
		private Date acceptDate = null;
		private String navigateTo = "Parcel.xhtml";
		private Integer selectedId = null;
		private List<Parcel> parcels = new ArrayList<Parcel>();
		private RouteMap currentPosition = null;
		private String act = "";
		private Integer id = null;
		
		
		public String Reset(){
			//clean		
			route = null;
			cityFrom = null;
			cityTo = null;
			clientFrom = null;
			clientTo = null;
			parcelType = null;
			weight = 0.0f;
			price = 0.0f;
			description = "";
			acceptDate = null;
			navigateTo = "Parcel.xhtml";
			selectedId = null;
			parcels = new ArrayList<Parcel>();
			currentPosition = null;
			act = "";
			id = null;
			try{
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("SelectParcel.xhtml");
			} catch (IOException e) {
				System.out.print("Cant redirect to ");
				e.printStackTrace();
			} 
			return "";
			
		}
		public String Select(){
			//clean		
			route = null;
			cityFrom = null;
			cityTo = null;
			clientFrom = null;
			clientTo = null;
			parcelType = null;
			weight = 0.0f;
			price = 0.0f;
			description = "";
			acceptDate = null;
			navigateTo = "Parcel.xhtml";
			selectedId = null;
			parcels = new ArrayList<Parcel>();
			currentPosition = null;
			act = "";
			
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		  String strId = params.get("selectedIdStr");

			Integer id = null;
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {}
			selectedId = id;
			
				String req = "";
				if(navigateTo.contains("?")){
					req = "&selectedParcelId="+selectedId;
				}else{
					req = "?selectedParcelId="+selectedId;
				}
			try{
				
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigateTo+req);
			} catch (IOException e) {
				System.out.print("Cant redirect to "+navigateTo);
				e.printStackTrace();
			} 
			
			return strId;
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


	
		public String getAct() {
			return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("act");
		}



		public Route getRoute() {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strCityId = params.get("selectedRouteId");

			if(strCityId != null){
				Integer cityId = null;
				try {
					cityId = Integer.parseInt(strCityId);
				} catch (Exception e) {}
				System.out.print("cityid="+cityId);
				DBConnector connector = new DBConnector();
				route = connector.GetRouteById(cityId);
				connector.Close();
			}
			return route;
		}



		public void setRoute(Route route) {
			this.route = route;
		}



		public Client getClientFrom() {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strCityParam = params.get("clientParam");
			String strCityId = params.get("selectedClientId");

			System.out.print(params.toString()+strCityParam+strCityId);
			if(strCityId != null && strCityParam != null && strCityParam.equals("from")){
				Integer cityId = null;
				try {
					cityId = Integer.parseInt(strCityId);
				} catch (Exception e) {}
				System.out.print("cityid="+cityId);
				DBConnector connector = new DBConnector();
				clientFrom = connector.GetClientById(cityId);
				connector.Close();
			}
			System.out.print("cityfrom="+cityFrom);
			return clientFrom;
		}



		public void setClientFrom(Client clientFrom) {
			this.clientFrom = clientFrom;
		}



		public Client getClientTo() {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strCityParam = params.get("clientParam");
			String strCityId = params.get("selectedClientId");

			System.out.print(params.toString()+strCityParam+strCityId);
			if(strCityId != null && strCityParam != null && strCityParam.equals("to")){
				Integer cityId = null;
				try {
					cityId = Integer.parseInt(strCityId);
				} catch (Exception e) {}
				System.out.print("cityid="+cityId);
				DBConnector connector = new DBConnector();
				clientTo = connector.GetClientById(cityId);
				connector.Close();
			}
			System.out.print("cityfrom="+cityFrom);
			return clientTo;
		}



		public void setClientTo(Client clientTo) {
			this.clientTo = clientTo;
		}



		public ParcelType getParcelType() {
			return parcelType;
		}



		public void setParcelType(ParcelType parcelType) {
			this.parcelType = parcelType;
		}



		public Float getWeight() {
			return weight;
		}



		public void setWeight(Float weight) {
			this.weight = weight;
		}



		public Float getPrice() {
			return price;
		}



		public void setPrice(Float price) {
			this.price = price;
		}



		public String getDescription() {
			return description;
		}



		public void setDescription(String description) {
			this.description = description;
		}



		public Date getAcceptDate() {
			return acceptDate;
		}



		public void setAcceptDate(Date acceptDate) {
			this.acceptDate = acceptDate;
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



		public RouteMap getCurrentPosition() {
			return currentPosition;
		}

		public List<Parcel> getParcels() {
			DBConnector connector = new DBConnector();
			if(id != null)
				parcels = connector.GetParcelsById(id);
			else if(clientFrom != null)
				parcels = connector.GetParcelsByClient(clientFrom);
			else if(cityFrom != null && cityTo != null)
				parcels = connector.GetParcelsFromCityToCity(cityFrom, cityTo);
			else if(route != null)
				parcels = connector.GetParcelsByRoute(route);
			connector.Close();
			return parcels;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}

		




}



