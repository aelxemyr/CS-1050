
/**
 * A representation of a weighted, undirected graph using an array of
 * adjacency lists. 
 *
 * @author Bennett Alex Myers
 * @version 2015.09.25
 */
public class Graph
{
    private Vertex[] vertices;
    private int order;

    /**
     * Create a new graph with the specified order.
     *
     * @param order The number of vertices of the new graph.
     */
    public Graph(int order)
    {
        vertices = new Vertex[order];
        this.order = order;
        char name = 'a';
        for (int i = 0; i < order; i++) {
            vertices[i] = new Vertex(name);
            name++;
        }
    }

    /**
     * Create a new graph with the specified vertex array.
     *
     * @param vertices The vertices of the new graph.
     */
    public Graph(Vertex[] vertices)
    {
        this.vertices = vertices;
        order = vertices.length;
    }

    /**
     * Add an edge between two vertices with a specified weight. The vertices
     * are indicated by their index in the vertices array of this graph.
     * That is, upon initialization, index 0 corresponds to vertex a, index
     * 1 to vertex b, and so on.
     *
     * @param v1 The index of the first endpoint in the array.
     * @param v2 The index of the second endpoint in the array.
     * @param weight The weight associated with the new edge.
     */
    public void addEdge(int v1, int v2, int weight)
    {
        vertices[v1].add(vertices[v2], weight);
        vertices[v2].add(vertices[v1], weight);
    }

    /**
     * Add an edge between two vertices with a specified weight.
     *
     * @param v1 The first endpoint of the edge.
     * @param v2 The second endpoint of the edge.
     * @param weight The weight associated with the new edge.
     */
    public void addEdge(Vertex v1, Vertex v2, int weight)
    {
        v1.add(v2, weight);
        v2.add(v1, weight);
    }

    /**
     * Return a minimum spanning tree of this graph, given a
     * starting vertex.
     *
     * @param index The index of the root of the tree (the initial vertex).
     * @return tree A minimum spanning tree of this graph.
     */
    public Graph getMinimumTree(int index)
    {
        Vertex[] treeVertices = new Vertex[order];
        Vertex root = vertices[index];
        MinPriorityQueue vList = new MinPriorityQueue();
        for (int i = 0; i < order; i++) {
            vertices[i].setMinWeight(999);
            vertices[i].setParent(null);
            Node node = new Node(vertices[i]);
            vList.insert(node);
        }
        root.setMinWeight(0);
        for (int j = 0; j < order; j++) {
            MinPriorityQueue temp = sortQueue(vList);
            vList = temp;
            Vertex u = vList.extractMin();
            treeVertices[j] = new Vertex(u.getName(),
                    u.getParent(),
                    u.getMinWeight());
            for (Edge edge : u.getAdj()) {
                Vertex v = edge.getVertex();
                int w = edge.getWeight();
                if (w < v.getMinWeight() && vList.contains(v)) {
                    v.setParent(treeVertices[j]);
                    v.setMinWeight(w);
                }
            }
        }
        Graph tree = new Graph(treeVertices);
        for (int n = 1; n < order; n++) {
            Vertex u = treeVertices[n];
            Vertex v = u.getParent();
            int w = u.getMinWeight();
            tree.addEdge(v, u, w);
        }
        return tree;
    }

    /**
     * Return a sorted copy of a given MinPriorityQueue.
     *
     * @param q The MinPriorityQueue to be sorted.
     * @return A sorted copy of the MinPriorityQueue.
     */
    public MinPriorityQueue sortQueue(MinPriorityQueue q)
    {
        MinPriorityQueue sortedQueue = new MinPriorityQueue();
        int queueLength = q.getLength();
        for (int i = 0; i < queueLength ; i++) {
            Vertex vertex = q.extractMin();
            Node node = new Node(vertex);
            sortedQueue.insert(node);
        }
        return sortedQueue;
    }

    /**
     * Print out the adjacency lists of the vertices of
     * this graph.
     */
    public void printGraph()
    {
        for (int i = 0; i < order; i++) {
            vertices[i].printList();
        }
    }
}
