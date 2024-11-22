package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.c = c;
    }

    public T max() {
        return max(c);
    }

    public T max(Comparator<T> comparator) {
        T max = null;
        for (int i = 0; i < this.size(); i += 1) {
            T curr = this.get(i);
            if (max == null || comparator.compare(max, curr) < 0) {
                max = curr;
            }
        }
        return max;
    }
}
