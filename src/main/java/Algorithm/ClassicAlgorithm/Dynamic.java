package Algorithm.ClassicAlgorithm;

//动态规划算法-背包问题
public class Dynamic {

    public static void main(String[] args) {
        int[] w = {1,4,3};//每个物品的重量
        int[] val = {1500,3000,2000};//每个物品的价格
        int m=4;//背包的容量
        int n=val.length;//物品的个数
        int[][] path = new int[n+1][m+1];

        //创建二维数组
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];

        //初始化第一行和第一列，在本程序中可以不处理，默认是0；
        for (int i=0;i<v.length;i++){
            v[i][0]=0;//第一列
        }
        for (int i = 0; i<v[0].length;i++){
            v[0][i]=0;
        }

        //根据前面得到的公式来动态规划
        for (int i=1;i<v.length;i++){
            for (int j=1;j<v[i].length;j++){
                //公式
                if (w[i-1]>j){
                    v[i][j] = v[i-1][j];
                }else {
                    v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                }
            }
        }



    }
}
