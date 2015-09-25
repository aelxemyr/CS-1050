
/**
 * An element (vertex) in a min priority queue.
 *
 * @author Bennett Alex Myers
 * @version 2015.09.24
 */
public class Node
{
    private Vertex vertex;
    private Node next;
    private Node prev;

    /**
     * Create a new node.
     */
    public Node(Vertex vertex)
    {
        this.vertex = vertex;
        next = null;
        prev = null;
    }

    /**
     * Update priority.
     */
    public int getPriority()
    {
        return vertex.getMinWeight();
    }

    public Vertex getVertex()
    {
        return vertex;
    }

    public void setNext(Node next)
    {
        this.next = next;
    }

    public Node getNext()
    {
        return next;
    }

    public void setPrev(Node prev)
    {
        this.prev = prev;
    }

    public Node getPrev()
    {
        return prev;
    }
}
