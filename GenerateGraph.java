package Graphs;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
/**

The GenerateGraph class reads graph data from a folder, generates a graph, runs Dijkstra's algorithm on it,

and then writes the results to a CSV file.
*/
public class GenerateGraph {
/**

The main method reads all files in the "Datasets" folder, generates a graph from the file data,

and then runs Dijkstra's algorithm on it. The results are then written to a CSV file.

@param args the command-line arguments

@throws IOException if an I/O error occurs
*/
    public static void main(String[] args) throws IOException{
        // Initialize the "Datasets" folder and get all files inside it
        File folder = new File("Datasets");
        File[] files = folder.listFiles();
        // Create a string that will be written to a CSV file later on
        String writeToFile = "Vertices, Edges, vCount, eCount, pqCount, ElogV, operations\n";
        // Iterate over all files in the folder and process them
        for (File file : files){
            ArrayList<String> startingNodes = new ArrayList<String>();
            Graph graph = new Graph();
            Scanner scanner = new Scanner(file);
            // Read the data from the file and add it to the graph
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] splice = line.split(" ");
                
                String node1 = splice[0];
                startingNodes.add(node1);

                String node2 = splice[1];
                int cost = Integer.parseInt(splice[2]);

                graph.addEdge(node1, node2, cost);
            }
            scanner.close();
             // Extract the number of vertices and edges from the file name and calculate other statistics
            String fname = file.getName();
            int vertex = Integer.parseInt(fname.split("_")[1]);
            int edges = Integer.parseInt(fname.split("_")[2].split("\\.")[0]);
            double eLogV = edges * Math.log(vertex);
            int ops = vertex + edges;
             // Run Dijkstra's algorithm on the graph and write the results to the string that will be written to the CSV file
            writeToFile += vertex + " , " + edges + " , " + graph.dijkstra(startingNodes.get(0)) + " , " + eLogV + " , " + ops + "\n";

        }
        // Write the final string to a CSV file
        File results = new File("resultsDataset.csv");
        FileWriter fileWriter = new FileWriter(results);
        fileWriter.write(writeToFile);
        fileWriter.close();

    }
    
}