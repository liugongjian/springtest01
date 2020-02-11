package Algorithm.Sort;

import java.util.Arrays;

public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {304,190,1,10};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int insertVal = arr[i];
            int insertIndex = i-1;
            while (insertIndex>=0 && insertVal< arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }

            arr[insertIndex+1] = insertVal;

        }
    }
}
