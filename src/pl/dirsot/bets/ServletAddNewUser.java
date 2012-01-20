package pl.dirsot.bets;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.dirsot.bets.dao.Dao;
import pl.dirsot.bets.utils.Mail;

@SuppressWarnings("serial")
public class ServletAddNewUser extends HttpServlet {

	public void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, InterruptedException, ExecutionException, ParseException {
		
		System.out.println("zaczynam");
		boolean isAdded;
		String activationKey = "123";
		String login = checkNull(req.getParameter("login"));
		String email = checkNull(req.getParameter("email"));
		String name = checkNull(req.getParameter("name"));
		String surname = checkNull(req.getParameter("surname"));
		String password = checkNull(req.getParameter("password"));
		String passwordAgain = checkNull(req.getParameter("passwordAgain"));
		req.setAttribute("registerStatus", "Wystąpił błąd przy próbie zapisu użytkownika");
		if(password.contentEquals(passwordAgain)){
			System.err.println("passOk");
			isAdded = Dao.INSTANCE.addNewUser(login,name,surname,password);
			Dao.INSTANCE.addNewIncom(login,name,10);
			if(isAdded){
				Mail mail = new Mail();
				mail.sendActivactionMail(email, activationKey,login);
				req.setAttribute("registerStatus", "Stworzyłeś konto, teraz aktywuj je.");
			}
		}
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
