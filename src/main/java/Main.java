import java.util.Random;
public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>(50);
        Random r = new Random();

        for (int i = 0; i < 10_000; i++) {
            MyTestingClass key = new MyTestingClass(r.nextInt(100_000));
            Integer value = r.nextInt(1000);
            table.put(key, value);
        }
        System.out.println("Chain sizes: ");
        for (int i = 0; i < 50; i++) {
            System.out.println("Bucket " + i + ": " + table.getChainSize(i) + " elements");
        }
    }
}
