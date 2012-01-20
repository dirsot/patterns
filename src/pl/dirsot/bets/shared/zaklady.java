package pl.dirsot.bets.shared;

import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
// dzien live http://www.livescore.in/pl/x/feed/f_1_1319241600_2_pl_4
// liga http://www.livescore.in/pl/x/feed/c_1_77_2_pl_y_4
//more http://www.livescore.in/pl/detail/nD7r2fk2/#szczegoly-meczu

import com.ning.http.client.AsyncHandler;
import com.ning.http.client.AsyncHandler.STATE;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.AsyncHttpClientConfig.Builder;
import com.ning.http.client.HttpResponseBodyPart;
import com.ning.http.client.HttpResponseHeaders;
import com.ning.http.client.HttpResponseStatus;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;

import com.google.appengine.api.urlfetch.*;
import com.google.appengine.api.urlfetch.URLFetchServicePb.URLFetchRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import pl.dirsot.bets.ServletUpdateBets;
import pl.dirsot.bets.dao.Dao;

/**
 * 
 * @author stive
 */
@SuppressWarnings("unused")
public class zaklady {

	private static Character D = (char) 247;
	private static Character Z = (char) 172;
	private static String DZIEL = D.toString();
	private static String ZET = Z.toString();

	private static final Logger log = Logger.getLogger(ServletUpdateBets.class
			.getName());
	Connection conn = null;
	PreparedStatement statement = null;
	ResultSet resultSet2 = null;

	public zaklady() throws SQLException {

	}

	static class CustomAsyncHandler implements AsyncHandler<String> {

		private static final Logger LOG = Logger
				.getLogger(CustomAsyncHandler.class.getName());
		private ByteArrayOutputStream body = new ByteArrayOutputStream();

		@Override
		public STATE onStatusReceived(HttpResponseStatus status)
				throws Exception {
			// System.out.println("STATUS " + status.getStatusCode() + " - " +
			// status.getStatusText());
			return STATE.CONTINUE;
		}

		@Override
		public STATE onHeadersReceived(HttpResponseHeaders headers)
				throws Exception {
			/*
			 * System.out.println("HEADERS:"); for(Map.Entry<String,
			 * List<String>> entry : headers.getHeaders().entrySet()) {
			 * 
			 * System.out.println(entry.getKey() + ": " + entry.getValue()); }
			 */
			return STATE.CONTINUE;
		}

		@Override
		public STATE onBodyPartReceived(HttpResponseBodyPart bodyPart)
				throws Exception {
			// bodyPart.writeTo(body);
			return STATE.CONTINUE;
		}

		@Override
		public String onCompleted() throws Exception {

			return body.toString("UTF-8");
		}

		@Override
		public void onThrowable(Throwable t) {
			LOG.warning("Error occured while recieving response from server.");
		}
	}

