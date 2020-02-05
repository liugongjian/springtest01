package Algorithm.LinkedList;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1,"songjiang","jishiyu");
        HeroNode h2 = new HeroNode(2,"h2","h2");
        HeroNode h3 = new HeroNode(3,"h3","h3");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(h1);
//        singleLinkedList.add(h2);
        singleLinkedList.addByOrder(h1);
        singleLinkedList.addByOrder(h3);
        singleLinkedList.addByOrder(h2);
        singleLinkedList.list();
        System.out.println("修改后");
//        singleLinkedList.modify(2,"h22","h22");
//        singleLinkedList.list();
        singleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
    }
}

class SingleLinkedList{
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead(){
        return head;
    }

    public void add(HeroNode heroNode){
        HeroNode temp = head;

        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next=heroNode;
    }



    //按照排名no来顺序添加，如果已经有相应的排名，那么提示失败
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){
                break;
            } else if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            System.out.printf("准备插入的英雄的编号已经存在，不能加入：%d",heroNode.no);

        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }



    }

    public void reverseList(HeroNode head){

        if(head.next==null || head.next.next==null){
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");

        while(cur != null){
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    public void modify(int no,String name,String nickname){
        if(head.next == null){
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if(temp == null){
                break;//已经遍历完
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.name = name;
            temp.nickname = nickname;
        }else {
            System.out.printf("没有找到该节点：%d",no);
        }
    }

    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}


class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname){

        this.no = no;
        this.name = name;
        this.nickname = nickname;

    }

    @Override
    public String toString() {
        return no+name+nickname;
    }
}