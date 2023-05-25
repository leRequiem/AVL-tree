import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayCreator {
    int size = 10000;
    static File file = new File("input.txt");


    public static File getFile() {
        return file;
    }


    public void createRandomArray() throws IOException {

        int[] arrayOfInts = new int[size];
        OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(file));
        for (int i = 0; i < size; i++) {
            arrayOfInts[i] = (int) (Math.random() * 10000);
        }
        int[] uniqueNumsArray = deleteCommonNums(arrayOfInts);

        os.write(Arrays.toString(uniqueNumsArray));
        os.close();
    }


    private int[] deleteCommonNums(int[] array) {
        Set<Integer> uniqueNums = new HashSet<Integer>();
        while (uniqueNums.size() < 10000) {
            uniqueNums.addAll(Arrays.stream(array).boxed().toList());
            while (uniqueNums.size() < 10000) {
                uniqueNums.add((int) (Math.random() * 100000));
            }
        }
        return uniqueNums.stream().mapToInt(i -> i).toArray();
    }

}
