package wybren_erik.hanzespel;

import java.util.HashSet;
import java.util.Set;

import wybren_erik.hanzespel.interfaces.Edge;
import wybren_erik.hanzespel.interfaces.Graph;
import wybren_erik.hanzespel.interfaces.Vertex;

public class RoadMap implements Graph {

    private Set<Vertex> vertices = new HashSet<>();
    private Set<Edge> roads = new HashSet<>();

    public RoadMap() {
    }

    @Override
    public Set getNeighbours(Vertex from) {
        Set<Vertex> result = new HashSet<>();

        for (Edge r : roads) {
            if (r.fromNode().equals(from)) {
                result.add((Vertex) r.toNode());
            }
        }

        return result;
    }

    @Override
    public Set getEdges(Vertex from) {
        Set<Edge> result = new HashSet<>();

        for (Edge r : roads) {
            if (r.fromNode().equals(from)) {
                result.add(r);
            }
        }

        return result;
    }

    @Override
    public boolean addEdge(Vertex from, Vertex to, int distance) {
        Road r = new Road(from, to, distance);

        if (roads.contains(r)) return false;

        roads.add(r);
        return true;
    }

    @Override
    public boolean addVertex(Vertex newVertex) {
        if (vertices.contains(newVertex)) return false;

        vertices.add(newVertex);
        return true;
    }
}
