package deque;

public class Main {
    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        LinkedListDeque<Integer> listTwo = new LinkedListDeque<>();
        list.printDeque();
        listTwo.addLast(0);
        listTwo.addLast(1);
        listTwo.addLast(2);
        listTwo.addLast(3);
        listTwo.addLast(4);
        listTwo.addLast(5);
        listTwo.addLast(6);
        list.addLast(0);
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);

        for (int i: list) {
            System.out.println(i);
        }

        System.out.println("size one: " + list.size());
        System.out.println("size two: " + listTwo.size());
        System.out.println("list one == list two: " + list.equals(listTwo));
        System.out.println(list.getRecursive(6));

        list.printDeque();
    }
}
