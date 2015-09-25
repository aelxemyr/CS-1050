
/**
 * A vertex weight pair, to be stored in an adjacency list of
 * a vertex of a graph.
 *
 * @author Bennett Alex Myers
 * @version 2015.09.24
 */
public class Edge
{
    private Vertex vertex;
    private int weight;

    /**
     * Create a new vertex-weight pair.
     *
     * @param vertex The vertex to which this edge connects.
     * @param weight The weight associated with this edge.
     */
    public Edge(Vertex vertex, int weight)
    {
        this.vertex = vertex;
        this.weight = weight;
    }

    /**
     * Return the vertex.
     *
     * @return The vertex connected to this edge.
     */
    public Vertex getVertex()
    {
        return vertex;
    }

    /**
     * Return the weight.
     *
     * @return The weight associated with this edge.
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Return this vertex-weight pair represented as a string.
     *
     * @return This edge as a string.
     */
    public String toString()
    {
        String edge = "(" + vertex.getName() + ", " + weight + ")";
        return edge;
    }
}
