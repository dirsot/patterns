package pl.dirsot.bets;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.dirsot.bets.dao.Dao;

import pl.dirsot.bets.utils.*;

@SuppressWarnings("serial")
public class ServletActivateAccount extends HttpServlet {

	public void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, InterruptedException, ExecutionException,
			ParseException, InvalidValidationKeyException {
		
		String kod="";
		String login="";
		try {
			kod = DoString.checkActivationKey(req.getParameter("kod"));
			login = checkNull(req.getParameter("login"));
		} catch (EmptyValidationKeyException ex) {
			resp.sendRedirect("/index.jsp");
		}
		Dao.INSTANCE.activateAccount(kod,login);
		resp.sendRedirect("/index.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
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
