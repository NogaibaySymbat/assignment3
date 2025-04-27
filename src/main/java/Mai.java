public class Mai {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(8, "eight");
        tree.put(1, "one");
        tree.put(6, "six");
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(2, "two");

        System.out.println("Tree size: " + tree.size());

        for (var elem : tree){
            System.out.println("key: " + elem.getKey() + " value: " + elem.getValue());
        }
        tree.delete(2);
        System.out.println("After deleting key 2:");
        for (var elem : tree){
            System.out.println("key: " + elem.getKey() + " value: " + elem.getValue());
        }
    }
}
