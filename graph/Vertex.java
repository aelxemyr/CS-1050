
/**
 * Write a description of class Vertex here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vertex
{
    private char name;
    private int minWeight;

    /**
     * Constructor for objects of class Vertex
     */
    public Vertex(char name)
    {
        this.name = name;
        minWeight = 0;
    }

    /**
     * 
     */
    public char getName()
    {
        return name;
    }
    
    /**
     * 
     */
    public int getMinWeight()
    {
        return minWeight;
    }
    
    /**
     * 
     */
    public void setMinWeight(int weight)
    {
        minWeight = weight;
    }
}
