package Algorithm.Tree.BinarySortTree.AVLTree;

public class AVLTreeDemo {

    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        int[] arr = {10,7,11,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0 ; i < arr.length ; i++ ){
            avlTree.add(new AVLNode(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();


        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
        System.out.println(avlTree.getRoot().left.value);
        System.out.println(avlTree.getRoot().left.left.value);



    }

}


class AVLTree{
    private AVLNode root;

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
        this.root = root;
    }

    public void add(AVLNode node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("树为空，不能遍历");
        }
    }

    public AVLNode search(int value){
        if (root == null){
            return null;

        }else {
            return root.search(value);
        }
    }

    public AVLNode searchParent(int value) {
        if (root == null) {
            return null;

        } else {
            return root.searchParent(value);
        }
    }


    /**
     *
     * @param node 传入的节点，当作二叉排序树的根节点
     * @return 返回的是，以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(AVLNode node){

        AVLNode target = node;
        //循环查找左节点，就会找到最小值
        while (target.left != null){
            target = target.left;
        }
        //target指向最小节点
        delNode(target.value);//删除最小的节点
        return target.value;
    }


    public void delNode(int value){
        if (root == null){
            return;
        }else {
            AVLNode target = search(value);
            if (target == null){
                return;
            }
            if (root.left == null && root.right == null){//如果这棵树只有root一个节点
                root = null;
                return;
            }
            AVLNode parent = searchParent(value);


            if (target.left == null && target.right == null){//1.如果是叶子节点
                //target是parent的左子节点，还是右子节点
                if (parent.left != null && parent.left.value == value){
                    parent.left = null;
                }else if (parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            }else if(target.left !=null && target.right != null){//2.删除有两个子树的节点

                //从右子树找最小的节点-》删除掉最小节点—》把最小节点的值赋给当前要删除的节点
                int minVal = delRightTreeMin(target.right);
                target.value = minVal;

            }else {//3.删除有一颗子树的节点
                //如果要删除的节点有左子节点
                if (target.left != null){//如果target是parent的左子节点
                    if (parent.left.value == value){
                        parent.left = target.left;
                    }else {//target是parent的右子节点
                        parent.right = target.left;
                    }
                }else {//如果要删除的节点有右子节点

                    if (parent.left.value == value){
                        parent.left = target.right;
                    }else {
                        parent.right = target.right;
                    }
                }
            }
        }
    }
}

class AVLNode{
    int value;
    AVLNode left;
    AVLNode right;

    public AVLNode(int value) {
        this.value = value;
    }

    //返回该节点为根节点的树高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height())+1;
    }

    //返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }


    //左旋转——右子树的高度降低1
    private void leftRotate(){
        //创建一个新的节点，以当前root的值
        AVLNode newNode = new AVLNode(value);

        //把新的节点的左子树设置为当前节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置为带你过去节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点的右子节点的右子树
        right = right.right;
        //把当前节点的左子树（左子节点）设置成新的节点
        left = newNode;
    }

    //右旋转——左子树的高度降低1
    private void rightRotate(){
        AVLNode newNode = new AVLNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }


    public void add(AVLNode node){

        if (node == null){
            return;
        }
        if (node.value < this.value){
            if (this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }

        //当添加完一个节点后，如果(右子树的高度-左子树的高度) > 1,进行左旋转
        if (rightHeight() - leftHeight() > 1 ){
            //如果它的右子节点的左子树的高度>右子树的高度
            //先对它的右子节点，进行右旋转，然后再对当前节点进行左旋转
            if(right != null && right.leftHeight()>right.rightHeight()){

                right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
            }
            return;//必须加，要不再进行下面的处理比较危险？
        }
        //当添加完一个节点后，如果(左子树的高度-右子树的高度) > 1,进行右旋转
        if (leftHeight() - rightHeight() > 1 ){
            if (left != null && left.rightHeight()>left.leftHeight()) {
                //先对当前节点的左子树-》左旋转
                left.leftRotate();
                rightRotate();
            } else {
                //直接进行右旋转就可以了
                rightRotate();
            }
        }

    }


    public AVLNode search(int value){
        if (value == this.value){
            return this;
        }else if (value<this.value){
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        }else {
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找节点的父节点
    public AVLNode searchParent(int value){
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else {
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);
            }else {
                return null;//没找到父节点
            }
        }
    }


    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "value=" + value +
                '}';
    }
}
