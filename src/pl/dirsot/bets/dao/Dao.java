package pl.dirsot.bets.dao;

import java.util.List;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import pl.dirsot.bets.model.*;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.org.apache.bcel.internal.generic.RET;

@SuppressWarnings({"unused","unchecked"})
public enum Dao {
	INSTANCE;
	EntityManager em = EMFService.get().createEntityManager();
	long now = System.currentTimeMillis() / 1000;

	
	public boolean isActiveUser(String user){
		Query q = em.createQuery("SELECT u FROM users u WHERE Us_login = ?1").setParameter(1, user);
		boolean ret = false;
		System.out.println(user);
		try{
			System.out.println(q.getResultList().get(0));
			ret = ((users)q.getResultList().get(0)).getActive();

		
		}catch(Exception e){
			System.out.println(e);
		}
		return ret; 
	}
	public boolean addNewUser(String login, String name, String surname,
			String password) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			users user = new users(login, password, name, surname, 1, false,
					now, 0, 0);
			em.persist(user);
			em.close();
		}
		return true;
	}
	public incoms addNewIncom(String login, String name,float ammount) {
		incoms incom;
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			incom = new incoms(login,name,ammount);
			em.persist(incom);
			em.close();
		}
		return incom;
	}
	public boolean addMoney(String login,float ammount) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("SELECT i FROM incoms i WHERE In_userName=?1");
		q.setParameter(1, login);
		List<incoms> res = q.getResultList();
		if (res.size()>0) {
			try {
				incoms incom = em.find(incoms.class, res.get(0).getId());
				incom.addQuantity(ammount);
			} finally {
				em.close();
			}
		}
		
		return true;
	}
	public incoms getUserMoney(String login) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("SELECT i FROM incoms i WHERE In_userName=?1");
		q.setParameter(1, login);
		List<incoms> res = q.getResultList();
		if (res.size()>0) {
			return res.get(0);
		}

		return null;
	}

	
	public List<users> getUsers() {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("SELECT u FROM users u");

		List<users> res = q.getResultList();
		
		return res;
	}
	public boolean activateAccount(String kod,String login) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("SELECT u FROM users u WHERE Us_login=?1");
		q.setParameter(1, login);
		List<users> res = q.getResultList();
		if (res.size() == 1) {
			try {
				users user = em.find(users.class, res.get(0).getId());
				user.setActive(true);
			} finally {
				em.close();
			}
		}

		return true;
	}

	public boolean insertNews(String title, String content, long date,long epoch,
			String userName, boolean active, String userId, int type,
			String image) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			news news = new news(title, content, date,epoch, userId, userName, type,
					active, image);
			em.persist(news);
			em.close();
		}
		return true;
	}
	public boolean addComment(String user, String comment,String id, long date) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			comments news = new comments(user,comment,id,date);
			em.persist(news);
			em.close();
		}
		return true;
	}

	public incoms getIncom(String login) {
		EntityManager em = EMFService.get().createEntityManager();
		List<incoms> ret = em
				.createQuery("select i from incoms i WHERE In_userName = ?1")
				.setParameter(1, login).getResultList();
		if (ret.size() > 0)	
			return ret.get(0);
		incoms incom = addNewIncom(login,login,10);
		return incom;
	}
	
	public boolean insertTeam(String name, boolean active) {
		EntityManager em = EMFService.get().createEntityManager();
		List<bets> ret = em
				.createQuery("select t from teams t WHERE Tm_name = ?1")
				.setParameter(1, name).getResultList();
		if (ret.size() == 0)
			synchronized (this) {
				teams team = new teams(name, active);
				em.persist(team);
				em.close();
			}
		return true;
	}

	public boolean insertLeague(String leagueName, String country,
			boolean active) {
		EntityManager em = EMFService.get().createEntityManager();
		List<bets> ret = em
				.createQuery("select l from leagues l WHERE Lg_name = ?1")
				.setParameter(1, leagueName).getResultList();
		System.out.println(leagueName);
		System.out.println("add leage" + ret.size());
		if (ret.size() == 0)
			synchronized (this) {
				leagues league = new leagues(leagueName, country, active);
				em.persist(league);
				em.close();
			}
		return true;
	}

	public boolean insertUser(String login, String pass, String name,
			String surname, int type, boolean active, long lastFail,
			long lastOk, long added) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			users user = new users("admin", "pass", "imie", "nazw", 0, false,
					0, 0, 0);
			em.persist(user);
			em.close();
		}
		return true;
	}

	public List<comments> getCommentsById(String id) {
		

		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("SELECT c FROM comments c WHERE Cm_id = ?1");
		q.setParameter(1, id);
		// q.setParameter("hashPass", hashedPass);
		List<comments> ret = q.getResultList();
		return ret;
		// return null;
	}
	
	public users logUser(String login, String pass) {


		EntityManager em = EMFService.get().createEntityManager();

		Query q = em.createQuery("SELECT u FROM users u WHERE Us_login = ?1");
		q.setParameter(1, login);
		List<users> ret = q.getResultList();
		if(ret.size()>0)
		return ret.get(0);
		return null;
	}

	public boolean deleteOldDeals() {
		long now = System.currentTimeMillis() / 1000;
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT d FROM deals d ");

		List<deals> deal = q.getResultList();
		for (deals toUpdate : deal) {
			try {
				// em.getTransaction().begin();
				if (toUpdate.getDateEnd() > now) {
					deals tmp = em.find(deals.class, toUpdate.getId());
					tmp.setActive(false);

				}
				// em.getTransaction().commit();
			} finally {
				em.close();
			}
		}
		return true;
	}

	public boolean updateStawki(float first, float draw, float second,
			String more) {
		System.err.println("upstawki");
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT u FROM bets u WHERE Bt_more = :more");
		q.setParameter("more", more);
		List<bets> be = q.getResultList();
		if (be.size() < 1) {
			System.out.println("Nie znaleziono entity dla " + more);
			System.err.println("Nie znaleziono entity dla " + more);
			return false;
		}
		System.err.println("jest");
		try {
			// em.getTransaction().begin();
			System.err.println("jest");
			bets bet = em.find(bets.class, be.get(0).getId());
			bet.setFirst(first);
			bet.setDraw(draw);
			bet.setSecond(second);
			System.err.println("Zmieniono " + be.get(0).getMore());
			System.out.println("Zmieniono " + be.get(0).getMore());
			System.out.println("Zmieniono " + be.get(0).getId());
			// em.getTransaction().commit();
		}catch(Exception ex){
			System.err.println(ex);
		}
		finally {
			em.close();
		}

		return true;
	}

	public boolean addBet(String team1, String team2, long date, String league,
			String country, float first, float draw, float second, String more,
			int own, boolean active, int sc1, int sc2) {
		// System.out.println("add bet in");
		EntityManager em = EMFService.get().createEntityManager();
		List<bets> ret = em
				.createQuery("select b from bets b WHERE Bt_more = ?1")
				.setParameter(1, more).getResultList();
		if (ret.size() == 0)

			synchronized (this) {

				// System.out.println("create bet");
				bets bet = new bets(team1, team2, date, league, country, first,
						draw, second, more, own, active, sc1, sc2);
				// System.out.println("insert bet");
				em.persist(bet);
				// System.out.println("ok");
				em.close();
				return true;
			}

		return false;
	}

	public List<news> getNews(Integer limit) {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);


		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select n from news n WHERE Nw_active = true ORDER BY Nw_date DESC limit 5");
		List<news> ret = q.getResultList();
		return ret;
	}

	public List<deals> getUserDeals(String user, Integer limit) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select d from deals d WHERE Dl_userName = ?1 ORDER BY Dl_dateAdded DESC limit 15");
		q.setParameter(1, user);
		List<deals> deals = q.getResultList();
		return deals;
	}
	
	public List<deals> getDeals(Integer limit) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select d from deals d WHERE Dl_active=true ORDER BY Dl_dateAdded DESC limit ?1");
		q.setParameter(1, limit);
		List<deals> deals = q.getResultList();
		return deals;
	}

	public List<bets> getBetsByLeague(String id) {
		long now = System.currentTimeMillis() / 1000;
		EntityManager em = EMFService.get().createEntityManager();

		Query q = em
				.createQuery("select b from bets b WHERE Bt_league=:lName");
		q.setParameter(1,id.replace("_", " "));
		List<bets> bets = q.getResultList();
		return bets;
	}
	
	public boolean deactiveBet(long keyId){
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT b FROM bets b WHERE id = :id");
		q.setParameter("id", keyId);
		List<bets> be = q.getResultList();
		if (be.size() < 1) {

			return false;
		}
		try {
			bets bet = em.find(bets.class, be.get(0).getId());
			bet.setActive(false);
		}catch(Exception ex){
			System.err.println(ex);
		}
		finally {
			em.close();
		}

		return true;
	}
	public boolean changeBet(Long keyId,float first,float draw,float second,long date) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT b FROM bets b WHERE id = :id");
		q.setParameter("id", keyId);
		List<bets> be = q.getResultList();
		if (be.size() < 1) {
			System.err.println("nie ma ");
			return false;
		}
		try {
			bets bet = em.find(bets.class, be.get(0).getId());
			bet.setFirst(first);
			bet.setDraw(draw);
			bet.setSecond(second);
			
			System.out.println(bet.getDraw());
		}catch(Exception ex){
			System.err.println(ex);
		}
		finally {
			em.close();
		}

		return true;
	}
	
	public news getNewsById(String id) {
		Long primaryKeyFromId = Long.valueOf(id);
		EntityManager em = EMFService.get().createEntityManager();
		news  bet = em.find(news.class, primaryKeyFromId);
		return bet;
	}
	
	public bets getBetToChange(String id) {
		Long primaryKeyFromId = Long.valueOf(id);
		EntityManager em = EMFService.get().createEntityManager();
		bets  bet = em.find(bets.class, primaryKeyFromId);
		return bet;
	}
	
	public List<bets> getBets(Integer limit) {
		long now = System.currentTimeMillis() / 1000;
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select b from bets b WHERE Bt_date>:now ORDER BY Bt_date DESC");
		q.setParameter("now", now);
		List<bets> bets = q.getResultList();
		System.out.println(bets);
		return bets;
	}

	public void setLastLoginTime(String login,long time) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select u from users u WHERE Us_login=?1");
		q.setParameter(1,login);
		List<users> user = q.getResultList();
		if(user.size()>0){
			users us = user.get(0);
			us.setLastLogin(time);
			em.persist(us);
		}
	}
	public bets getOneBet(long id) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select b from bets b WHERE id=?1");
		q.setParameter(1,id);
		List<bets> bets = q.getResultList();
		try {
			return bets.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public void addDeal(String userId, String userName, long date, int bet,
			int who, int ammount) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			deals deal = new deals(userId, userName, date, bet, who, ammount);
			em.persist(deal);
			em.close();
		}
	}

	public List<leagues> getLeagues() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select b from leagues b ORDER BY Lg_name ASC");
		List<leagues> leagues = q.getResultList();
		return leagues;
	}

	public List<teams> getTeams() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from teams t");
		List<teams> teams = q.getResultList();
		return teams;
	}

	public List<Todo> listTodos() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Todo m");
		List<Todo> todos = q.getResultList();
		return todos;
	}

	public void add(String userId, String summery, String description,
			String url) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Todo todo = new Todo(userId, summery, description, url);
			em.persist(todo);
			em.close();
		}
	}

	public List<Todo> getTodos(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select t from Todo t where t.author = :userId");
		q.setParameter("userId", userId);
		List<Todo> todos = q.getResultList();
		return todos;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Todo todo = em.find(Todo.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}
}
