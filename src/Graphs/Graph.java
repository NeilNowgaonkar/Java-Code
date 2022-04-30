package Graphs;

import java.util.*;

public class Graph
{
    private LinkedList<Integer> adj[];

    public Graph(int v)
    {
        adj=new LinkedList[v];
        for(int i=0;i<v;i++)
        {
            adj[i]=new LinkedList<Integer>();
        }
    }

    public void addEdge(int source, int destination)
    {
        adj[source].add(destination);
        adj[destination].add(source);
    }

    private boolean recur_dfs(int source,int destination, boolean vis[])
    {
        if(source==destination) return true;

        for(int neighbor: adj[source])
        {
            if(!vis[neighbor])
            {
                vis[neighbor]=true;
                boolean isConnected=recur_dfs(neighbor,destination,vis);

                if(isConnected) return true;
            }
        }

        return false;
    }

    public boolean dfs(int source,int destination)
    {
        boolean vis[]=new boolean[adj.length];
        vis[source]=true;

        return recur_dfs(source,destination,vis);
    }

    public int bfs(int source,int destination)
    {
        boolean vis[]=new boolean[adj.length];

        int parent[]=new int[adj.length];
        Queue<Integer> q=new LinkedList<>();

        q.add(source);
        parent[source]=-1;
        vis[source]=true;

        while(!q.isEmpty())
        {
            int curr = q.poll();
            if (curr == destination)
            {
                break;
            }

            for (int neighbor : adj[curr])
            {
                if (!vis[neighbor])
                {
                    vis[neighbor]=true;
                    q.add(neighbor);
                    parent[neighbor] = curr;
                }
            }
        }

        int curr=destination;
        int dist=0;
        while (parent[curr]!=-1)
        {
            System.out.print(curr+"->");
            curr=parent[curr];
            destination++;
        }
        System.out.print(source);
        return dist;
    }

    public boolean dfs_stack(int source,int destination)
    {
        boolean vis[]=new boolean[adj.length];
        vis[source]=true;
        Stack<Integer> stack=new Stack<>();

        stack.push(source);

        while(!stack.empty())
        {
            int cur= stack.pop();

            if(cur==destination)
                return true;

            for(int neighbor: adj[cur])
            {
                if(vis[neighbor]==false)
                {
                    vis[neighbor]=true;
                    stack.push(neighbor);
                }
            }
        }


        return false;
    }

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter number of vertices and edges");

        int v=sc.nextInt();
        int e=sc.nextInt();

        Graph graph=new Graph(v);
        System.out.println("Enter "+e+" edges");
        for(int i=0;i<e;i++)
        {
            int source=sc.nextInt();
            int destination=sc.nextInt();

            graph.addEdge(source,destination);
        }

        System.out.println("Enter source and destination");
        int source=sc.nextInt();
        int destination=sc.nextInt();

        //graph.bfs(source,destination);
        System.out.println("DFS Possible = "+graph.dfs_stack(source,destination));

    }

    /*void KruskalMST()
    {
        // Tnis will store the resultant MST
        Edge result[] = new Edge[V];

        // An index variable, used for result[]
        int e = 0;

        // An index variable, used for sorted edges
        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        // Step 1:  Sort all the edges in non-decreasing
        // order of their weight.  If we are not allowed to
        // change the given graph, we can create a copy of
        // array of edges
        Arrays.sort(edge);

        // Allocate memory for creating V subsets
        subset subsets[] = new subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new subset();

        // Create V subsets with single elements
        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0; // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (e < V - 1)
        {
            // Step 2: Pick the smallest edge. And increment
            // the index for next iteration
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }

        // print the contents of result[] to display
        // the built MST
        System.out.println("Following are the edges in "
                + "the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < e; ++i)
        {
            System.out.println(result[i].src + " -- "
                    + result[i].dest
                    + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree "
                + minimumCost);
    }

    void KruskalAlgo() {
        Edge result[] = new Edge[vertices];
        int e = 0;
        int i = 0;
        for (i = 0; i < vertices; ++i)
            result[i] = new Edge();

        // Sorting the edges
        Arrays.sort(edge);
        subset subsets[] = new subset[vertices];
        for (i = 0; i < vertices; ++i)
            subsets[i] = new subset();

        for (int v = 0; v < vertices; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
        i = 0;
        while (e < vertices - 1) {
            Edge next_edge = new Edge();
            next_edge = edge[i++];
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " - " + result[i].dest + ": " + result[i].weight);
    }

    public static void dijkstra(int[][] graph, int source) {
        int count = graph.length;
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];
        for (int i = 0; i < count; i++) {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        // Distance of self loop is zero
        distance[source] = 0;
        for (int i = 0; i < count; i++) {

            // Update the distance between neighbouring vertex and source vertex
            int u = findMinDistance(distance, visitedVertex);
            visitedVertex[u] = true;

            // Update all the neighbouring vertex distances
            for (int v = 0; v < count; v++) {
                if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }
        for (int i = 0; i < distance.length; i++) {
            System.out.println(String.format("Distance from %s to %s is %s", source, i, distance[i]));
        }

    }

        public void Prim(int G[][], int V) {

            int INF = 9999999;

            int no_edge; // number of edge

            // create a array to track selected vertex
            // selected will become true otherwise false
            boolean[] selected = new boolean[V];

            // set selected false initially
            Arrays.fill(selected, false);

            // set number of edge to 0
            no_edge = 0;

            // the number of egde in minimum spanning tree will be
            // always less than (V -1), where V is number of vertices in
            // graph

            // choose 0th vertex and make it true
            selected[0] = true;

            // print for edge and weight
            System.out.println("Edge : Weight");

            while (no_edge < V - 1) {
                // For every vertex in the set S, find the all adjacent vertices
                // , calculate the distance from the vertex selected at step 1.
                // if the vertex is already in the set S, discard it otherwise
                // choose another vertex nearest to selected vertex at step 1.

                int min = INF;
                int x = 0; // row number
                int y = 0; // col number

                for (int i = 0; i < V; i++) {
                    if (selected[i] == true) {
                        for (int j = 0; j < V; j++) {
                            // not in selected and there is an edge
                            if (!selected[j] && G[i][j] != 0) {
                                if (min > G[i][j]) {
                                    min = G[i][j];
                                    x = i;
                                    y = j;
                                }
                            }
                        }
                    }
                }
                System.out.println(x + " - " + y + " :  " + G[x][y]);
                selected[y] = true;
                no_edge++;
            }
        }

        int mod_add(int a,int b,int m)
        {
            int ans=a+b;
            ans=ans%m;
            return ans;
        }

    int modexp(int x,int y,int N)
    {
        if(y == 0)
            return 1;

        int z = modexp(x,Math.floor((double)y/2),N);

        if(y%2 == 0)
            return z * z % N;
        else
            return x * z * z % N;
    }

    int gcdExtended(int a,int b,int x,int y)
    {
        if(a==0)
        {
            x=0;y=1;
            return b;
        }
        int x1,y1;
        int gcd = gcdExtended(b%a,a,x1,y1);
        x=y1-(b/a)*x1;
        y=x1;
        return gcd;
    }

    int modiverse(int b,int m)
    {
        int x,y;
        int g=gcdExtended(b,m,x,y);
        
    }*/


}

