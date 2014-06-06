package org.kharj.kursach_db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;



@SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
public class DBConnector {

	private static SessionFactory sessionFactory = null;
	private Session session = null;

	public DBConnector(){
		Locale.setDefault(Locale.ENGLISH);//! ora-00604 ora-12705
		try{
			if(sessionFactory == null){
				sessionFactory = new Configuration().configure().buildSessionFactory();
				session = sessionFactory.openSession();
			}
		}catch(Exception ex){
			System.out.print("Error create session.");
			ex.printStackTrace();
		}
	}
	public void Close(){

		try{
			if(session != null){
				session.close();
				session = null;
			}
			if(sessionFactory != null){
				sessionFactory.close();
				sessionFactory = null;
			}
		}catch(Exception ex){
			System.out.print("Error disconnect lol 。＿。");
		}
	}
	
	public Boolean FillDB(){
		try{
			Transaction tr = session.beginTransaction();
			//City
			ArrayList<City> cities = new ArrayList<City>();
			cities.add(new City("Kyiv", null));
			cities.add(new City("Kharkiv", null));
			cities.add(new City("Odessa", null));
			cities.add(new City("Poltava", null));
			for(City c : cities) session.save(c);
			//Client
			ArrayList<Client> clients = new ArrayList<Client>();
			clients.add(new Client( "Evgen", "Kharchenko", null, cities.get(1), "Kharkiv addr", "+380939415776", "1995kenny@gmail.com", "pass"));
			clients.add(new Client( "Igor", "Zhylin", null, cities.get(1), "Kharkiv addr 2", "1234", "igormail", "pass"));
			clients.add(new Client( "Ivan", "Dulin", null, cities.get(0), "Kyiv addr", "123456", "ivanmail", "pass"));
			for(Client c : clients) session.save(c);
			//Vehicle
			ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
			vehicles.add(new Vehicle("AX1454AH", 4000.0f, cities.get(1), "KAMAZ"));
			vehicles.add(new Vehicle("II7777BP", 3000.0f, cities.get(0), "Mercedes"));
			vehicles.add(new Vehicle("AB1234AA", 6000.0f, cities.get(0), "VOLVO"));
			for(Vehicle v : vehicles) session.save(v);
			//Route
			ArrayList<Route> routes = new ArrayList<Route>();
			routes.add(new Route(vehicles.get(0)));
			routes.add(new Route(vehicles.get(2)));
			for(Route v : routes) session.save(v);
			//RouteMap
			ArrayList<RouteMap> routeMaps = new ArrayList<RouteMap>();
			routeMaps.add(new RouteMap(0, routes.get(0), cities.get(1), 2014, 4, 17, 10, 0, 0));
			routeMaps.add(new RouteMap(1, routes.get(0), cities.get(3), 2014, 4, 17, 14, 4, 50));
			routeMaps.add(new RouteMap(2, routes.get(0), cities.get(0), 2014, 4, 17, 16, 30, 4));
			routeMaps.add(new RouteMap(0, routes.get(1), cities.get(0), 2014, 4, 16, 23, 50, 0));
			routeMaps.add(new RouteMap(1, routes.get(1), cities.get(2), 2014, 4, 17, 15, 6, 39));
			for(RouteMap v : routeMaps) session.save(v);
			//ParcelType
			ArrayList<ParcelType> ptypes = new ArrayList<ParcelType>();
			ptypes.add(new ParcelType("Packet", 50.0f, 1.0f));
			ptypes.add(new ParcelType("Box", 500.0f, 5.0f));
			ptypes.add(new ParcelType("Container", 3000.0f, 500.0f));
			for(ParcelType v : ptypes) session.save(v);
			//MaxLoad
			ArrayList<VehicleMaxload> maxload = new ArrayList<VehicleMaxload>();
			maxload.add(new VehicleMaxload(vehicles.get(2), ptypes.get(2), 6000.0f, 2));
			maxload.add(new VehicleMaxload(vehicles.get(0), ptypes.get(0), 2000.0f, 200));
			maxload.add(new VehicleMaxload(vehicles.get(0), ptypes.get(1), 200.0f, 50));
			maxload.add(new VehicleMaxload(vehicles.get(1), ptypes.get(0), 2000.0f, 100));
			maxload.add(new VehicleMaxload(vehicles.get(1), ptypes.get(1), 1000.0f, 20));
			for(VehicleMaxload v : maxload) session.save(v);
			//Rates
			ArrayList<Rate> rates = new ArrayList<Rate>();
			rates.add(new Rate(cities.get(0), cities.get(1), ptypes.get(0), 5.0f, 3.0f ));
			rates.add(new Rate(cities.get(0), cities.get(1), ptypes.get(1), 6.0f, 2.5f ));
			rates.add(new Rate(cities.get(0), cities.get(1), ptypes.get(2), 3000.0f, 1.8f ));
			rates.add(new Rate(cities.get(0), cities.get(2), ptypes.get(0), 5.0f, 3.0f ));
			rates.add(new Rate(cities.get(0), cities.get(2), ptypes.get(1), 6.0f, 2.5f ));
			rates.add(new Rate(cities.get(0), cities.get(2), ptypes.get(2), 3000.0f, 1.8f ));
			rates.add(new Rate(cities.get(1), cities.get(2), ptypes.get(0), 7.0f, 3.0f ));
			rates.add(new Rate(cities.get(1), cities.get(2), ptypes.get(1), 9.0f, 2.5f ));
			rates.add(new Rate(cities.get(1), cities.get(2), ptypes.get(2), 4500.0f, 1.8f ));
			for(Rate v : rates) session.save(v);
			//Parcel
			ArrayList<Parcel> parcels = new ArrayList<Parcel>();
			parcels.add(new Parcel(clients.get(0), clients.get(2), ptypes.get(0), cities.get(0), cities.get(1), null, 2.5f, "happy packet"));
			parcels.add(new Parcel(clients.get(2), clients.get(1), ptypes.get(1), cities.get(0), cities.get(1), null, 8.0f, "mega box"));
			for(Parcel v : parcels) session.save(v);
			//Schedule			
			for(City c : cities){
				for(int j=0;j<6;j++){
					Schedule s = new Schedule(c, j, 9, 0, ((j<5)?20:18), 0);
					session.save(s);
				}
			}

			
			tr.commit();
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
			return true;
	}
	//City
	public City GetCityById(int id) {
		City city = (City) session.get(City.class, id);
		return city;			
	}
	
	public City GetCityByName(String name) {
		List <City> l = session.createCriteria(City.class).add(Restrictions.like("name", name, MatchMode.ANYWHERE)).list(); 
		if(l != null && l.size()>0){
			return l.get(0);
		}
		return null;
	}
	
	public List<City> GetCities() {
		List l =  session.createCriteria(City.class).list();
		return l;			
	}
	//Parcel
	public Parcel GetParcelById(int id) {
		Parcel parcel = (Parcel) session.get(Parcel.class, id);
		return parcel;			
	}
	
	public List<Parcel> GetParcelsByClient(Client c){
	
		List<Parcel> l = session.createCriteria(Parcel.class).add(Restrictions.eq("clientFrom", c)).list();
		return l;
	}
	
	public Rate GetRateByParcel(Parcel p){
		List<Rate> l = session.createCriteria(Rate.class).add(Restrictions.eq("cityFrom", p.cityFrom)).add(Restrictions.eq("cityTo", p.cityTo)).add(Restrictions.eq("parcelType", p.parcelType)).list();
		if(l==null || l.size()<1){
			Rate r = l.get(0);
			return r;
		}
		return null;
	}
	
	public Float GetPriceByParcel(Parcel p){
		Float res = 0.0f;
		Float priceMin = 50.0f;//default
		Float pricePerKg = 10.0f;//default
		Rate r = GetRateByParcel(p);
		if(r!=null){
			priceMin = r.priceMin;
			pricePerKg = r.pricePerKg;
		}
		res = priceMin + pricePerKg*p.weight;
		return res;
	}
	
}