
import java.io.IOException;
import java.util.*;

public class PathFinder {
	
	private MetroGraph mGraph;
	private Hashtable<Integer, Integer> shortestTime;
	private Path shortest;
	
	
	
	public PathFinder(MetroGraph graph){
		mGraph = graph;
		shortestTime =  new Hashtable<Integer, Integer>(mGraph.numVertices());
	}
	
	// Methodes to handle getting vertices of a line of stations in the graph
	
	/**
	 * Return a Path that contains the ordered list of stations from one endpoint
	 * to the other to which the input belongs
	 * @param v
	 * 		Vertex of the station whose line we must compute
	 * @return
	 * 		Path object that contains the ordered list
	 */
	public Path getLine(int n1)throws IOException{
		Vertex v = mGraph.getVertex(n1);
		int numConnections = 0;
		shortestTime.clear();
		
		
		Path temp = new Path(v);
		shortestTime.put(v.getStation(), 0);
		
		for(Edge e : v.getIncidentEdges()){
			if(e.getTime() != -1){							// If edge.getTime == -1, the stations are not on the same line but at the same location
				if(numConnections == 0){								// A station can have a maximum of two stations connected to it on a line
															// Handle adding on the left side or right side till endpoint based on starting position
					addLastLine(e.getDest(), temp);
					numConnections++;
				}else if (numConnections == 1){
					addFirstLine(e.getDest(), temp);
				}
			}
		}
		return temp;
	}
	
	
	
	/**
	 * Add destination vertex of an edge to the Front of a list of vertices of a path
	 * @param v
	 * 			int representing destination vertex to add
	 * @param temp
	 * 			Path to add vertex to
	 */
	private void addFirstLine(int v, Path temp) throws IOException{
		if(shortestTime.get(v)!= null){	//Destination of edge has already been visited, no more vertices on left side
			return;
		}
		shortestTime.put(v, 0);			// else add dest to visited
		temp.addFirst(mGraph.getVertex(v));						// addFirst till endpoint is reached
		for(Edge e: mGraph.getVertex(v).getIncidentEdges()){
			if(e.getTime() != -1){
				addFirstLine(e.getDest(), temp);
			}
		}
		
	}
	
	
	/**
	 * Add destination vertex of an edge to the End of a list of vertices of a path
	 * @param v
	 * 			int representing destination vertex to add
	 * @param temp
	 * 			Path to add vertex to
	 */
	private void addLastLine(int v, Path temp)throws IOException{
		if(shortestTime.get(v)!= null){	//Destination of edge has already been visited, No more vertex on right side
			return;
		}
		shortestTime.put(v, 0);							// else add dest to visited
		temp.addLast(mGraph.getVertex(v));				// addLast to order to the right side
		for(Edge e: mGraph.getVertex(v).getIncidentEdges()){		// Keep repeating till endpoint is reached
			if(e.getTime() != -1){
				addLastLine(e.getDest(), temp);
			}
		}
		
	}
	
	
	// Method to find the shortest path from 1 station to another
	
	protected Path getShortPath(int origin, int dest) throws Exception{
		try{
			return getShortPath(origin, dest, -1);
		}
		catch (Exception e){
			throw e;
		}
	}
	
	
	protected Path getShortPath(int origin, int dest, int s3) throws Exception{
		shortestTime.clear();
		if(0 <= s3){											// -1 indicates there is no line to consider broken
			Path temp = getLine(s3);
			for(Vertex v: temp){
				shortestTime.put(v.getStation(), 0);
			}
		}

		
		shortest = search(origin, dest);
		if(shortest == null){
			throw new Exception("Invalid inputs, station: " +dest +" is not in this graph" );
		}

		return shortest;
	}
	
	
	/**
	 * BFS that finds the shortest simple path from 1 vertex to  another
	 * Does not delete p, accumulates all valid paths in p
	 * @param p
	 * 		LinkedList<Path> with 1 element, the starting point/path
	 * @param dest
	 * 		int representing the destination vertex
	 * @return
	 * @throws Exception
	 */
	private Path search(int origin, int dest) throws IOException{
		LinkedList<Path> p = new LinkedList<Path>();
		Path start = new Path(mGraph.getVertex(origin));
		shortest = null;
		shortestTime.put(origin, 0);
		p.add(start);
		
		while (!p.isEmpty()){
			Path curr = p.getFirst();							// curr = First path in list
			Vertex v = curr.getLast();							// Every path add it's latest stop to the end of the list, v is the last vertex of a path in the LinkedList of paths
			//System.out.println(edges);
			for(Edge e : v.getIncidentEdges()){					// Expand path to new edge if conditions are met
				if(v.getStation() == dest){							// path has reached destination, set shortest to this path
					if(shortest == null){
						shortest = curr;
					}else if(shortest.getTime() > curr.getTime()){
						shortest = curr;
					}
				}
				int eTime = e.getTime();
				if(eTime < 0){
					eTime =90;
				}
				int totalTime = curr.getTime() + eTime;				// Time of path so far + time if they take edge
				if(shortestTime.get(e.getDest())== null){				// case 1, vertex/station has not been visited
					//System.out.println("vis");
					shortestTime.put(e.getDest(), totalTime);			// create new entry in hastable, shortest time to get to vertex/station v
					Path expand = new Path(curr);							// create new path to vertex/station
					expand.addLast(mGraph.getVertex(e.getDest()));
					expand.addTime(eTime);
					p.addLast(expand);										// add new path to the list of viable paths
				} else {
					//System.out.println(totalTime + "loop");
					if (shortestTime.get(e.getDest()) > totalTime){				// case 2, Found a shorter time to get to vertex/station v
						shortestTime.replace(e.getDest(), totalTime);		// update value in hastable of shortest time to a node
						Path shorter = new Path(curr);							// create new path to vertex/station
						shorter.addLast(mGraph.getVertex(e.getDest()));
						shorter.addTime(eTime);
						p.addLast(shorter); 									// add new path to the list of viable paths
					}
				}
																		// case 3, already have a shorter path to this vertex/station
																		// Do nothing, do not create new path to this node
			}
			p.removeFirst();								//Remover redundant path that has been expanded
		}
		return shortest;
	}	
}
