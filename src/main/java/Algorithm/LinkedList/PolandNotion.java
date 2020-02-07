package Algorithm.LinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotion {
    public static void main(String[] args) {

        String suffixExpression = "3 4 + 5 * 6 - ";

        List<String> list = getListString(suffixExpression);
        int res = calculate(list);
        System.out.println("计算结果是：" + res);


        String infixExpression = "1+((2+3)*4)-5";
        //将中缀表达式转为后缀表达式
        List<String> infixExpressionList = toInfixExpressionList(infixExpression);
        System.out.println(infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println(suffixExpressionList);


    }


    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele:split){
            list.add(ele);
        }
        return list;
    }

    public static int calculate(List<String> ls){
        Stack<String>  stack = new Stack<String>();

        for(String item:ls){
            if(item.matches("\\d+")){
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1+num2;
                }else if(item.equals("-")){
                    res = num1-num2;
                }else if(item.equals("*")){
                    res = num1*num2;
                }else if(item.equals("/")){
                    res = num1/num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res+"");
            }
        }

        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式String转换为List

    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        int i=0;//指针
        String str;//拼接
        char c;
        do{

            //非数字
            if((c=s.charAt(i))< 48 || c > 57){
                ls.add(""+c);
                i++;
            }else {
                str="";//先将str置成空串
                while (i<s.length() && (c=s.charAt(i))>= 48 && (c <= 57)){
                    str += c;
                    i++;

                }
                ls.add(str);
            }
        }while(i<s.length());
        return ls;
    }

    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<String>();
        List<String> s2 = new ArrayList<>();
        for(String item:ls){
            if(item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将（ 弹出
            }else {
                while (s1.size() != 0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }


}


class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+" :
                result = ADD;
                break;
            case "-" :
                result = SUB;
                break;
            case "*" :
                result = MUL;
                break;
            case "/" :
                result = DIV;
                break;
                default:
                    System.out.println("不存在该运算符");
                    break;

        }
        return result;
    }
}
