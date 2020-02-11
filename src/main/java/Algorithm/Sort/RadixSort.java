package Algorithm.Sort;


import java.util.Arrays;

//空间换时间的经典算法:桶排序(计数排序)
public class RadixSort {


    public static void main(String[] args) {

        int[] arr = {53,3,542,748,14,214};
        radixSort(arr);

    }

    public static void radixSort(int[] arr){

        //1. 得到数组中最大的数的位数
        int max = arr[0];
        for(int i=0;i<arr.length;i++){
            if (arr[i]>max){
                max = arr[i];
            }
        }
        int maxLength = (max+"").length();


        //第一轮（针对每个元素的个位进行排序处理）

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];
        //记录每个桶实际存放了多少变量，我们定义一个一维数组，来记录各个桶每次放入数据的个数
        int[] bucketElementCount = new int[10];




        for(int i = 0, n=1 ; i<maxLength;i++,n*=10){
            //针对每一位,个位、十位、百位
            for (int j=0; j<arr.length;j++){
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
                bucketElementCount[digitOfElement]++;
            }

            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //遍历每一个桶中的数据，放入到原数组
            for(int k = 0; k<bucketElementCount.length;k++){
                //如果第K个桶有数据
                if (bucketElementCount[k] !=0){
                    //循环第K个桶，放入
                    for(int l=0;l<bucketElementCount[k];l++){
                        //放入arr中
                        arr[index] = bucket[k][l];
                        index++;
                    }

                }
                //每轮处理过后，需要将bucketElementCount[k]置0；
                bucketElementCount[k] = 0;
            }
            System.out.printf("第%d轮：%s\n",i+1, Arrays.toString(arr));
        }

    }
}
