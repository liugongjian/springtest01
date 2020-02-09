package Algorithm.Sort;

import java.util.Arrays;

public class OptimizedBubbleSort {

    public static void main(String[] args) {
        int arr[] = {1,2,5,-1,0};
        int temp = 0;
        boolean flag = false;

        for(int i = 0 ; i<arr.length-1; i++){
            for (int j = 0; j<arr.length-i-1; j++){
                if(arr[j]>arr[j+1]){
                    flag = true;
                    temp = arr[j];

                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }

            if(!flag){
                break;
            }else {
                flag = false;
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
