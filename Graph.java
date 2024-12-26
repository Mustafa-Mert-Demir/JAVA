import java.util.*;

public class Graph {

    List<Integer>[] adjList;
    // Constructor
    int N;

    public Graph(int N) {
    adjList = new ArrayList[N];
        for (int i = 0; i <N ; i++) {
            adjList[i] = new ArrayList<>(); {
            }
        }
    this.N = N;
   }

  public void addEdge(int src,int dest){
        adjList[src].add(dest);

   }
   public void RemoveEdge(int src,int dest){
        adjList[src].remove(Integer.valueOf(dest));
   }
   public void printGraph(){
       for (int i = 0; i < adjList.length; i++) {
           System.out.print(i+"->");
            for (int item : adjList[i]) {
               System.out.print(item +" ");
           }
           System.out.println();

       }
       System.out.println("-----------------");
   }

   public void BFS(int start){
        String[] color = new String[N];
        int[] dist = new int[N];
        Integer[] pred = new Integer[N];
       for (int i = 0; i < N; i++) {
           color[i] = "WHITE";
           dist[i] = Integer.MAX_VALUE;
           pred[i] = null;

       }
       color[start] = "GRAY";
       dist[start] = 0;
       pred[start] = null;
       Queue<Integer> queue = new LinkedList<>();
       queue.add(start);
       System.out.println("BFS starting from vertex "+start+" :");
       while (!queue.isEmpty()){
           int u = queue.poll();
           System.out.print(u+"->");
           for (int v: adjList[u]) {
               if (color[v].equals("WHITE")) {
                   color[v] = "GRAY";
                   dist[v] = dist[u] +1;
                   pred[v] = u;
                   queue.add(v);
               }

           }
           color[u] = "BLACK";
       }
   }
   public  void DFS(int Start){
        String[] color = new String[N];
        Integer[] pred = new Integer[N];
       for (int i = 0; i < N; i++) {
           color[i] = "WHITE";
           pred[i] = null;
       }
       Stack<Integer> stack = new Stack<>();
       stack.push(Start);
       color[Start] = "GRAY";

       System.out.println("DFS starting from vertex "+Start+" :");
       while (!stack.isEmpty()){
           int u = stack.pop();
           if (color[u].equals("GRAY")){
               System.out.print(u+" ");
               color[u] = "BLACK";
               for (int v : adjList[u]) {
                   if (color[v].equals("WHITE")){
                       color[v] = "GRAY";
                       pred[v] = u;
                       stack.push(v);
                   }
               }
           }
       }

       System.out.println();
       System.out.println(buildTree(pred,Start));
    }

    public String buildTree(Integer[] pred,int start) {
        StringBuilder sb = new StringBuilder();
        sb.append("[/").append(start);
        for (int i = 0; i < N; i++) {
            if (pred[i] != null && pred[i] == start) {
               sb.append(buildTree(pred,i));

            }
        }
        sb.append("]");
        return sb.toString();
    }
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(3,0);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(4,3);
        graph.addEdge(4,2);

        graph.printGraph();
        graph.DFS(4);

    }
}
