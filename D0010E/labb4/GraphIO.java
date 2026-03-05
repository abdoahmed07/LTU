import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* To Compile and run
javac -cp BFTmain.jar GraphIO.java
java -cp ".:BFTmain.jar:FIFOmain.jar" BFTmain gridgraph
*/

public class GraphIO {

    public static void readFile(Graph graph, String filename) {

        // opens the file and reads the file safely, handling exceptions for file not found and bad file format
        try (Scanner sc = new Scanner(new File(filename))) {

            int n = sc.nextInt(); // n is the first integer in the file

            // read n nodes
            for (int i = 0; i < n; i++) {
                int id = sc.nextInt();
                int x  = sc.nextInt();
                int y  = sc.nextInt();
                graph.addNode(id, x, y);
            }

            // read edges until end of file
            while (sc.hasNextInt()) {
                int from = sc.nextInt();
                int to   = sc.nextInt();
                int w    = sc.nextInt();
                graph.addEdge(from, to, w);
            }

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Bad file format in: " + filename);
        }

    }
}