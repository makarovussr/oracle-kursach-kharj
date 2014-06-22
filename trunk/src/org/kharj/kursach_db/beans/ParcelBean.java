package org.kharj.kursach_db.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import oracle.jdbc.proxy.annotation.GetProxy;

import org.kharj.kursach_db.*;
@ManagedBean(name = "parcelBean", eager = true)
@SessionScoped
public class ParcelBean {

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
		private Integer id = null;
		private Parcel parcel = null;
		private RouteMap currentPosition = null;
		private String act = "";
		private String parcelTypeName = "";
		
		
		
		public String Update(){
			System.out.print("update");
			if(id == null) return "";
			DBConnector connector = new DBConnector();
			parcel = connector.GetParcelById(id);
			if(parcel != null){
				//parcel.acceptDate = acceptDate;
				parcel.cityFrom = cityFrom;
				parcel.cityTo = cityTo;
				parcel.clientFrom = clientFrom;
				parcel.clientTo = clientTo;
				parcel.description = description;
				parcel.parcelType = parcelType;
				parcel.price = price;
				parcel.route = route;
				parcel.weight = weight;


				connector.UpdateParcel(parcel);
			}
			connector.Close();
			return "";
		}
		
	
		
		private void Create(){
			
			DBConnector connector = new DBConnector();
			Parcel p = connector.CreateEmptyParcel();
			if(p == null) return;
			
			String req = "";
			if(navigateTo.contains("?")){
				req = "&selectedParcelTypeId="+p.id;
			}else{
				req = "?selectedParcelTypeId="+p.id;
			}
			navigateTo="Parcel.xhtml";
			parcel = p;
			id = p.id;
			/*try{				
				FacesContext.getCurrentInstance().getExternalContext().redirect(navigateTo+req);
			} catch (IOException e) {
				System.out.print("Cant redirect to "+navigateTo);
				e.printStackTrace();
			} */
		
		}
		private void LoadParcel(){
			
				Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				String strId = params.get("selectedParcelId");
				String isnew = params.get("newParcel");
				
				if(isnew != null && parcel == null){
					Create();
					//return;
				}
				
				
				try {
					id = Integer.parseInt(strId);
				} catch (Exception e) {
					//System.out.print("Client not found by id "+id);
				}

			if(parcel == null || !(id.equals(parcel.id))){
				DBConnector connector = new DBConnector();
				parcel = connector.GetParcelById(id);
				currentPosition = connector.GetParcelLastLocation(parcel);
				connector.Close();

				if(parcel == null){
					System.out.print("Client not found by id "+id);
					return;
				}
				clientFrom = parcel.clientFrom;
				clientTo = parcel.clientTo;
				cityTo =parcel.cityTo;
				cityFrom = parcel.cityFrom;
				acceptDate = parcel.acceptDate;
				description = parcel.description;
				price = parcel.price;
				route = parcel.route;
				parcelType = parcel.parcelType;
				weight = parcel.weight;
			}
		}
		
		public City getCityFrom() {
			LoadParcel();
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
			LoadParcel();
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
			LoadParcel();
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
				Update();
			}
			return route;
		}



		public void setRoute(Route route) {
			this.route = route;
		}



		public Client getClientFrom() {
			LoadParcel();
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
			LoadParcel();
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
			LoadParcel();
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strCityId = params.get("selectedParcelTypeId");
			if(strCityId != null ){
				Integer cityId = null;
				try {
					cityId = Integer.parseInt(strCityId);
				} catch (Exception e) {}
				System.out.print("cityid="+cityId);
				DBConnector connector = new DBConnector();
				parcelType = connector.GetParcelTypeById(cityId);
				connector.Close();
			}

			route = null;
			getPrice();
			
			return parcelType;
		}



		public void setParcelType(ParcelType parcelType) {
			this.parcelType = parcelType;
		}



		public Float getWeight() {
			LoadParcel();
			return weight;
		}



		public void setWeight(Float weight) {
			this.weight = weight;
		}



		public Float getPrice() {
			LoadParcel();
			if(parcel != null){
				DBConnector connector = new DBConnector();
				try{
					this.price = connector.GetPriceForParcel(parcel);
				}catch(Exception ex){
				}
				connector.Close();
			}
			return price;
		}

		public String getDescription() {
			LoadParcel();
			return description;
		}



		public void setDescription(String description) {
			this.description = description;
		}



		public Date getAcceptDate() {
			LoadParcel();
			return acceptDate;
		}


		public String getNavigateTo() {
			return navigateTo;
		}



		public void setNavigateTo(String navigateTo) {
			this.navigateTo = navigateTo;
		}



		public Integer getId() {
			return id;
		}



		public void setId(Integer id) {
			this.id = id;
		}



		public RouteMap getCurrentPosition() {
			LoadParcel();
			return currentPosition;
		}

		public String RedirectSelectRoute(){
			String nav = "SelectRoute.xhtml?act=parcelChangeRoute&selectedParcelId="+id;
			try{				
			FacesContext.getCurrentInstance().getExternalContext().redirect(nav);
		} catch (IOException e) {
			System.out.print("Cant redirect to ");
			e.printStackTrace();
		} 
			return "";
		}



		public String getParcelTypeName() {
			if(parcelType == null){
				return "";
			}
			return parcelType.name + "("+parcelType.minWeight.toString()+" - "+parcelType.maxWeight.toString()+")";
		}



		public void setParcelTypeName(String parcelTypeName) {
		}




}



