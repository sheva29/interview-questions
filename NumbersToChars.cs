using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace mapNumbersToStrings
{
    /* Long String Coding
     * 
     * The following problem encodes letters in the following format:
     * a-j = 1-10 ,
     * k-z = 11#-26#,
     * When a string presents the same character multiple times consecutively the encoding adds parenthesis with the number of repetitions
     * 1(3) = "aaa"
     * sample strings: 
     * string 1 = "123(2)11#(2)" = "abcckk" 
     * string 2 = "26#(3)25#(2)" = "zzzyy"
     * Ask:
     * Write a program that returns an array with the number of each character for example:
     * input: "12311#"
     * output: 1 1 1 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     */
    class Program
    {
        static void Main(string[] args)
        {
            //Console.WriteLine(getCharForNumber(26).ToLower());

            //string input1 = "1234";
            string input1 = "26#(3)25#(2)";
            int[] letterPos = returnPositions(input1);
            Console.WriteLine(string.Join(" ", letterPos));
            Console.ReadLine();
            
        }

        //private static string getCharForNumber(int i)
        //{
        //    return i > 0 && i < 27 ? Convert.ToString((char)(i + 64)) : null;
        //}

        private static int[] returnPositions(string s)
        {
            int[] pos = new int[26];
            int it = s.Length-1;
            while(it >= 0)
            {
                int multiplier = 0;
                int index = 0;

                if (Char.IsDigit(s[it]))
                {
                    index = (int)Char.GetNumericValue(s[it]) - 1;
                    pos[index]++;
                }

  
                if (s[it] == ')')
                {
                    multiplier = (int)Char.GetNumericValue(s[it - 1]);
                    it -= 3;
                }

                if(s[it] == '#')
                {
                    string number = s.Substring(it - 2, 2);
                    index = Convert.ToInt32(number) - 1;
                    for(int i = 0; i < multiplier; i++)
                    {
                        pos[index] += 1;
                    }
                    it -= 2;
                }

                it--;
            }
            return pos;
        }
    }
}
