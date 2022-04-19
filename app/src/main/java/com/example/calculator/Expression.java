package com.example.calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

public class Expression {
    String s;
    ArrayList<String>res;
    public Expression(String user,ArrayList<String>list) {
        this.s = user;
        this.res=list;
    }
    public boolean isOptr(char ch){
        if(ch=='+'||ch=='-'||ch=='*'||ch=='/'){
            return true;
        }
        return false;
    }

    public int precedence(char ch){
        if(ch=='+'||ch=='-'){
            return 1;
        }
        return 2;
    }

    public int calcVal(int a,int b,char ch){
        if(ch=='*'){
            int temp=a*b;
            String l=a+"*"+b+"="+temp;
            res.add(l);
            return a*b;
        }
        else if(ch=='/'){
            int temp=a/b;
            String l=a+"/"+b+"="+temp;
            res.add(l);
            return a/b;
        }
        else if(ch=='+'){
            int temp=a+b;
            res.add(a+"+"+b+"="+temp);
            return a+b;
        }else{
            int temp=a-b;
            res.add(a+"-"+b+"="+temp);
            return a-b;
        }
    }
    public Listi calculate() {
//        res=new ArrayList<>();
//        Stack<Integer> operand=new Stack();
//        Stack<Character> operator=new Stack();
//
//
//        for(int i=0;i<s.length();i++){
//            char ch=s.charAt(i);
//
//            if(ch>='0'&&ch<='9'){
//                int val=0;
//                while(i<s.length()&&s.charAt(i)>='0'&&s.charAt(i)<='9'){    //Character.IsDigit()
//                    val=val*10+(s.charAt(i)-'0');
//                    i++;
//                }
//                i--;
//                operand.push(val);
//            }else if(isOptr(ch)){
//                while(operator.size()!=0 && precedence(ch)<=precedence(operator.peek())){
//                    int v2=operand.pop();
//                    int v1=operand.pop();
//                    char op=operator.pop();
//                    int calc=calcVal(v1,v2,op);
//                    operand.push(calc);
//                }
//                operator.push(ch);
//            }
//        }
//
//        while(operator.size()!=0){
//            int v2=operand.pop();
//            int v1=operand.pop();
//            char op=operator.pop();
//            int calc=calcVal(v1,v2,op);
//            operand.push(calc);
//        }
//
//        return new Listi(operand.pop(),res);
        res=new ArrayList<>();
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        boolean hasPrevNum = false;

        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1)))
                    num = num * 10 + (s.charAt(i++ + 1) - '0');
                nums.push(num);
                hasPrevNum = true;
            } else if (c == '(') {
                ops.push('(');
                hasPrevNum = false;
            } else if (c == ')') {
                while (ops.peek() != '(')
                    calc(nums, ops);
                ops.pop(); // pop '('
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (!hasPrevNum)
                    nums.push(0);
                while (!ops.isEmpty() && precedes(ops.peek(), c))
                    calc(nums, ops);
                ops.push(c);
            }
        }

        while (!ops.isEmpty())
            calc(nums, ops);

        return new Listi(nums.peek(),res);
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        final int b = nums.pop();
        final int a = nums.pop();
        final char op = ops.pop();
        if (op == '+') {
            int temp=a+b;
            res.add(a + "+" + b + "=" +temp);
            nums.push(a + b);
        } else if (op == '-') {
            int temp=a-b;
            res.add(a + "-" + b + "=" + temp);
            nums.push(a - b);
        } else if (op == '*') {
            int temp=a*b;
            res.add(a + "*" + b + "=" + temp);
            nums.push(a * b);
        } else {// op == '/'
            int temp=a/b;
            res.add(a + "/" + b + "=" + temp);
        nums.push(a / b);
        }
    }

    // return true if prevOp is a operator and
    // priority(prevOp) >= priority(currOp)
    private boolean precedes(char prevOp, char currOp) {
        if (prevOp == '(')
            return false;
        return prevOp == '*' || prevOp == '/' || currOp == '+' || currOp == '-';
    }
}
class Listi{
    int total;
    ArrayList<String>res;

    public Listi(int total, ArrayList<String> res) {
        this.total=total;
        this.res=res;
    }
}
