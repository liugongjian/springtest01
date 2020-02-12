package Algorithm.Tree;

public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"1");
        HeroNode node2 = new HeroNode(2,"2");
        HeroNode node3 = new HeroNode(3,"3");
        HeroNode node4 = new HeroNode(4,"4");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);

        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();


        System.out.println("后序遍历");
        binaryTree.postOrder();
    }
}


class BinaryTree{

    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }


    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }



}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);

        if(this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    //中序
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    //后序
    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }

        if (this.right != null){
            this.right.postOrder();
        }

        System.out.println(this);
    }
}
