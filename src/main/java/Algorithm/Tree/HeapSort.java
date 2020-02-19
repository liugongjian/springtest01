package Algorithm.Tree;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int arr[] = {4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void heapSort(int[] arr){
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次："+ Arrays.toString(arr));
//
//
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次："+ Arrays.toString(arr));

        //形成第一次大顶堆（9，6，8，5，4）
        int temp;
        for(int i = arr.length /2-1;i >= 0; i--){
            adjustHeap(arr,i,arr.length);
        }

        for (int j = arr.length-1;j>0;j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }

    }
    //将一个数组（二叉树），调整成一个大顶堆

    /**
     *
     * @param arr
     * @param i 非叶子节点在数组中的索引
     * @param length 待排序的数组长度
     */
    public static void adjustHeap(int arr[], int i, int length){

        int temp = arr[i];
        //开始调整
        //k=i*2+1,找到i的左子节点
        for (int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length && arr[k]<arr[k+1]){//说明左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if (arr[k]>temp){//如果子节点>父节点
                arr[i] = arr[k];//把较大的那个子节点的值赋予当前节点
                i=k;//!!!i指向k，然后循环比较
            }else {
                break;
            }
        }
        //当for循环结束后，已经将以i为父节点的树的最大值，放在了最顶（局部）

        arr[i] = temp;//将temp的值放到调整后的位置
    }
}
