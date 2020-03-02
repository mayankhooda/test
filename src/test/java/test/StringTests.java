package test;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Stack;

public class StringTests {

    @Test
    public void testLinkedHashMap() {
        String str = "mayank";
        LinkedHashSet<Character> lhs = new LinkedHashSet<>();

        for (int i=0; i<str.length(); i++) {
            lhs.add(str.charAt(i));
        }
        for (Character c : lhs)
            System.out.println(c);
    }

    @Test
    public void test_MinStack() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();
    }

    class MinStack {

        int minValue;
        Stack<Integer> stack;

        /** initialize your data structure here. */
        public MinStack() {
            this.stack = new Stack<>();
            this.minValue = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(x);
                this.minValue = x;
            } else {
                if (x > minValue) {
                    this.stack.push(x);
                } else {
                    this.stack.push(x-minValue);
                    this.minValue = x;
                }
            }
        }

        public void pop() {
            if (stack.peek() < minValue) {
                minValue = stack.peek() - minValue;
            }
            stack.pop();
        }

        public int top() {
            if (stack.peek() < minValue) {
                return minValue;
            } else {
                return stack.peek();
            }
        }

        public int getMin() {
            return minValue;
        }
    }
}
