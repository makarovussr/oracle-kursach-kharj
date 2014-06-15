package org.kharj.kursach_db.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.kharj.kursach_db.*;
@ManagedBean(name = "cityBean", eager = true)
@SessionScoped
public class CityBean {
	private String name = "";
	private String address = "";
	private String phone = "";
	private Integer id = null;
	private City city = null;
	private Boolean isOpen = false;
	private List<Schedule> schedules = new ArrayList<Schedule>();

	public String Update(){
		System.out.print("update");
		if(id == null) return "";
		DBConnector connector = new DBConnector();
		city = connector.GetCityById(id);
		if(city != null){
			city.name = name;
			city.address = address;
			city.phone = phone;
			connector.UpdateCity(city);
		}
		connector.Close();
		return "";
	}
	private void LoadCity(){
		
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strId = params.get("selectedCityId");
			try {
				id = Integer.parseInt(strId);
			} catch (Exception e) {
				//System.out.print("Client not found by id "+id);
			}

		if(city == null || !(id.equals(city.id))){
			DBConnector connector = new DBConnector();
			city = connector.GetCityById(id);
			schedules = connector.GetSchedulesByCity(city);
			connector.Close();

			if(city == null){
				System.out.print("Client not found by id "+id);
				return;
			}
			name = city.name;
			phone = city.phone;
			address = city.address;
			isOpen = true;//TODO
		}
	}
	public String DeleteScheduleRow(){
		Integer schId = null;
		try {
			Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String strId = params.get("schRemoveIdStr");
			schId = Integer.parseInt(strId);
		} catch (Exception e) {
			//System.out.print("Client not found by id "+id);
		}
		if(schId!=null){
			for(Schedule s : schedules){
				if(s.id.equals(schId)){
					//db
					DBConnector connector = new DBConnector();
					connector.RemoveSchedule(s);
					connector.Close();
					
					schedules.remove(s);					
				}
			}
		}
		
		return "";
		
	}
	
	public String getName() {
		LoadCity();
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		LoadCity();
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		LoadCity();
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getId() {
		LoadCity();
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Schedule> getSchedules() {
		LoadCity();
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
		System.out.print(schedules);
		//TODO
	}
	public Boolean getIsOpen() {
		return isOpen;
	}


}
