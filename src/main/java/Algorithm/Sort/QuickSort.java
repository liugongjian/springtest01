package Algorithm.Sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,-1,0,23,-567,79,800,-86};

        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }


    public static void quickSort(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int pivot = arr[(left+right)/2];
        int temp;
        while (l<r){
            //找到左边大于pivot的值的下标
            while ( arr[l] < pivot){
                l += 1;
            }
            //找到右边小于pivot的值的下标
            while ( arr[r] > pivot){
                r -= 1;
            }
            //说明，pivot左右两边的值，已经都符合左小右大了
            if (l>=r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;


            //如果交换完后，发现这个arr[l] == pivot的值，则前移,r--;
            if (arr[l] == pivot){
                r-=1;
            }
            if (arr[r] == pivot){
                l+=1;
            }
        }

        if(l == r){
            l +=1;
            r -=1;
        }
        //向左递归
        if(left<r){
            quickSort(arr,left,r);
        }
        if(right>l){
            quickSort(arr,l,right);
        }
    }
}
