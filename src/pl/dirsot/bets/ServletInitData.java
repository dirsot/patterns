package pl.dirsot.bets;

import java.io.IOException;
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
public class ServletInitData extends HttpServlet {

	public void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Dao.INSTANCE.addBet("test1", "test2", (long)1066610, "polska", "polska", (float)1.0, (float)1.0, (float)1.0, "", 1, true, -1, -1);
		Dao.INSTANCE.addDeal("12","admin",(long)1066610,1,1,1);
		Dao.INSTANCE.insertUser("admin","pass","imie","nazw",0,true,(long)111,(long)111,(long)111);
		Dao.INSTANCE.insertTeam("Biali", true);
		Dao.INSTANCE.insertLeague("mistrzow", "polska", true);
		Dao.INSTANCE.insertNews("Wielkie bergy Londynu","Czy tym razem biała gwiazda pokona swojego rywala zza wisly", (long)1066600000,(long)34,"admin",true , "1",1, "");
		
		resp.sendRedirect("/index3.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			processRequest(request, response);

	}

}
