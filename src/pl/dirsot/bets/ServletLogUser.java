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
public class ServletLogUser extends HttpServlet {

	@SuppressWarnings("deprecation")
	public void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, InterruptedException, ExecutionException,
			ParseException {


		HttpSession session = req.getSession(true);
		String login = checkNull(req.getParameter("login"));
		String pass = checkNull(req.getParameter("pass"));
		users user = Dao.INSTANCE.logUser(login, pass);
		long time = System.currentTimeMillis() * 1000;
		if((user!=null)){
		// if (user.getActive())  &&(user.validPass(pass))
		if (true) {
			session.putValue("user", user.getLogin());
			session.putValue("type", Integer.toString(user.getType()));
			session.putValue("logged", "1");
			req.setAttribute("info", "Gratuluje zalogowania");
			Dao.INSTANCE.setLastLoginTime(login, time);
		} else {
			req.setAttribute("info", "Blad logowania");
		}}
		resp.sendRedirect("/index.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, IOException {
		try {
			processRequest(request, response);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, IOException {
		try {
			processRequest(request, response);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
