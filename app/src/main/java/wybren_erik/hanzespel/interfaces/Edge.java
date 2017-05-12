package wybren_erik.hanzespel.interfaces;

public interface Edge<T> {

    /**
     * Returns to origin (point) for this edge.
     * @return The origin of this edge.
     */
    T fromNode();

    /**
     * Returns the destination for this edge.
     * @return The destination of this edge
     */
    T toNode();

    /**
     * Returns the weight of this edge.
     * @return the weight of this edge
     */
    int getWeight();

}
