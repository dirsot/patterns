package pl.dirsot.bets.model;

import javax.jdo.annotations.Index;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Index(members={"Bt_team1","Bt_team2","Bt_date"}, unique="true")
public class deals {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// private int Us_id;
	private String Dl_userId;
	private String Dl_userName;
	private Integer Dl_bet;
	private long Dl_dateAdded;
	private long Dl_dateEnd;
	private Integer Dl_who;
	private Integer Dl_ammount;
	private boolean Dl_active;


	public deals(String userId, String userName,long date ,int bet, int who,
			int ammount) {
		this.Dl_userId = userId;
		this.Dl_userName = userName;
		this.Dl_bet = bet;
		this.Dl_dateEnd = date + 256000;
		this.Dl_dateAdded = date;
		this.Dl_who = who;
		this.Dl_ammount = ammount;
		this.Dl_active = true;
	}


	public Long getId() {
		return id;
	}

	public boolean getActive() {
		return this.Dl_active;
	}
	
	public void setActive(boolean Dl_active) {
		this.Dl_active = Dl_active;
	}

	public String getUserName() {
		return this.Dl_userName;
	}

	public void setUserName(String Dl_userName) {
		this.Dl_userName = Dl_userName;
	}

	public String getUserId() {
		return this.Dl_userId;
	}

	public void setUserId(String Dl_userId) {
		this.Dl_userId = Dl_userId;
	}

	public Integer getBet() {
		return this.Dl_bet;
	}

	public void setBet(Integer Dl_bet) {
		this.Dl_bet = Dl_bet;
	}

	public long getDateEnd() {
		return this.Dl_dateEnd;
	}
	
	public void setDateEnd(long Dl_dateEnd) {
		this.Dl_dateEnd = Dl_dateEnd;
	}

	public long getDateAdded() {
		return this.Dl_dateAdded;
	}
	
	public void setDateAdded(long Dl_dateAdded) {
		this.Dl_dateAdded = Dl_dateAdded;
	}
	
	public String getHumanDate() {
		return new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm").format(new java.util.Date (Dl_dateEnd*1000));  
	}
	
	public int getWho() {
		return this.Dl_who;
	}

	public void setWho(int Dl_who) {
		this.Dl_who = Dl_who;
	}

	public Integer getAmmount() {
		return this.Dl_ammount;
	}

	public void setAmmount(int Dl_ammount) {
		this.Dl_ammount = Dl_ammount;
	}
}
