package com.gk.dmMethod.KNN;

public class NameValueEntry implements Comparable<NameValueEntry>{
	
	double value;
	String name;
	
	
	
	public NameValueEntry(double value, String name) {
		super();
		this.value = value;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public int compareTo(NameValueEntry o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof NameValueEntry){
			NameValueEntry entry = (NameValueEntry)obj;
			return value == entry.value;
		}
		return false;
	}
	
}
