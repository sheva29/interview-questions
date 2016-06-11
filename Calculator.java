import java.util.Stack;

public class Calculator {
    public static class Operation {
        String operator;
        Integer value1;
        Integer value2;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Need exactly one string to perform a calculation on");
        }

        System.err.println(calculate(args[0]));
    }

    public static int calculate(String calculation) {
        if (calculation == null || "".equals(calculation)) {
            throw new IllegalArgumentException("Empty string passed");
        }

        String[] tokens = calculation.split(" ");

        Stack<Operation> operationStack = new Stack<Operation>();
        for (String token : tokens) {
            switch (token) {
            case "(":
                operationStack.push(new Operation());
                break;
            case ")":
                int value = calculateValue(operationStack.pop());
                if (operationStack.size() == 0) {
                    return value;
                }

                Operation above = operationStack.peek();
                if (above.value1 == null) {
                    above.value1 = value;
                } else {
                    above.value2 = value;
                }
                break;
            case "add":
                operationStack.peek().operator = "add";
                break;
            case "mult":
                operationStack.peek().operator = "mult";
                break;
            default:
                Operation currentOperation = operationStack.peek();
                Integer integerRepresentation = Integer.parseInt(token);
                if (currentOperation.value1 == null) {
                    currentOperation.value1 = integerRepresentation;
                } else {
                    currentOperation.value2 = integerRepresentation;
                }
            }
        }

        if (operationStack.size() != 1) {
            throw new IllegalArgumentException("Input string was unbalanced");
        }

        return calculateValue(operationStack.pop());
    }

    public static int calculateValue(Operation operation) {
        if ("add".equals(operation.operator)) {
            return operation.value1 + operation.value2;
        } else if ("mult".equals(operation.operator)) {
            return operation.value1 * operation.value2;
        } else  {
            throw new IllegalArgumentException("Unsupported Operation in the string");
        }
    }
}
