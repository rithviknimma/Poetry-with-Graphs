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

	ArrayList <Vertex<String> > graph = new ArrayList<>();
	ArrayList <String> graphValues = new ArrayList<>(); // to keep track of last vertex
	
    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */

    public GraphPoet(File corpus) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(corpus)); 
    	  
    	String st;
    	String lowercase;
    	
    	while ((st = br.readLine()) != null) {
    		lowercase = st.toLowerCase(); // convert all words to lowercase
    	    String[] line = lowercase.split(" "); // array of words for one line
    	    String lastVertex;
    	    boolean sequenceHasAppeared; // boolean to check if sequence has already appeard; this is for edge weight 
    	    
    	    for(String newVertex : line) {
    	    	if(graphValues.contains(newVertex) == false) { // new vertex to add
    	    		graphValues.add(newVertex);
    	    		graph.add(new Vertex<String>(newVertex));
    	    		sequenceHasAppeared = false;
    	    		
    	    		// connect vertex to previous one if previous one exists
    	    		if(graph.size() > 1) {
    	    			lastVertex = graphValues.get(graphValues.size()-2); // get previous vertex from graphValues
    	    			connectVertex(lastVertex, newVertex, sequenceHasAppeared); // add new vertex to the previous vertex from graph
    	    		}
    	    	}
    	    	else { // graph already contains this word
    	    		
    	    		lastVertex = graphValues.get(graphValues.size()-1);
    	    		sequenceHasAppeared = sequenceHasAppeared(lastVertex, newVertex);
    	    		graphValues.add(newVertex); // add it to list of words to keep track of previous vertex
    	    		connectVertex(lastVertex, newVertex, sequenceHasAppeared);
    	    	}
    	    }
    	}
    }
    
    
    // check if two word sequence has appeared before, returns boolean
    public boolean sequenceHasAppeared(String lastVertex, String newVertex) {
    	for(int i = 0; i < graphValues.size() - 1; i++) {
    		if(graphValues.get(i).equals(lastVertex) && graphValues.get(i+1).equals(newVertex)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // connects vertices or updates weight between vertices if sequence has appeared before
    public void connectVertex(String lastVertex, String newVertex, boolean sequenceHasAppeared) {
    	for(int i = 0; i < graph.size(); i++) {
    		if(sequenceHasAppeared) { // update weight
    			if(graph.get(i).getName().equals(lastVertex)) {
    				graph.get(i).incrementEdgeCount(newVertex);
    			}
    		}
    		else if(graph.get(i).getName().equals(lastVertex)) { // connect new vertex
				graph.get(i).addVertex(newVertex);
			}
		}
    }
    
    // prints graph for debugging purposes
    public void printGraph() {
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
        BufferedReader br = new BufferedReader(new FileReader(input));
        
        String st;
        String lowercase;
        
        while((st = br.readLine()) != null) {
        	lowercase = st.toLowerCase();
        	String[] line = lowercase.split(" "); // all lowercase letters to compare with graph vertices
        	String[] inputWords = st.split(" "); // input intact for poem creation
        	String middleWord;
        	
        	// writes poem
        	for(int i = 0; i < inputWords.length - 1; i++) {
        		poem = poem + inputWords[i] + " "; // to preserve capital letters of input, using inputWords
        		
        		// add middleWord if it exists
        		middleWord = returnMiddleWord(line[i], line[i+1]);
        		if(middleWord.equals("DNE") == false) {
        			poem += middleWord + " ";
        		}
        	}
        	poem += inputWords[inputWords.length - 1]; // add last word
        }
        
        
        return poem;
    }
    
    // return bridge word between vertices if it exists
    public String returnMiddleWord(String vertex, String nextVertex) {
    	String middleVertex = "DNE";
    	int maxMiddleVertexWeight = 0;
    	int vertexIndex = 0, nextVertexIndex = 0;
    	
    	if(graphValues.contains(vertex) && graphValues.contains(nextVertex)) {
    		// get indexes of vertex and nextVertex
    		for(int i = 0; i < graph.size(); i++) {
    			if(graph.get(i).getName().equals(vertex)) {
    				vertexIndex = i;
    			}
    			if(graph.get(i).getName().equals(nextVertex)) {
    				nextVertexIndex = i;
    			}
    		}
    		
    		Set<String> middleVertices = graph.get(vertexIndex).getMap().keySet(); // get all middle vertices
    		
    		for(String middleWord : middleVertices) {
    			int wordIndex = getVertexIndex(middleWord);
    			if(graph.get(wordIndex).getMap().keySet().contains(nextVertex)){ // if nextVertex is connected to this middleVertex
    				if(graph.get(vertexIndex).getMap().get(middleWord) > maxMiddleVertexWeight){ // if the weight of the middleVertex is greater than the current one
    					maxMiddleVertexWeight = graph.get(vertexIndex).getMap().get(middleWord);
    					middleVertex = middleWord;
    				}
    			}
    		}
    	}
    	
    	return middleVertex;
    }
    
    // returns index of vertex in graph
    public int getVertexIndex(String vertex) {
    	int index = 0;
    	for(int i = 0; i < graph.size(); i++) {
    		if(graph.get(i).getName().equals(vertex)) {
    			index = i;
    		}
    	}
    	
    	return index;
    }
}
