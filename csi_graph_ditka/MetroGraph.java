
import java.io.IOException;
import java.util.*;

public class MetroGraph {
	
	
	private ArrayList<Vertex> nodes;
	
	
	public MetroGraph(){
		nodes = new ArrayList<Vertex>();
		for(Vertex v: nodes){
			v = null;
		}
	}
	
	public MetroGraph(int iniCap){
		nodes = new ArrayList<Vertex>(iniCap);
	}
	
	
	
	/************************************************************
     * 
     * Generic Methods
     * 
     ***********************************************************/

	public int numVertices(){
		return nodes.size();
	}
	
	public int numEdges(){
		int size = 0;
		for(Vertex v: nodes){
			size += v.getDegree();
		}
		return size;
	}
	
	/************************************************************
     * 
     * Setters, Getters and methods to manipulate LinkedList
     * 
     ***********************************************************/
	
	
	/**
	 * Return number of Vertices in grapk
	 * @return
	 * 		int representing number of vertices
	 */
	
	/* Handling vertices */
	
	/**
	 * Return list of iterable vertices
	 * @return
	 * 		LinkedList<Vertex>
	 */
	public LinkedList<Vertex> vertices(){
		LinkedList<Vertex> vertices = new LinkedList<Vertex>();
		for(Vertex v: nodes){
			vertices.add(v);
		}
		return vertices;
	}
	
	
	/**
	 * Return vertex at a certain position
	 * @param pos
	 * 			int of the position of the vertex in graph
	 * @return
	 * 			vertex
	 */
	public Vertex getVertex(int pos) throws IOException{
		try{
			return nodes.get(pos);
		}catch (Exception e){
			throw new IOException("vertex: " + pos + " is not a valid staion number or position for a vertex");
		}
	}
	
	
	/**
	 * Insert vertex v into graph
	 * @param v
	 * 			vertex to insert
	 * @throws Exception if vertex already exists in this position
	 */
	public void insertVertex(Vertex v) throws Exception{
		int pos = v.getStation();
		nodes.add(pos,v);
	}
	
	
	/**
	 * Remove vertex from graph
	 * @param pos
	 * 			vertex to remove
	 */
	public void removeVertex(int pos){
		nodes.remove(pos);
	}
	
	
	/**
	 * Go through array of vertex looking for v
	 * if v is present remove it
	 * @param v
	 * 		int of position to remove
	 * @return
	 * 		True if v is in graph
	 * 		False if v is not in graph
	 */
	public boolean removeVertex(Vertex v){
		return nodes.remove(v);
	}
	
	
	/* Handling edges */
	
	
	/**
	 * Return iterable list of edges
	 * @return
	 * 		LinkedList<Edge>
	 */
	public LinkedList<Edge> edges(){
		LinkedList<Edge> edges = new LinkedList<Edge>();
		for(Vertex v: nodes){
			for(Edge e: v.getIncidentEdges()){
				edges.add(e);
			}
		}
		return edges;
	}
	
	
	/**
	 * Return incident edges of a vertex
	 * @param pos
	 * 			int representing position of vertex in array
	 * @return
	 * 			LinkedList<Edge>
	 */
	public LinkedList<Edge> getIncidentEdges(int pos){
		return nodes.get(pos).getIncidentEdges();
	}
	
	
	/**
	 * Insert edge into graph
	 * @param e
	 * 			Edge to insert
	 * @throws Exception
	 * 			If Origin vertex does not exist
	 */
	public void insertEdge(Edge e) throws Exception{
		int pos = e.getOrigin();
		if (nodes.get(pos) != null){
			nodes.get(pos).add(e);
			return;
		}
		throw new Exception("The origin vertex of this edge does not exist");
	}
	
	
	/**
	 * Remove Edge from graph
	 * @param e
	 * 			Edge to remove
	 */
	public void removeEdge(Edge e){
		nodes.get(e.getOrigin()).remove(e);
	}
	
	
	
	/**
	 * Print Graph on console
	 */
	public void print(){
		System.out.println("Adjacency list style graph possessing nodes :");
		for(Vertex v: nodes){		//For every vertex
				if(v !=null){
				System.out.println(v);				//Print Vertex
				for(Edge e: v.getIncidentEdges()){	//Print edges of vertex
					System.out.println(e);
				}
			}
		}
	}
}
