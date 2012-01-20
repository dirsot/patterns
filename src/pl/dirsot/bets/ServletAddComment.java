package pl.dirsot.bets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import pl.dirsot.bets.dao.Dao;

@SuppressWarnings("serial")
public class ServletAddComment extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession(true);
		String user = (String)session.getAttribute("user");
		if (user == null) {
			user="Anonim";
		}

		String id = checkNull(req.getParameter("id"));
		String comment = checkNull(req.getParameter("comment"));
		Long time = System.currentTimeMillis()/1000;
		
		Dao.INSTANCE.addComment(user,comment,id,time);

		resp.sendRedirect("/index.jsp");
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}