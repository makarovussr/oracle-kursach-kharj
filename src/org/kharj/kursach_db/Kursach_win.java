package org.kharj.kursach_db;

import java.util.Locale;

import org.hibernate.Session;

public class Kursach_win {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			DBConnector connector = new DBConnector();
			connector.FillDB();
			System.out.print(connector.GetPriceByParcel(connector.GetParcelById(35)));
			connector.Close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
