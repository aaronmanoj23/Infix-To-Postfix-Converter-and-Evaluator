public class ExpressionTest {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide at least one expression to evaluate.");
            return;
        }

        for (String infixExpression : args) {
            System.out.println("Infix: " + infixExpression);
            String[] infixTokens = infixExpression.split(" ");
            
            // Convert Infix to Postfix
            String[] postfixExpression = Expression.convertToPostfix(infixTokens);
            System.out.print("Postfix: ");
            for (String token : postfixExpression) {
                System.out.print(token + " ");
            }

            // Evaluate Postfix
            int result = Expression.evaluatePostfix(postfixExpression);
            System.out.println("= " + result);
        }
    }
}
