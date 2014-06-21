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
import org.hibernate.criterion.Disjunction;
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
			System.out.print("Error disconnect lol");
		}
	}
	
	public java.sql.Date CreateDate(int year, int month, int date, int hour, int minute, int second){
		return new java.sql.Date(new Timestamp(year - 1900, month - 1, date, hour, minute, second, 0).getTime());
	}
	
	public Boolean FillDB(){
		try{
			Transaction tr = session.beginTransaction();
			//City
			ArrayList<City> cities = new ArrayList<City>();
			cities.add(new City("Kyiv", null, "044 1234567"));
			cities.add(new City("Kharkiv", null, "057 1234567"));
			cities.add(new City("Odessa", null, "001 1234567"));
			cities.add(new City("Poltava", null, "002 1234567"));
			for(City c : cities) session.save(c);
			//Client
			ArrayList<Client> clients = new ArrayList<Client>();
			clients.add(new Client( "Evgen", "Kharchenko", null, cities.get(1), "Kharkiv addr", "+380939415776", "1995kenny@gmail.com"));
			clients.add(new Client( "Igor", "Zhylin", null, cities.get(1), "Kharkiv addr 2", "1234", "igormail"));
			clients.add(new Client( "Ivan", "Ivanov", null, cities.get(0), "Kyiv addr", "123456", "ivanmail"));
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
			routeMaps.add(new RouteMap(0, routes.get(0), cities.get(1), 2014, 5, 17, 10, 0, 0));
			routeMaps.add(new RouteMap(1, routes.get(0), cities.get(3), 2014, 5, 17, 14, 4, 50));
			routeMaps.add(new RouteMap(2, routes.get(0), cities.get(0), 2014, 5, 17, 16, 30, 4));
			routeMaps.add(new RouteMap(0, routes.get(1), cities.get(0), 2014, 5, 16, 23, 50, 0));
			routeMaps.add(new RouteMap(1, routes.get(1), cities.get(2), 2014, 5, 17, 15, 6, 39));
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
			//User
			session.save(new User("admin", "admin", true));
			session.save(new User("user", "user", false));
			tr.commit();
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
			return true;
	}
	Boolean AddObject(Object c){
		try{
		Transaction tr = session.beginTransaction();
		session.save(c);
		tr.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	//Client
	
	public Client GetClientById(int id){
		return (Client)session.get(Client.class, id);
	}
	public List<Client> GetClientsByPhone(String phone){
		return session.createCriteria(Client.class).add(Restrictions.ilike("phone", phone, MatchMode.ANYWHERE)).list();
	}
	public List<Client> GetClientsByEmail(String email){
		return session.createCriteria(Client.class).add(Restrictions.ilike("email", email, MatchMode.ANYWHERE)).list();
	}
	public List<Client> GetClientsByAddress(String address){
		return session.createCriteria(Client.class).add(Restrictions.ilike("address", address, MatchMode.ANYWHERE)).list();
	}
	public List<Client> GetClientsByName(String name){
		String []parts = name.split(" .");
		if(parts.length == 0) return null;
		Criteria crit = session.createCriteria(Client.class);
		Disjunction or = Restrictions.disjunction();
		for(String p : parts){
			if(p.length()>0){
				or.add(Restrictions.ilike("firstName", p, MatchMode.ANYWHERE));
				or.add(Restrictions.ilike("middleName", p, MatchMode.ANYWHERE));
				or.add(Restrictions.ilike("lastName", p, MatchMode.ANYWHERE));
			}
		}
		return crit.add(or).list();
		
	}
	public void UpdateClient(Client c){
		Transaction tr = session.beginTransaction();
		Client cl = (Client)session.load(Client.class, c.id);
		cl.firstName = c.firstName;
		cl.middleName = c.middleName;
		cl.lastName = c.lastName;
		cl.address = c.address;
		cl.phone = c.phone;
		cl.email = c.email;
		cl.city = c.city;
		tr.commit();
		
	}
	//City
	public City GetCityById(int id) {
		City city = (City) session.get(City.class, id);
		return city;			
	}
	
	public List<City> GetCitiesByName(String name) {
		return session.createCriteria(City.class).add(Restrictions.ilike("name", name, MatchMode.ANYWHERE)).list(); 
	}
	
	public List<City> GetCities() {
		List l =  session.createCriteria(City.class).list();
		return l;			
	}
	public void UpdateCity(City c){
		Transaction tr = session.beginTransaction();
		City cl = (City)session.load(City.class, c.id);
		cl.name = c.name;
		cl.address = c.address;
		cl.phone = c.phone;
		tr.commit();
		
	}
	
	
	//Schedule
	public List<Schedule> GetSchedulesByCity(City city){
		return session.createCriteria(Schedule.class).add(Restrictions.eq("city", city)).list();
	}
	public List<City> GetCitiesOpenedNow(){
		//TODO: 
		return null;
	}
	public void RemoveSchedule(Schedule s){
		Transaction tr = session.beginTransaction();
		Schedule dbS = (Schedule)session.load(Schedule.class, s.id);
		if(dbS!=null){
			session.delete(dbS);
		}
		tr.commit();
	}
	public void UpdateSchedulesForomList(List<Schedule> update){
		for(Schedule up : update){
			if(up.dayOfWeek>6) continue; //wrong
			List<Schedule> l = session.createCriteria(Schedule.class).add(Restrictions.eq("city", up.city)).add(Restrictions.eq("dayOfWeek", up.dayOfWeek)).setMaxResults(1).list();
			Transaction tr = session.beginTransaction();
			if(l!=null && l.size()>0){
				up.id = l.get(0).id;
				session.update(up);
			}else{
				up.id = null;
				session.save(up);
			}
			tr.commit();
			
		}
	}
	//Parcel type
	public ParcelType GetParcelTypeById(int id){
		return (ParcelType)session.get(ParcelType.class, id);
	}
	
	public List<ParcelType> GetParcelTypes(){
		return session.createCriteria(ParcelType.class).list();
	}
	
	//Parcel
	public Parcel GetParcelById(int id) {
		Parcel parcel = (Parcel) session.get(Parcel.class, id);
		return parcel;			
	}
	
	public List<Parcel> GetParcelsById(int id) {
		List<Parcel> l = session.createCriteria(Parcel.class).add(Restrictions.eq("id", id)).list();
		return l;
		
	}
	
	public List<Parcel> GetParcelsByClient(Client c){
	
		List<Parcel> l = session.createCriteria(Parcel.class).add( Restrictions.or(Restrictions.eq("clientFrom", c), Restrictions.eq("clientTo", c))).list();
		return l;
	}
	public List<Parcel> GetParcelsFromCityToCity(City fr, City t){
		
		List<Parcel> l = session.createCriteria(Parcel.class).add(Restrictions.eq("cityFrom", fr)).add(Restrictions.eq("cityTo", t)).list();
		return l;
	}
	
	public List<Parcel> GetParcelsByRoute(Route r){
		List<Parcel> l = session.createCriteria(Parcel.class).add( Restrictions.eq("route", r)).list();
		return l;
	}
	
	public RouteMap GetParcelLastLocation(Parcel p){
		if(p.route != null){
			return GetRouteLastLocation(p.route);
		}
		return null;
	}
	
	public void UpdateParcel(Parcel p){
		Transaction tr = session.beginTransaction();
		Parcel cl = (Parcel)session.load(Parcel.class, p.id);
		cl.acceptDate = p.acceptDate;
		cl.cityFrom =p.cityFrom;
		cl.cityTo = p.cityTo;
		cl.clientFrom = p.clientFrom;
		cl.clientTo = p.clientTo;
		cl.description = p.description;
		cl.parcelType = p.parcelType;
		cl.price = p.price;
		cl.route = p.route;
		cl.weight = p.weight;
		tr.commit();
		
	}
	
	public Parcel CreateEmptyParcel(){
		Parcel p = new Parcel(null, null, null, null, null, null, 0.0f, "");
		Transaction tr = session.beginTransaction();
		session.save(p);	
		tr.commit();
		List <Parcel> l = session.createCriteria(Parcel.class).addOrder(Order.desc("id")).setMaxResults(1).list();
		if(l != null && l.size() >0){
			return l.get(0);
		}
		return null;
		
	}
	//Rate
	public Rate GetRateById(int id){
		return (Rate)session.get(Rate.class, id);
	}
	public Rate GetRateForParcel(Parcel p){
		List<Rate> l = session.createCriteria(Rate.class).add(Restrictions.eq("cityFrom", p.cityFrom)).add(Restrictions.eq("cityTo", p.cityTo)).add(Restrictions.eq("parcelType", p.parcelType)).list();
		if(l!=null && l.size()>0){
			Rate r = l.get(0);
			return r;
		}
		return null;
	}
	
	public Float GetPriceForParcel(Parcel p) throws ParcelException{
		Float res = 0.0f;
		Float priceMin = 0.0f;
		Float pricePerKg = 0.0f;
		Rate r = GetRateForParcel(p);
		if(r==null){
			ParcelException ex = new ParcelException("Rate not found");
			System.out.print("Rate not found.");
			ex.rateNotFound = true;
			throw ex;
		}
		priceMin = r.priceMin;
		pricePerKg = r.pricePerKg;
		res = priceMin + pricePerKg*p.weight;
		return res;
	}
	//Route
	public Route GetRouteById(int id){
		return (Route)session.get(Route.class, id);
	}
	public List<Route> GetRoutesForParcel(Parcel p){
		List<Route> routes = GetRoutesFromCityToCity(p.cityFrom, p.cityTo);
		System.out.print(p);
		return routes;/*
		List<Route> okRoutes = new ArrayList<Route>();
		for(Route route : routes){
			Boolean fuckThisRoute = false;
			VehicleMaxload vMaxLoad = GetMaxLoadByVehicleAndParcelType(route.vehicle, p.parcelType);
			Float freeWeightVehicle = route.vehicle.maxLoad;
			Float freeWeightParcelType = vMaxLoad.maxWeight;
			Integer freeCountParcelType = vMaxLoad.maxCount;
			List<RouteMap> maps = GetRoutemapByRoute(route);
			List<Parcel> parcels = GetParcelsByRoute(route);
			parcels.add(p);
			List<Parcel> startedParcels = new ArrayList<Parcel>();
			List<Parcel> finishedParcels = new ArrayList<Parcel>();
			for(RouteMap m : maps){
				for(Parcel curP : parcels){
					if(curP.cityFrom.equals(m.city) && !(startedParcels.contains(curP)) && !(finishedParcels.contains(curP))){//this is start city, but parcel didnt startted yet
						//start
						startedParcels.add(p);
						freeWeightVehicle -= curP.weight;
						if(p.parcelType.equals(curP.parcelType)){
							freeWeightParcelType -= curP.weight;
							freeCountParcelType -= 1;
						}
					}
					if(curP.cityTo.equals(m.city) && (startedParcels.contains(curP)) && !(finishedParcels.contains(curP))){//this is end city, but parcel didnt startted yet
						//finish
						finishedParcels.add(p);
						freeWeightVehicle += curP.weight;
						if(p.parcelType.equals(curP.parcelType)){
							freeWeightParcelType += curP.weight;
							freeCountParcelType += 1;
						}
					}
					if(freeCountParcelType<0 || freeWeightParcelType < 0.0f || freeWeightVehicle<0.0f){
						//NO
						//fuckThisRoute = true;
						break;
					}//if					
				}//for p

				if(fuckThisRoute) break;//next route
			}//for m
			if(!fuckThisRoute){
				okRoutes.add(route);
			}
			fuckThisRoute = false;
		}
		
		return okRoutes;
		//System.out.print(routes);
		//return routes;*/
	}
	
	public List<RouteMap> GetRoutemapByRoute(Route r){
		List<RouteMap> l = session.createCriteria(RouteMap.class).add(Restrictions.eq("route", r)).addOrder(Order.asc("stopDate")).list();
		return l;
	}
	public RouteMap GetRouteLastLocation(Route r){
		java.util.Date now = new java.util.Date();
		List<RouteMap> l = session.createCriteria(RouteMap.class).add(Restrictions.eq("route", r)).add(Restrictions.le("stopDate", now)).addOrder(Order.desc("stopDate")).setMaxResults(1).list();
		if(l != null && l.size()>0){
			return l.get(0);
		}
		return null;
	}
	public List<Route> GetRoutesByVehicle(Vehicle v){
		return session.createCriteria(Route.class).add(Restrictions.eq("vehicle", v)).list();
	}
	public List<Route> RoutesInCityBetweenDate(City c, Date from, Date to){
		List <RouteMap> rm = session.createCriteria(RouteMap.class).add(Restrictions.eq("city", c)).add(Restrictions.between("stopDate", from, to)).list();
		List<Route> res = new ArrayList<Route>();
		for(RouteMap m : rm){
			if(!res.contains(m.route)){
				res.add(m.route);
			}
		}
		return res;
	}
	public List<Route> GetRoutesFromCityToCityBetweenDate(City fromC, City toC,  Date fromD, Date toD){
		List <RouteMap> rmFrom = session.createCriteria(RouteMap.class).add(Restrictions.eq("city", fromC)).add(Restrictions.between("stopDate", fromD, toD)).addOrder(Order.asc("stopDate")).list();
		List<Route> res = new ArrayList<Route>();
		for(RouteMap m : rmFrom){
			if(!res.contains(m.route)){
				List <RouteMap> rmTo = session.createCriteria(RouteMap.class).add(Restrictions.eq("route", m.route)).add(Restrictions.eq("city", toC)).add(Restrictions.gt("stopDate", m.stopDate)).list();
				if(rmTo != null && !rmTo.isEmpty()){
					//found
					res.add(m.route);
				}
			}
		}
		return res;
		
	}
	public List<Route> GetRoutesFromCityToCity(City fromC, City toC){
		List <RouteMap> rmFrom = session.createCriteria(RouteMap.class).add(Restrictions.eq("city", fromC)).addOrder(Order.asc("stopDate")).list();
		List<Route> res = new ArrayList<Route>();
		for(RouteMap m : rmFrom){
			if(!res.contains(m.route)){
				List <RouteMap> rmTo = session.createCriteria(RouteMap.class).add(Restrictions.eq("route", m.route)).add(Restrictions.eq("city", toC)).add(Restrictions.gt("stopDate", m.stopDate)).list();
				if(rmTo != null && !rmTo.isEmpty()){
					//found
					res.add(m.route);
				}
			}
		}
		return res;
		
	}
	public Boolean PlaceForParcelInRoute(Parcel p){
		//TODO:
				return null;
		
		
	}
	public void UpdateRoute(Route r){
		Transaction tr = session.beginTransaction();
		Route cl = (Route)session.load(Route.class, r.id);
		cl.vehicle = r.vehicle;
		tr.commit();
	}
		
	public void RemoveRouteMap(RouteMap s){
		Transaction tr = session.beginTransaction();
		RouteMap dbS = (RouteMap)session.load(RouteMap.class, s.id);
		if(dbS!=null){
			session.delete(dbS);
		}
		tr.commit();
	}
	
	
	
	//Vehicle
	public Vehicle GetVehicleById(int id){
		return (Vehicle)session.get(Vehicle.class, id);
	}
	public RouteMap GetVehicleCurrentLocation(Vehicle v){
		java.util.Date now = new java.util.Date();
		List<RouteMap> l = session.createCriteria(RouteMap.class).createAlias("route", "route").add(Restrictions.eq("route.vehicle", v)).add(Restrictions.le("stopDate", now)).addOrder(Order.desc("stopDate")).setMaxResults(1).list();
		if(l != null && l.size()>0){
			return l.get(0);
		}
		return null;
	}
	public RouteMap GetVehicleLastLocation(Vehicle v){
		List<RouteMap> l = session.createCriteria(RouteMap.class).createAlias("route", "route").add(Restrictions.eq("route.vehicle", v)).addOrder(Order.desc("stopDate")).setMaxResults(1).list();
		if(l != null && l.size()>0){
			return l.get(0);
		}
		return null;
	}
	public List<Vehicle> GetVehiclesByRegNumber(String regNum){
		List<Vehicle> l = session.createCriteria(Vehicle.class).add(Restrictions.ilike("registrationNumber", regNum, MatchMode.ANYWHERE)).list();
		return l;
	}
	public List<Vehicle> GetVehiclesByModel(String model){
		List<Vehicle> l = session.createCriteria(Vehicle.class).add(Restrictions.ilike("model", model, MatchMode.ANYWHERE)).list();
		return l;
	}
	public void UpdateVehicle(Vehicle v){
		Transaction tr = session.beginTransaction();
		Vehicle cl = (Vehicle)session.load(Vehicle.class, v.id);
		cl.registrationNumber = v.registrationNumber;
		cl.model = v.model;
		cl.maxLoad = v.maxLoad;
		cl.homeCity = v.homeCity;
		tr.commit();
		
	}
	//Max Load
	public VehicleMaxload GetMaxLoadByVehicleAndParcelType(Vehicle v, ParcelType pt){
		List<VehicleMaxload> l = session.createCriteria(VehicleMaxload.class).add(Restrictions.eq("vehicle", v)).add(Restrictions.eq("parcelType", pt)).setMaxResults(1).list();
		if(l != null && l.size()>0){
			return l.get(0);
		}
		return null;
	}
	public List<VehicleMaxload> GetMaxLoadsByVehicle(Vehicle v){
		List<VehicleMaxload> l = session.createCriteria(VehicleMaxload.class).add(Restrictions.eq("vehicle", v)).list();
		return l;
	}
	
	//User
	public User GetUserById(int id){
		return (User)session.get(User.class, id);
	}
	public User GetUserByLoginPassword(String login, String password){
		List<User> l = session.createCriteria(User.class).add(Restrictions.eq("login", login)).add(Restrictions.eq("password", password)).list();
		if(l != null && l.size()>0){
			return l.get(0);
		}
		return null;
	}
	public Boolean UserIsAdmin(String login, String password){
		User u = GetUserByLoginPassword(login, password);
		if(u != null && u.isAdmin){
			return true;
		}
		return false;
	}
	
	public Boolean CreateUser(String login, String password, Boolean isAdmin){
		List <User>list = session.createCriteria(User.class).add(Restrictions.eq("login", login)).list();
		if(list != null && list.size()>0){
			return false;//found
		}
		Transaction tr = session.beginTransaction();
		User u = new User(login,password, isAdmin);
		session.save(u);
		tr.commit();
		return true;
	}
	
	
	
}