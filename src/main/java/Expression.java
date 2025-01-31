public class Expression {
    
    // Convert Infix to Postfix
    public static String[] convertToPostfix(String[] infixExpression) {
        ArrayStack<String> operatorStack = new ArrayStack<>();
        StringBuilder postfix = new StringBuilder();

        for (String token : infixExpression) {
            if (isNumeric(token)) {
                postfix.append(token).append(" ");
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.top()) >= precedence(token)) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.top().equals("(")) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                operatorStack.pop(); // Remove the "("
            } else {
                throw new RuntimeException("Invalid token: " + token);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop()).append(" ");
        }

        return postfix.toString().trim().split(" ");
    }

    // Evaluate Postfix Expression
    public static int evaluatePostfix(String[] postfixExpression) {
        ArrayStack<Integer> valueStack = new ArrayStack<>();
        
        for (String token : postfixExpression) {
            if (isNumeric(token)) {
                valueStack.push(Integer.parseInt(token));
            } else if (isOperator(token)) {
                int operand2 = valueStack.pop();
                int operand1 = valueStack.pop();
                int result = applyOperator(token, operand1, operand2);
                valueStack.push(result);
            } else {
                throw new RuntimeException("Invalid token: " + token);
            }
        }

        return valueStack.pop();
    }

    private static int applyOperator(String operator, int operand1, int operand2) {
        switch (operator) {
            case "+": return operand1 + operand2;
            case "-": return operand1 - operand2;
            case "*": return operand1 * operand2;
            case "/": return operand1 / operand2;
            case "^": return (int) Math.pow(operand1, operand2);
            default: throw new RuntimeException("Invalid operator: " + operator);
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^");
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
            default: return -1;
        }
    }
}
