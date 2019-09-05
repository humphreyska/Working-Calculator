package solution;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 
 * @author ???
 * @version ???
 * 
 */
public class ExpressionEvaluator
{

    public static final Pattern UNSIGNED_DOUBLE =
            Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    public static final Pattern CHARACTER = Pattern.compile("\\S.*?");
    private Stack<Character> stack = new Stack();
    private Stack<String> stackd = new Stack();

    /**
     * Takes an infix expression and converts it to postfix.
     * 
     * @param expression
     *            The infix expression.
     * @return the postfix expression.
     */
    public String toPostfix(String expression)
    {
        // ... other local variables
        Scanner input = new Scanner(expression);
        String next;
        char symbol;
        String postfixExpression = "";


        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                // TODO: do what you want to with a String that
                // holds a number
                if (postfixExpression.equals(""))
                {
                    postfixExpression = postfixExpression + next;
                }
                else
                {
                    postfixExpression = postfixExpression + " " + next;
                }
            }
            else
            {
                next = input.findInLine(CHARACTER);
                symbol = next.charAt(0);

                // TODO: do what you want to with a symbol
                // such as (, ), *, /, +, -
                if (symbol == '(')
                {
                    stack.push(symbol);
                }
                else if (symbol == '+' || symbol == '-' 
                        || symbol == '/' || symbol == '*')
                {
                    boolean yay = false;

                    if (!stack.isEmpty())
                    {
                        if (stack.peek() == '+' || stack.peek() == '-')
                        {
                            if (symbol == '*' || symbol == '/')
                            {
                                yay = false;
                            }
                            else if (symbol == '+' || symbol == '-')
                            {
                                yay = true;
                            }
                        }
                        else if (stack.peek() == '/' || stack.peek() == '*')
                        {
                            if (symbol == '*' || symbol == '/')
                            {
                                yay = true;
                            }
                            else if (symbol == '+' || symbol == '-')
                            {
                                yay = true;
                            }
                        }
                    }


                    while (!stack.isEmpty() && stack.peek() != '(' && yay)
                    {
                        postfixExpression = postfixExpression +
                                " " + stack.pop(); 
                        if (!stack.isEmpty())
                        {
                            if (stack.peek() == '+' || stack.peek() == '-')
                            {
                                if (symbol == '*' || symbol == '/')
                                {
                                    yay = false;
                                }
                                else if (symbol == '+' || symbol == '-')
                                {
                                    yay = true;
                                }
                            }
                            else if (stack.peek() == '/' || stack.peek() == '*')
                            {
                                if (symbol == '*' || symbol == '/')
                                {
                                    yay = true;
                                }
                                else if (symbol == '+' || symbol == '-')
                                {
                                    yay = true;
                                }
                            }
                        }

                    }

                    stack.push(symbol);
                }
                else if (symbol == ')')
                {
                    while (!stack.isEmpty() && stack.peek() != '(')
                    {
                        postfixExpression = postfixExpression 
                                + " " + stack.pop(); 
                    }
                    stack.pop();
                }
                else
                {
                    throw new IllegalStateException("Error");
                }

            }

        }
        while (!stack.isEmpty())
        {
            postfixExpression = postfixExpression + " " + stack.pop(); 
        }
        return postfixExpression;
    }


    /**
     * Evaluates a postfix expression and returns the result.
     * 
     * @param postfixExpression
     *            The postfix expression.
     * @return The result of the expression.
     */
    public double evaluate(String postfixExpression)
    {


        // other local variables you may need
        Scanner input = new Scanner(postfixExpression);
        String next;
        char operator;
        double answer = Double.NaN;
        double operand = 0;
        double operators = 0;

        while (input.hasNext())
        {
            if (input.hasNext(UNSIGNED_DOUBLE))
            {
                next = input.findInLine(UNSIGNED_DOUBLE);
                // TODO: do what you want to with a String that
                // holds a number
                stackd.push(next);
                operand++;
            }
            else
            {
                
                next = input.findInLine(CHARACTER);
                operator = next.charAt(0);
                double loperand = 0;
                double roperand = 0;
                operators++;

                // TODO: do what you want to with an operator
                // such as *, /, +, -
                if (!stackd.isEmpty())
                {
                    roperand = Double.parseDouble(stackd.pop());

                    if (!stackd.isEmpty())
                    {
                        
                        loperand = Double.parseDouble(stackd.pop());
                        double number = 0;
                        if (operator == '+')
                        {
                            number = loperand + roperand;
                        }
                        else if (operator == '-')
                        {
                            number = loperand - roperand;

                        }
                        else if (operator == '/')
                        {
                            number = loperand / roperand;
                        }
                        else if (operator == '*')
                        {
                            number = loperand * roperand;
                        }
                        System.out.println("Pushed: " + number);
                        stackd.push("" + number);
                    }

                }
                else
                {
                    return Double.NaN;
                }
            }
        }
        if(operand != operators + 1)
        {
            return Double.NaN;
        }
        answer = Double.parseDouble(stackd.pop());
        return answer;
    }
    
    public static void main(String[] args)
    {
        ExpressionEvaluator e = new ExpressionEvaluator();
        String s1 = "3 + 5 * 10 - 2 *";
        System.out.println(e.evaluate(s1));
    }
}


