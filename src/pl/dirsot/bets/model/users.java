package pl.dirsot.bets.model;

import javax.jdo.annotations.Index;
import javax.jdo.annotations.Unique;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Index(members={"Us_login"}, unique="true")
public class users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//private int Us_id;
	@Unique private String Us_login;
	private String Us_pass;
	private String Us_name;
	private String Us_surname;

	private long Us_created;
	private long Us_lastLogin;
	private long Us_lastFail;
	private int Us_type;
	private boolean Us_active;

	public users(String login, String name, String surname,
			int type,boolean active, int created,int lastLogin,int lastFail ) {
		this.Us_login = login;
		this.Us_name = name;
		this.Us_surname = surname;
		this.Us_created = created;
		this.Us_type = type;
		this.Us_active = active;
	}
	public users(String login,String pass, String name, String surname,
			int type,boolean active, long created,long lastLogin,long lastFail ) {
		this.Us_pass = pass;
		this.Us_login = login;
		this.Us_name = name;
		this.Us_surname = surname;
		this.Us_created = created;
		this.Us_type = type;
		this.Us_active = active;
	}
	
	public users(String login, String name, String surname,
			String pass, int type,boolean active) {
		this.Us_login = login;
		this.Us_name = name;
		this.Us_surname = surname;
		this.Us_pass = pass;
		this.Us_type = type;
		this.Us_active = active;
	
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return this.Us_login;
	}

	public void setLogin(String login) {
		this.Us_login = login;
	}

	public boolean validPass(String pass){
		return pass.equals(this.Us_pass);
	}
	
	public void setPass(String pass) {
		this.Us_pass = pass;
	}
	
	public String getName() {
		return this.Us_name;
	}

	public void setName(String name) {
		this.Us_name = name;
	}
	
	public String getSurname() {
		return this.Us_surname;
	}

	public void setSurname(String surname) {
		this.Us_surname = surname;
	}
	
	public long getCreated() {
		return this.Us_created;
	}

	public void setCreated(long created) {
		this.Us_created = created;
	}
	
	public long getLastLogin() {
		return this.Us_lastLogin;
	}

	public void setLastLogin(long lastLogin) {
		this.Us_lastLogin = lastLogin;
	}
	
	public long getLastFail() {
		return this.Us_lastFail;
	}

	public void setLastFail(long lastFail) {
		this.Us_lastFail = lastFail;
	}
	
	public int getType() {
		return this.Us_type;
	}

	public void setType(int type) {
		this.Us_type = type;
	}
	
	public boolean getActive() {
		return this.Us_active;
	}

	public void setActive(boolean active) {
		this.Us_active = active;
	}
}
