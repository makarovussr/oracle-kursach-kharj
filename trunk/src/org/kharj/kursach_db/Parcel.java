package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name="parcels")
public class Parcel {	
	public Parcel(Client clientFrom, Client clientTo, ParcelType parcelType,
			City cityFrom, City cityTo, Route route, Float weight,
			String description) {
		this.clientFrom = clientFrom;
		this.clientTo = clientTo;
		this.parcelType = parcelType;
		this.cityFrom = cityFrom;
		this.cityTo = cityTo;
		this.route = route;
		this.weight = weight;
		this.description = description;
		java.util.Date d = new java.util.Date();//today
		this.acceptDate = new Date(d.getTime());
		
	}

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;

    @ManyToOne
    @JoinColumn(name = "client_from", nullable = true)
	public Client clientFrom;
    
    @ManyToOne
    @JoinColumn(name = "client_to", nullable = true)
	public Client clientTo;
    
    @ManyToOne
    @JoinColumn(name = "parcel_type", nullable = true)
	public ParcelType parcelType;
    
    @ManyToOne
    @JoinColumn(name = "city_from", nullable = true)
	public City cityFrom;
    
    @ManyToOne
    @JoinColumn(name = "city_to", nullable = true)
	public City cityTo;
	
    @ManyToOne
    @JoinColumn(name = "route", nullable = true)
	public Route route;

	@Column(name="weight", nullable = true)
	public Float weight;
	
	@Column(name="description", nullable = true)
	public String description;
	
	@Column(name="price", nullable = true)
	public Float price;
	
	@Column(name="accept_date")
	public Date acceptDate;
	
	public Parcel(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClientFrom() {
		return clientFrom;
	}

	public void setClientFrom(Client clientFrom) {
		this.clientFrom = clientFrom;
	}

	public Client getClientTo() {
		return clientTo;
	}

	public void setClientTo(Client clientTo) {
		this.clientTo = clientTo;
	}

	public ParcelType getParcelType() {
		return parcelType;
	}

	public void setParcel_type(ParcelType parcelType) {
		this.parcelType = parcelType;
	}

	public City getCityFrom() {
		return cityFrom;
	}

	public void setCityFrom(City cityFrom) {
		this.cityFrom = cityFrom;
	}

	public City getCityTo() {
		return cityTo;
	}

	public void setCityTo(City cityTo) {
		this.cityTo = cityTo;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	
	@Override
	public String toString() {
		return "Parcel [id=" + id + ", clientFrom=" + clientFrom
				+ ", clientTo=" + clientTo + ", parcelType=" + parcelType
				+ ", cityFrom=" + cityFrom + ", cityTo=" + cityTo + ", route="
				+ route + ", weight=" + weight + ", description=" + description
				+ ", price=" + price + ", acceptDate=" + acceptDate + "]";
	}
	
}
	
	