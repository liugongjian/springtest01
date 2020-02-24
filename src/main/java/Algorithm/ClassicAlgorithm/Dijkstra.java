package Algorithm.ClassicAlgorithm;

import java.util.Arrays;

//解决最短路径问题
public class Dijkstra {


    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};

        Graph graph = new Graph(vertex,matrix);
        graph.showGraph();


        graph.dsj(6);
    }
}


class Graph{

    private char[] vertex;//顶点
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;


    public Graph(char[] vertex, int[][] matrix){

        this.vertex = vertex;
        this.matrix = matrix;

    }
    public void showGraph(){
        for (int[] link : matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    //表示出发顶点对应的下标
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length,index);
        update(index);//更新index顶点到周围顶点的距离和前驱顶点
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱节点
    private void update(int index){
        int len = 0;
        //根据遍历我们的邻接矩阵的matrix[index]行
        for (int j = 0; j<matrix[index].length; j++){
            //len的含义是：出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = vv.getDis(index)+matrix[index][j];
            //如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!vv.in(j) && len < vv.getDis(j)){
                vv.updatePre(j,index);
                vv.updateDis(j,len);
            }

        }
    }


}

class VisitedVertex{
    //记录各个顶点是否访问过
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //出发顶点到其它所有顶点的距离，会动态更新
    public int[] dis;


    public VisitedVertex(int length, int index){
        this.already_arr = new int[length];

        this.pre_visited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis,65535);
        this.already_arr[index] = 1;//设置出发顶点被访问过，为1
        this.dis[index] = 0;//设置出发顶点的访问举例为0；
    }

    //判断index顶点是否被访问过，访问过就true
    public boolean in(int index){

        return already_arr[index] == 1;
    }

    //更新出发顶点到index顶点的距离
    public void updateDis(int index, int len){

        dis[index] = len;

    }

    //更新pre顶点的前驱节点为index节点
    public void updatePre(int pre, int index){

        pre_visited[pre] = index;
    }

    //返回出发顶点到index顶点的距离
    public int getDis(int index){

        return dis[index];
    }

}
