package Algorithm.ClassicAlgorithm;

import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        //String str2 = "ABCDABD";
        String str2 = "ABCDABD";

        int[] next = kmpNext("ABCDABD");
        System.out.println("next = "+ Arrays.toString(next));
        int index = kmpSearch(str1,str2,next);
        System.out.println("index = " + index);
        
    }

    /**
     *
     * @param str1 源串
     * @param str2 匹配串
     * @param next 部分匹配表
     * @return
     */
    public static int kmpSearch(String str1,String str2,int[] next){

        for (int i = 0,j = 0; i < str1.length() ; i++){

            //kmp核心点
            while ( j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    //获取一个字符串（字串）的部分匹配值
    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];//保存部分匹配值
        next[0] = 0;//如果字符串长度为1，那么部分匹配值就是0
        for (int i = 1,j=0; i<dest.length();i++){

            //当dest.charAt(i) != dest.charAt(j)，需要从next[j-1]中获取新的j
            //直到发现有dest.charAt(i) == dest.charAt(j) 才退出
            //此处是kmp算法的核心点
            while (j>0 && dest.charAt(i) != dest.charAt(j)){
                j=next[j-1];
            }
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }
}
