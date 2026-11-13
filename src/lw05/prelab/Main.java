package lw05.prelab;

import java.util.*;

public class Main {
    static int size;
    static String[] names;
    static int[][] graph;
    static void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    static List<Integer> bfs(int start) {
        List<Integer> bfsOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            bfsOrder.add(current);
            for (int i = 0; i < size; i++) {
                if (graph[current][i] != 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }

        return bfsOrder;
    }

    static void dfs(int current, Set<Integer> visited, List<Integer> dfsOrder) {
        visited.add(current);
        dfsOrder.add(current);

        for (int i = 0; i < size; i++) {
            if (graph[current][i] != 0 && !visited.contains(i)) {
                dfs(i, visited, dfsOrder);
            }
        }
    }

    static int findTheNextNode(int[] distance, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int nextNode = -1;
        for (int i = 0; i < size; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                nextNode = i;
            }
        }

        return nextNode;
    }

    static void route(int[] prev, int destination) {
        if (prev[destination] != -1) {
            route(prev, prev[destination]);
            System.out.print(" -> ");
        }

        System.out.print(names[destination]);
    }

    static void dijkstraAlg(int start, int destination) {
        int[] distance = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];
        for (int i = 0; i < size; i++) {
            distance[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        distance[start] = 0;

        for (int i = 0; i < size; i++) {
            int current = findTheNextNode(distance, visited);
            if (current == -1) {
                break;
            }
            visited[current] = true;
            for (int j = 0; j < size; j++) {
                if (graph[current][j] != 0 && !visited[j]) {
                    int newDistance = distance[current] + graph[current][j];
                    if (newDistance < distance[j]) {
                        distance[j] = newDistance;
                        prev[j] = current;
                    }
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");

        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + distance[i] + " min");
        }

        System.out.println("=== Shortest Route from " +
                names[start] + " to " + names[destination] + " ===");
        route(prev, destination);
        System.out.println();
        System.out.println("Total time: " + distance[destination] + " min");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(
                Main.class.getResourceAsStream("campus.txt"));

        size = sc.nextInt();
        names = new String[size];
        graph = new int[size][size];

        for (int i = 0; i < size; i++) {
            names[i] = sc.next();
        }

        int E = sc.nextInt();

        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();

            addEdge(u, v, weight);
        }

        int start = sc.nextInt();
        int destination = sc.nextInt();

        sc.close();

        System.out.println("=== BFS from " + names[start] + " ===");
        List<Integer> bfsOrder = bfs(start);
        printPath(bfsOrder);
        System.out.println("=== DFS from " + names[start] + " ===");
        List<Integer> dfsOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        dfs(start, visited, dfsOrder);
        printPath(dfsOrder);
        dijkstraAlg(start, destination);
    }
}
