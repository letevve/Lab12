package it.polito.tdp.rivers.model;

import java.time.LocalDate;

public class Event implements Comparable<Event> {
	
	public enum EventType { INGRESSO , USCITA } ;
	
	private LocalDate date;
	private double f;
	private EventType type;
	
	

	public Event(LocalDate date, double f, EventType type) {
		super();
		this.date = date;
		this.f = f;
		this.type = type;
	}

	

	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}



	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}



	/**
	 * @return the f
	 */
	public double getF() {
		return f;
	}



	/**
	 * @param f the f to set
	 */
	public void setF(double f) {
		this.f = f;
	}



	@Override
	public int compareTo(Event other) {
		// TODO Auto-generated method stub
		if(this.date.isAfter(other.getDate()))
			return 1;
		else if(this.date.isBefore(other.getDate()))
			return -1;
		else 
			return 0; 
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Event [date=%s, f=%s, type=%s]", date, f, type);
	}
	
	

}
