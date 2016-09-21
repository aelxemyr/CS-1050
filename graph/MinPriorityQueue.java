
/**
 * A doubly linked list implementation of a min priority queue for vertices
 * in a graph.
 * 
 * @author Bennett Alex Myers
 * @version 2015.10.01
 */
public class MinPriorityQueue
{
    private Node minimum;

    /**
     * Creates a new empty min priority queue.
     */
    public MinPriorityQueue()
    {
        this.minimum = null;
    }
    
    /**
     * Check whether queue is empty or not.
     * @return true if queue is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return minimum == null;
    }

    /**
     * Insert and sort a new vertex into the queue.
     * @param vertex The vertex to insert.
     */
    public void insert(Vertex vertex)
    {
        Node node = new Node(vertex);
        int p = node.getPriority();
        if (minimum == null) {
            minimum = node;
        }
        else if (p < minimum.getPriority()) {
            node.setNext(minimum);
        }
        else {
            Node currentNode = minimum;
            Node next = currentNode.getNext();
            while (next != null && p >= next.getPriority()) {
                currentNode = next;
                next = next.getNext();
            }
            if (next != null) {
                node.setNext(next);
            }
            currentNode.setNext(node);
        }
    }
    
    /**
     * Update the priority of the nodes in this queue.
     */
    public void updateNodes()
    {
        if (minimum != null) {
            Node currentNode = minimum;
            minimum.updatePriority();
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
                currentNode.updatePriority();
            }
        }
    }
    
    /**
     * Update the priority of the nodes in this queue and sort accordingly.
     */
    public void sort()
    {
        updateNodes();
        
    }
    
    /**
     * Return and remove the first item in the queue.
     */
    public Vertex pop()
    {
        Vertex toReturn = minimum.getVertex();
        minimum = minimum.getNext();
        return toReturn;
    }
}
