package wybren_erik.hanzespel;

import java.util.HashSet;
import java.util.Set;

import wybren_erik.hanzespel.interfaces.Graph;
import wybren_erik.hanzespel.interfaces.Vertex;

/**
 * Created by wybrenoppedijk on 12/05/2017.
 */

public class RoadMap implements Graph {

    private Set<Vertex> vertices = new HashSet<>();
    private Set<Vertex> roads = new HashSet<>();

    public RoadMap() {
    }

    @Override
    public Set getNeighbours(Vertex from) {
        return null;
    }

    @Override
    public Set getEdges(Vertex from) {
        return null;
    }

    @Override
    public boolean addEdge(Vertex from, Vertex to, int distance, boolean bidirectional) {
        return false;
    }

    @Override
    public boolean addVertex(Vertex newVertex) {
        return false;
    }
}
