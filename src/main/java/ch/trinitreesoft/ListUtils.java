package ch.trinitreesoft;

import java.util.Iterator;

public class ListUtils {
    private ListUtils(){
    }

    public static class EnumeratedItem<T> {
        private final T item;
        private final int index;

        private EnumeratedItem(T item, int index) {
            this.item = item;
            this.index = index;
        }

        public int getIndex(){
            return this.index;
        }

        public T get(){
            return this.item;
        }
    }

    private record ListEnumerator<T>(Iterable<T> target, int start) implements Iterable<EnumeratedItem<T>> {

        @Override
            public Iterator<EnumeratedItem<T>> iterator() {
                final Iterator<T> targetIterator = target.iterator();
                return new Iterator<>() {

                    int index = start;

                    @Override
                    public boolean hasNext() {
                        return targetIterator.hasNext();
                    }

                    @Override
                    public EnumeratedItem<T> next() {
                        EnumeratedItem<T> nextIndexedItem = new EnumeratedItem<>(targetIterator.next(), index);
                        index++;
                        return nextIndexedItem;
                    }

                };
            }

        }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(Iterable<T> iterable, int start) {
        return new ListEnumerator<>(iterable, start);
    }

    public static <T> Iterable<EnumeratedItem<T>> enumerate(Iterable<T> iterable) {
        return enumerate(iterable, 0);
    }

}
