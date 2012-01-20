package pl.dirsot.bets.model;

import javax.jdo.annotations.Index;
import javax.jdo.annotations.Unique;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("unused")
@Entity
public class leagues {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private int Us_id;
	@Unique
	private String Lg_name;
	private String Lg_country;
	private boolean Tm_active;

	public leagues(String name, String country, boolean active) {
		this.Lg_name = name;
		this.Lg_country = country;
		this.Tm_active = active;
	}

	public Long getId() {
		return id;
	}

	public String getLeague() {
		return this.Lg_name;
	}

	public void setLeague(String Lg_name) {
		this.Lg_name = Lg_name;
	}
	
	public String getCountry() {
		return this.Lg_country;
	}

	public void setCountry(String Lg_country) {
		this.Lg_country = Lg_country;
	}
	
	public boolean getActive() {
		return this.Tm_active;
	}

	public void setActive(boolean Tm_active) {
		this.Tm_active = Tm_active;
	}
	
}
