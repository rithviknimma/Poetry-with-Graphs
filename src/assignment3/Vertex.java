/* EE422C Assignment #3 submission by 
 * <Rithvik Baddam>
 * <rrb2442>
 */

package assignment3;

import java.util.HashMap;
import java.util.Map;

class Vertex <T> {
	private T name;
	private Map<T,Integer> edges; // L is a vertex, Integer is the weight}
	
	
	Vertex(T name){
		this.name = name;
		edges = new HashMap<>();
	}
	
	// adds new vextex with edgecount to 
	public void addVertex(T newVertex) {
		edges.put(newVertex, 1); // initializing vertex with weight 1
	}
	
	// to increase the weight of a vertex
	public void incrementEdgeCount(T vertex) {
		int edgeCount = edges.get(vertex);
		edgeCount++;
		edges.replace(vertex, edgeCount);
	}
	
	public T getName() {
		return name;
	}
	
	public Map<T,Integer> getMap(){
		return edges;
	}
	
}
