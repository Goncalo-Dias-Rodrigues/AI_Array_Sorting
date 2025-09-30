import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ArrayCfg implements Ilayout {

    int data[];
    int cost;


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
        ArrayList<Ilayout> children = new ArrayList<>();

        int numberTemp;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (i < j){
                    int[] arrayTemp = this.data.clone();

                    numberTemp = arrayTemp[i];
                    arrayTemp[i] = arrayTemp[j];
                    arrayTemp[j] = numberTemp;


                    int swapCost = calculateCost(this.data[i], this.data[j]);
                    ArrayCfg array = new ArrayCfg(arrayTemp, swapCost);

                    children.add(array);
                }
            }
        }

        return children;
    }

    /**
     * Calculates the cost of swapping 2 numbers given the restrictions
     * @param number1
     * @param number2
     * @return cost of the swap
     */
    private int calculateCost(int number1, int number2) {
        if (isEven(number1) && isEven(number2)){
            return 2;
        }
        else if (!isEven(number1) && !isEven(number2)) {
            return 20;
        }
        return 11;
    }

    /**
     * Says if number is even or not
     * @param number
     * @return true if even, false otherwise
     */
    private boolean isEven(int number){
        return number % 2 == 0;
    }

    @Override
    public boolean isGoal(Ilayout l) {
        if(!(l instanceof ArrayCfg)){
            return false;
        }
        return Arrays.equals(this.data, ((ArrayCfg) l).data);
    }

    @Override
    public double getK() {
        return this.cost;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.data.length; i++) {
            stringBuilder.append(this.data[i]);
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

}
