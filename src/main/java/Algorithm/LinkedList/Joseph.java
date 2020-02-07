package Algorithm.LinkedList;

public class Joseph {

    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoys(1,2,5);
    }

}

//环形单向链表
class CircleSingleLinkedList{
    private Boy first = null;
    public void addBoy(int nums){
        if(nums<1){
            System.out.println("nums值不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i<=nums; i++){
            Boy boy = new Boy(i);
            if(i==1){
                first = boy;
                first.setNext(first);//只有一个的时候，指向自己（环形）
                curBoy = first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }


        }
    }

    /**
     *
     * @param startNo 数小孩的开始位置
     * @param countNo 表示数几下
     * @param nums 表示最开始有几个小孩
     */
    public void countBoys(int startNo, int countNo, int nums){

        if(first == null || startNo <1 || startNo > nums){
            System.out.println("参数输入有误");
            return;
        }
        Boy helper = first;
        while (true){//让helper指向最后一个小孩
            if(helper.getNext() == first){
                break;
            }else {
                helper = helper.getNext();
            }
        }
        //小孩儿报数前，先让first和helper移动
        for(int j=0;j<startNo-1;j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        while(true){
            if(helper == first){
                break;
            }
            for(int j=0;j<countNo-1;j++){
                first=first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩儿%d出圈",first.getNo());
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.printf("最后留在圈中的小孩：%d",first.getNo());



    }

    //遍历环形链表
    public void showBoy(){
        if(first == null){
            System.out.println("没有任何小孩");
            return;
        }
        Boy curBoy = first;

        while (true){
            System.out.printf("小孩的编号：%d \n",curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }
}

class Boy{
    private int no;

    private Boy next;

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
