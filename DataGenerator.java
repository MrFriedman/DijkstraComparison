package Graphs;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
/**

The DataGenerator class generates random graphs with a specific number of vertices and edges

and saves them as text files in a specified directory.
*/
public class DataGenerator {
    /**

The main method generates 25 random graphs with different number of vertices and edges.

@param args An array of command-line arguments (not used in this method).

@throws Exception if an error occurs while creating or writing to a file.
*/
    public static void main(String[] args) throws Exception {
        // Define the number of vertices and edges for each dataset
        int[] V = {10, 20, 30, 40, 50};
        int[] E = {20, 35, 50, 65, 80};
        // Create a folder to store the generated datasets
        File folder = new File("Datasets");
        folder.mkdir();
        
        try{
            // Generate 25 datasets
            for (int i=1; i <= 25; i++ ){
                // Determine the number of vertices and edges for this dataset
                int vIndex = (i - 1) % V.length;    
                int eIndex = (i - 1) / V.length;    
                int vertexCount = V[vIndex];        
                int edgeCount = E[eIndex];
                // Create a file to store the graph for this dataset
                String fileName = "T5Dataset" + i + "_" + V[vIndex] + "_" + E[eIndex] + ".txt";
                File file = new File(folder, fileName);
                FileWriter writer = new FileWriter(file);
                Random random = new Random();
                // Generate the edges for this graph
                for (int j = 0; j < edgeCount; j++){
                    // Select two vertices randomly to create an edge between them
                    int u = random.nextInt(vertexCount) + 1; //source vertex        
                    int v = random.nextInt(vertexCount) + 1; //destination vertex   
                    int w = random.nextInt(10) + 1; //edge cost we'll use       

                    // Check that the edge doesn't already exist to avoid self-loops
                     while (u == v || edgeExists(writer, u, v)){
                        u = random.nextInt(vertexCount) + 1;
                        v = random.nextInt(vertexCount) + 1;
                    }
                    // Write the edge to the file
                    writer.write("Node" + u + " Node" + v + " " + w + "\n");
                    }
                writer.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    /**

Checks if an edge with the given vertices already exists in the file.

@param writer The FileWriter object representing the file being written to.

@param u The source vertex of the edge to check.

@param v The destination vertex of the edge to check.

@return True if the edge already exists in the file, false otherwise.

@throws IOException if an error occurs while reading the file.
*/
    private static boolean edgeExists(FileWriter writer, int u, int v) throws IOException{
        writer.flush();
        String contents = writer.toString();
        String[] lines = contents.split("\\r?\\n");

        for (String line : lines){
            String[] tokens = line.split(" ");

            if (tokens.length != 3){
                continue;
            }

            int x = Integer.parseInt(tokens[0].substring(4));
            int y = Integer.parseInt(tokens[1].substring(4));

            if ((u == x && v == y) || (u == y && v == x)){
                return true;
            }
        }
        return false;
    }
}
