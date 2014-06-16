package org.kharj.kursach_db.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.kharj.kursach_db.*;
@ManagedBean(name = "routeBean", eager = true)
@SessionScoped
public class RouteBean {
	private Integer id = null;
	private Vehicle vehicle = null;
	private Route route = null;
	private List<RouteMap> routeMaps = new ArrayList<RouteMap>();

	public String Update(){
		System.out.print("update");
		if(id == null) return "";
		DBConnector connector = new DBConnector();
		route = connector.GetRouteById(id);
		if(route != null){
			route.vehicle = vehicle;
			connector.UpdateRoute(route);
		}
		connector.Close();
		return "";
	}
	private void LoadRoute(){
		
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strId = params.get("selectedRouteId");
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {
				//System.out.print("Client not found by id "+id);
			}

		if(route == null || !(id.equals(route.id))){
			DBConnector connector = new DBConnector();
			route = connector.GetRouteById(id);
			routeMaps = connector.GetRoutemapByRoute(route);
			connector.Close();

			if(route == null){
				System.out.print("Client not found by id "+id);
				return;
			}
			vehicle = route.vehicle;
		}
	}
	public String DeleteRouteMapRow(){
		Integer schId = null;
		try {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strId = params.get("mapRemoveIdStr");
			schId = Integer.parseInt(strId);
		} catch (Exception e) {
			//System.out.print("Client not found by id "+id);
		}
		if(schId!=null){
			for(RouteMap s : routeMaps){
				if(s.id.equals(schId)){
					//db
					DBConnector connector = new DBConnector();
					connector.RemoveRouteMap(s);
					connector.Close();
					
					routeMaps.remove(s);					
				}
			}
		}
		
		return "";
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Route getRoute() {
		LoadRoute();
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public List<RouteMap> getRouteMaps() {
		LoadRoute();
		return routeMaps;
	}
	public void setRouteMaps(List<RouteMap> routeMaps) {
		this.routeMaps = routeMaps;
	}
	public Vehicle getVehicle() {
		LoadRoute();
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String strCityId = params.get("selectedVehicleId");
		if(strCityId != null){
			Integer cityId = null;
			try {
				cityId = Integer.parseInt(strCityId);
			} catch (Exception e) {}
			DBConnector connector = new DBConnector();
			vehicle = connector.GetVehicleById(cityId);
			connector.Close();
		}
		return vehicle;
	}
	



}
