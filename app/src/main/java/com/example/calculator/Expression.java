package com.example.calculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Expression {
    String s;
    ArrayList<String>res;
    public Expression(String user,ArrayList<String>list) {
        this.s = user;
        this.res=list;
    }

    public Listi calculate() {
        res=new ArrayList<>();
        Deque<Float> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        boolean hasPrevNum = false;

        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            if (Character.isDigit(c)) {
                float num = c - '0';
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
                    nums.push(0.0F);
                while (!ops.isEmpty() && precedes(ops.peek(), c))
                    calc(nums, ops);
                ops.push(c);
            }
        }

        while (!ops.isEmpty())
            calc(nums, ops);

        return new Listi(nums.peek(),res);
    }

    private void calc(Deque<Float> nums, Deque<Character> ops) {
        final float b = nums.pop();
        final float a = nums.pop();
        final char op = ops.pop();
        if (op == '+') {
            float temp=a+b;
            res.add(a + "+" + b + "=" +temp);
            nums.push(a + b);
        } else if (op == '-') {
            float temp=a-b;
            res.add(a + "-" + b + "=" + temp);
            nums.push(a - b);
        } else if (op == '*') {
            float temp=a*b;
            res.add(a + "*" + b + "=" + temp);
            nums.push(a * b);
        } else {// op == '/'
            float temp=a/b;
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
    float total;
    ArrayList<String>res;

    public Listi(float total, ArrayList<String> res) {
        this.total=total;
        this.res=res;
    }
}
