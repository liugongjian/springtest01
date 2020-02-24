package Algorithm.ClassicAlgorithm;

import java.util.Arrays;

public class Prim {

    public static void main(String[] args) {

        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int vertexs = data.length;
        //邻接矩阵的关系用二维数组描述,10000表示两个点不连通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        MGraph mGraph = new MGraph(vertexs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,vertexs,data,weight);
        minTree.showGraph(mGraph);

        minTree.prim(mGraph,0);
    }
}
class MinTree{
    public void createGraph(MGraph graph, int vertexs, char[] data, int[][] weight){

        int i,j;
        for (i = 0; i<vertexs;i++){
            graph.data[i] = data[i];
            for (j=0; j<vertexs;j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }


    //prim算法，得到最小生成树

    /**
     *
     * @param graph
     * @param v  从哪个顶点开始
     */
    public void prim(MGraph graph, int v){

        //标记顶点是否被访问过，java初始化数组后，默认里面的成员就是0；
        int[] visited = new int[graph.vertexs];

        visited[v] = 1;
        //记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int miniWeight = 10000;

        for (int k = 1; k < graph.vertexs; k++){//prim算法结束后，有graph.vertexs -1 条边，因此循环这些次


            for (int i = 0; i < graph.vertexs; i++){//遍历访问过的节点
                for (int j = 0; j<graph.vertexs; j++){//遍历未访问的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < miniWeight){
                        miniWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小的
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+"> 权值："+miniWeight);
            visited[h2] = 1;
            miniWeight = 10000;
        }
    }
}

//图
class MGraph{
    int vertexs;//图中的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，就是我们的邻接矩阵

    public MGraph(int vertexs){
        this.vertexs =  vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }
}
