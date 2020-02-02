/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange.Utils;

public class AppState {

	public static enum enumLayout {
		MyProfile, MyProperties, AgentProperties, MyNoticeboard, MyAccount, Properties, Agents, Noticeboard
	};

	public static enum enumLogin {
		LoggedIn, LoggedOut
	};

	public static int AppVersion = 9;
	public static int ServerAppVersion;

	public static String API_KEY = "6a6a0338dd872ea12a32d3c0dcd9829633566e70b7495d1a7842aa8bc43640f8";
	public static String SECRET_KEY = "7b28bc7555bd0b9baf4dfef6e53155f64a323b76ecddca80edfed1c3252536a7";

	public static enumLayout currentLayout = null;
	public static enumLogin loginState = enumLogin.LoggedOut;
	public static int OldPropertyDays = 15;
	public static int MaxProperties = 10;
	public static int PropertiesCount = 0;
	public static int RateThreshold = 200000;
	public static String absoluteUri = "http://www.miraroadagents.com/app/android/";

	public static String filterResCom = "";
	public static String filterType = "";
	public static int filterCostFrom = 0;
	public static int filterCostTo = 90000000;

	public static String jsonMyProfile = null;
	public static String jsonCityTown = null;
	public static String jsonAgents = null;
	public static String jsonProperties = null;
	public static String jsonAgentProperties = null;
	public static String Paid = "";
	public static String PaidStartDate = "";
	public static String PaidEndDate = "";
	public static String UserType = "";
	public static String BName = "";
	public static String AName = "";
	public static String City = "";
	public static String Town = "";
	public static String Locality = "";
	public static String Email = "";
	public static String Phoneno = "";
	public static String Address = "";
	public static String Altno = "";
	public static String Website = "";
	public static String Notice = "";
	public static String DeviceID = "";
	public static final int REQUEST_READ_PHONE_STATE = 0;
	public static String currentCity = "";
	public static int currentCityIndex = -1;
	public static String currentTown = "";
	public static int currentTownIndex = -1;
	public static String currentPID = "";
	public static int currentPIDIndex = -1;
	public static String currentAID = "";
	public static int currentAIDIndex = -1;
	public static String currentSellRent = "SELL";
	public static String UID = "";

	public static String temp = null;

}
