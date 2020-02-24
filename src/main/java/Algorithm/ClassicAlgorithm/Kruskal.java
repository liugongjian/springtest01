package Algorithm.ClassicAlgorithm;

import java.util.Arrays;

public class Kruskal {

    private int edgeNum;
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    private static final int INF = Integer.MAX_VALUE;//表示两个顶点不能连通

    public static void main(String[] args) {

        char[] vertexs = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                /*A*//*B*//*c*//*D*//*E*//*F*//*G*/
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,5,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0}
        };

        Kruskal kruskal = new Kruskal(vertexs,matrix);
        kruskal.print();

        EData[] edges = kruskal.getEdges();

        System.out.println(Arrays.toString(edges));//未排序的

        kruskal.sortEdges(edges);
        System.out.println(Arrays.toString(edges));//排序后

        kruskal.kruskal();
    }

    public Kruskal(char[] vertexs, int[][] matrix){
        int vlen = vertexs.length;
        this.vertexs = new char[vlen];
        //初始化顶点
        for (int i = 0; i< vertexs.length; i++){
            this.vertexs[i] = vertexs[i];
        }
        //初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0;i<vlen;i++){
            for (int j = 0; j<vlen; j++){
                this.matrix[i][j] = matrix[i][j];

            }
        }

        for (int i = 0; i < vlen;i++){
            for (int j = i+1;j<vlen;j++){
                if (this.matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }

    }


    public void kruskal(){
        int index = 0;
        int[] ends = new int[edgeNum];

        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        EData[] edges = getEdges();
        sortEdges(edges);

        for (int i = 0; i <edgeNum; i++){
            //获取第i条边的第一个顶点(起点)
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnds(ends,p1);
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnds(ends,p2);
            //是否构成回路
            if(m != n){//没有构成回路
                ends[m] = n;// 设置m在“已有最小生成树”中的终点
                rets[index++] = edges[i];
            }
        }

        //统计并打印“最小生成树”
        System.out.println("最小生成树为=" + Arrays.toString(rets));
    }

    public void print(){
        System.out.println("邻接矩阵为：");
        for (int i = 0; i<vertexs.length;i++){
            for (int j = 0; j<vertexs.length;j++){
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序处理，冒泡
    private void sortEdges(EData[] edges){

        for (int i = 0; i< edges.length-1; i++){
            for (int j = 0; j < edges.length-1-i;j++ ){
                if (edges[j].weight>edges[j+1].weight){
                    EData tmp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = tmp;
                }
            }
        }
    }


    //返回某个顶点的下标
    private int getPosition(char vertex){

        for (int i = 0; i < vertexs.length; i++){
            if (vertexs[i] == vertex){
                return i;
            }
        }
        return -1;
    }

    //获取图中的边，放到数组中，后面我们需要遍历该数组EData[]
    private EData[] getEdges(){

        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++){
            for (int j=i+1; j<vertexs.length;j++){
                if (matrix[i][j] != INF){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //功能：获取下标为i的顶点的终点

    /**
     *
     * @param ends 记录了各个顶点对应的终点时哪一个
     * @param i 表示的是传入的顶点对应的下标
     * @return 返回的是下标为i的顶点，对应的终点的下标
     */
    private int getEnds(int[] ends,int i){

        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}


//创建一个类，他的一个对象实例表示一条边
class EData{
    char start;//边的一个点
    char end;//边的另一个点
    int weight;

    public EData(char start, char end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}