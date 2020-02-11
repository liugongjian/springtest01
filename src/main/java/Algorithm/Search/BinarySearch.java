package Algorithm.Search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,3,99,100,100,121};
//        int resIndex = binarySearch(arr,0,arr.length-1,12);
//        System.out.println(resIndex);

        List resIndexList = binarySearchAll(arr,0,arr.length-1,100);
        System.out.println(resIndexList);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal){

        //如果left > right说明没有找到，则返回-1；
        if(left > right){
            return -1;
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];

        if(findVal>midVal){
            return binarySearch(arr, mid+1,right,findVal);
        }else if(findVal<midVal){
            return binarySearch(arr, left,mid-1,findVal);
        }else {
            return mid;
        }
    }


    //找到所有匹配的值，如果有多个重复的值
    public static ArrayList<Integer> binarySearchAll(int[] arr, int left, int right, int findVal){

        //如果left > right说明没有找到，则返回-1；
        if(left > right){
            return new ArrayList<>();
        }
        int mid = (left+right)/2;
        int midVal = arr[mid];

        if(findVal>midVal){
            return binarySearchAll(arr, mid+1,right,findVal);
        }else if(findVal<midVal){
            return binarySearchAll(arr, left,mid-1,findVal);
        }else {
            ArrayList<Integer> resIndexList = new ArrayList<Integer>();
            int temp = mid - 1;
            while (true){
                if(temp<0 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp -=1;
            }
            resIndexList.add(mid);
            temp = mid + 1;
            while (true){
                if(temp > arr.length-1 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp +=1;
            }

            return resIndexList;

        }
    }

}
