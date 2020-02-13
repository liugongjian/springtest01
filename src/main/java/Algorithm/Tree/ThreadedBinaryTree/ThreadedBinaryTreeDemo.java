package Algorithm.Tree.ThreadedBinaryTree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"1");
        HeroNode node2 = new HeroNode(2,"2");
        HeroNode node3 = new HeroNode(3,"3");
        HeroNode node4 = new HeroNode(4,"4");
        HeroNode node5 = new HeroNode(5,"5");
        HeroNode node6 = new HeroNode(6,"6");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes(root);


//        HeroNode leftnode = node5.getLeft();
//        HeroNode rightnode = node5.getRight();
//        System.out.println("5号节点的中序前驱节点是"+leftnode.getName());
//        System.out.println("5号节点的中序前驱节点是"+rightnode.getName());

        System.out.println("线索化遍历二叉树");
        threadedBinaryTree.threadedList();
    }

}

//线索化二叉树
class ThreadedBinaryTree{

    private HeroNode root;

    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root){
        this.root = root;
    }


    public void threadedList(){
        HeroNode node = root;
        while (node != null){
            //1.如果leftType==0 表示左子树，如果1则表示指向前驱节点
            //2.如果rightType==0 表示右子树，如果1则表示指向后继节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }


    //中序线索化
    public void threadedNodes(HeroNode node){
        if (node == null){
            return;
        }

        //（一）先线索化左子树
        threadedNodes(node.getLeft());
        //（二）线索化当前节点

        //处理当前节点的前驱节点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }

        //处理后继节点
        if(pre != null && pre.getRight() == null){
            //让前驱节点的右指针指向当前指针
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //（三）再线索化右子树
        threadedNodes(node.getRight());
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


    public HeroNode preOrderSearch(int no){
        if (root != null){
            return this.root.preOderSearch(no);
        }else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return this.root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode postOrderSearch(int no){
        if (root != null){
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }


    //删除节点
    public void delNode(int no){
        if (root != null){
            if (root.getNo()==no){
                root = null;
            }else {
                root.delNode(no);
            }
        }
    }
}


class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    //1.如果leftType==0 表示左子树，如果1则表示指向前驱节点
    //2.如果rightType==0 表示右子树，如果1则表示指向后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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


    //前序遍历查找
    public HeroNode preOderSearch(int no){
        if(this.no == no){
            return this;
        }

        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.preOderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找

    public HeroNode infixOrderSearch(int no){
        HeroNode resNode =null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后续遍历查找

    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null ){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null ){
            return resNode;
        }
        if(this.no == no){
            return  this;
        }
        return resNode;

    }


    public void delNode(int no){
        if (this.left !=null && this.left.no == no ){
            this.left = null ;
            return;
        }
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }
    }
}


