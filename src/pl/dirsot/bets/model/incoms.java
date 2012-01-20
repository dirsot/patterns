package pl.dirsot.bets.model;

import javax.jdo.annotations.Index;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

@Entity
public class incoms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// private int Us_id;
	private String In_userName;
	private String In_userID;
	private float In_quantity;
	
	public incoms(){
		this.In_quantity=10;
	}
	public incoms(String name, String id,float ammount) {
		this.In_quantity = ammount+10;
		this.In_userID = id;
		this.In_userName = name;
	}


	public Long getId() {
		return id;
	}

	public float getQuantity() {
		return this.In_quantity;
	}

	public void setQuantity(float ammount) {
		this.In_quantity = ammount;
	}
	public void addQuantity(float ammount){
		this.In_quantity +=ammount;
	}
	
	public String getName(){
		return this.In_userName;
	}
	public String getUserId(){
		return this.In_userName;
	}

}
