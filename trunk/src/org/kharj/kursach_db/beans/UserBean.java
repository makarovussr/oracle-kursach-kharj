package org.kharj.kursach_db.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.kharj.kursach_db.*;
@ManagedBean(name = "userBean", eager = true)
@SessionScoped
public class UserBean {
	public UserBean() {
	}
	private User user = null;
	private Integer id = null;
	private Boolean isAdmin = false;
	private String login_input = "";
	private String password_input = "";
	public String getLogin_input() {
		return login_input;
	}
	public void setLogin_input(String login_input) {
		this.login_input = login_input;
	}
	public String getPassword_input() {
		return password_input;
	}
	public void setPassword_input(String password_input) {
		this.password_input = password_input;
	}
	private String status = "";
	
	public String LoginB(){
		System.out.print("in");
		return Login(login_input, password_input);
	}
	public String RegB(){
		System.out.print("reg");
		return Register(login_input, password_input, false);
	}
	public String Login(String login, String password){
		Logout();
		if(login.length() < 1 || password.length() < 1){
			return "empty fields";
		}
		DBConnector connector = new DBConnector();
		user = connector.GetUserByLoginPassword(login, password);
		if(user != null){
			id = user.id;
			isAdmin = user.isAdmin;
			status = "Signed in";
			if(isAdmin)
				status = "Signed in as admin";
		}else{
			Logout();
			status = "Error login";
		}
		connector.Close();
		return status;
	}
	public String Logout(){
		System.out.print("out");
		id = null;
		user = null;
		isAdmin = false;
		login_input = "";
		password_input = "";
		status="";
		return "";
	}
	public String Register(String login, String password, Boolean isAdmin){
		if(login.length() < 1 || password.length() < 1){
			status = "empty fields";
			return status;
		}
		DBConnector connector = new DBConnector();
		Boolean res = connector.CreateUser(login, password, isAdmin);
		connector.Close();
		if(res){
			//login
			Login(login, password);
			status = "Registered";
		}else{
			status = "Register error";
		}
		return status;
	}
	public User getUser() {
		return user;
	}
	public Integer getId() {
		return id;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public String getStatus() {
		String s = status;
		return s;
	}
	public String Redirect(String url){
		
		if(user == null || !isAdmin){
			if(url.equals("SelectParcel.xhtml")){
				url="SimpleSelectParcel.xhtml";
			}else{
			url = "Login.xhtml";
			}
			
		}
		try{

			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			System.out.print("Cant redirect to ");
			e.printStackTrace();
		} 
		return "";
	}
	public String goSelectClient(){
		Redirect("SelectClient.xhtml?act=");
		return "";
	}
	public String goSelectParcel(){
			Redirect("SelectParcel.xhtml");
		return "";
	}
	public String goSelectCity(){
		Redirect("SelectCity.xhtml?act=");
		return "";
	}
	public String goSelectRoute(){
		Redirect("SelectRoute.xhtml");
		return "";
	}
	public String goSelectVehicle(){
		Redirect("SelectVehicle.xhtml");
		return "";
	}
}
	