import java.util.NoSuchElementException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;


public class GraphIO {


	public static void readFile(Graph g, String filename) throws IOException {
		int n;
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			n = scanner.nextInt();
			
			for(int i = 0; i <= n; i++) {
				/*
				 * Since every line has three
				 * three integers, this for
				 * loop iterates through the
				 * first 100 lines.
				 */
				int first = scanner.nextInt();
				int second = scanner.nextInt();
				int third = scanner.nextInt();
				g.addNode(first, second, third);
			}
			
			while(scanner.hasNextLine()) {
				/*
				 * Goes through the remaining
				 * lines until there are no
				 * more lines.
				 */
				int first = scanner.nextInt();
				int second = scanner.nextInt();
				int third = scanner.nextInt();
				g.addEdge(first, second, third);
			}
		}
		
		catch(Exception FileNotFoundException) {
			throw new IOException("File not found");
		}
	}
	
}
