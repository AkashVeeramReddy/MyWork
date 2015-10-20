package stack;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * 
 * Valid operators are +, -, *, /. Each operand may be an integer or another
 * expression.
 * 
 * Some examples: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9 ["4", "13",
 * "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 * @author user
 * 
 */
public class Postfix {
	public static void main(String[] args) {
		String[] tokens = {"2", "1", "+", "3", "*"};
		int evalRPN = evalRPN(tokens);
		System.out.println(evalRPN);
	}
	public static int evalRPN(String[] tokens) {
		LinkedList<Integer> stack = new LinkedList<Integer>();
        String token;
		for (int i = 0; i < tokens.length; i++) {
			token = tokens[i];
			if(token.equals("-") || token.equals("*")
					|| token.equals("/") || token.equals("+")) {
				Integer op2 = stack.removeLast();
				Integer op1 = stack.removeLast();
				Integer result;
				if(token.equals("-")) {
					result = op1 - op2;
				} else if(token.equals("*")) {
					result = op1 * op2;
				} else if(token.equals("/")) {
					result = op1 / op2;
				} else {
					result = op1 + op2;
				}
				stack.addLast(result);
			} else {
				stack.addLast(Integer.parseInt(token));
			}
		}
		if(stack.isEmpty())
			return 0;
		else return stack.getLast();
    }
}
