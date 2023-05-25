import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileOutputStream addingElement = new FileOutputStream("addingInfo.txt");
        FileOutputStream searchingElement = new FileOutputStream("searchingInfo.txt");
        FileOutputStream deletingElement = new FileOutputStream("deletingInfo.txt");
        DataWriter dataWriter = new DataWriter();
        AVLTree avlTree = new AVLTree();
        ArrayCreator ar = new ArrayCreator();
        ar.createRandomArray();

        Scanner in = new Scanner(ArrayCreator.getFile());
        String line = in.nextLine();
        line = line.replaceAll("\\[", "");
        line = line.replaceAll("]", "");
        line = line.replaceAll(" ", "");
        String[] numbersString = line.split(",");


        /*
        adding each num from txt file into the tree
        counting time and steps of adding element
         */
        for (String s : numbersString) {
            long start = System.nanoTime();
            avlTree.insert(Integer.parseInt(s));
            int time = (int) (System.nanoTime() - start);
            dataWriter.dataWrite("addingInfo.txt", avlTree.getAddCount(), time);
        }


        /*
        searching 100 random nums from array
         */
        for (int i = 0; i < 100; i++) {
            int randomNumber = (int) Math.floor(Math.random() * numbersString.length);
            long start = System.nanoTime();
            avlTree.find(randomNumber);
            int time = (int) (System.nanoTime() - start);
            dataWriter.dataWrite("searchingInfo.txt", avlTree.getSearchCount(), time);
        }


        /*
        deleting 1000 random nums from avlTree
         */
        for (int i = 0; i < 1000; i++) {
            int randomNumber = (int) Math.floor(Math.random() * numbersString.length);
            long start = System.nanoTime();
            avlTree.delete(randomNumber);
            int time = (int) (System.nanoTime() - start);
            dataWriter.dataWrite("deletingInfo.txt", avlTree.getDeleteCount(), time);
        }

        System.out.println("Работа выполнена, начальник!");
    }
}
