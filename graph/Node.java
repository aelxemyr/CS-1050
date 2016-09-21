
/**
 * A node in a doubly linked implementation of a min priority queue
 * for vertices in a graph.
 * 
 * @author Bennett Alex Myers
 * @version 2015.10.01
 */
public class Node
{
    private Node next;
    private Vertex vertex;
    private int priority;

    /**
     * Create a new node with the specified priority.
     */
    public Node(Vertex vertex)
    {
        this.next = null;
        this.vertex = vertex;
        this.priority = vertex.getMinWeight();
    }
    
    /**
     * Set the next node.
     * @param next The next node in the queue.
     */
    public void setNext(Node next)
    {
        this.next = next;
    }
    
    /**
     * Change the priority of the node.
     * @param newPriority The new value of the priority of this node.
     */
    public void changePriority(int newPriority)
    {
        this.priority = newPriority;
    }
    
    /**
     * Update the priority of this node to the current minimum
     * weight value associated with this node's vertex.
     */
    public void updatePriority()
    {
        int minWeight = vertex.getMinWeight();
        changePriority(minWeight);
    }
    
    /**
     * Get the next node.
     * @return The next node in the queue.
     */
    public Node getNext()
    {
        return this.next;
    }
    
    /**
     * Get the priority of this node.
     * @return The priority of this node.
     */
    public int getPriority()
    {
        return this.priority;
    }
    
    /**
     * Get the vertex associated with this node.
     */
    public Vertex getVertex()
    {
        return vertex;
    }
}    
