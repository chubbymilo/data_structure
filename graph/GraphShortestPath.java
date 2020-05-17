import java.util.*;

public class GraphShortestPath{
    private HashMap<Character,Node> lookup = new HashMap<Character, Node>();
   

private class Node{
    private char value;
    private int distance_from_source = Integer.MAX_VALUE;
    private Node previous_node = null;
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

public static GraphShortestPath findShortestPath(GraphShortestPath graph, Node source){
    HashSet<Character> visited = new HashSet<Character>();
    HashSet<Node> unvisited = new HashSet<Node>();
    source.distance_from_source = 0;
    source.previous_node = graph.getNode('A');
    unvisited.add(source);

    while(unvisited.size()!=0){
        Node currentNode = getLowestDistance(unvisited);
        unvisited.remove(currentNode);

        for (Map.Entry <Node, Integer> adjacentPairs :currentNode.adjacentNodes.entrySet()){
            
            Integer weight = adjacentPairs.getValue();
            Node adjacentNode = adjacentPairs.getKey();

            if (!visited.contains(adjacentNode.value)){
                if (currentNode.distance_from_source + weight < adjacentNode.distance_from_source){
                    adjacentNode.distance_from_source = (currentNode.distance_from_source + weight);
                    adjacentNode.previous_node = currentNode;
                }
                unvisited.add(adjacentNode);
            }
        }
        visited.add(currentNode.value);
    }
    return graph;

}

private static Node getLowestDistance(HashSet<Node> unvisited){
    Node lowestDistanceNode = null;
    int lowestDistance = Integer.MAX_VALUE;
    for (Node node: unvisited){
        int nodeDistance = node.distance_from_source;
        if (nodeDistance < lowestDistance){
            lowestDistance = nodeDistance;
            lowestDistanceNode = node;
        }
    }
    return lowestDistanceNode;
}

public static void main(String [] args){
    GraphShortestPath graph = new GraphShortestPath();
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

    graph = findShortestPath(graph,graph.getNode('A'));
    System.out.println(graph.getNode('A').previous_node.value);
    System.out.println(graph.getNode('B').previous_node.value);
    System.out.println(graph.getNode('C').previous_node.value);
    System.out.println(graph.getNode('D').previous_node.value);
    System.out.println(graph.getNode('E').previous_node.value);

    System.out.println(graph.getNode('A').distance_from_source);
    System.out.println(graph.getNode('B').distance_from_source);
    System.out.println(graph.getNode('C').distance_from_source);
    System.out.println(graph.getNode('D').distance_from_source);
    System.out.println(graph.getNode('E').distance_from_source);

}
}