package pl.dirsot.bets.model;

import javax.jdo.annotations.Index;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Index(members={"Bt_team1","Bt_team2","Bt_date"}, unique="true")
public class news {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String Nw_title;
	private String Nw_content;
	private String Nw_userId;
	private String Nw_userName;
	private long Nw_date;
	private long Nw_endDate;

	private int Nw_type;
	private String Nw_image;
	private boolean Nw_active;

	public news(String title, String content,long date ,long endDate,String userId, String userName,
			int type,boolean active,String image) {
		this.Nw_title = title;
		this.Nw_content = content;
		this.Nw_userId = userId;
		this.Nw_userName = userName;
		this.Nw_date = date;
		this.Nw_endDate = date;
		this.Nw_type = type;
		this.Nw_image = image;
		this.Nw_active = active;

	}


	public Long getId() {
		return id;
	}

	public String getTitle() {
		return this.Nw_title;
	}

	public void setTtitle(String Nw_title) {
		this.Nw_title = Nw_title;
	}

	public String getContent() {
		return this.Nw_content;
	}

	public void setContent(String Nw_content) {
		this.Nw_content = Nw_content;
	}

	public String getUserId() {
		return this.Nw_userId;
	}

	public void setUserId(String Nw_userId) {
		this.Nw_userId = Nw_userId;
	}

	public String getUserName() {
		return this.Nw_userName;
	}
	
	public void setUserName(String Nw_userName) {
		this.Nw_userName = Nw_userName;
	}
	
	public long getDate() {
		return this.Nw_date;
	}
	public long getEndDate() {
		return this.Nw_endDate;
	}

	public String getHumanDate() {
		return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date (Nw_date*1000));  
	}
	
	public void setDate(long Nw_date) {
		this.Nw_date = Nw_date;
	}

	public int getType() {
		return this.Nw_type;
	}

	public void setType(int Nw_type) {
		this.Nw_type = Nw_type;
	}

	public boolean getActive() {
		return this.Nw_active;
	}

	public void setActive(boolean Nw_active) {
		this.Nw_active = Nw_active;
	}

	public String getImage() {
		return this.Nw_image;
	}

	public void setImage(String Nw_image) {
		this.Nw_image = Nw_image;
	}

}
