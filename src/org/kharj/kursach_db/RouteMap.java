package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="route_maps")
public class RouteMap {	
	public RouteMap(Integer num, Route route, City city, Date stopDate) {
		this.num = num;
		this.route = route;
		this.city = city;
		this.stopDate = stopDate;
	}
	
	@SuppressWarnings("deprecation")
	public RouteMap(Integer num, Route route, City city, int year, int month, int date, int hour, int minute, int second) {
		this.num = num;
		this.route = route;
		this.city = city;
		this.stopDate =  new Date(new Timestamp(year - 1900, month - 1, date, hour, minute, second, 0).getTime());
		//1900 костыль
	}


	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
    @Column(name="num", nullable = false)
	public Integer num;
	
    @ManyToOne
    @JoinColumn(name = "route", nullable = false)
	public Route route;
    
	@ManyToOne
    @JoinColumn(name = "city", nullable = false)
	public City city;
	
	@Column(name="stop_date")
	public Date stopDate;
	
	
	public RouteMap(){
		
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public Route getRoute() {
		return route;
	}


	public void setRoute(Route route) {
		this.route = route;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public Date getStopDate() {
		return stopDate;
	}


	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	@Override
	public String toString() {
		return "RouteMap [id=" + id + ", num=" + num + ", route=" + route
				+ ", city=" + city + ", stopDate=" + stopDate + "]";
	}
}