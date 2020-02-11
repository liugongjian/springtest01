package Algorithm.Sort;

import java.util.Arrays;

public class ShellSort {
    //希尔排序之交换法
    public static void shellSort1(int[] arr){

        int count = 1;
        //gap是步长
        for(int gap = arr.length / 2; gap>0; gap /= 2){
            int temp = 0;

            for (int i= gap;i<arr.length;i++){
                for (int j = i - gap; j>=0;j-=gap){
                    if(arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            System.out.println("第"+(count++) +"轮："+ Arrays.toString(arr));
        }
        System.out.println("第"+(count++) +"轮："+ Arrays.toString(arr));
    }
    //希尔排序之移位法
    public static void shellSort2(int[] arr){

        int count = 1;
        //gap是步长
        for(int gap = arr.length / 2; gap>0; gap /= 2){
            for (int i= gap;i<arr.length;i++){
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j-gap]){
                    while (j-gap>=0 && temp<arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }

                    arr[j] = temp;
                }
            }
        }
        System.out.println("第"+(count++) +"轮："+ Arrays.toString(arr));
    }



    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0,11};
        shellSort2(arr);
    }




}
