
/**
 * A min priority queue using a doubly linked list.
 *
 * @author Bennett Alex Myers
 * @version 2015.09.25
 */
public class MinPriorityQueue
{
    private Node minimum;

    /**
     * Creates an empty queue.
     */
    public MinPriorityQueue()
    {
        minimum = null;
    }

    /**
     * Insert a node into the queue and move it down the queue
     * based on its priority.
     *
     * @param node The Node to be inserted and sorted.
     */
    public void insert(Node node)
    {
        Node currentNode = minimum;
        if (minimum == null) {
            minimum = node;
        }
        else if (node.getPriority() < minimum.getPriority()) {
            node.setNext(minimum);
            minimum.setPrev(node);
            minimum = node;
        }
        else {
            while (currentNode.getNext() != null && node.getPriority()
                    >= currentNode.getNext().getPriority()) {
                currentNode = currentNode.getNext();
            }
            if (currentNode.getNext() != null) {
                node.setNext(currentNode.getNext());
                currentNode.getNext().setPrev(node);
            }
            node.setPrev(currentNode);
            currentNode.setNext(node);
        }
    }

    /**
     * Remove and return the minimum node of this queue.
     *
     * @return The minimum node of this queue.
     */
    public Vertex extractMin()
    {
        Vertex minVertex = null;
        if (minimum != null) {
            minVertex = minimum.getVertex();
            if (minimum.getNext() != null) {
                minimum.getNext().setPrev(null);
                minimum = minimum.getNext();
            }
            else {
                minimum = null;
            }
        }
        return minVertex;
    }

    /**
     * Check if the given vertex is contained in this queue.
     *
     * @vertex The vertex in question.
     * @return Whether or not the vertex is in the queue.
     */
    public boolean contains(Vertex vertex)
    {
        boolean contains = false;
        Node currentNode = minimum;
        while (currentNode != null) {
            if (currentNode.getVertex() == vertex) {
                contains = true;
            }
            currentNode = currentNode.getNext();
        }
        return contains;
    }

    /**
     * Return the length of this queue.
     *
     * @return The length of this queue.
     */
    public int getLength()
    {
        Node currentNode = minimum;
        int length = 0;
        while (currentNode != null) {
            currentNode = currentNode.getNext();
            length++;
        }
        return length;
    }
}
