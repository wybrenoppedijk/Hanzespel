package wybren_erik.hanzespel.interfaces;

import java.util.Set;

public interface Graph<V extends Vertex, E extends Edge> {

    /**
     * Returns a set of all neighbouring vertices.
     * @param from The vertex of which the neighbours have to been returned.
     * @return Set of neighbouring vertices.
     */
    Set<V> getNeighbours(V from);

    /**
     * Returns all edges leading out from provided vertex.
     * @param from The vertex of which all outgoing edges have to be returned.
     * @return Set of outgoing edges for provided vertex.
     */
    Set<E> getEdges(V from);

    /**
     * Adds an edge to this graph from <i>from</i> to <i>to</i> with weight <i>distance</i>. If bidirectional is true,
     * the edge can be traversed both ways.
     * @param from The origin vertex
     * @param to The destination vertex
     * @param distance The weight of the edge
     * @param bidirectional Can the edge be traversed both ways?
     * @return True, if the edge can be added. False, if the edge is already in the graph.
     */
    boolean addEdge(V from, V to, int distance, boolean bidirectional);

    /**
     * Adds a vertex to this graph.
     * @param newVertex The vertex to be added.
     * @return True, if the vertex can be added. False, if the vertex is already in the graph.
     */
    boolean addVertex(V newVertex);

}
