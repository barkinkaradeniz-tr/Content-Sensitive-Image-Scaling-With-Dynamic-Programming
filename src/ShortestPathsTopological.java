import java.util.Iterator;
import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    /**
     * Constructor
     * Finds shortest paths in G starting in s. Saves result in the instance attribute parent.
     *
     * @param G Graph G
     * @param s start node s
     */
    public ShortestPathsTopological(WeightedDigraph G, int s) {
        // TODO
        this.parent = new int[G.V()];
        this.dist = new double[G.V()];
        this.s = s;

        for (int i = 0; i < G.V(); i++) {
            dist[i] = Double.MAX_VALUE;
        }
        dist[s] = 0;

        TopologicalWD twd = new TopologicalWD(G);
        twd.dfs(s);

        Stack<Integer> order = twd.order();


        for (int i = 0; i < Math.sqrt(G.V()); i++) {
            for (int k = 0; k < twd.order().size(); k++) {
                for (DirectedEdge e : G.incident(order.pop())) {
                    relax(e);
                }
            }
        }


    }

    /**
     * Implements the relaxation of en edge.
     * For further information see subsection on relaxation in the script.
     *
     * @param e edge that will be relaxed
     */
    public void relax(DirectedEdge e) {
        // TODO

        if (dist[e.to()] > (dist[e.from()] + e.weight())) {
            parent[e.to()] = e.from();
            dist[e.to()] = dist[e.from()] + e.weight();
        }
    }

    /**
     * @param v node
     * @return whether the node already has a path to it
     */
    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    /**
     * @param v node
     * @return path to node from start node s
     */
    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }
}

