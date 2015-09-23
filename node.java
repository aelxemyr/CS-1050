
/**
 * An element in a LinkedList.
 *
 * @author Bennett Alex Myers
 * @version 2015.09.21
 */
public class Node
{
    private int value;
    private Node next;
    private Node prev;

    /**
     * Constructor for objects of class Node
     */
    public Node(int value)
    {
        this.value = value;
        next = null;
        prev = null;
    }

    /**
     * Return value.
     *
     * @return The int value stored in value.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Return the next element in the list. Return null if there is no next element.
     *
     * @return The next element in the list.
     */
    public Node getNext()
    {
        return next;
    }

    /**
     * Return the previous element in the list. Return null if there is no previous element.
     *
     * @return The previous element in the list.
     */
    public Node getPrev()
    {
        return prev;
    }

    /**
     * Set the next element in the list to the given node.
     *
     * @param next The element to be next in the list.
     */
    public void setNext(Node next)
    {
        this.next = next;
    }

    /**
     * Set the previous element in the list to the given node.
     *
     * @param prev The element to be previous in the list.
     */
    public void setPrev(Node prev)
    {
        this.prev = prev;
    }
}
