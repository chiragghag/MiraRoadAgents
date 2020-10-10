/**
* Author: Krishna Modi
* Contact: krish512@hotmail.com
*/
package com.krish512.k_exchange.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

import com.krish512.k_exchange.Utils.LoadData.Agent;
import com.krish512.k_exchange.Utils.LoadData.City;
import com.krish512.k_exchange.Utils.LoadData.Locality;
import com.krish512.k_exchange.Utils.LoadData.Notice;
import com.krish512.k_exchange.Utils.LoadData.Property;
import com.krish512.k_exchange.Utils.LoadData.Town;

public class Operation {

	public static String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest
					.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static String postHttpResponse(URI uri,
			List<BasicNameValuePair> params) {
		String APP_TAG = null;
		HttpEntity messageEntity = null;
		Log.d(APP_TAG, "Going to make a post request");
		StringBuilder response = new StringBuilder();
		try {
			HttpPost post = new HttpPost();
			post.setURI(uri);
			post.setEntity(new UrlEncodedFormEntity(params));
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(post);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				Log.d(APP_TAG, "HTTP POST succeeded");
				messageEntity = httpResponse.getEntity();
				InputStream is = messageEntity.getContent();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String line = null;
				while ((line = br.readLine()) != null) {
					response.append(line);
					Log.d(APP_TAG, "Success:" + response);
				}
			} else {
				Log.d(APP_TAG, "HTTP POST error");
				messageEntity = httpResponse.getEntity();
				InputStream is = messageEntity.getContent();
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				String line = null;
				while ((line = br.readLine()) != null) {
					response.append(line);
				}
				Log.d(APP_TAG, "Error:" + response);
			}
		} catch (Exception e) {
			Log.e(APP_TAG, "Error: " + e.getMessage());
		} finally {
			if (messageEntity != null) {
				try {
					messageEntity.consumeContent();
				} catch (IOException e) {
					Log.e(APP_TAG, "", e);
				}
			}
		}

