package Algorithm.Search;

import java.util.Arrays;

public class FibonacciSearch {


    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};

        System.out.println(fibSearch(arr,89));
    }

    //得到一个斐波那契数列
    public static int[] fib(){

        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2;i<maxSize;i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }


    //非递归的方式
    public static int fibSearch(int[] a, int findVal){//返回对应的下标

        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;
        int f[] = fib();//获取斐波那契数列
        //获取到fib数列的下标
        while (high > f[k] -1){
            k++;
        }
        //因为f[k]的值可能大于a的长度，因此需要构造一个Arrays类，构造一个新的数组，并指向a[]
        //不足的部分由0填充

        int[] temp = Arrays.copyOf(a,f[k]);
        //实际上需求使用a数组最后的数填充temp

        for (int i = high + 1; i<temp.length;i++ ){
            temp[i] = a[high];
        }
        while (low <= high){
            mid = low + f[k-1]-1;
            if(findVal < temp[mid]){
                high = mid-1;
                /**
                 * 为什么是k--：1.全部元素 = 前面的元素+后边的元素
                 * 2. f[k] = f[k-1]+f[k-2]
                 *
                 */
                k--;
            }else if(findVal > temp[mid]){//右边查找
                low = mid+1;
                k -= 2;

            }else {
                if(mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }
        }

        return -1;

    }


}
