package org.kharj.kursach_db.beans;

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
		}
		connector.Close();
		return "";
	}
	private String Logout(){
		id = null;
		user = null;
		isAdmin = false;
		login_input = "";
		password_input = "";
		return "ok";
	}
	public String Register(String login, String password, Boolean isAdmin){
		if(login.length() < 1 || password.length() < 1){
			return "empty fields";
		}
		DBConnector connector = new DBConnector();
		Boolean res = connector.CreateUser(login, password, isAdmin);
		connector.Close();
		if(res){
			//login
			Login(login, password);
			return "ok";
		}else{
			return "error";
		}
		
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
	public void setLogin_input(String login_input) {
		this.login_input = login_input;
	}
	public void setPassword_input(String password_input) {
		this.password_input = password_input;
	}
}
	