package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	RiversDAO rd = new RiversDAO();
	private List<River> listaRiver;

	public List<River> getRivers() {
		// TODO Auto-generated method stub
		if(this.listaRiver==null){
			this.listaRiver= rd.getAllRivers();
		}
		else{
		}
		//System.out.println("Dal model: " + listaRiver.toString());
		return listaRiver;
	}
	
	public List<String> getCampi(River river){
		List<String> ris = new LinkedList<String>();
		List<Flow> flows = rd.getSingleFlow(river);
		
		LocalDate d2 =  LocalDate.now();
		LocalDate d1 = LocalDate.of(1900, Month.JANUARY, 01);
		int misure = flows.size();
		double media = 0.0;
		double somma = 0.0;
		
		for(Flow f : flows){
			if(f.getDay().isAfter(d1)){
				d1=f.getDay();
			}
			somma += f.getFlow();
		}
		
		media = somma/misure;
		
		for(Flow f : flows){
			if(f.getDay().isBefore(d2)){
				d2=f.getDay();
			}
		}
		
		ris.add(d2.toString());
		ris.add(d1.toString());
		ris.add(""+misure);
		ris.add(""+media);
		
		return ris;
	}

	public List<Flow> getFlows(River river) {
		// TODO Auto-generated method stub
		return rd.getSingleFlow(river);
	}

}
