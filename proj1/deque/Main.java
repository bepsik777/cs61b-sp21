package deque;

public class Main {
    public static void main(String[] args) {
       ArrayDeque<String> list = new ArrayDeque<>();
       list.addFirst("a");
       list.addFirst("a");
       list.addFirst("a");
       list.resizeArray();
       list.printWholeArray();
    }
}
