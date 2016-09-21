import java.util.Random;
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    

    /**
     * Constructor for objects of class Test
     */
    public Test()
    {
        
    }

    public static void main(String[] args) {
        char name = 'a';
        Random r = new Random();
        MinPriorityQueue mpq = new MinPriorityQueue();
        for (int i = 0; i < 20; i++) {
            Vertex v = new Vertex(name);
            v.setMinWeight(r.nextInt(100));
            mpq.insert(v);
            name++;
        }
        while (!mpq.isEmpty()) {
            Vertex v = mpq.pop();
            System.out.println("Vertex " + v.getName() + ": " + v.getMinWeight());
        }
    }
}
