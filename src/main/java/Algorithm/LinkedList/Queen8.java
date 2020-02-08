package Algorithm.LinkedList;

public class Queen8 {


    int max = 8;//表示有多少个皇后
    int[] array = new int[max];//保存皇后拜访的位置，如array = {0.4.3.7.5.2.1}
    static int count = 0;
    public static void main(String[] args) {

        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法",count);

    }

    //每一层check里面都有一个for循环，因此会产生回溯（）
    private void check(int n){//n从0开始，表示第几个的皇后
        if (n==max){
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i = 0;i<max;i++){
            //先把当前这个皇后n，放到该行的第一个
            array[n]=i;

            //判断当放至第n个皇后到i列时，是否冲突
            if(judge(n)){
                //不冲突
                //接着放n+1个皇后，开始递归
                check(n+1);
            }
            //如果冲突，就继续执行这个array[n]=i;即将第n个皇后，放置在本行的后移一个位置
        }
    }


    private boolean judge(int n){

        for (int i = 0;i<n;i++){

            //1.array[i]==array[n] 判断第n个皇后和第i个皇后是否同一列
            //2.Math.abs(n-i) == Math.abs(array[n]-array[i] 表示判断第n个皇后和第i个皇后是否再一条斜线上
            if(array[i]==array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    private void print(){
        count++;
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
