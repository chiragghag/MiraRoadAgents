/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange.Utils;

import java.util.Date;

public class LoadData {

	public static class CityTown {
		public static City[] cities;
	}

	public static class City {
		public String city;
		public Town[] towns;
	}

	public static class Town {
		public String town;
		public Locality[] localities;
	}

	public static class Locality {
		public String locality;
	}

	public static class Agents {
		public static Agent[] agents;
	}

	public static class Agent {
		public String aid;
		public String bname;
		public String aname;
		public String locality;
		public String phoneno;
		public String altno;
	}

	public static class Properties {
		public static Property[] properties;
	}

	public static class MyProperties {
		public static Property[] properties;
	}

	public static class AgentProperties {
		public static Property[] properties;
	}

	public static class Property {
		public String pid;
		public String sellrent;
		public String rescom;
		public String locality;
		public String area;
		public String type;
		public String floor;
		public String address;
		public int cost;
		public int rent;
		public String directside;
		public Date lastupdate;
	}

	public static class Notices {
		public static Notice[] notices;
	}

	public static class Notice {
		public String aid;
		public String bname;
		public String phoneno;
		public String altno;
		public String content;
		public Date lastupdate;
	}

}