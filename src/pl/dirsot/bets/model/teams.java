package pl.dirsot.bets.model;

import javax.jdo.annotations.Unique;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class teams {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Unique
	private String Tm_name;
	//private String TM_league;
	//private String TM_country;
	private boolean Tm_active;

	public teams(String name, boolean active) {
		this.Tm_name = name;
		this.Tm_active = active;
	}

	public Long getId() {
		return id;
	}

	public String getTeam() {
		return this.Tm_name;
	}

	public void setTeam(String Tm_name) {
		this.Tm_name = Tm_name;
	}
	
	public boolean getActive() {
		return this.Tm_active;
	}

	public void setActive(boolean Tm_active) {
		this.Tm_active = Tm_active;
	}
	
}
