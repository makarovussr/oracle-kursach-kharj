package org.kharj.kursach_db;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Locale;

import oracle.jdbc.proxy.annotation.GetCreator;

import org.hibernate.Session;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class Kursach_win {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			DBConnector connector = new DBConnector();
			//connector.FillDB();
			//Parcel testParcel = new Parcel(connector.GetClientById(1), connector.GetClientById(2), connector.GetParcelTypeById(19), connector.GetCityById(4), connector.GetCityById(1), null, 60.0f, "lalka");
			//System.out.print(connector.GetRoutesForParcel(testParcel));
			connector.Close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
