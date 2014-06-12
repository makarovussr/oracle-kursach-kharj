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
			//connector.FillDB()
			java.sql.Date d1 =  new Date(new Timestamp(2014-1900, 5 - 1, 17, 0, 0, 0, 0).getTime());
			java.sql.Date d2 =  new Date(new Timestamp(2014-1900, 5 - 1, 17, 23, 59, 59, 0).getTime());
			System.out.print(connector.GetRoutesFromCityToCityBetweenDate(connector.GetCityById(4), connector.GetCityById(1), d1, d2));
			connector.Close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