	public void doScan(int type, int startDay) throws IOException,
			InterruptedException, ExecutionException, SQLException,
			ParseException {

		log.info("Do scan");
		int i = startDay;
		Future<String> response = null;
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		long dayUnix = 86000;
		long epoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				.parse(month + 1 + "/" + day + "/" + year + " 00:00:00")
				.getTime() / 1000;

		log.info("Do scan petka");
		dayUnix = epoch + 86400 * i;
		String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
				.format(new java.util.Date(dayUnix * 1000));
		System.out.println(date);
		System.out
				.println("http://www.livescore.in/pl/x/feed/f_1_"+dayUnix+"_2_pl_4_eu");
		System.out.println("idziedddd" + dayUnix);
		log.info("idziedddd = " + dayUnix);
		System.err.println("typw" + type);
		System.err.println("day" + i);
		System.err.println("dayUnix" + dayUnix);
		if (type == 0) {
			try {
				URL url = new URL("http://www.livescore.in/pl/x/feed/f_1_"
						+ dayUnix + "_2_pl_4_eu");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(url.openStream(), "UTF8"));
				String line = reader.readLine();
				//log.info(line);
				insertZaklady(line);
				reader.close();

			} catch (MalformedURLException e) {
				log.info("MalformedURLException" + e.toString());
			} catch (IOException e) {
				log.info("IOException" + e.toString());
			}
		} else {
			try {
				URL url = new URL("http://www.livescore.in/pl/x/feed/fo_1_"
						+ dayUnix + "_2_pl_4_eu");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(url.openStream(), "UTF8"));
				String line = reader.readLine();
				System.out.println(line);
				log.info(line);
				insertStawki(line);
				reader.close();

			} catch (MalformedURLException e) {
				log.info("MalformedURLException" + e.toString());
			} catch (IOException e) {
				log.info("IOException" + e.toString());
			}
		}

		/*
		 * request =
		 * requestBuilder.setUrl("http://www.livescore.in/pl/x/feed/f_1_"
		 * +dayUnix+"_2_pl_4").addHeader("HEADER-NAME",
		 * "HEADER-VALUE").setBody("BODY DATA").build();
		 * 
		 * Future<String> response1 = client.executeRequest(request, new
		 * CustomAsyncHandler()); if (response1.get().length() > 10) {
		 * insertZaklady(response1.get()); System.out.println(response1.get());
		 * System.out.println("jestem w 1");
		 * 
		 * } System.out.println("http://www.livescore.in/pl/x/feed/f_1_"+i+
		 * "_2_pl_4"); System.out.println("Koniec etap 1"); request =
		 * requestBuilder
		 * .setUrl("http://www.livescore.in/pl/x/feed/fo_1_"+dayUnix
		 * +"_2_pl_4_eu").addHeader("HEADER-NAME",
		 * "HEADER-VALUE").setBody("BODY DATA").build();
		 */
		/*
		 * Future<String> response2 = client.executeRequest(request, new
		 * CustomAsyncHandler()); if (response2.get().length() > 10) {
		 * insertStawki(response2.get()); System.out.println(response2.get());
		 * System.out.println("jestem w 1"); }
		 * System.out.println("Koniec tury");
		 */

		// }
	}

	public void insertStawki(String val) throws SQLException {
		log.info("[InsertStawki] START");
		System.err.println("[InsertStawki] START");
		val = val.replace('\'', '_');
		String[] arr = val.split("~AA");
		Pattern meczId = Pattern.compile(DZIEL + "([^" + ZET + "]*)" + ZET+ "AY");
		Pattern first = Pattern.compile(ZET + "YA" + DZIEL + "s([^" + ZET+ "]*)" + ZET);
		Pattern draw = Pattern.compile(ZET + "YB" + DZIEL + "s([^" + ZET+ "]*)" + ZET);
		Pattern second = Pattern.compile(ZET + "YC" + DZIEL + "s([^" + ZET+ "]*)" + ZET);
		Pattern firstX = Pattern.compile(ZET + "XA" + DZIEL + "s-([^" + ZET+ "]*)" + ZET);
		Pattern drawX = Pattern.compile(ZET + "XB" + DZIEL + "s-([^" + ZET+ "]*)" + ZET);
		Pattern secondX = Pattern.compile(ZET + "XC" + DZIEL + "s-([^" + ZET+ "]*)" + ZET);
		String mecz = "";
		String firs = "-1";
		String dra = "-1";
		String sec = "-1";
		String firsX = "-1";
		String draX = "-1";
		String secX = "-1";
		String up = "";
		// System.out.println(val);
		// System.out.println(arr[1]);
//		System.err.println("arr.length " + arr.length);
//		System.err.println("val " + val);
		for (int i = 1; i < arr.length; i++) {
			//System.err.println("arr[i]" + arr[i]);
			//System.err.println("i" + i);
			Matcher mat = meczId.matcher(arr[i]);
			while (mat.find()) {
				mecz = mat.group(1);
			}
			Matcher mat1 = first.matcher(arr[i]);
			while (mat1.find()) {
				firs = mat1.group(1);
			}
			if(firs.contains("-")){
				mat1 = firstX.matcher(arr[i]);
				while (mat1.find()) {
					firs = mat1.group(1);
				}
			}
			Matcher mat2 = draw.matcher(arr[i]);
			while (mat2.find()) {
				dra = mat2.group(1);
			}
			if(dra.contains("-")){
				mat1 = drawX.matcher(arr[i]);
				while (mat1.find()) {
					dra = mat1.group(1);
				}
			}
			Matcher mat3 = second.matcher(arr[i]);
			while (mat3.find()) {
				sec = mat3.group(1);
			}
			if(sec.contains("-")){
				mat1 = secondX.matcher(arr[i]);
				while (mat1.find()) {
					sec = mat1.group(1);
				}
			}
			up = "UPDATE bets.Bets SET Bt_first=\'" + firs + "\',Bt_draw=\'"
					+ dra + "\',Bt_second=\'" + sec + "\' WHERE Bt_more=\'"
					+ mecz + "\'";
			System.err.println(up);
			System.err.println(arr[i]);
			Dao.INSTANCE.updateStawki(Float.parseFloat(firs.toString()),
					Float.parseFloat(dra.toString()),
					Float.parseFloat(sec.toString()), mecz);
			// System.err.println(up);

			firs = "-1";
			dra = "-1";
			sec = "-1";
		}
	}

	public void insertZaklady(String val) throws SQLException {
		
		System.out.println("insert zaklady");
		System.err.println(val);

		val = val.replace('\'', '_');
		Pattern team1 = Pattern.compile(ZET + "AE" + DZIEL + "([^" + ZET
				+ "]*)" + ZET);
		Pattern team2 = Pattern.compile(ZET + "AF" + DZIEL + "([^" + ZET
				+ "]*)" + ZET);
		String team11 = "";
		String team22 = "";
		Pattern team1score = Pattern.compile(ZET + "AG" + DZIEL + "([^" + ZET
				+ "]*)" + ZET);
		Pattern team2score = Pattern.compile(ZET + "AH" + DZIEL + "([^" + ZET
				+ "]*)" + ZET);
		String team11score = "-1";
		String team22score = "-1";
		String[] tmpPL = new String[2];
		String[] tmp2 = new String[2];
		String meczId = "";
		String date = "";
		String[] spotkania;
		String panstwo = "";
		String liga = "";
		String text = val;
		String[] arr = text.split("ZA" + DZIEL);
		
		// System.out.println(arr[0]);
		// System.out.println(arr.length);
		//System.err.println("arr len " + arr.length);
		// System.out.println("arr len "+arr.length);
		for (int i = 1; i < arr.length; i++) {
			tmpPL = arr[i].split(ZET + "ZB" + DZIEL);
			tmpPL = tmpPL[0].split(":");
			panstwo = tmpPL[0];
			liga = tmpPL[1];
			// System.out.println(liga);
			// Dao.INSTANCE.insertLeague(liga, panstwo, true);

			// System.out.println("~AA"+DZIEL);
			// System.out.println(arr[i]);

			spotkania = arr[i].split("~AA" + DZIEL);
			//System.err.println(spotkania.length);
			// System.err.println("spotkania "+spotkania.length);
			// System.out.println("spotkania "+spotkania.length);
			for (int j = 1; j < spotkania.length; j++) {
				//System.err.println("do ");
				try {
					tmp2 = spotkania[j].split(ZET + "AD" + DZIEL);
					meczId = tmp2[0];
					tmp2 = tmp2[1].split(ZET + "AB" + DZIEL);
					date = tmp2[0];

					Matcher mat = team1.matcher(tmp2[1]);
					while (mat.find()) {
						team11 = mat.group(1);
					}
					Matcher mat2 = team2.matcher(tmp2[1]);
					while (mat2.find()) {
						team22 = mat2.group(1);
					}
					Matcher matscore = team1score.matcher(tmp2[1]);
					while (matscore.find()) {
						team11score = matscore.group(1);
					}
					Matcher mat2score = team2score.matcher(tmp2[1]);
					while (mat2score.find()) {
						team22score = mat2score.group(1);
					}
					// System.out.println(tmp2[1]);
					// System.out.println(meczId);
					/*
					 * String a1 =
					 * "INSERT IGNORE INTO bets.Countries (Cn_name) values (\'"
					 * + panstwo + "\');"; String a2 =
					 * "INSERT IGNORE INTO bets.Leagues (Lg_name,Lg_cnId) values (\'"
					 * + liga + "\'," +
					 * "(SELECT Cn_id FROM bets.Countries where Cn_name = \'" +
					 * panstwo + "\'));"; String a3 =
					 * "INSERT IGNORE INTO bets.Teams (Tm_name,Tm_sport_id,Tm_leage,Tm_country) "
					 * + "values (\'" + team11.replace('\'', '_') +
					 * "\',1,(SELECT Lg_id FROM bets.Leagues where Lg_name = \'"
					 * + liga + "\')," +
					 * "(SELECT Cn_id FROM bets.Countries where Cn_name = \'" +
					 * panstwo + "\'));"; String a5 =
					 * "INSERT IGNORE INTO bets.Teams (Tm_name,Tm_sport_id,Tm_leage,Tm_country) "
					 * + "values (\'" + team22.replace('\'', '_') +
					 * "\',1,(SELECT Lg_id FROM bets.Leagues where Lg_name = \'"
					 * + liga + "\')," +
					 * "(SELECT Cn_id FROM bets.Countries where Cn_name = \'" +
					 * panstwo + "\'));";
					 */
					String a4 = "INSERT IGNORE INTO bets.Bets (Bt_team1,Bt_team2,Bt_date,Bt_first,Bt_draw,"
							+ "Bt_second,Bt_active,Bt_more,Bt_sc_fi,Bt_sc_se) values ((SELECT Tm_id FROM Teams WHERE Tm_name = \'"
							+ team11.replace('\'', '_')
							+ "\'),(SELECT Tm_id FROM Teams WHERE Tm_name = \'"
							+ team22.replace('\'', '_')
							+ "\'),\'"
							+ date
							+ "\',"
							+ "-1,-1,-1,1,\'"
							+ meczId
							+ "\',\'"
							+ team11score + "\',\'" + team22score + "\');";
//					System.err.println("insert data");
//					System.err.println(a4);
					// Dao.INSTANCE.addTeam(1, 1);
					// System.out.println(a1);
					// System.out.println(a2);
					// System.out.println(a3);
					// System.err.println(a4);
					// System.out.println(a4);
					Dao.INSTANCE.insertTeam(team11, true);
					Dao.INSTANCE.insertTeam(team22, true);
					Dao.INSTANCE.addBet(team11, team22, Integer.valueOf(date),
							liga, panstwo, (float) -1, (float) -1, (float) -1,
							meczId, 0, true, Integer.valueOf(team11score),
							Integer.valueOf(team22score));

				} catch (Exception e) {
					System.out.println("[ERROR] " + e.toString());
				}
			}

			// System.out.println(panstwo);
			// System.out.println(liga);
		}
	}
}
