
package Graphs; 
// Represents an edge in the graph.
class Edge<T>
{
    public T     dest;   // Second vertex in Edge
    public double     cost;   // Edge cost
    
    public Edge( T d, double c )
    {
        dest = d;
        cost = c;
    }
}
