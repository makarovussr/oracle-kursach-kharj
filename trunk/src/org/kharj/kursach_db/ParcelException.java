package org.kharj.kursach_db;

public class ParcelException extends Exception {

	public Boolean rateNotFound = false;
	public Boolean minWeight = false;
	public Boolean maxWeight = false;
	
	public ParcelException() {
		// TODO Auto-generated constructor stub
	}

	public ParcelException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ParcelException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ParcelException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ParcelException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
