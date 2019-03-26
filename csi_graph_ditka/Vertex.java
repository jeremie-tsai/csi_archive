
import java.util.*;

public class Vertex extends LinkedList<Edge>{
	private int station;
	private String name;
	
	public Vertex(){
		this.station = -1;
		this.name = null;
	}
	
	public Vertex(int s, String n){
		this.station = s;
		this.name = n;
	}
	
	
	/************************************************************
     * 
     * Setters, Getters and methods to manipulate LinkedList
     * 
     ***********************************************************/
	
	/**
	 * Returns the list of edges
	 * @return iterable List of edges
	 */
	public LinkedList<Edge> getIncidentEdges(){
		return this;
	}
	
	/**
	 * Returns the position/number of a station
	 * @return
	 */
	public int getStation(){
		return station;
	}
	
	
	/**
	 * Returns the name of a station
	 * @return
	 * 		String - name of the station
	 */
	public String getName(){
		return name;
	}
	
	
	/**
	 * Change the value of position/station number
	 * @param station
	 * 		int to replace value of station
	 */
	public void setStation(int station){
		this.station = station;
	}
	
	
	/**
	 * Change the value of this.name
	 * @param name
	 * 		String to replace value of name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	
	
	/**
	 * Return degree of vertex
	 * @return
	 * 		int - degree of vertex
	 */
	public int getDegree(){
		return this.size();
	}
	
	/* printing method*/
	/**
	 * Print the vertex position/station number and name of the station
	 */
	public String toString(){
		return "Vertex postion: " + this.station + ", station: " + this.name;
	}
	
}
