package Algorithm.LinkedList;

import java.util.Stack;

public class Calculator {
    public static void main(String[] args) {

        String expression = "3+2*6-2";

        Mystack numStack = new Mystack();
        Mystack operStack = new Mystack();

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';

        while (true){
            ch = expression.substring(index,index+1).charAt(0);
            if(operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    if(operStack.priority(ch) <= operStack.priority(Integer.parseInt((String) operStack.peek()))){
                        num1 = (int) numStack.pop();
                        num2 = (int) numStack.pop();
                        oper = (int) operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }

                }else {
                    operStack.push(ch);

                }
            }else {
                numStack.push(ch-48);//字符转int，asii编码
            }

            index++;
            if(index >= expression.length()){
                break;
            }
        }

        while (true){
            if(operStack.isEmpty()){
                break;
            }

            num1 = (int) numStack.pop();
            num2 = (int) numStack.pop();
            oper = (int) operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        int res2 = (int) numStack.pop();
        System.out.printf("表达式结果%d",res2);

    }
}

class Mystack extends Stack{

    public int priority(int oper){
        if(oper == '*' || oper =='/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }

    }
    public boolean isOper(char val){
        return val == '+' || val == '-'|| val == '*'|| val == '/';
    }

    public int cal(int num1,int num2,int oper){

        int res = 0;
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num1-num2;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num1/num2;
                break;
             default:
                 break;
        }
        return res;
    }
}
