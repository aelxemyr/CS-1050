
/**
 * A list which keeps track of a head node and assigns next and previous
 * attributes to new nodes to impose a linked list structure.
 *
 * @author Bennett Alex Myers
 * @version 2015.09.22
 */
public class LinkedListNullNode
{
    private int length;
    private Node nullNode;

    /**
     * Constructor for objects of class LinkedList
     */
    public LinkedListNullNode()
    {
        length = 0;
        nullNode = null;
    }

    /**
     * Return the length of the list.
     *
     * @return The length of the list.
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Given an int value, find the first node in the list
     * with that value. If no such node exists, return null.
     *
     * @param value The value we wish to match to a node.
     * @return The first node whose value matches the search
     *         parameter, or null if there is no match.
     */
    public Node search(int value)
    {
        Node currentNode = nullNode.getNext();
        if(nullNode.getNext() == null) {
            System.out.println("List is empty.");
        }
        else {
            while(currentNode != null
                    && currentNode.getValue() != value) {
                currentNode = currentNode.getNext();
            }
        }
        return currentNode;
    }

    /**
     * Given a node, return whether or not the list contains that
     * node.
     *
     * @param node The node in question.
     * @return Whether or not the list contains the node.
     */
    public boolean contains(Node node)
    {
        boolean contains = false;
        if(nullNode.getNext() != null) {
            Node currentNode = nullNode.getNext();
            if(currentNode == node) {
                contains = true;
            }
            while(!contains && currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
                if(currentNode == node) {
                    contains = true;
                }
            }
        }
        return contains;
    }

    /**
     * Given a node, "splice" that node onto the front of the list.
     *
     * @param node The node to be inserted.
     */
    public void insert(Node node)
    {
        node.setNext(nullNode.getNext());
        if(nullNode.getNext() != null) {
            nullNode.getNext().setPrev(node);
        }
        nullNode.setNext(node);
        length++;
    }

    /**
     * Given an int value, "splice" a node with that value onto
     * the front of the list.
     *
     * @param value The int value of the node to be inserted.
     */
    public void insert(int value)
    {
        Node node = new Node(value);
        insert(node);
    }

    /**
     * Given a node, "splice" that node onto the end of the list.
     *
     * @param node The node to be inserted.
     */
    public void insertOntoEnd(Node node)
    {
        node.setNext(nullNode);
        length++;
    }

    /**
     * Given an int value, "splice" a node with that value onto
     * the end of the list.
     *
     * @param value The int value of the node to be inserted.
     */
    public void insertOntoEnd(int value)
    {
        Node node = new Node(value);
        insertOntoEnd(node);
    }

    /**
     * Given a node, "splice" that node out of the list. Return
     * true if successful, false otherwise.
     *
     *@param node The node to be deleted.
     *@return Whether or not the deletion was successful.
     */
    public boolean delete(Node node)
    {
        boolean successful = false;
        if(contains(node)) {
            if(node.getPrev() != null) {
                node.getPrev().setNext(node.getNext());
            }
            else {
                head = node.getNext();
            }
            if(node.getNext() != null) {
                node.getNext().setPrev(node.getPrev());
            }
            length--;
            successful = true;
        }
        return successful;
    }

    /**
     * Given an int value, "splice" the first node with that value out
     * of the list. Return true if successful, false otherwise.
     */
    public boolean delete(int value)
    {
        boolean successful = false;
        Node node = search(value);
        successful = delete(node);
        return successful;
    }

    /**
     * Print out the elements of the list along with the length of the
     * list. If the list is empty, print out a message indicating this.
     */
    public void print()
    {
        if(head == null) {
            System.out.println("List is empty");
        }
        else {
            System.out.println(head.getValue());
            Node currentNode = head;
            while(currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
                System.out.println(currentNode.getValue());
            }
            System.out.println("Length: " + length);
        }
    }
}
