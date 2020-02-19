package Algorithm.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {


    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//边的数量

    //记录某个节点是否被访问过
    private boolean[] isVisited;

    public static void main(String[] args) {


        int n = 5;
        String[] VertexValue = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for (String value : VertexValue){
            graph.insertVertex(value);
        }
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);//A-B
        graph.insertEdge(0,2,1);//A-C
        graph.insertEdge(1,2,1);//B-C
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        //显示邻接矩阵
        graph.showGraph();
//
//        graph.dfs();//A-B-C-D-E
        graph.bfs();

    }

    public Graph(int n){
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }


    //得到第一个邻接节点的下标,如果不存在返回-1；
    public int getFirstNeighbor(int index){
        for (int j = 0 ; j < vertexList.size(); j++){
            if (edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNexNeighbor(int v1, int v2){
        for (int j = v2+1 ;j < vertexList.size();j++){
            if (edges[v1][j] > 0){
                return j;
            }
        }

        return -1;
    }

    //深度优先遍历
    //i第一次是0，访问第一个节点
    public void dfs(boolean[] isVisited, int i){
        System.out.print(getValueByIndex(i)+"->");
        //将当前节点设置为一访问
        isVisited[i] = true;
        //下一个i的邻接节点
        int w = getFirstNeighbor(i);
        while (w != -1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果w已经被访问过
            w = getNexNeighbor(i,w);
        }
    }
    //遍历dfs
    public void dfs(){
        for (int i = 0; i<getNumOfVertex();i++){
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    private void bfs(){
        for (int i = 0; i < getNumOfVertex(); i ++){
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    //对一个节点的广度优先遍历
    private void bfs(boolean[] isVisited, int i ){
        int u;//表示队列头节点对应的下标
        int w;//邻接节点w
        LinkedList queue = new LinkedList();//队列，记录节点访问的顺序
        System.out.print(getValueByIndex(i)+"=>");
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()){
            //取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接节点的下标
            w = getFirstNeighbor(u);
            while (w != -1){
                //是否访问过
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w)+"=>");
                    //标记已访问
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接点
                w = getNexNeighbor(u,w);

            }
        }
    }

    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


    public int getNumOfVertex(){
        return vertexList.size();
    }
    public int getNumOfEdges(){
        return numOfEdges;
    }
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }
    public void showGraph(){
        for (int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

}
