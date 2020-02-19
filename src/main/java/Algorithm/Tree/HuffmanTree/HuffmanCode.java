package Algorithm.Tree.HuffmanTree;

import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);//40

        List<HNode> nodes = getNodes(contentBytes);
        System.out.println("赫夫曼树");
        HNode huffmanTreeRoot = HNode.createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();


        getCodes(huffmanTreeRoot,"",stringBuilder);

        System.out.println("生成的哈夫曼编码表："+huffmanCodes);

        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);


        System.out.println("huffmancode:"+Arrays.toString(huffmanCodeBytes));//[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
        //共17个字符，原字符串共40个，压缩掉23个

        byte[] sourceBytes = decode(huffmanCodes,huffmanCodeBytes);
        System.out.println("原来的字符串："+new String(sourceBytes));
    }

    //解压思路：
    //1. 将huffmanBytes[-88,-65,....]重写先转成赫夫曼编码对应的二进制的字符串"1010100010111..."
    //2. 赫夫曼编码对应的二进制的字符串"1010100010111..." =》 对照赫夫曼编码 =》 "i like like ...."

    //解码：
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){
        //1.先得到huffmanBytes 对应的二进制的字符串，形式10110100010111...
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i< huffmanBytes.length;i++){
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length-1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //System.out.println("赫夫曼字节数组对应的二进制字符串="+stringBuilder.toString());

        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为需要反向查询
        Map<String,Byte> map = new HashMap<String,Byte>();
        for (Map.Entry<Byte,String> entry: huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ){
            int count = 1;//小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag){
                //取出一个1或者0
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b==null) {
                    count++;
                }else {
                    flag = false;
                }
            }

            list.add(b);
            i += count;//i直接移动到count个位置后
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i<b.length;i++){
            b[i] = list.get(i);
        }
        return b;
    }
    //将一个byte转成一个二进制字符串
    /**
     *
     * @param flag  表示是否需要补高位，true表示需要补高位，如果是false则不需要,如果是最后一个，则不需要补高位
     * @param b
     * @return
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;//将b转成int
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码；
        if (flag) {
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }
    //根据huffman编码表，根据输入的字节数组，生成huffman编码后的数组
    /**
     *
     * @param bytes
     * @param huffmanCodes
     * @return
     * 举例：String content = "i like like like java do you like a java";=>byte[] contentBytes = content.getByte;返回的是【字符串】"1010100010......"
     * => 对应的byte[] huffmanCodeBytes,即8位对应一个byte，放入到huffmanCodeBytes
     * 将字符串转换为byte：huffmanCodes[0] = 10101000(补码) => byte [推导 10101000 => 10101000 - 1 => 1010111(反码) => 11011000 ]
     *                    huffmanCodes[1]
     */
    private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes){
        //1. 先利用赫夫曼编码表，将bytes 转成 赫夫曼编码后的字符串
        StringBuilder stringBuilder1 = new StringBuilder();

        for (byte b: bytes){
            stringBuilder1.append(huffmanCodes.get(b));
        }

        //System.out.println("测试stringBuilderr = " + stringBuilder1.toString());
        //将字符串转成byte数组
        //1.统计返回的byte[]的长度
        int len;
        if (stringBuilder1.length() % 8 == 0){
            len = stringBuilder1.length() / 8;
        }else {
            len = stringBuilder1.length() / 8 + 1;
        }
        //2. 创建存储压缩后的byte数组
        byte[] huffmanCodesBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder1.length(); i += 8){//因为每8位对应一个byte，所以步长+8

            String strByte;

            if (i+8 > stringBuilder1.length()){
                strByte = stringBuilder1.substring(i);
            }else {
                strByte = stringBuilder1.substring(i,i+8);
            }
            //将strByte转成一个byte，放入到huffmanCodeByte
            huffmanCodesBytes[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }

        return huffmanCodesBytes;
    }


    //生成huffman编码表
    //编码表存放在Map中，Map<Byte,String>
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    //<32,01>,<97,100>,<85,10010>,后面的是编码，前面的是字母的ascii值
    static StringBuilder stringBuilder = new StringBuilder();

    //根据传入的node节点的所有叶子节点的信息，生成Huffman编码

    /**
     *
     * @param node
     * @param code 路径：左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接code
     */
    private static void getCodes(HNode node,String code, StringBuilder stringBuilder){

        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

        stringBuilder2.append(code);
        if (node != null){
            if (node.data == null){//非叶子节点
                //向左
                getCodes(node.left,"0",stringBuilder2);
                //向右
                getCodes(node.right,"1",stringBuilder2);
            }else {//说明是一个叶子节点

                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }

    private static void preOrder(HNode root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("树为空");
        }
    }


    private static List<HNode> getNodes(byte[] bytes){
        ArrayList<HNode> nodes = new ArrayList<>();

        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b:bytes){
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            }else{
                counts.put(b,count+1);
            }

        }

        for (Map.Entry<Byte,Integer> entry: counts.entrySet()){
            nodes.add(new HNode(entry.getKey(),entry.getValue()));
        }

        return nodes;
    }

}



class HNode implements Comparable<HNode>{

    Byte data;//存放数据（字符）本身
    int weight;//权值，表示字符出现的次数

    HNode left;
    HNode right;

    public HNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(HNode o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "HNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }


    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public static HNode createHuffmanTree(List<HNode> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            //取出第一个最小的二叉树
            HNode leftNode = nodes.get(0);
            HNode rightNode = nodes.get(1);

            HNode parent = new HNode(null,leftNode.weight+rightNode.weight);

            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);

        }
        return nodes.get(0);
    }
}


