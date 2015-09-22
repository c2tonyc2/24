import java.util.ArrayList;
import java.util.Arrays;
public class PossibilityTester {
    private char[] operators = {'+', '-', '*', '/'};
    public ArrayList<char[]> mixedOps;
    private ArrayList<int []> mixedInts;
    private ArrayList<int[]> allSolutionInts;
    private ArrayList<char[]> allSolutionsOps;

    // Pass int array to Permutations ; returns?
    // Pass char array to Permutations ; returns?
    // switch statements in a for loop iterating through the returned lists?

    public PossibilityTester() {
        mixedOps = operatorMixer(operators);
    }

    /*
     * Tests if a given set of integers can possibly form 24.
     * Programmed to ignore decimal arithmetic, unintuitive (for players).
     */
    public boolean isPossible(int[] values) {
        if (values == null) {
            return false;
        }
        allSolutionInts = new ArrayList<int[]>();
        allSolutionsOps = new ArrayList<char[]>();
        mixedInts = new ArrayList<int[]>();
        integerMixer(values, 0, mixedInts);
        for (char[] chars : mixedOps) {
            for (int[] nums : mixedInts) {
                 if (evaluate(chars, nums) == 24) {
                    allSolutionsOps.add(chars);
                    allSolutionInts.add(nums);
                 }
            }
        }
        if (allSolutionsOps.isEmpty() || allSolutionInts.isEmpty()) {
            return false;
        }
        return true;
    }

    public ArrayList<String> getSolutions(int[] values, int solutionNum) {
        ArrayList<String> solutions = new ArrayList<String>();
        if (!allSolutionInts.isEmpty() || isPossible(values)) {
            for (int i = 0; i < solutionNum && i < allSolutionInts.size(); i += 1) {
                int[] ints = allSolutionInts.get(i);
                char[] ops = allSolutionsOps.get(i);
                String solution = String.format("((( %d %c %d ) %c %d) %c %d )", 
                                               ints[0], ops[0], ints[1], ops[1], 
                                               ints[2], ops[2], ints[3]); 
                solutions.add(solution);
            }
        } else {
            solutions.add("No Solution, Try another set of cards!");
        }
        return solutions;
    }

    private int evaluate(char[] operators, int[] numbers) {
        int result = apply(operators[0], numbers[0], numbers[1]);
        for (int i = 1; i < 3; i += 1) {
            result = apply(operators[i], result, numbers[i+1]);
            if (result == 28562) {
                return 0;
            }
        }
        return result;
    }

    private int apply(char operator, int num1, int num2) {
            switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if ((num2 == 0) || (num1 % num2 != 0)) {
                    return 28562;
                } else {
                    return num1 / num2;
                }
            default:
                //TODO: Handle exceptions here
                return 28562;
        }
    }

    /* Permutes operators into arrays of length 3. 
     * Allows repeats and considers order.
     */ 
    private ArrayList<char[]> operatorMixer(char[] ops) {
        ArrayList<char[]> mixed = new ArrayList<char[]>();
        for (char first : ops) {
            for (char second : ops) {
                for (char third : ops) {
                    char [] proxy = {first, second, third};
                    mixed.add(proxy);                  
                }
            }
        }
        return mixed;
    }

    private void integerMixer(int[] arr, int pos, ArrayList<int[]> list){
        if (arr.length - pos == 1) {
            list.add(arr.clone());
        }
        else {
            for(int i = pos; i < arr.length; i++){
                swapper(arr, pos, i);
                integerMixer(arr, pos+1, list);
                swapper(arr, pos, i);
            }
        }
    }
 
    private void swapper(int[] arr, int pos1, int pos2){
        int h = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = h;
    }
}
