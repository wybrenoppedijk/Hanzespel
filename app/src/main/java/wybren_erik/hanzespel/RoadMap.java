package wybren_erik.hanzespel;

import java.util.HashSet;
import java.util.Set;

import wybren_erik.hanzespel.interfaces.Edge;
import wybren_erik.hanzespel.interfaces.Graph;
import wybren_erik.hanzespel.interfaces.Vertex;

public class RoadMap implements Graph {

    private static RoadMap instance;
    private Set<Vertex> vertices = new HashSet<>();
    private Set<Edge> roads = new HashSet<>();

    private RoadMap() {
    }

    /**
     * Retrieve the roadmap instance
     *
     * @return The Singleton instance of RoadMap
     */
    public static RoadMap getInstance() {
        if (instance == null) instance = new RoadMap();
        return instance;
    }

    /**
     * Get the neighbours from a vertex.
     *
     * @param from The vertex of which the neighbours have to be returned.
     * @return A HashSet containing the neighbour Vertices of given Vertex.
     */
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

    /**
     * Retrieve the edges connected to a vertex.
     *
     * @param from The vertex of which all outgoing edges have to be returned.
     * @return A HashSet containing all edges connected to given Vertex.
     */
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

    /**
     * Add an edge between to vertices.
     *
     * @param from     The origin vertex
     * @param to       The destination vertex
     * @param distance The weight of the edge
     * @return True if the edge has successfully been added, false if the edge already exists.
     */
    @Override
    public boolean addEdge(Vertex from, Vertex to, int distance) {
        Road r = new Road(from, to, distance);

        if (roads.contains(r)) return false;

        roads.add(r);
        return true;
    }

    /**
     * Add a Vertex to the RoadMap.
     *
     * @param newVertex The vertex to be added.
     * @return True if the Vertex was successfully added, false if the Vertex already exists.
     */
    @Override
    public boolean addVertex(Vertex newVertex) {
        if (vertices.contains(newVertex)) return false;

        vertices.add(newVertex);
        return true;
    }

    /**
     * Retrieve a City from a Location to prevent inefficient memory usage.
     *
     * @param name Location of the city
     * @return City with location given
     */
    public City getCity(Location name) {
        for (Vertex v : vertices) {
            if (v.getName().equals(name)) {
                return (City) v;
            }
        }
        return null; // Won't happen.
    }

    /**
     * Retrieve a list of all vertices.
     *
     * @return A HashSet containing all cities in the roadmap.
     */
    public Set<City> getCities() {
        Set<City> result = new HashSet<>();
        for (Vertex v : vertices) {
            result.add((City) v);
        }
        return result;
    }
}
