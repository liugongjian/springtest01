package Algorithm.Tree.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {

    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};

        Node root = createHuffManTree(arr);

        preOrder(root);

    }

    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("是空树，不能遍历");
        }
    }

    public static Node createHuffManTree(int[] arr){
        //1. 遍历arr数组
        //2. 将arr的每个元素构成一个node
        //3. 将Node放入ArrayList中
        List<Node> nodes = new ArrayList<Node>();

        for (int value : arr){
            nodes.add(new Node(value));
        }

        while (nodes.size()>1) {
            //从小到大排序
            Collections.sort(nodes);

            System.out.println("nodes = " + nodes);

            //取出根节点权值最小的两个二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);

            nodes.add(parent);
            //Collections.sort(nodes);
        }

        return nodes.get(0);

    }
}

class Node implements Comparable<Node>{
    int value;//节点权值
    Node left;//指向左子节点
    Node right;//指向左子节点

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value-o.value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}
