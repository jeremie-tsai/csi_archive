

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParisMetro {
	
	private static MetroGraph mGraph;
	private static PathFinder pf;
	private int step;		// Represents the 3 stages of reading the metro file
									// stage 0 = expected 
	private int expNumVertices;
	private int expNumEdges;
	
	/**
	 * Default constructor of ParisMetro, creates graph from DEFAULT_GRAPH
	 * @throws Exception
	 * @throws IOException
	 */
	public ParisMetro()throws Exception, IOException{
		step = 0;
		readMetro("metro.txt");
		pf = new PathFinder(getGraph());
	}
	
	
	public ParisMetro(String fileName) throws Exception, IOException {
		step = 0;
		readMetro(fileName);
	}
	
	
	/**
	 * Reads data from a txt file to create a graph
	 * @param fileName
	 * 			String filepath
	 * @throws Exception
	 * @throws IOException
	 */
	private void readMetro(String fileName) throws Exception, IOException{
		
		String filePath = System.getProperty("user.dir") + "/" + fileName;
		BufferedReader metroFile =  new BufferedReader( new FileReader(filePath));
		
		String line;
		while((line = metroFile.readLine())!= null){
			StringTokenizer st = new StringTokenizer(line);
			
			if (step == 0){							//case 1: Read expected number of edges + vertices, instantiate MetroGraph 
				if (st.countTokens() == 2){
					expNumVertices = Integer.parseInt(st.nextToken());
					expNumEdges = Integer.parseInt(st.nextToken());
					mGraph = new MetroGraph(expNumVertices);				//Create skeleton of the graph
					step = 1;												//Change to case 2
				} else {
					throw new IOException("Incorrect input at line: " + line);
				}
			}
			//case 2: Read Station number + name to create vertices
			else if (step == 1){								// Use != 1 as the condition since the name of station in the textfile add StringTkenizer tokens
				if (st.countTokens() != 1){						// Vertex are represented by a number followed by the name of the station
					int pos = Integer.parseInt(st.nextToken());
					String name = "";
					while(st.hasMoreTokens()){					// st separates the string in substrings if they have a space in their name
																// while loop reassembles the string of the name if it was split
						name += (st.nextToken() + " ");
					}
					Vertex temp = new Vertex(pos, name);
					mGraph.insertVertex(temp);				// insert the vertex into the graph
				} else {										// handle case st.countTokers == 1, indicates line = "$\n"
					step =2 ;
				}
			}
			else if (step == 2){						//case 3: Create Edges for MetroGraph
				if(st.countTokens() == 3){
					int origin = Integer.parseInt(st.nextToken());
					int dest = Integer.parseInt(st.nextToken());
					int time = Integer.parseInt(st.nextToken());
					
					Edge temp = new Edge(origin, dest, time);
					mGraph.insertEdge(temp);						// insert edge into graph
				} else{
					throw new IOException("Incorrect input at line: " + line);
				}
			}
		}
	}
	
	
	
	
	/*Generic Methods */
	
	public MetroGraph getGraph(){
		return mGraph;
	}
	
	public void printGraph(){
		mGraph.print();
	}
	
	
	/* static methods */
	public static Path getLine(int n1) throws IOException{
		try{
			return pf.getLine(n1);
		}catch (IOException e){
			throw e;
		}
	}
	
	public static Path getShortPath(int n1,int n2) throws Exception{
		try{
			return pf.getShortPath(n1, n2);
		}catch (Exception e){
			throw e;
		}
	}
	
	public static Path getShortPath(int n1,int n2, int n3) throws Exception{
		try{
			return pf.getShortPath(n1, n2, n3);
		}catch (Exception e){
			throw e;
		}
	}

	
	
	public static void main(String args[]) throws IOException, Exception{

		ParisMetro metro = new ParisMetro();
		if(args.length<4 && args.length>0){
			try{
				if(args.length == 1){
					Path l1 = getLine(Integer.parseInt(args[0]));
					System.out.println("Inputs:");
					System.out.println("\tN1 = " + args[0]);
					System.out.println("Outputs:");
					System.out.println("\tLine: " + l1);
				}
				else if (args.length == 2){
					int n1 = Integer.parseInt(args[0]);
					int n2 = Integer.parseInt(args[1]);
					Path p1 = getShortPath(n1, n2);
					System.out.println("Inputs:");
					System.out.println("\tN1 = " + n1);
					System.out.println("\tN2 = " + n2);
					System.out.println("Outputs:");
					System.out.println("\tTime = " + p1.getTime());
					System.out.println("\tPath: " + p1);
				}
				else if (args.length == 3){
					int n1 = Integer.parseInt(args[0]);
					int n2 = Integer.parseInt(args[1]);
					int n3 = Integer.parseInt(args[2]);
					Path p1 = getShortPath(n1, n2);
					Path p2 = getShortPath(n1, n2, n3);
					System.out.println("Inputs :");
					System.out.println("\tN1 = " + n1);
					System.out.println("\tN2 = " + n2);
					System.out.println("Outputs:");
					System.out.println("\tTime = " + p1.getTime());
					System.out.println("\tunrestricted Path: " + p1);
					System.out.println("Inputs:");
					System.out.println("\tN1 = " + n1);
					System.out.println("\tN2 = " + n2);
					System.out.println("\tN3 = " + n3);
					System.out.println("Outputs:");
					System.out.println("\tTime = " + p2.getTime());
					System.out.println("\tRestricted Path: " + p2);
				}
			} catch (Exception e){
				throw e;
			}
		}else {
			throw new IOException("invalid input for this class please try to enter arguments in these formats:\n"
					+ "N1, N1 N2 or N1 N2 N3 where N1,N2,N3 are numbers");
		}
	}

}
