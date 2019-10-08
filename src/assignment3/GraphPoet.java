/* EE422C Assignment #3 submission by 
 * <Rithvik Baddam>
 * <rrb2442>
 */

package assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphPoet {


    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */

    public GraphPoet(File corpus) throws IOException {

        /* Read in the File and place into graph here */
    	ArrayList <Vertex<String> > graph = new ArrayList<>();
    	ArrayList <String> graphValues = new ArrayList<>(); // to keep track of last vertex
    	// might be unnecessary
    	
    	BufferedReader br = new BufferedReader(new FileReader(corpus)); 
    	  
    	String st;
    	String lowercase;
    	
    	while ((st = br.readLine()) != null) {
    		lowercase = st.toLowerCase(); // convert all words to lowercase
    	    String[] line = lowercase.split(" "); // array of words for one line
    	    String lastVertex;
    	    
    	    for(String word : line) {
    	    	if(graphValues.contains(word) == false) { // new vertex to add
    	    		graphValues.add(word);
    	    		graph.add(new Vertex<String>(word));
    	    		
    	    		// connect vertex to previous one if it exists
    	    		if(graph.size() > 1) {
    	    			lastVertex = graphValues.get(graphValues.size()-2); // get previous vertex from graphvalues
    	    			connectVertex(graph, lastVertex, word); // add new vertex to the right previous vertex from graph
    	    		}
    	    	}
    	    	else { // if graph contains this word dont add it to vertex again but connect vertices with previous edge
    	    		graphValues.add(word); // add it to list of words though to make sure new vertices are added to right vexter
    	    		lastVertex = graphValues.get(graphValues.size()-2);
    	    		connectVertex(graph, lastVertex, word);
    	    	}
    	    }
    	} 
    	
    	printGraph(graph);
    	
    }
    
    // connects vertices
    public void connectVertex(ArrayList <Vertex<String>> graph, String lastVertex, String word) {
    	for(int i = 0; i < graph.size(); i++) {
			if(graph.get(i).getName().equals(lastVertex)) {
				graph.get(i).addVertex(word);
			}
		}
    }
    
    // prints graph
    public void printGraph(ArrayList <Vertex<String>> graph) {
    	for(int i = 0; i < graph.size(); i++) {
    		System.out.println(graph.get(i).getName()+": "+graph.get(i).getMap());
    	}
    }

    /**
     * Generate a poem.
     *
     * @param input File from which to create the poem
     * @return poem (as described above)
     */
    public String poem(File input) throws IOException {

        /* Read in input and use graph to complete poem */
        String poem = "";
        return poem;
    }

}
