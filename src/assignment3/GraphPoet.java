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
    	Vertex<String> v1 = new Vertex<String>("explore");
    	v1.addVertex("happy");
    	ArrayList <Vertex<String> > graph = new ArrayList<>();
    	ArrayList <String> graphValues = new ArrayList<>();
    	System.out.println(v1.getT());
    	
    	
    	BufferedReader br = new BufferedReader(new FileReader(corpus)); 
    	  
    	String st; 
    	while ((st = br.readLine()) != null) {
    	    System.out.println(st); 
    	    
    	    st.toLowerCase(); // convert all words to lowercase
    	    String[] line = st.split(" "); // array of words for one line
    	    
    	    for(String word : line) {
    	    	if(graphValues.contains(word) == false) {
    	    		graphValues.add(word);
    	    		graph.add(new Vertex<String>(word));
    	    		// increment edges
    	    		if(graphValues.size() > 1) {
    	    			graph.get(graph.size()-1).addVertex(word);
    	    		}
    	    	}
    	    	else { // if it contains this word dont add it but increment edges for whatever is connecting
    	    		
    	    	}
    	    }
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
