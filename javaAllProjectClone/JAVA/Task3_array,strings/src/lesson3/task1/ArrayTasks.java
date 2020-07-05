package lesson3.task1;

import java.util.ArrayList;
import java.util.Random;

public class ArrayTasks {
    static Random random = new Random();
    public static final int SIZE = 20;
    public static final int DIFFERENCE = 10;
    public static final int MAX_RANDOM_VALUE = 21;
    private static final int[] nums = new int[SIZE];

    public void setNums() {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(MAX_RANDOM_VALUE) - DIFFERENCE;
        }
    }

    public int[] getNums() {
        for (int i : nums) {
            System.out.print(i + ", ");
        }
        System.out.println();
        return nums;
    }

    public void swapMinPositiveAndMaxNegativeNumbers_1() {
        getNums();
        int minPositive = nums[0];
        int maxNegative = nums[0];
        int indMaxNegat = 0;
        int indMinPosit = 0;
        for (int i = 0; i < nums.length; i++) {
            if (minPositive <= 0) {
                minPositive = nums[i + 1];

            }
            if (nums[i] > 0 && minPositive > nums[i]) {
                minPositive = nums[i];
                indMinPosit = i;
            }
            if (maxNegative >= 0) {
                maxNegative = nums[i + 1];
            }
            if (nums[i] < 0 && maxNegative < nums[i]) {
                maxNegative = nums[i];
                indMaxNegat = i;
            }
        }
        System.out.println("min_posit = " + minPositive + ", ind_min_posit = " + indMinPosit);
        System.out.println("max_negat = " + maxNegative + ", ind_max_posit = " + indMaxNegat);

        int temp = nums[indMinPosit];
        nums[indMinPosit] = nums[indMaxNegat];
        nums[indMaxNegat] = temp;

        for (int i : nums) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public int sumOfEvenNumbers_2() {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 != 0) {
                sum += nums[i];
            }
        }
        System.out.println("sum = " + sum);
        return sum;
    }

    public void changeOnZeroAllNegativeNumbers_3() {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] = 0;
            }
            System.out.print(nums[i] + ", ");
        }
    }

    public void multiplOn3PositiveNumbersBeforeNegative_4() {
        getNums();
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > 0 && nums[i + 1] < 0) {
                nums[i] *= 3;
            }
        }
        for (int i : nums) {
            System.out.print(i + ", ");
        }
    }

    public void differenceBetweenAverageAndMinElement_5() {
        getNums();
        int sum = 0;
        int minElement = nums[0];
        for (int i : nums) {
            sum += i;
        }

        for (int i : nums) {
            if (minElement > i) {
                minElement = i;
            }
        }
        double rs = (double) sum / nums.length;
        System.out.println("sum = " + sum);
        System.out.println("Min element = " + minElement);
        System.out.println("Difference = " + (rs - minElement));
    }

    public void getEqualsNumbersWintOddIndx_6() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && i % 2 != 0) {
                    list.add(nums[i]);
                }
            }
        }
        getNums();
        for (int i : list) {
            System.out.print(i + ", ");
        }
    }
}
