import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ArrayCfg implements Ilayout {

    final int[] data;
    final int cost;


    /**
     @ Create an array of integers from the string,
     where integers are separated by spaces
     */
    ArrayCfg(String s){
        this.data = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        this.cost = 0;
    }

    /**
     @ Create an array of integers and adds the cost
     from the original array
     */
    ArrayCfg(int[] array, int cost){
        this.data = array;
        this.cost = cost;
    }

    /**
     * Creates the possible children given the movement restrictions and their cost
     * @return list of children from the original layout
     */
    @Override
    public List<Ilayout> children() {

        int n = data.length;
        int expectedChildren = n * (n - 1) / 2;

        ArrayList<Ilayout> children = new ArrayList<>(expectedChildren);

        for (int i = 0; i < data.length; i++) {
            for (int j = i + 1; j < data.length; j++) {

                int[] arrayTemp = Arrays.copyOf(this.data, n);

                int numberTemp = arrayTemp[i];
                arrayTemp[i] = arrayTemp[j];
                arrayTemp[j] = numberTemp;

                int swapCost = calculateCost(this.data[i], this.data[j]);
                children.add(new ArrayCfg(arrayTemp, swapCost));

            }
        }

        return children;
    }

    /**
     * Calculates the cost of swapping 2 numbers given the restrictions
     * @return cost of the swap
     */
    private int calculateCost(int number1, int number2) {

        boolean isNum1Even = isEven(number1);
        boolean isNum2Even = isEven(number2);

        if (isNum1Even && isNum2Even){
            return 2;
        }
        else if (!isNum1Even && !isNum2Even) {
            return 20;
        }
        return 11;
    }

    /**
     * Says if number is even or not
     * @return true if even, false otherwise
     */
    private boolean isEven(int number){
        return number % 2 == 0;
    }

    /**
     * Confirms if this array is the goal array
     * @return true if it is the goal, false otherwise
     */
    @Override
    public boolean isGoal(Ilayout l) {
        return this.equals(l);
    }

    /**
     * Returns the cost of the path of the current array
     * @return total cost of the path
     */
    @Override
    public double getK() {
        return this.cost;
    }

    /**
     * Coverts the array into string separated the numbers by spaces
     * @return current array in string form
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int datum : this.data) {
            stringBuilder.append(datum);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * Confirms of the current array is equal to another array
     * @return true if the arrays are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }

        ArrayCfg arrayCfg = (ArrayCfg) o;
        return Arrays.equals(data, arrayCfg.data);
    }

    /**
     * Returns a hash code value for this array configuration.
     * The hash code is computed based on the contents of the array,
     * ensuring that equal configurations produce the same hash code.
     *
     * @return the hash code value corresponding to this array configuration
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

}
