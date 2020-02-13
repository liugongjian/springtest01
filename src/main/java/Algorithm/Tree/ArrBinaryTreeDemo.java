package Algorithm.Tree;

public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
    }
}


class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }

    public void preOrder(int index){
        if (arr==null || arr.length == 0){
            System.out.println("素组为空");
        }
        System.out.println(arr[index]);
        if (2 * index +1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index +2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}