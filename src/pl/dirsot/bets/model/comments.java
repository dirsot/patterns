package pl.dirsot.bets.model;

import javax.jdo.annotations.Index;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Index(members={"Bt_team1","Bt_team2","Bt_date"}, unique="true")
public class comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// private int Us_id;
	private String Cm_user;
	private String Cm_content;
	@SuppressWarnings("unused")
	private String Cm_id;
	private long Cm_time;


	public comments(String user, String content,String id,long date ) {
		this.Cm_user = user;
		this.Cm_content = content;
		this.Cm_id = id;
		this.Cm_time = date;

	}


	public Long getId() {
		return id;
	}

	public String getUser() {
		return this.Cm_user;
	}
	public String getContent() {
		return this.Cm_content;
	}
	public long getTime() {
		return this.Cm_time;
	}





}
