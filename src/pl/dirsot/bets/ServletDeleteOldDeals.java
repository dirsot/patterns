package pl.dirsot.bets;

import java.io.IOException;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.dirsot.bets.dao.Dao;
import pl.dirsot.bets.model.users;
import pl.dirsot.bets.shared.zaklady;

@SuppressWarnings({ "serial", "unused" })
public class ServletDeleteOldDeals extends HttpServlet {

	private static final Logger log = Logger.getLogger(ServletUpdateBets.class
			.getName());

	public void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {

		String kod = req.getParameter("kod");
		String co = req.getParameter("co");
		Character D = (char) 247;
		Character Z = (char) 172;
		String DZIEL = D.toString();
		String ZET = Z.toString();
		try {
			URL url = new URL(
					"http://www.livescore.in/pl/x/feed/f_1_1319936400_1_pl_4");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream(), kod));
			String line = reader.readLine();
			System.out.println(line);
			log.info(line);
			PrintWriter out = resp.getWriter();
			String oo = line.substring(0, 1500);
			Character znak = (char) 172;
			out.println(oo.charAt(0));
			out.println((int)oo.charAt(0));
			out.println(oo.charAt(1));
			out.println((int)oo.charAt(1));
			out.println(oo.charAt(2));
			out.println((int)oo.charAt(2));
			out.println(oo.charAt(3));
			out.println((int)oo.charAt(3));
			out.println(oo.charAt(4));
			out.println((int)oo.charAt(4));
			out.println(oo.charAt(5));
			out.println((int)oo.charAt(5));;
			out.println(oo.charAt(6));
			out.println((int)oo.charAt(6));
			out.println(oo.charAt(7));
			out.println((int)oo.charAt(7));
			
			out.println(DZIEL);
			out.println(oo.split(ZET+"ZB"+DZIEL)[0]);
			out.println(oo.split(znak.toString())[0]);
			
			OutputStreamWriter out2 = new OutputStreamWriter(new ByteArrayOutputStream());
			String encoding = out2.getEncoding();
			out.println(encoding);
			reader.close();

		} catch (MalformedURLException e) {
			log.info("MalformedURLException" + e.toString());
		} catch (IOException e) {
			log.info("IOException" + e.toString());
		}

		// Dao.INSTANCE.deleteOldDeals();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
