import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCodeSolutions {

    /* UNIQUE MORSE CODE WORDS
    DESCRIPTION: International Morse Code defines a standard encoding where each letter is mapped to a series
    of dots and dashes, as follows: "a" maps to ".-", "b" maps to "-...", "c" maps to "-.-.", and so on.

    For convenience, the full table for the 26 letters of the English alphabet is given below:

    [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--."
    ,"--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]

    Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter.
    For example, "cab" can be written as "-.-.-....-", (which is the concatenation "-.-." + "-..." + ".-").
    We'll call such a concatenation, the transformation of a word.

    Return the number of different transformations among all words we have.

    Example:
        Input: words = ["gin", "zen", "gig", "msg"]
    Output: 2
    Explanation:
    The transformation of each word is:
        "gin" -> "--...-."
        "zen" -> "--...-."
        "gig" -> "--...--."
        "msg" -> "--...--."

    There are 2 different transformations, "--...-." and "--...--.".


    Note:

        The length of words will be at most 100.
        Each words[i] will have length in range [1, 12].
        words[i] will only consist of lowercase letters.

    SOLUTION: Approach #1: Hash Set [Accepted]
        Intuition and Algorithm

        We can transform each word into it's Morse Code representation.

        After, we put all transformations into a set seen, and return the size of the set.

     COMPLEXITY ANALYSIS:
     Time Complexity: O(S), where S is the sum of the lengths of words in words.
      We iterate through each character of each word in words.

     Space Complexity: O(S)
     */

    public int uniqueMorseRepresentaions(String[] words) {
        String[] MORSE = new String[] {".-","-...","-.-.","-..",".","..-.","--.",
                "....","..",".---","-.-",".-..","--","-.",
                "---",".--.","--.-",".-.","...","-","..-",
                "...-",".--","-..-","-.--","--.."};

        Set<String> seen = new HashSet<>();

        /*
        HashSet uses HashMap internally to store it's objects. Whenever you create a HashSet object,
        one HashMap object associated with it is also created. This HashMap object is used to store the
        elements you enter in the HashSet. The elements you add into HashSet are stored as keys of this HashMap
        object. The value associated with those keys will be a constant.

        https://www.quora.com/How-does-Hashset-work-in-java
         */

        for (String word: words) {
            StringBuilder code = new StringBuilder();
            for (char c: word.toCharArray()) {

                /*
                characters are ascii values that are just numbers. We want to translate this to array index.
                'a' represents some number. if we do 'a' - 'a' we got 0. doing c - 'a', where c is any lower case
                character we get a range of values 0-25, which translate to our array of a-z.
                 */

                code.append(MORSE[c - 'a']);
            }
            seen.add(code.toString());
        }

        return seen.size();
    }

    /*
    JEWELS AND STONES
    DESCRIPTION: You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

    The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

    Example 1:

        Input: J = "aA", S = "aAAbbbb"
        Output: 3
        Example 2:

        Input: J = "z", S = "ZZ"
        Output: 0
    Note:

        S and J will consist of letters and have length at most 50.
        The characters in J are distinct.
     */

    //My Solution
    public int numJewelsInStonesDan(String J, String S) {
        char[] solutionSet = J.toCharArray();

        int result = 0;

        if (S == "") {
            return 0;
        }

        for (int i = 0; i < solutionSet.length; i++) {
            for (int j = 0; j < S.length(); j++) {
                if (solutionSet[i] == S.charAt(j)) {
                    result++;
                }
            }
        }

        return result;
    }

    //User submission "Java solution that beats 99.20% of Java submissions"
    //https://leetcode.com/problems/jewels-and-stones/discuss/151243/Java-solution-that-beats-99.20-of-java-submissions
    public int numJewelsInStones(String J, String S) {
        if (S == null || J == null) {
            return 0;
        }

        int[] counts = new int[52];
        int len = S.length();
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            int index = Character.isUpperCase(c)? c - 'A' + 26 : c - 'a';
            counts[index]++;
        }

        len = J.length();
        int sum = 0;
        for (int i = 0; i < len; i++) {
            char c = J.charAt(i);
            int index = Character.isUpperCase(c)? c - 'A' + 26 : c - 'a';
            sum += counts[index];
        }

        return sum;
    }

    /*
    REVERSE INTEGER
    Description: Given a 32-bit signed integer, reverse digits of an integer.

    Example 1:

        Input: 123
        Output: 321
    Example 2:

        Input: -123
        Output: -321
    Example 3:

        Input: 120
        Output: 21
    Note: Assume we are dealing with an environment which could only store integers within the 32-bit signed integer
    range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed
     integer overflows.

    Solution: Approach 1: Pop and Push Digits & Check before Overflow
    Intuition

        We can build up the reverse integer one digit at a time. While doing so, we can check beforehand whether or not
        appending another digit would cause overflow.

    Algorithm

        Reversing an integer can be done similarly to reversing a string.

        We want to repeatedly "pop" the last digit off of x and "push" it to the back of the rev. In the end,
        rev will be the reverse of the x.

        To "pop" and "push" digits without the help of some auxiliary stack/array, we can use math.

        //pop operation:
        pop     = x % 10;
        x /=    10;

        //push operation:
        temp = rev * 10 + pop;
        rev = temp;

      Time Complexity: O(log(x)). There are roughly log10(x) digits in x.
      Space Complexity: O(1)
     */

    //Official Solution
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }

        return rev;
    }

    //Given Solution
    public int reverseD(int x) {
        Long reverse = 0L;

        while (x != 0) {
            reverse += x % 10;
            x /= 10;
            if (x != 0) {
                reverse *= 10;
            }

            if (reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
                return 0;
            }
        }

        return reverse.intValue();
    }

    /*
    TWO SUM
    Description: Given an array of integers, return indices of the two numbers such that they add up to a specific
    target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Example:

        Given nums = [2, 7, 11, 15], target = 9,

        Because nums[0] + nums[1] = 2 + 7 = 9,
        return [0, 1].

        Solutions:
        Approach 1: Brute Force
        The brute force approach is simple. Loop through each element xx and find if there is another value that equals
        to target - x.

        Time Complexity: O(n^2). For each element, we try to find its complement by looping through the rest of array
        which takes O(n) time. Therefore, the time complexity is O(n^2).

        Space Complexity: O(1).
     */

    //Brute Force
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[j] == target - nums[i]) {
                    return new int[] {i, j};
                }
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    /*
    Approach 2: Two-pass Hash Table
    To improve our run time complexity, we need a more efficient way to check if the complement exists in the array. If
    the complement exists, we need to look up its index. What is the best way to maintain a mapping of each element in
    the array to its index? A hash table.

    We reduce the look up time from O(n) to O(1) by trading space for speed. A hash table is built exactly for
    this purpose, it supports fast look up in near constant time. I say "near" because if a collision occurred, a look
    up could degenerate to O(n) time. But look up in hash table should be amortized O(1)O(1) time as long as the
    hash function was chosen carefully.

    A simple implementation uses two iterations. In the first iteration, we add each element's value and its index to
    the table. Then, in the second iteration we check if each element's complement (target - nums[i])
    exists in the table. Beware that the complement must not be nums[i] itself!

    Time Complexity: O(n). We traverse the list containing n elements exactly twice. Since the hash table reduces the
    look up time to O(1), the time complexity is O(n).

    Space Complexity: O(n). The extra space required depends on the number of items stored in the hash table, which
    stores exactly n elements
     */

    //Two-pass Hash Table
    public int[] twoSumTPH(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] {i, map.get(complement)};
            }
        }
        
        throw new IllegalArgumentException("No two sum solution");
    }

    /*
    Approach 3: One-pass Hash Table
    It turns out we can do it in one-pass. While we iterate and inserting elements into the table, we also look back to
    check if current element's complement already exists in the table. If it exists, we have found a solution and
    return immediately.

    Time complexity: O(n). We traverse the list containing n elements only once. Each look up in the table costs only
    O(1) time.

    Space complexity: O(n). The extra space required depends on the number of items stored in the hash table, which
    stores at most n elements.
     */

    //One-pass Hash Table
    public int[] twoSumOPH(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] {map.get(complement)};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
    }
}
