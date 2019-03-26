

public class Edge{
	
	private int origin;
	private int dest;
	private int time;
	
	public Edge(int station, int dest, int time){
		this.origin = station;
		this.dest = dest;
		this.time = time;
	}
	
	public Edge(){
		this.origin = -1;
		this.dest = -1;
		this.time = -1;
	}
	
	/************************************************************
     * 
     * Setters and Getters
     * 
     ***********************************************************/
	
	protected void setOrigin(int station){
		this.origin = station;
	}
	
	protected void setDest(int dest){
		this.dest = dest;
	}
	
	protected void setTime(int time){
		this.time = time;
	}
	
	public int getOrigin(){
		return origin;
	}
	
	public int getDest(){
		return dest;
	}
	
	public int getTime(){
		return time;
		
	}
	
	/* printing method */
	public String toString(){
		return "Edge " + this.origin + " to " + this.dest + " in " +this.time + "s.";
	}

}
