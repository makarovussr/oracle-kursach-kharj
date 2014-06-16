package org.kharj.kursach_db;
import javax.persistence.*;

import java.util.Date;//<<<< here changed
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="schedules")
public class Schedule {	
	public Schedule(City city, Integer dayOfWeek, Date openTime, Date closeTime) {
		this.city = city;
		this.dayOfWeek = dayOfWeek;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	@SuppressWarnings("deprecation")
	public Schedule(City city, Integer dayOfWeek, int openHour, int openMin, int closeHour, int closeMin) {
		this.city = city;
		this.dayOfWeek = dayOfWeek;
		this.openTime = new Date(new Time(openHour, openMin, 0).getTime());
		this.closeTime = new Date(new Time(closeHour, closeMin, 0).getTime());
	}

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@ManyToOne
    @JoinColumn(name = "city", nullable = false)
	public City city;
	
	@Column(name="day_of_week")
	public Integer dayOfWeek;
	
	@Column(name="open_time")
	public Date openTime;
	
	@Column(name="close_time")
	public Date closeTime;

	public Schedule(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", city=" + city + ", dayOfWeek="
				+ dayOfWeek + ", openTime=" + openTime + ", closeTime="
				+ closeTime + "]";
	}
		
}