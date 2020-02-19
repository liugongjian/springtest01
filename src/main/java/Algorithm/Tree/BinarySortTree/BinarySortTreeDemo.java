package Algorithm.Tree.BinarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {

        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree tree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++){
            tree.add(new BNode(arr[i]));
        }

        tree.infixOrder();

        tree.delNode(1);
        System.out.println("删除后");
        tree.infixOrder();


    }
}


class BinarySortTree{
    private BNode root;
        public void add(BNode node){
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

    public BNode search(int value){
        if (root == null){
            return null;

        }else {
            return root.search(value);
        }
    }

    public BNode searchParent(int value) {
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
    public int delRightTreeMin(BNode node){

        BNode target = node;
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
                BNode target = search(value);
                if (target == null){
                    return;
                }
                if (root.left == null && root.right == null){//如果这棵树只有root一个节点
                    root = null;
                    return;
                }
                BNode parent = searchParent(value);


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

class BNode{
    int value;
    BNode left;
    BNode right;

    public BNode(int value) {
        this.value = value;
    }

    public void add(BNode node){

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

    }


    public BNode search(int value){
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
    public BNode searchParent(int value){
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
        return "BNode{" +
                "value=" + value +
                '}';
    }
}
