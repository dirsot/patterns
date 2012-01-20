package pl.dirsot.bets.shared;


import java.util.ArrayList;
import java.util.List;

import pl.dirsot.bets.dao.Dao;
import pl.dirsot.bets.model.deals;

public enum Stats{
	INSTANCE;
	
	public List<List<deals>> getDealsByUser2(){
		List<List<deals>> dealsByUser = new ArrayList<List<deals>>();
		List<deals>  allActiveDeals = Dao.INSTANCE.getDeals(100);

		
		return dealsByUser;
	}
	public List<deals> getDealsByUser(){
		List<deals>  allActiveDeals = Dao.INSTANCE.getDeals(100);
		
		return allActiveDeals;
	}
	public List<deals> getActiveDeals(){
		List<deals>  allActiveDeals = Dao.INSTANCE.getDeals(100);
		return allActiveDeals;
	}
	public List<deals> getDealsByAmmount(){
		List<deals>  allActiveDeals = Dao.INSTANCE.getDeals(100);
		return allActiveDeals;
	}
	
}