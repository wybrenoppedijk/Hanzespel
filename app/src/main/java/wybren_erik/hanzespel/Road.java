package wybren_erik.hanzespel;

import wybren_erik.hanzespel.interfaces.*;
import wybren_erik.hanzespel.interfaces.Edge;

public class Road implements Edge {
    private int weight;
    private Vertex<?> from;
    private Vertex<?> to;

    Road(Vertex from, Vertex to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public Object fromNode() {
        return from;
    }

    @Override
    public Object toNode() {
        return to;
    }

    @Override
    public int getWeight() {
        return weight;
    }



}
