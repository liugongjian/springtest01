package Algorithm.Hash;

public class HashTabDemo {

    public static void main(String[] args) {

    }
}


class Emp{
    public int id;
    public String name;
    public Emp next;
    public Emp(int id, String name){
        super();
        this.id = id;
        this.name = name;
    }

}


class HashTab{
    private EmpLinkedList[] empLinkedListsArray;

    private int size ;
    public HashTab(int size){
        this.size = size;
        empLinkedListsArray = new EmpLinkedList[size];
        for(int i = 0; i < size; i++){
            empLinkedListsArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp){
        int empLinkedListNO = hashFun(emp.id);

        empLinkedListsArray[empLinkedListNO].add(emp);

    }

    //遍历所有的链表
    public void list(){
        for (int i = 0; i < size; i++){
            empLinkedListsArray[i].list();
        }
    }

    public void findEmpById(int id){
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListsArray[empLinkedListNO].findById(id);
        if(emp != null){
            System.out.println("找到该雇员");
        }else {
            System.out.println("未找到该雇员");
        }
    }

    public int hashFun(int id){
        return id % size;
    }
}

class EmpLinkedList{
    private Emp head = null;

    //假定新增员工id是自增长的
    public void add(Emp emp){
        if(head == null){
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }


    public void list(){
        if(head == null){
            System.out.println("当前链表为空");
            return;
        }
        System.out.println("当前链表的信息为：");
        Emp curEmp = head;
        while (true){
            System.out.printf("=>id=%d,name=%s\t",curEmp.id,curEmp.name);
            if (curEmp.next == null){
                break;
            }
        }
    }


    public Emp findById(int id){
        if(head == null){
            System.out.println("链表空");
            return null;
        }
        Emp curEmp = head;
        while (true){
            if(curEmp.id == id){
                break;
            }
            if (curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}
