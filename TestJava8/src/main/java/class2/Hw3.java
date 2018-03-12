package class2;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by james on 2018/2/27.
 */
public class Hw3 {

    public static void main(String[] args) {
        System.out.print("Please enter a stock's closing price as a decimal value (e.g. 12.45), enter 99.99 to exit: ");
        double signValue = 99.99;

        double inputValue;
        List<Double> list = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        boolean hasNext = input.hasNextDouble();
        while (hasNext) {
            inputValue = input.nextDouble();
            if (inputValue == signValue) {
                break;
            } else {
                System.out.println("Please enter a stock's closing price as a decimal value (e.g. 12.45), enter 99.99 to exit: ");
                System.out.println(inputValue);
                list.add(inputValue);
            }
        }
        if (null != list && !list.isEmpty()) {
            handleInputValueAvg(list);
            handleInputValueMax(list);
            handleInputValueMaxByBubbleSort(list);
            handleInputValueMaxByInsertSort(list);
            handleInputValueMaxBySelectSort(list);
        }
    }

    private static void handleInputValueMaxBySelectSort(List<Double> list) {
        Double[] arr = list.stream().toArray(Double[]::new);
        int count = arr.length;
        int outer;
        int inner;
        int min;
        for(outer = 0 ; outer < count -1 ; outer++){
            min = outer;
            for(inner = outer + 1 ; inner < count ; inner++){
                if(arr[inner] < arr[min]){
                    min = inner;
                }
            }
            double temp = arr[outer];
            arr[outer] = arr[min];
            arr[min] = temp;
        }
        System.out.println(MessageFormat.format("The highest closing price was: ${0}", arr[arr.length - 1]));
    }

    private static void handleInputValueMaxByInsertSort(List<Double> list) {
        Double[] arr = list.stream().toArray(Double[]::new);
        int count = arr.length;
        int inner;
        int outer;
        for (outer = 1; outer < count; outer++) {
            double temp = arr[outer];
            inner = outer;
            while (inner > 0 && arr[inner - 1] >= temp) {
                arr[inner] = arr[inner - 1];
                --inner;
            }
            arr[inner] = temp;
        }
        System.out.println(MessageFormat.format("The highest closing price was: ${0}", arr[arr.length - 1]));
    }

    private static void handleInputValueMaxByBubbleSort(List<Double> list) {
        Double[] arr = list.stream().toArray(Double[]::new);
        int count = arr.length;
        for (int outer = count - 1; outer > 1; outer--) {
            for (int inner = 0; inner < count-1; inner++) {
                if(arr[inner] > arr[inner+1]){
                    double temp = arr[inner];
                    arr[inner] = arr[inner+1];
                    arr[inner+1] = temp;
                }
            }
        }
        System.out.println(MessageFormat.format("The highest closing price was: ${0}", arr[arr.length - 1]));
    }

    private static void handleInputValueMax(List<Double> list) {
        Double max = Collections.max(list);
        System.out.println(MessageFormat.format("The highest closing price was: ${0}", max));
    }

    private static void handleInputValueAvg(List<Double> list) {
        double sum = 0;
        double average;
        for (Double val : list) {
            sum += val;
        }
        double count = list.size();
        average = sum / count;
        System.out.println(MessageFormat.format("The average closing price was: ${0}", average));

    }
}