		Log.d(APP_TAG, "Done with HTTP posting");
		return response.toString();
	}

	public static JSONObject getJsonResponse(URI uri,
			List<BasicNameValuePair> params) {
		String response = postHttpResponse(uri, params);

		String word = response.substring(0, response.indexOf(' '));
		if (word == "Error") {
			return null;
		} else {
			return null;
		}

	}

	public static boolean getCityTown(String UID) {
		String APP_TAG = null;
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("uid", UID));
		String jsonCityTown = null;
		try {
			jsonCityTown = postHttpResponse(new URI(AppState.absoluteUri
					+ "getcitytown.php"), params);
		} catch (URISyntaxException e) {
			Log.d(APP_TAG, "Error:" + e.getMessage());
			e.printStackTrace();
		}

		if (jsonCityTown == null) {
			return false;
		} else {

			AppState.jsonCityTown = jsonCityTown;
			JSONObject json;
			JSONArray citiesArray;
			JSONArray townsArray;
			JSONArray localitiesArray;
			try {
				Log.i("jsonCityTown: ", jsonCityTown);
				json = new JSONObject(jsonCityTown);
				citiesArray = json.getJSONArray("city");
				LoadData.CityTown.cities = new City[citiesArray.length()];
				for (int i = 0; i < citiesArray.length(); i++) {
					JSONObject cities = citiesArray.getJSONObject(i);
					LoadData.CityTown.cities[i] = new City();
					String city = cities.getString("city");
					LoadData.CityTown.cities[i].city = city;
					Log.d(APP_TAG, city);
					townsArray = cities.getJSONArray("town");
					LoadData.CityTown.cities[i].towns = new Town[townsArray
							.length()];
					for (int j = 0; j < townsArray.length(); j++) {
						JSONObject towns = townsArray.getJSONObject(j);
						LoadData.CityTown.cities[i].towns[j] = new Town();
						String town = towns.getString("town");
						LoadData.CityTown.cities[i].towns[j].town = town;
						Log.d(APP_TAG, town);
						localitiesArray = towns.getJSONArray("locality");
						LoadData.CityTown.cities[i].towns[j].localities = new Locality[localitiesArray
								.length()];
						for (int k = 0; k < localitiesArray.length(); k++) {
							LoadData.CityTown.cities[i].towns[j].localities[k] = new Locality();
							String locality = localitiesArray.getString(k);
							LoadData.CityTown.cities[i].towns[j].localities[k].locality = locality;
							Log.d(APP_TAG, locality);
						}
					}
				}

				AppState.Paid = json.getString("type");
				Log.d(APP_TAG, AppState.Paid);
				AppState.PaidStartDate = json.getString("paidstartdate");
				Log.d(APP_TAG, AppState.PaidStartDate);
				AppState.PaidEndDate = json.getString("paidenddate");
				Log.d(APP_TAG, AppState.PaidEndDate);
				AppState.PropertiesCount = json.getInt("count");
				Log.d(APP_TAG, String.valueOf(AppState.PropertiesCount));
				AppState.Notice = json.getString("notice");
				Log.d(APP_TAG, String.valueOf(AppState.Notice));
				AppState.MaxProperties = json.getInt("maxproperties");
				Log.d(APP_TAG, String.valueOf(AppState.MaxProperties));
				AppState.ServerAppVersion = json.getInt("appversion");
				Log.d(APP_TAG, String.valueOf(AppState.ServerAppVersion));
			} catch (JSONException e1) {
				e1.printStackTrace();
				return false;
			}
			return true;
		}
	}

	public static boolean getMyProfile() {
		String APP_TAG = null;
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("uid", AppState.UID));
		String jsonProfile = null;
		try {
			jsonProfile = postHttpResponse(new URI(AppState.absoluteUri
					+ "getmyprofile.php"), params);
		} catch (URISyntaxException e) {
			Log.d(APP_TAG, "Error:" + e.getMessage());
			e.printStackTrace();
		}

		if (jsonProfile == null) {
			return false;
		} else {

			AppState.jsonMyProfile = jsonProfile;
			Log.d(APP_TAG, "JSON:" + jsonProfile);
			JSONObject json;
			try {
				json = new JSONObject(jsonProfile);
				JSONObject profile = json.getJSONObject("profile");
				AppState.Paid = profile.getString("paid");
				Log.d(APP_TAG, AppState.Paid);
				AppState.PaidStartDate = profile.getString("paidstartdate");
				Log.d(APP_TAG, AppState.PaidStartDate);
				AppState.PaidEndDate = profile.getString("paidenddate");
				Log.d(APP_TAG, AppState.PaidEndDate);
				AppState.UserType = profile.getString("usertype");
				Log.d(APP_TAG, AppState.UserType);
				AppState.BName = profile.getString("bname");
				Log.d(APP_TAG, AppState.BName);
				AppState.AName = profile.getString("aname");
				Log.d(APP_TAG, AppState.AName);
				AppState.City = profile.getString("city");
				Log.d(APP_TAG, AppState.City);
				AppState.Town = profile.getString("town");
				Log.d(APP_TAG, AppState.Town);
				AppState.Locality = profile.getString("locality");
				Log.d(APP_TAG, AppState.Locality);
				AppState.Address = profile.getString("address");
				Log.d(APP_TAG, AppState.Address);
				AppState.Email = profile.getString("email");
				Log.d(APP_TAG, AppState.Email);
				AppState.Phoneno = profile.getString("phoneno");
				Log.d(APP_TAG, AppState.Phoneno);
				AppState.Altno = profile.getString("altno");
				Log.d(APP_TAG, AppState.Altno);
				AppState.Website = profile.getString("website");
				Log.d(APP_TAG, AppState.Website);
				AppState.MaxProperties = profile.getInt("maxproperties");
				Log.d(APP_TAG, String.valueOf(AppState.MaxProperties));
				AppState.PropertiesCount = profile.getInt("count");
				Log.d(APP_TAG, String.valueOf(AppState.PropertiesCount));
			} catch (JSONException e1) {
				e1.printStackTrace();
				return false;
			}
			return true;
		}
	}

	public static boolean getAgents() {
		String APP_TAG = null;
		if (AppState.currentCity.contentEquals("")) {
			return false;
		}
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("city", AppState.currentCity));
		params.add(new BasicNameValuePair("town", AppState.currentTown));
		String jsonAgents = null;
		try {
			jsonAgents = postHttpResponse(new URI(AppState.absoluteUri
					+ "getagents.php"), params);
		} catch (URISyntaxException e) {
			Log.d(APP_TAG, "Error:" + e.getMessage());
			e.printStackTrace();
			return false;
		}

		if (jsonAgents == null) {
			return false;
		} else {
			AppState.jsonAgents = jsonAgents;
			JSONObject json;
			JSONArray agentsArray;
			try {
				json = new JSONObject(jsonAgents);
				agentsArray = json.getJSONArray("agent");
				LoadData.Agents.agents = new Agent[agentsArray.length()];
				for (int i = 0; i < agentsArray.length(); i++) {
					JSONObject agents = agentsArray.getJSONObject(i);
					String aid = agents.getString("aid");
					LoadData.Agents.agents[i] = new Agent();
					LoadData.Agents.agents[i].aid = aid;
					Log.d(APP_TAG, aid);
					String bname = agents.getString("bname");
					LoadData.Agents.agents[i].bname = bname;
					Log.d(APP_TAG, bname);
					String aname = agents.getString("aname");
					LoadData.Agents.agents[i].aname = aname;
					Log.d(APP_TAG, bname);
					String locality = agents.getString("locality");
					LoadData.Agents.agents[i].locality = locality;
					Log.d(APP_TAG, locality);
					String phoneno = agents.getString("phoneno");
					LoadData.Agents.agents[i].phoneno = phoneno;
					Log.d(APP_TAG, phoneno);
					String altno = agents.getString("altno");
					LoadData.Agents.agents[i].altno = altno;
					Log.d(APP_TAG, altno);
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
				return false;
			}
			return true;
		}
	}

	@SuppressLint("SimpleDateFormat")
	public static boolean getProperties() {
		String APP_TAG = null;
		if (AppState.currentCity.contentEquals("")) {
			return false;
		}
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("city", AppState.currentCity));
		params.add(new BasicNameValuePair("town", AppState.currentTown));
		params.add(new BasicNameValuePair("rescom", AppState.filterResCom));
		params.add(new BasicNameValuePair("type", AppState.filterType));
		params.add(new BasicNameValuePair("costfrom", String
				.valueOf(AppState.filterCostFrom)));
		Log.d(APP_TAG, String.valueOf(AppState.filterCostFrom));
		params.add(new BasicNameValuePair("costto", String
				.valueOf(AppState.filterCostTo)));
		Log.d(APP_TAG, String.valueOf(AppState.filterCostTo));
		String jsonProperties = null;
		try {
			jsonProperties = postHttpResponse(new URI(AppState.absoluteUri
					+ "getproperties.php"), params);
		} catch (URISyntaxException e) {
			Log.d(APP_TAG, "Error:" + e.getMessage());
			e.printStackTrace();
			return false;
		}

		if (jsonProperties == null) {
			return false;
		} else {

			AppState.jsonProperties = jsonProperties;
			JSONObject json;
			JSONArray propertiesArray;
			try {
				json = new JSONObject(jsonProperties);
				propertiesArray = json.getJSONArray("property");
				LoadData.Properties.properties = new Property[propertiesArray
						.length()];
				for (int i = 0; i < propertiesArray.length(); i++) {
					JSONObject properties = propertiesArray.getJSONObject(i);
					LoadData.Properties.properties[i] = new Property();
					String pid = properties.getString("pid");
					LoadData.Properties.properties[i].pid = pid;
					Log.d(APP_TAG, pid);
					String sellrent = properties.getString("sellrent");
					LoadData.Properties.properties[i].sellrent = sellrent;
					Log.d(APP_TAG, sellrent);
					String rescom = properties.getString("rescom");
					LoadData.Properties.properties[i].rescom = rescom;
					Log.d(APP_TAG, rescom);
					String locality = properties.getString("locality");
					LoadData.Properties.properties[i].locality = locality;
					Log.d(APP_TAG, locality);
					String area = properties.getString("area");
					LoadData.Properties.properties[i].area = area;
					Log.d(APP_TAG, area);
					String type = properties.getString("type");
					LoadData.Properties.properties[i].type = type;
					Log.d(APP_TAG, type);
					String floor = properties.getString("floor");
					LoadData.Properties.properties[i].floor = floor;
					Log.d(APP_TAG, floor);
					String address = properties.getString("address");
					LoadData.Properties.properties[i].address = address;
					Log.d(APP_TAG, address);
					int cost = properties.getInt("cost");
					LoadData.Properties.properties[i].cost = cost;
					Log.d(APP_TAG, String.valueOf(cost));
					int rent = properties.getInt("rent");
					LoadData.Properties.properties[i].rent = rent;
					Log.d(APP_TAG, String.valueOf(rent));
					String directside = properties.getString("directside");
					LoadData.Properties.properties[i].directside = directside;
					Log.d(APP_TAG, directside);
					String lastupdate = properties.getString("lastupdate");
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd");
					LoadData.Properties.properties[i].lastupdate = formatter
							.parse(lastupdate);
					Log.d(APP_TAG, lastupdate);
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
				return false;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}
			return true;
		}
	}

	@SuppressLint("SimpleDateFormat")
	public static boolean getMyProperties() {
		String APP_TAG = null;
		if (AppState.UID.contentEquals("")) {
			return false;
		}
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("uid", AppState.UID));
		String jsonProperties = null;
		try {
			jsonProperties = postHttpResponse(new URI(AppState.absoluteUri
					+ "getmyproperties.php"), params);
		} catch (URISyntaxException e) {
			Log.d(APP_TAG, "Error:" + e.getMessage());
			e.printStackTrace();
			return false;
		}

		if (jsonProperties == null) {
			return false;
		} else {

			AppState.jsonProperties = jsonProperties;
			JSONObject json;
			JSONArray propertiesArray;
			try {
				json = new JSONObject(jsonProperties);
				propertiesArray = json.getJSONArray("property");
				LoadData.MyProperties.properties = new Property[propertiesArray
						.length()];
				for (int i = 0; i < propertiesArray.length(); i++) {
					JSONObject properties = propertiesArray.getJSONObject(i);
					LoadData.MyProperties.properties[i] = new Property();
					String pid = properties.getString("pid");
					LoadData.MyProperties.properties[i].pid = pid;
					Log.d(APP_TAG, pid);
					String sellrent = properties.getString("sellrent");
					LoadData.MyProperties.properties[i].sellrent = sellrent;
					Log.d(APP_TAG, sellrent);
					String rescom = properties.getString("rescom");
					LoadData.MyProperties.properties[i].rescom = rescom;
					Log.d(APP_TAG, rescom);
					String locality = properties.getString("locality");
					LoadData.MyProperties.properties[i].locality = locality;
					Log.d(APP_TAG, locality);
					String area = properties.getString("area");
					LoadData.MyProperties.properties[i].area = area;
					Log.d(APP_TAG, area);
					String type = properties.getString("type");
					LoadData.MyProperties.properties[i].type = type;
					Log.d(APP_TAG, type);
					String floor = properties.getString("floor");
					LoadData.MyProperties.properties[i].floor = floor;
					Log.d(APP_TAG, floor);
					String address = properties.getString("address");
					LoadData.MyProperties.properties[i].address = address;
					Log.d(APP_TAG, address);
					int cost = properties.getInt("cost");
					LoadData.MyProperties.properties[i].cost = cost;
					Log.d(APP_TAG, String.valueOf(cost));
					int rent = properties.getInt("rent");
					LoadData.MyProperties.properties[i].rent = rent;
					Log.d(APP_TAG, String.valueOf(rent));

					LoadData.MyProperties.properties[i].deposit = properties.optInt("deposit");
					LoadData.MyProperties.properties[i].optionalinfo = properties.optString("optionalinfo");

					String directside = properties.getString("directside");
					LoadData.MyProperties.properties[i].directside = directside;
					Log.d(APP_TAG, directside);
					String lastupdate = properties.getString("lastupdate");
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd");
					LoadData.MyProperties.properties[i].lastupdate = formatter
							.parse(lastupdate);
					Log.d(APP_TAG, lastupdate);
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
				return false;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}
			return true;
		}
	}

	@SuppressLint("SimpleDateFormat")
	public static boolean getAgentProperties() {
		String APP_TAG = null;
		if (AppState.currentCity.contentEquals("")) {
			return false;
		}
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("uid", AppState.currentAID));
		Log.d(APP_TAG, "UID:" + AppState.currentAID);
		String jsonProperties = null;
		try {
			jsonProperties = postHttpResponse(new URI(AppState.absoluteUri
					+ "getagentproperties.php"), params);
		} catch (URISyntaxException e) {
			Log.d(APP_TAG, "Error:" + e.getMessage());
			e.printStackTrace();
			return false;
		}

		if (jsonProperties == null) {
			return false;
		} else {

			AppState.jsonAgentProperties = jsonProperties;
			Log.d(APP_TAG, "Json:" + AppState.jsonAgentProperties);
			JSONObject json;
			JSONArray propertiesArray;
			try {
				json = new JSONObject(jsonProperties);
				propertiesArray = json.getJSONArray("property");
				LoadData.AgentProperties.properties = new Property[propertiesArray
						.length()];
				for (int i = 0; i < propertiesArray.length(); i++) {
					JSONObject properties = propertiesArray.getJSONObject(i);
					LoadData.AgentProperties.properties[i] = new Property();
					String pid = properties.getString("pid");
					LoadData.AgentProperties.properties[i].pid = pid;
					Log.d(APP_TAG, pid);
					String sellrent = properties.getString("sellrent");
					LoadData.AgentProperties.properties[i].sellrent = sellrent;
					Log.d(APP_TAG, sellrent);
					String rescom = properties.getString("rescom");
					LoadData.AgentProperties.properties[i].rescom = rescom;
					Log.d(APP_TAG, rescom);
					String locality = properties.getString("locality");
					LoadData.AgentProperties.properties[i].locality = locality;
					Log.d(APP_TAG, locality);
					String area = properties.getString("area");
					LoadData.AgentProperties.properties[i].area = area;
					Log.d(APP_TAG, area);
					String type = properties.getString("type");
					LoadData.AgentProperties.properties[i].type = type;
					Log.d(APP_TAG, type);
					String floor = properties.getString("floor");
					LoadData.AgentProperties.properties[i].floor = floor;
					Log.d(APP_TAG, floor);
					String address = properties.getString("address");
					LoadData.AgentProperties.properties[i].address = address;
					Log.d(APP_TAG, address);
					int cost = properties.getInt("cost");
					LoadData.AgentProperties.properties[i].cost = cost;
					Log.d(APP_TAG, String.valueOf(cost));
					int rent = properties.getInt("rent");
					LoadData.AgentProperties.properties[i].rent = rent;
					Log.d(APP_TAG, String.valueOf(rent));
					String directside = properties.getString("directside");
					LoadData.AgentProperties.properties[i].directside = directside;
					Log.d(APP_TAG, directside);
					String lastupdate = properties.getString("lastupdate");
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd");
					LoadData.AgentProperties.properties[i].lastupdate = formatter
							.parse(lastupdate);
					Log.d(APP_TAG, lastupdate);
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
				return false;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}
			return true;
		}
	}

	@SuppressLint("SimpleDateFormat")
	public static boolean getNotices() {
		String APP_TAG = null;
		if (AppState.currentCity.contentEquals("")) {
			return false;
		}
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("city", AppState.currentCity));
		params.add(new BasicNameValuePair("town", AppState.currentTown));
		params.add(new BasicNameValuePair("uid", AppState.UID));
		String jsonAgents = null;
		try {
			jsonAgents = postHttpResponse(new URI(AppState.absoluteUri
					+ "getnotice.php"), params);
		} catch (URISyntaxException e) {
			Log.d(APP_TAG, "Error:" + e.getMessage());
			e.printStackTrace();
			return false;
		}

		if (jsonAgents == null) {
			return false;
		} else {

			AppState.jsonAgents = jsonAgents;
			// String jstring = jsonAgents;
			JSONObject json;
			JSONArray agentsArray;
			try {
				json = new JSONObject(jsonAgents);
				agentsArray = json.getJSONArray("notice");
				LoadData.Notices.notices = new Notice[agentsArray.length()];
				for (int i = 0; i < agentsArray.length(); i++) {
					JSONObject agents = agentsArray.getJSONObject(i);
					String aid = agents.getString("aid");
					LoadData.Notices.notices[i] = new Notice();
					LoadData.Notices.notices[i].aid = aid;
					Log.d(APP_TAG, aid);
					String bname = agents.getString("bname");
					LoadData.Notices.notices[i].bname = bname;
					Log.d(APP_TAG, bname);
					String phoneno = agents.getString("phoneno");
					LoadData.Notices.notices[i].phoneno = phoneno;
					Log.d(APP_TAG, phoneno);
					String altno = agents.getString("altno");
					LoadData.Notices.notices[i].altno = altno;
					Log.d(APP_TAG, altno);
					String content = agents.getString("content");
					LoadData.Notices.notices[i].content = content;
					Log.d(APP_TAG, content);
					String lastupdate = agents.getString("lastupdate");
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd");
					LoadData.Notices.notices[i].lastupdate = formatter
							.parse(lastupdate);
					Log.d(APP_TAG, lastupdate);
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
				return false;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}
			return true;
		}
	}

}
