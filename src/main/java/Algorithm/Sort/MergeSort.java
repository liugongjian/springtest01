package Algorithm.Sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {


        int[] arr = {3,5,-9,8,2,16};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));

    }


    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left<right){
            int mid = (left+right)/2;
            mergeSort(arr,left,mid,temp);
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }

    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;// 初始化i，左边有序序列的初始索引
        int j = mid +1;//初始化j，右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        /**
         * （1）先把左右两边（有序）的数据按照规则填充到temp数组
         * 直到左右两边的有序序列，有一边处理完毕为止
         */
        while(i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t] = arr[i];
                t +=1;
                i +=1;
            }else {
                temp[t] = arr[j];
                t +=1;
                j +=1;
            }
        }

        /**
         * （2）把有剩余数据的一边的数据全部填充到temp
         */

        while (i<=mid){
            temp[t]=arr[i];
            t += 1;
            i += 1;
        }
        while (j<=right){
            temp[t]=arr[j];
            t += 1;
            j += 1;
        }

        /**
         * （3）将temp数组的元素拷贝到arr
         */

        t=0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t +=1;
            tempLeft +=1;
        }
    }
}
