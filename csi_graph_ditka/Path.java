

import java.util.LinkedList;


public class Path extends LinkedList<Vertex>{
	
	private int time;
	
	/**
	 * Constructs a new Path that is initialized with a LinkedList<Vertex> 
	 * and add start as its first element.
	 * @param start
	 * 		Vertex to insert as first element on Path
	 */
	public Path(Vertex start){
		this.addFirst(start);
		time = 0;
	}
	
	/**
	 * Constructs a new Path that extends a preexisting LinkedList<Vertex>
	 * @param curr
	 * 		LinkedList<Vertex> to extend
	 */
	public Path(Path curr){
		super(curr);
		this.time = curr.time;
	}
	
	
	/********************************
	 * 
	 * Generic function
	 * 
	 *******************************/
	
	
	
	/**
	 * Add t time in seconds
	 * @param t
	 * 		int representing time in s to add
	 */
	protected void addTime(int t){
		time += t;
	}
	
	/**
	 * Return time to travel from one path to another
	 * @return
	 * 		int
	 */
	protected int getTime(){
		return time;
	}
	
	
	/**
	 * Return incident edges of a vertex in Path
	 * @param v
	 * 		Vertex to search for
	 * @return
	 * 		LinkedList<Edge> incident edges of vertex v
	 * 		null if vertex is not in Path
	 */
	protected LinkedList<Edge> getIncidentEdges(Vertex v){
		if(this.contains(v)){
			return v.getIncidentEdges();
		}
		return null;
	}
	
	
	/**
	 * get LinkedList stored in Path object
	 * @return
	 * 		LinkedList<Vertex>	
	 */
	protected LinkedList<Vertex> getPath(){
		return this;
	}

	
	/**
	 * Print the station numbers of the LinkedList
	 */
	public String toString(){
		String res = "";
		for(Vertex v: this){
			res += v.getStation() + " ";
		}
		return res;
	}
	

}