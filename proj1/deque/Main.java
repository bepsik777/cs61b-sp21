package deque;

public class Main {
    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        list.printDeque();
        list.addLast(0);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);

        list.printDeque();
        System.out.println(list.get(100));
        System.out.println(list.getRecursive(6));
    }
}
