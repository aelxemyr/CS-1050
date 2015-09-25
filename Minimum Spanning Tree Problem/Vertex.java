import java.util.LinkedList;
/**
 * A representation of a vertex in a graph. Keeps track of a list
 * of adjacent vertices and weights in a <code>LinkedList</code>
 * of vertex-weight pairs (<code>Edge</code> objects).
 *
 * @author Bennett Alex Myers
 * @version 2015.09.24
 */
public class Vertex
{
    private LinkedList<Edge> adjacent;
    // This vertex's immediate predecessor in a tree.
    private Vertex parent;
    /*
     *  The minimum weight of any edge connecting this vertex
     *  to a vertex in a tree.
     */
    private int minWeight;
    private char name;

    /**
     * Create a new Vertex object with an empty adjacency list,
     * and set parent to null and minWeight to 999.
     */
    public Vertex(char name)
    {
        adjacent = new LinkedList<>();
        parent = null;
        minWeight = 999;
        this.name = name;
    }

    /**
     * Create a new Vertex object with an empty adjacency list
     * with the specified parent and minWeight values.
     *
     * @param parent The immediate predecessor of this vertex in a tree.
     * @param minWeight The minimum weight of any edge connecting this
     *                  vertex to a vertex in a tree.
     */
    public Vertex(char name, Vertex parent, int weight)
    {
        adjacent = new LinkedList<>();
        this.parent = parent;
        minWeight = weight;
        this.name = name;
    }

    /**
     * Return the adjacency list of this vertex.
     *
     * @return The LinkedList of Edge objects.
     */
    public LinkedList<Edge> getAdj()
    {
        return adjacent;
    }

    /**
     * Set the parent of this vertex.
     *
     * @param parent The immediate predecessor of this vertex
     *               in a tree.
     */
    public void setParent(Vertex parent)
    {
        this.parent = parent;
    }

    /**
     * Get the parent of this vertex.
     *
     * @return The immediate predecessor of this vertex in a tree.
     */
    public Vertex getParent()
    {
        return parent;
    }

    /**
     * Set the minWeight of this vertex.
     *
     * @param weight The minimum weight of any edge conecting this
     *               vertex to a vertex in a tree.
     */
    public void setMinWeight(int weight)
    {
        minWeight = weight;
    }

    public int getMinWeight()
    {
        return minWeight;
    }

    /**
     * Make a new edge. That is, add a new vertex-weight pair to
     * the adjacency list of this vertex.
     *
     * @param vertex The vertex to which to add an edge.
     * @param weight The weight associated with the new edge.
     */
    public void add(Vertex vertex, int weight)
    {
        Edge edge = new Edge(vertex, weight);
        adjacent.add(edge);
    }

    /**
     * Return the name of this vertex.
     *
     * @return The name of this vertex.
     */
    public char getName()
    {
        return name;
    }

    /**
     * Print the adjacency list of this vertex.
     */
    public void printList()
    {
        String adj = "";
        for (Edge edge : adjacent) {
            adj = adj + ", " + edge.toString();
        }
        System.out.println(name + adj);
    }
}
