package org.kharj.kursach_db;
import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name="rates")
public class Rate {	
	public Rate(City cityFrom, City cityTo, ParcelType parcelType,
			Float priceMin, Float pricePerKg) {
		this.cityFrom = cityFrom;
		this.cityTo = cityTo;
		this.parcelType = parcelType;
		this.priceMin = priceMin;
		this.pricePerKg = pricePerKg;
	}

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@ManyToOne
    @JoinColumn(name = "city_from", nullable = false)
	public City cityFrom;
	
	@ManyToOne
    @JoinColumn(name = "city_to", nullable = false)
	public City cityTo;
	
	@ManyToOne
    @JoinColumn(name = "parcel_type", nullable = false)
	public ParcelType parcelType;
	
	@Column(name="price_min", nullable = false)
   	public Float priceMin;
    
    @Column(name="price_per_kg", nullable = false)
   	public Float pricePerKg;
    
    public Rate(){
    	
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public ParcelType getParcelType() {
		return parcelType;
	}

	public void setParcelType(ParcelType parcelType) {
		this.parcelType = parcelType;
	}

	public Float getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(Float priceMin) {
		this.priceMin = priceMin;
	}

	public Float getPricePerKg() {
		return pricePerKg;
	}

	public void setPricePerKg(Float pricePerKg) {
		this.pricePerKg = pricePerKg;
	}

	@Override
	public String toString() {
		return "Rate [id=" + id + ", cityFrom=" + cityFrom + ", cityTo="
				+ cityTo + ", parcelType=" + parcelType + ", priceMin="
				+ priceMin + ", pricePerKg=" + pricePerKg + "]";
	}
    
}