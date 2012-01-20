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
import pl.dirsot.bets.model.incoms;
import pl.dirsot.bets.model.users;
import pl.dirsot.bets.shared.zaklady;

@SuppressWarnings({ "serial", "unused" })
public class ServletAddDeal extends HttpServlet {

	public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession(true);
		String user;
		try{
		user = checkNull(session.getAttribute("user").toString());
		}catch(NullPointerException e){
			resp.sendRedirect("/index.jsp");
			return;
		}
		
		if(Dao.INSTANCE.isActiveUser(user)){
		
		Long time = System.currentTimeMillis()*1000;
		
		String bet = checkNull(req.getParameter("bet"));
		String ammount = checkNull(req.getParameter("ammount"));
		String who = checkNull(req.getParameter("typ"));
		System.out.println(bet);
		
		int amm = Integer.parseInt(ammount);
		incoms incom = Dao.INSTANCE.getUserMoney(user);
		if(incom.getQuantity()<amm){
			resp.sendRedirect("/addDeal.jsp?bet="+bet);
			return;
		}
			
		Dao.INSTANCE.addDeal("id", 
				user,
				time, 
				Integer.parseInt(bet), 
				Integer.parseInt(who), 
				amm);
		Dao.INSTANCE.addMoney(user, -amm);
		}
		resp.sendRedirect("/index.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException {
			processRequest(request, response);
	}
	private String checkNull(String s) {
		if (s == null) {
			return "-1";
		}
		return s;
	}	

}
