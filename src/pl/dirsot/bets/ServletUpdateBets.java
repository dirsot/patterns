package pl.dirsot.bets;

import java.util.logging.Logger;

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
public class ServletUpdateBets extends HttpServlet {

	private static final Logger log = Logger.getLogger(ServletUpdateBets.class
			.getName());

	public void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, SQLException {

		zaklady tmp;
		try {
			String typeReq =req.getParameter("type");
			String dayReq =req.getParameter("day");
			
			System.out.println(typeReq);
			
			if(dayReq ==null)
				dayReq = "2";
			int type =Integer.parseInt(typeReq);
			int day =Integer.parseInt(dayReq);
			//int day =2;
			
			if(type >1 || type <0 || day <-1|| day >4 ){
				resp.sendRedirect("/index.jsp");
			}
			tmp = new zaklady();
			// tmp.insertZaklady("SA÷1¬~ZA÷ALBANIA: Super League¬ZB÷fl_17¬ZC÷C6Gh75fM¬ZD÷p¬ZE÷0¬ZF÷0¬ZJ÷2¬~AA÷EykzhW0o¬AD÷1319284800¬AB÷3¬AC÷3¬AE÷Flamurtari¬AS÷1¬AZ÷1¬AG÷1¬BA÷1¬BC÷0¬AF÷Vllaznia¬AH÷0¬BB÷0¬BD÷0¬~AA÷OSi9ZloH¬AD÷1319284800¬AB÷3¬AC÷3¬AE÷Kamza¬AS÷1¬AZ÷1¬AG÷2¬BA÷2¬BC÷0¬AF÷Berat¬AH÷1¬BB÷1¬BD÷0¬~AA÷ncVhyBG4¬AD÷1319284800¬AB÷3¬AC÷3¬AE÷Pogradeci¬AS÷0¬AZ÷0¬AG÷0¬BA÷0¬BC÷0¬AF÷Shkumbini Peqin¬AH÷0¬BB÷0¬BD÷0¬~ZA÷ALGIERIA: Dywizja 1¬ZB÷fl_18¬ZC÷nLUwHPzr¬ZD÷p¬ZE÷0¬ZF÷0¬ZJ÷2¬~AA÷8SeAX0BJ¬AD÷1319295600¬AB÷3¬AC÷3¬AE÷Batna¬AS÷0¬AZ÷0¬AG÷0¬BA÷0¬BC÷0¬AF÷Bejaia¬AH÷0¬BB÷0¬BD÷0¬~AA÷I1opRblm¬AD÷1319295600¬AB÷3¬AC÷3¬AE÷Eulma¬AS÷0¬AZ÷0¬AG÷1¬BA÷1¬BC÷0¬AF÷Kabylie ¬AH÷1¬BB÷1¬BD÷0¬~AA÷OEfF9cJC¬AD÷1319295600¬AB÷3¬AC÷3¬AE÷Oran¬AS÷1¬AZ÷1¬AG÷2¬BA÷1¬BC÷1¬AF÷Constantine¬AH÷1¬BB÷0¬BD÷1¬~AA÷Gli7BJl0¬AD÷1319302800¬AB÷3¬AC÷3¬AE÷Belouizdad¬AS÷1¬AZ÷1¬AG÷3¬BA÷2¬BC÷1¬AF÷Saida¬AH÷1¬BB÷1¬BD÷0¬~AA÷MVaEWKQP¬AD÷1319302800¬AB÷3¬AC÷3¬AE÷Harrach¬AS÷1¬AZ÷1¬AG÷1¬BA÷0¬BC÷1¬AF÷Hussein Dey¬AH÷0¬BB÷0¬BD÷0¬~AA÷vBntSvZt¬AD÷1319302800¬AB÷3¬AC÷3¬AE÷Khroub¬AS÷0¬AZ÷0¬AG÷2¬BA÷1¬BC÷1¬AF÷Setif¬AH÷2¬BB÷1¬BD÷1¬~AA÷fujBAw46¬AD÷1319302800¬AB÷3¬AC÷3¬AE÷Tlemcen¬AS÷0¬AZ÷0¬AG÷1¬BA÷1¬BC÷0¬AF÷USM Alger¬AH÷1¬BB÷1¬BD÷0¬~ZA÷ANGLIA: Premier League¬ZB÷fl_198¬ZC÷xOR3gQta¬ZD÷p¬ZE÷0¬ZF÷0¬ZJ÷2¬~AA÷YqGMTYUR¬AD÷1319283900¬AB÷3¬AC÷3¬AE÷Wolves¬AS÷0¬AZ÷0¬AG÷2¬BA÷0¬BC÷2¬AF÷Swansea¬AH÷2¬BB÷2¬BD÷0¬~AA÷hjWz9k2M¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Aston Villa¬AJ÷1¬AG÷1¬BA÷1¬BC÷0¬AF÷West Brom¬AS÷2¬AZ÷2¬AH÷2¬BB÷1¬BD÷1¬~AA÷CzD73TPq¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Bolton¬AG÷0¬BA÷0¬BC÷0¬AF÷Sunderland¬AS÷2¬AZ÷2¬AH÷2¬BB÷0¬BD÷2¬~AA÷4EHAPAn3¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Newcastle¬AS÷1¬AZ÷1¬AG÷1¬BA÷0¬BC÷1¬AF÷Wigan¬AH÷0¬BB÷0¬BD÷0¬~AA÷plBF17fd¬AD÷1319301000¬AB÷3¬AC÷3¬AE÷Liverpool¬AS÷0¬AZ÷0¬AG÷1¬BA÷1¬BC÷0¬AF÷Norwich¬AH÷1¬BB÷0¬BD÷1¬~ZA÷ANGLIA: Championship¬ZB÷fl_198¬ZC÷A7jQrg9k¬ZD÷p¬ZE÷0¬ZF÷0¬ZJ÷2¬~AA÷WECwZJx4¬AD÷1319281200¬AB÷3¬AC÷3¬AE÷Peterborough¬AJ÷1¬AG÷2¬BA÷1¬BC÷1¬AF÷Leeds¬AS÷2¬AZ÷2¬AH÷3¬BB÷1¬BD÷2¬~AA÷MmwbWHMM¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Blackpool¬AG÷1¬BA÷1¬BC÷0¬AF÷Nottingham¬AS÷2¬AZ÷2¬AK÷1¬AH÷2¬BB÷1¬BD÷1¬~AA÷EaB8HI7T¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Cardiff¬AS÷1¬AZ÷1¬AG÷5¬BA÷3¬BC÷2¬AF÷Barnsley¬AH÷3¬BB÷1¬BD÷2¬~AA÷EoEYPFaj¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Coventry¬AJ÷1¬AG÷1¬BA÷0¬BC÷1¬AF÷Burnley¬AS÷2¬AZ÷2¬AH÷2¬BB÷0¬BD÷2¬~AA÷A5BsYwhA¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Hull¬AS÷1¬AZ÷1¬AG÷3¬BA÷0¬BC÷3¬AF÷Watford¬AH÷2¬BB÷1¬BD÷1¬~AA÷zmJUzt8i¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Ipswich¬AJ÷1¬AG÷0¬BA÷0¬BC÷0¬AF÷Crystal Palace¬AS÷2¬AZ÷2¬AH÷1¬BB÷0¬BD÷1¬~AA÷EwIYZaNc¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Leicester¬AG÷0¬BA÷0¬BC÷0¬AF÷Millwall¬AS÷2¬AZ÷2¬AH÷3¬BB÷2¬BD÷1¬~AA÷KtlyDxGp¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Middlesbrough¬AS÷1¬AZ÷1¬AG÷2¬BA÷1¬BC÷1¬AF÷Derby¬AH÷0¬BB÷0¬BD÷0¬~AA÷6LEUQepp¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Portsmouth¬AS÷1¬AZ÷1¬AG÷3¬BA÷1¬BC÷2¬AF÷Doncaster¬AH÷1¬BB÷1¬BD÷0¬~AA÷4SAoXc7G¬AD÷1319300400¬AB÷3¬AC÷3¬AE÷Reading¬AS÷0¬AZ÷0¬AG÷1¬BA÷0¬BC÷1¬AF÷Southampton¬AK÷1¬AH÷1¬BB÷0¬BD÷1¬~ZA÷ANGLIA: League One¬ZB÷fl_198¬ZC÷rBo4UMrt¬ZD÷p¬ZE÷0¬ZF÷0¬ZJ÷2¬~AA÷SjcrHsbe¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Bournemouth¬AG÷1¬BA÷1¬BC÷0¬AF÷Bury¬AS÷2¬AZ÷2¬AH÷2¬BB÷1¬BD÷1¬~AA÷UkS4Q5jF¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Charlton¬AS÷1¬AZ÷1¬AG÷4¬BA÷3¬BC÷1¬AF÷Carlisle¬AK÷1¬AH÷0¬BB÷0¬BD÷0¬~AA÷8tR8PP5L¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Chesterfield¬AG÷2¬BA÷1¬BC÷1¬AF÷Hartlepool¬AS÷2¬AZ÷2¬AH÷3¬BB÷2¬BD÷1¬~AA÷fs7owPKE¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Exeter¬AS÷1¬AZ÷1¬AG÷3¬BA÷1¬BC÷2¬AF÷Rochdale¬AH÷1¬BB÷1¬BD÷0¬~AA÷j5GDOqLR¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Huddersfield¬AS÷1¬AZ÷1¬AG÷3¬BA÷2¬BC÷1¬AF÷Preston¬AH÷1¬BB÷0¬BD÷1¬~AA÷fwazJ3Tr¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Leyton Orient¬AS÷0¬AZ÷0¬AJ÷1¬AG÷1¬BA÷0¬BC÷1¬AF÷Sheffield Utd¬AH÷1¬BB÷0¬BD÷1¬~AA÷x6djxqzL¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Milton Keynes¬AS÷0¬AZ÷0¬AG÷0¬BA÷0¬BC÷0¬AF÷Scunthorpe¬AH÷0¬BB÷0¬BD÷0¬~AA÷l0bvINrk¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Notts County¬AS÷0¬AZ÷0¬AG÷1¬BA÷1¬BC÷0¬AF÷Brentford¬AH÷1¬BB÷0¬BD÷1¬~AA÷tv3sv558¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Oldham¬AS÷1¬AZ÷1¬AG÷2¬BA÷0¬BC÷2¬AF÷Wycombe¬AH÷0¬BB÷0¬BD÷0¬~AA÷pSimG1D1¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Sheffield Wed¬AS÷1¬AZ÷1¬AG÷2¬BA÷0¬BC÷2¬AF÷Colchester¬AH÷0¬BB÷0¬BD÷0¬~AA÷0GN0Roy9¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Stevenage¬AS÷0¬AZ÷0¬AG÷0¬BA÷0¬BC÷0¬AF÷Yeovil¬AK÷1¬AH÷0¬BB÷0¬BD÷0¬~AA÷OIjiFLS7¬AD÷1319292000¬AB÷3¬AC÷3¬AE÷Tranmere¬AS÷1¬AZ÷1¬AG÷2¬BA÷0¬BC÷2¬AF÷Walsall¬AH÷1¬BB÷0¬BD÷1¬~ZA÷ANGLIA: League");
			tmp.doScan(type, day);

		} catch (InterruptedException e) {
			log.info("1InterruptedException." + e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			log.info("2ExecutionException." + e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			log.info("3ParseException." + e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect("/index.jsp");
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
