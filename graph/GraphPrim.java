
import java.util.Map;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
public class GraphPrim{

    private HashMap<Character,Node> lookup = new HashMap<Character, Node>();
   
private class Node{
    private char value;
    //private int distance_from_source = Integer.MAX_VALUE;
    //private Node previous_node = null;

    Map<Node, Integer> adjacentNodes = new HashMap<>();
    public Node(char value){
        this.value = value;
    }
}

public void addLookup(char value){
    lookup.put(value, new Node(value));
}

public Node getNode(char value){
    return lookup.get(value);
}

public void addEdge(char source, char destination, int distance){
    Node s = getNode(source);
    Node d = getNode(destination);
    s.adjacentNodes.put(d,distance);
}

public static ArrayList<Character> findMimimumSpanningTree(GraphPrim graph, Node source){
    ArrayList<Character> result = new ArrayList<Character>();
    HashSet<Character> visited = new HashSet<Character>();
    HashSet<Node> unvisited = new HashSet<Node>();
    visited.add(source.value);
    result.add(source.value);
    PriorityQueue<Node> q = new PriorityQueue<Node>();
    for (Map.Entry <Node, Integer> adjacentPairs :source.adjacentNodes.entrySet()){
        q.add(adjacentPairs.getKey(), -adjacentPairs.getValue());
}
    while(!q.isEmpty()){
        Node e = q.removeNext();
        if(!visited.contains(e.value)){
            visited.add(e.value);
            result.add(e.value);
            for (Map.Entry <Node, Integer> adjacentPairs :e.adjacentNodes.entrySet()){
                q.add(adjacentPairs.getKey(), -adjacentPairs.getValue());
        }
    }
}
return result;
}

public static void main(String [] args){
    GraphPrim graph = new GraphPrim();
    graph.addLookup('A');
    graph.addLookup('B');
    graph.addLookup('C');
    graph.addLookup('D');
    graph.addLookup('E');

    graph.addEdge('A','B',6);
    graph.addEdge('A','D',1);
    graph.addEdge('D','B',2);
    graph.addEdge('D','E',1);
    graph.addEdge('D','A',1);
    graph.addEdge('B','E',2);
    graph.addEdge('B','C',5);
    graph.addEdge('B','A',6);
    graph.addEdge('B','D',2);
    graph.addEdge('E','D',1);
    graph.addEdge('E','B',2);
    graph.addEdge('E','C',5);
    graph.addEdge('C','B',5);
    graph.addEdge('C','E',5);
    ArrayList<Character> result = new ArrayList<Character>();
    result = findMimimumSpanningTree(graph, graph.getNode('A'));
    System.out.println(result);


}

}



















