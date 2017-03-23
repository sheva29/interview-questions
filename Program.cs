using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Calculator
{
    class Program
    {
        public class Operation
        {
            public string Operator;
            public int Value1;
            public int Value2;
            public int Value3;              
        }

        static void Main(string[] args)
        {
            string operation = "( add 3 ( mult 2 5 3 ) )";
            Console.WriteLine("Result: {0}",Calculate(operation));
            Console.ReadKey();
        }

        public static int Calculate(string calculation)
        {
            if( string.IsNullOrEmpty(calculation) || string.IsNullOrWhiteSpace(calculation))
            {
                throw new Exception("Empty String passed");
            }

            string[] tokens = calculation.Split(' ');
            Stack<Operation> operationStack = new Stack<Operation>();

            foreach(var token in tokens)
            {
                switch (token)
                {

                    case "(":
                        operationStack.Push(new Operation());
                        break;

                    case ")":
                        int value = calculateValue(operationStack.Pop());
                        if(operationStack.Count()  == 0)
                        {
                            return value;
                        }
                        Operation above = operationStack.Peek();
                        if(above.Value1 == 0 )
                        {
                            above.Value1 = value;
                        }else if (above.Value2 == 0)
                        {
                            above.Value2 = value;
                        }else
                        {
                            above.Value3 = value;
                        }
                        break;

                    case "add":
                        operationStack.Peek().Operator = "add";
                        break;

                    case "mult":
                        operationStack.Peek().Operator = "mult";
                        break;

                    default:
                        Operation currentOperation = operationStack.Peek();
                        int IntegerRepresentation = Convert.ToInt16(token);
                        if(currentOperation.Value1 == 0)
                        {
                            currentOperation.Value1 = IntegerRepresentation;

                        }else if( currentOperation.Value2 == 0)
                        {
                            currentOperation.Value2 = IntegerRepresentation;

                        }else
                        {
                            currentOperation.Value3 = IntegerRepresentation;
                        }
                        break;
                }

            }

            if(operationStack.Count() != 1)
            {
                throw new Exception("Input string was unbalanced");
            }

            return calculateValue(operationStack.Pop());
            
        }

        public static int calculateValue(Operation operation)
        {
            if (operation.Operator == "add")
            {
                return operation.Value1 + operation.Value2 + operation.Value3;

            }else if (operation.Operator == "mult")
            {
                return operation.Value1 * operation.Value2 * operation.Value3;
            }
            else
            {
                throw new Exception("Unsupported Operation in the string");
            }
        }
    }
}
