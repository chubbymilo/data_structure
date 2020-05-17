/* Depth first search and breadth first search in a graph. Based on a tutorial on youtube.
 * @ author James Zhao
 * DFS: Continue searching the source node's children until there is no children to search from. (Using a stack)
 * Each time we encounter a node that has not been searched before, add it to the stack.
 * Pop the stack and continue searching.
 * 
 * BFS: Continue searching the source nodes' chirdren until there is no children to search from. (using a queue)
 * Each tiem we encounter a node that has not been searched, add it to the queue.
 * Dequeue a node and continue searching. (We search a node's neighbour first, comparing to DFS.)
 * 
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

public class GraphSearch{
    private HashMap<Integer, Node> lookup = new HashMap<Integer, Node>();

    private class Node{
        private int id;
        LinkedList<Node> adjacent = new LinkedList<Node>();
        private Node(int id){
            this.id = id;
        }
    }

    public  void addLookup(int id){
        lookup.put(id, new Node(id));
    }

    private Node getNode(int id){
        return lookup.get(id);
    }

    public void addEdge(int source, int destination){
        Node s = getNode(source);
        Node d = getNode(destination);
        s.adjacent.add(d);
    }

    public boolean hasPathDFS(int source, int destination){
        Node s = getNode(source);
        Node d = getNode(destination);
        HashSet<Integer> visited = new HashSet<Integer>();
        return hasPathDFS(s, d, visited);
    }
    // Recursive implementation of DFS.
    private boolean hasPathDFS(Node source, Node destination, HashSet<Integer> visited){
        if (visited.contains(source.id)){
            return false;
        }

        visited.add(source.id);
        if (source == destination){
            return true;
        }
        for (Node child: source.adjacent){
            if (hasPathDFS(child, destination, visited)){
                return true;
            }

        }
        return false;
    }

    public boolean hasPathDFSIterative(int source, int destination){
        Node s = getNode(source);
        Node d = getNode(destination);
        return hasPathDFSIterative(s, d);
    }

     // Using a stack for iterative implemenation of DFS.
    private boolean hasPathDFSIterative(Node source, Node destination){
        Stack<Node> needVisited = new Stack<Node>();
        HashSet<Integer> visited = new HashSet<Integer>();
        needVisited.push(source);

        while (!needVisited.isEmpty()){

            Node node = needVisited.pop();

            if (!visited.contains(node.id)){
                if (node == destination){
                    return true;
                }

                visited.add(node.id);
            }

            for (Node child: node.adjacent){
                if (!visited.contains(child.id)){
                    needVisited.add(child);
                }
            }
        }
        return false;
    }


    public boolean hasPathBFS(int source, int destination){
        Node s = getNode(source);
        Node d = getNode(destination);
        return hasPathBFS(s, d);
    }
    
    // Iterative implementation of BFS using a queue.
    private boolean hasPathBFS(Node source, Node destination){
        LinkedList<Node> nextToVisit = new LinkedList<Node>();
        HashSet<Integer> visited = new HashSet<Integer>();
        nextToVisit.add(source);

        while(!nextToVisit.isEmpty()){
            Node node = nextToVisit.remove();

            if (node == destination){
                return true;
            }
            if (visited.contains(node.id)){
                continue;
            }
            visited.add(node.id);

            for (Node child: node.adjacent){
                nextToVisit.add(child);
            }

        }
        return false;

    }
    




    public static void main(String[] args){
        GraphSearch graph_search = new GraphSearch();
        graph_search.addLookup(1);
        graph_search.addLookup(2);
        graph_search.addLookup(3);
        graph_search.addLookup(4);
        graph_search.addEdge(2, 4);
        graph_search.addEdge(3, 1);
        graph_search.addEdge(3, 2);
        System.out.println(graph_search.hasPathDFS(3, 4));
        System.out.println(graph_search.hasPathBFS(3, 4));
        System.out.println(graph_search.hasPathDFSIterative(3, 1));


    }



    
}