package org.kharj.kursach_db.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.kharj.kursach_db.*;
@ManagedBean(name = "selectParcelTypeBean", eager = true)
@SessionScoped
public class SelectParcelTypeBean {

	private String navigateTo = "City.xhtml";
	private String act = "";

	private Integer selectedId = null;
	private List<ParcelType> types = new ArrayList<ParcelType>();
	
	
	public String Select(){
		types = new ArrayList<ParcelType>();
		selectedId = null;
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String strId = params.get("selectedIdStr");

		Integer id = null;
		try {
			id = Integer.parseInt(strId);
		} catch (Exception e) {}
		selectedId = id;
		navigateTo = "City.xhtml";
		//targets
		String act = params.get("act");
		if(act != null && act.equals("parcelChangeType")){
			navigateTo = "Parcel.xhtml";
		}
		
		String req = "";
		if(navigateTo.contains("?")){
			req = "&selectedParcelTypeId="+selectedId;
		}else{
			req = "?selectedParcelTypeId="+selectedId;
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


	public String getAct() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("act");
	}


	public Integer getSelectedId() {
		return selectedId;
	}


	public void setSelectedId(Integer selectedId) {
		this.selectedId = selectedId;
	}


	public List<ParcelType> getTypes() {

			DBConnector connector = new DBConnector();
			types = connector.GetParcelTypes();
			connector.Close();

		return types;
	}

}
