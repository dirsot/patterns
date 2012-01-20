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
public class ServletAddNews extends HttpServlet {

	public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
		
		HttpSession session = req.getSession(true);
		String title = checkNull(req.getParameter("title"));
		String content = checkNull(req.getParameter("content"));
		String active = checkNull(req.getParameter("active"));
		
		String user = (String)session.getAttribute("user");
		String until = checkNull(req.getParameter("endTime"));
		System.out.println(until);
		
		long epoch = new java.text.SimpleDateFormat ("dd/MM/yyyy HH:mm").parse(until).getTime();
		Long time = System.currentTimeMillis()/1000;
		boolean ac = (active=="0")?false:true;
		Dao.INSTANCE.insertNews(title,content,time,epoch,user,ac,"",-1,"");

		resp.sendRedirect("/index.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			try {
				processRequest(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException {
			try {
				processRequest(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}	

}
