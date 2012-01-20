package pl.dirsot.bets.utils;

import pl.dirsot.bets.dao.Dao;
import pl.dirsot.bets.model.*;

import java.util.ArrayList;
import java.util.List;

public enum search{
	INSTANCE;
	
	public List<teams> searchTeams(String name){

		List<teams> teamsArray = Dao.INSTANCE.getTeams();
		List<teams> ret =new ArrayList<teams>();
 		for(teams team:teamsArray){
			String tmpName = team.getTeam().toLowerCase();
			if(tmpName.contains(name.toLowerCase()))
				ret.add(team);
		}
		return ret;
	}
	
	public List<leagues> searchLeagues(String name){
		
		List<leagues> leagues = Dao.INSTANCE.getLeagues();
		List<leagues> ret = new ArrayList<leagues>();
 		for(leagues league:leagues){
			String tmpName = league.getLeague();
			if(tmpName.toString().contains(name))
				ret.add(league);
		}
		return ret;
	}
	
	public List<bets> getBets(String name,String type){
		List<bets> result = Dao.INSTANCE.getBets(1000);
		if(type==null)
			return null;
		if(type.equalsIgnoreCase("l")){
			System.out.println("l");
			List<bets> ret = new ArrayList<bets>();
			for(bets bet:result){
				String tmp = bet.getLeague();
				System.out.println(tmp);
				System.out.println(name);
				if(tmp.equalsIgnoreCase(name.replace("_"," ")))
					ret.add(bet);
			}
			return ret;
		}else{
			List<bets> ret = new ArrayList<bets>();
			for(bets bet:result){
				
				String tmp = bet.getTeam1();
				String tmp2 = bet.getTeam2();

				if(tmp.equalsIgnoreCase(name.replace("_"," ")) || tmp2.equalsIgnoreCase(name.replace("_"," "))){
					ret.add(bet);
					}
			}	
			return ret;
		}
	}	
}