import java.util.ArrayList;
public class PossibilityTester {
    private char[] operators = {'+', '-', '*', '/'};
    private ArrayList<char[]> mixedOps;
    private ArrayList<int []> mixedInts;
    private ArrayList<int []> orderOfOperations;

    // Pass int array to Permutations ; returns?
    // Pass char array to Permutations ; returns?
    // switch statements in a for loop iterating through the returned lists?

    public void setUp() {
        mixedOps = operatorMixer(operators);
        int[] proxy = {-1, 0, 1};
        orderOfOperations = integerMixer(proxy);
    }


    public boolean isPossible(int[] values) {
        ArrayList<int []> holder = new ArrayList<int[]>();
        mixedInts = integerMixer(values, 0, holder);
    }   


    /* Permutes operators into arrays of length 3. 
     * Allows repeats and considers order.
     */ 
    private ArrayList<char []> operatorMixer(char[] ops) {
        ArrayList<char []> mixed = new ArrayList<char []>();
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

    /* Permutes integers into arrays of length 4. 
     * Does not allow repeats and considers order.
     */
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
