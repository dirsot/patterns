package pl.dirsot.bets.model;

import javax.jdo.annotations.Index;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Index(members={"Bt_team1","Bt_team2","Bt_date"}, unique="true")
public class bets {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// private int Us_id;
	private String Bt_team1;
	private String Bt_team2;
	private String Bt_league;
	private String Bt_country;
	private long Bt_date;

	private float first;
	private float draw;
	private float second;
	private String Bt_more;
	private int Bt_sc_fi;
	private int Bt_sc_se;
	private int Bt_own;
	private boolean Bt_active;

	public bets(String team1, String team2,long date ,String league, String country,
			float first,float draw,float second, String more, int own, boolean active,
			Integer sc1,Integer sc2) {
		this.Bt_team1 = team1;
		this.Bt_team2 = team2;
		this.Bt_date = date;
		this.Bt_country = country;
		this.Bt_league = league;
		this.first = first;
		this.draw = draw;
		this.second = second;
		this.Bt_sc_fi = sc1;
		this.Bt_sc_se = sc2;
		this.Bt_own = own;
		this.Bt_active = active;
		this.Bt_more = more;
	}


	public Long getId() {
		return id;
	}

	public String getTeam1() {
		return this.Bt_team1;
	}

	public void setTeam1(String Bt_team1) {
		this.Bt_team1 = Bt_team1;
	}

	public String getTeam2() {
		return this.Bt_team2;
	}

	public void setTeam2(String team2) {
		this.Bt_team2 = team2;
	}

	public String getLeague() {
		return this.Bt_league;
	}

	public void setLeague(String league) {
		this.Bt_league = league;
	}

	public long getDate() {
		return this.Bt_date;
	}
	
	public String getHumanDate() {
		return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date (Bt_date*1000));  
	}
	
	public void setDate(long date) {
		this.Bt_date = date;
	}
	public String getCountry() {
		return this.Bt_country;
	}

	public void setCountry(String country) {
		this.Bt_country = country;
	}
	
	public float getPositiveFirst() {
		if(this.first>0)	
			return this.first;
		return 1;
	}

	public float getFirst() {
		return this.first;
	}

	public void setFirst(float first) {
		this.first = first;
	}

	public float getPositiveDraw() {
		if(this.draw>0)	
			return this.draw;
		return 1;
	}
	
	public float getDraw() {
		return this.draw;
	}

	public void setDraw(float draw) {
		this.draw = draw;
	}
	
	public float getPositiveSecond() {
		if(this.second>0)
			return this.second;
		return 1;
	}

	public float getSecond() {
		return this.second;
	}

	public void setSecond(float second) {
		this.second = second;
	}

	public int getScore1() {
		return this.Bt_sc_fi;
	}

	public void setScore1(int Bt_sc_fi) {
		this.Bt_sc_fi = Bt_sc_fi;
	}

	public int getScore2() {
		return this.Bt_sc_se;
	}

	public void setScore2(int Bt_sc_se) {
		this.Bt_sc_se = Bt_sc_se;
	}

	public int getOwn() {
		return this.Bt_own;
	}

	public void setOwn(int Bt_own) {
		this.Bt_own = Bt_own;
	}

	public boolean getActive() {
		return this.Bt_active;
	}

	public void setActive(boolean Bt_active) {
		this.Bt_active = Bt_active;
	}

	public String getMore() {
		return this.Bt_more;
	}

	public void setMore(String Bt_more) {
		this.Bt_more = Bt_more;
	}

}
