import java.util.*;

public class GraphTraversal {

    static HashMap<String, ArrayList<String>> graph = new HashMap<>();

    public static void main(String[] args) {

        createGraph();

        System.out.println("Depth First Search:");
        depthFirstSearch("A");

        System.out.println();

        System.out.println("Breadth First Search:");
        breadthFirstSearch("A");
    }

    public static void createGraph() {

        graph.put("A", new ArrayList<>(Arrays.asList("C", "B", "D")));
        graph.put("B", new ArrayList<>(Arrays.asList("A", "C", "E", "G")));
        graph.put("C", new ArrayList<>(Arrays.asList("A", "B", "D")));
        graph.put("D", new ArrayList<>(Arrays.asList("C", "A")));
        graph.put("E", new ArrayList<>(Arrays.asList("G", "F", "B")));
        graph.put("F", new ArrayList<>(Arrays.asList("G", "E")));
        graph.put("G", new ArrayList<>(Arrays.asList("F", "B")));
    }

    public static void depthFirstSearch(String startVertex) {

        HashSet<String> visitedVertices = new HashSet<>();

        visitDepth(startVertex, visitedVertices);

        System.out.println();
    }

    public static void visitDepth(String currentVertex,
                                  HashSet<String> visitedVertices) {

        visitedVertices.add(currentVertex);

        System.out.print(currentVertex + " ");

        for (String neighbour : graph.get(currentVertex)) {

            if (!visitedVertices.contains(neighbour)) {

                visitDepth(neighbour, visitedVertices);

            }
        }
    }

    public static void breadthFirstSearch(String startVertex) {

        HashSet<String> visitedVertices = new HashSet<>();

        Queue<String> waitingVertices = new LinkedList<>();

        visitedVertices.add(startVertex);

        waitingVertices.add(startVertex);

        while (!waitingVertices.isEmpty()) {

            String currentVertex = waitingVertices.remove();

            System.out.print(currentVertex + " ");

            for (String neighbour : graph.get(currentVertex)) {

                if (!visitedVertices.contains(neighbour)) {

                    visitedVertices.add(neighbour);

                    waitingVertices.add(neighbour);

                }
            }
        }

        System.out.println();
    }
}