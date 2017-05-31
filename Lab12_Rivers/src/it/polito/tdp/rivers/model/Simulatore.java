package it.polito.tdp.rivers.model;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;

public class Simulatore {
	
	PriorityQueue<Event> queue;
	private double Q;
	private double coeffMin = 0.8 ;
	
	
	public Simulatore() {
		super();
		this.queue = new PriorityQueue<Event>();
	}

	public void addEvent(Event event){
		queue.add(event);
	}

	public String run(double media, double K) {
		
		double capienzaMax = K*media*3600*24*30 ;
		double C = capienzaMax/2 ;
		double fOutMin = coeffMin*media ;
		double fMaggiorato ;
		double fUscita ;
		int gInsoddisfatti = 0;
		List<Double> occupazioni = new LinkedList<Double>();
		String ris = "" ;
		double somma = 0.0 ;
		double occMedia = 0.0 ;
		
		while(!queue.isEmpty()){
			
			Event e = queue.poll();
			
			switch(e.getType()) {
			
			case INGRESSO: 
				
				C += e.getF() * 3600 * 24;
				if(C>capienzaMax){
					fMaggiorato = (C-capienzaMax)/(3600*24) ;
				}
				else {
					fMaggiorato = 0.0 ;
				}
				
				if(Math.random() < 0.95){
					fUscita = fOutMin + fMaggiorato;
				}
				else {
					fUscita = fOutMin*10 + fMaggiorato;
				}
				
				Event e1 = new Event(e.getDate(), fUscita , EventType.USCITA);
				queue.add(e1);
				
				break;
				
				
			case USCITA:
				
				C -= e.getF() * 3600 * 24 ;
				
				if(C<0){
					C = 0 ;
					gInsoddisfatti ++ ;
				}
				
				occupazioni.add(C);
				
				break;
			}
			
		}
		
		for(double d : occupazioni){
			//calcola l'occupazione media
			somma += d ;
		}
		occMedia = somma / occupazioni.size();
		
		String occ = String.format("%.2f", occMedia/(10E6));
		
		ris = "Giorni senza erogazione minima: " + gInsoddisfatti + "\n" +
				     "Occupazione media del bacino: circa " + occ + " milioni di m^3.\n" ;
		return ris ;
	}

	

}
