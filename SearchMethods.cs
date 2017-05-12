using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SearchMethods
{
    class SearchMethods
    {
        static void Main(string[] args)
        {
            int[] array = new int[] { 35, 4, 5, 29, 17, 58, 0 };
            List<int> L = new List<int>(array);
            
            //selSort(L);

            List<int> newList = sort(L);
            string newListFormat = string.Join(", ", newList);
            Console.WriteLine("Sorted List: " + newListFormat);
            Console.ReadKey();
        }

        //Selection Sort
        static void selSort(List<int> _list)
        {
            for (var i = 0; i < _list.Count; i++)
            {
                string current = string.Join(", ", _list);
                Console.WriteLine(current);

                int minIndex = i;// first current element
                int minVal = _list[i];// always assume the first element is the lowest
                int j = i + 1;// move one step forward

                while (j < _list.Count)// start moving on the second element and move forward
                {
                    if (_list[j] < minVal)// if the next element is less that minVal
                    {
                        minIndex = j;// new minIndex is one being compared
                        minVal = _list[j];// new minVal becomes current j element
                    }
                    j++;
                }

                int temp = _list[i];// temp reference to current value
                _list[i] = _list[minIndex];// make sure min element is the current one
                _list[minIndex] = temp;// if the minIndex has changed, we pass that new value
            }
        }

        // Merge Sort
        static List<int> merge(List<int> left, List<int> right, Func<int, int, bool> condition)
        {
            List<int> result = new List<int>();
            int i = 0;
            int j = 0;
            List<int> t = new List<int>();
            List<int> d = new List<int>();
            while (i < left.Count && j < right.Count)
            {
                if (condition(left[i], right[j]))
                {
                    result.Add(left[i]);
                    i++;
                }else
                {
                    result.Add(right[j]);
                    j++;
                }
            }
            while (i < left.Count)
            {
                result.Add(left[i]);
                i++;
            }
            while ( j < right.Count)
            {
                result.Add(right[j]);
                j++;
            }
            return result;
        }

        static List<int> sort(List<int> L)
        {
            if (L.Count < 2)
            {
                return new List<int>(L);

            }else
            {
                int middle = (int)L.Count / 2;
                //Console.WriteLine("Left: " + string.Join(", ", L.Slice(0, middle)) + " Right: " + string.Join(", ", L.Slice(middle, L.Count)));

                List<int> left = sort(L.Slice(0, middle));
                List<int> right = sort(L.Slice(middle, L.Count));                

                string leftList = string.Join(", ", left);
                string rightList = string.Join(", ", right);
                Console.WriteLine("About to merge " + leftList + " and " + rightList);
                
                return merge(left, right, (x, y) => x < y);
            }
        }        
    }

    // Add Splice() to List<T>
    public static class Extensions    {
        public static List<T> Slice<T>(this IEnumerable<T> source, int startIndex, int endIndex)
        {
            //DONE: validation
            if (null == source)
                throw new ArgumentNullException("source");
            else if (startIndex < 0)
                throw new ArgumentOutOfRangeException("startIndex",
                  $"startIndex ({startIndex}) must be non-negative.");
            else if (startIndex > endIndex)
                throw new ArgumentOutOfRangeException("startIndex",
                  $"startIndex ({startIndex}) must not exceed endIndex ({endIndex}).");

            // Instead of pure Linq 
            // return source.Skip(startIndex).Take(endIndex - startIndex).ToList();
            // let's use a loop

            // it doesn't create endIndex - startIndex items but reserve space for them
            List<T> result = new List<T>(endIndex - startIndex);

            foreach (var item in source.Skip(startIndex))
                if (result.Count >= endIndex - startIndex)
                    return result;
                else
                    result.Add(item); // we add items, not put them with result[i] = ...

            // source exhausted, but we don't reach endIndex. 
            //TODO: Shall we return as is or throw an exception?
            return result;
        }
    }
}
