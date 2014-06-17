import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * For self education: Range Iterator like Python range.
 * For more information, see ArrayIterator example from
 * official Oracle documantation and examples.
 * @author Yuri Astrov (yuriastrov@gmail.com)
 */
class IntRangeIterable implements Iterable<Integer> {
    private Integer start=0, stop=0, step=1;
    
    public MyRange (int start, int stop, int step) {
        this.start = start;
        this.stop = stop;
        this.step = step;
    }
    public MyRange (int start, int stop) {
        this.start = start;
        this.stop = stop;
    }
    public MyRange (int stop) {
        this.start = 0;
        this.stop = stop;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int value = start;

            /**
             * Returns status for next element in the iteration
             *
             * @return true if the next element is aviable, or false.
             */
            @Override
            public boolean hasNext() {
                return (value <= stop);
            }
            
            /**
             * Returns the next element in the iteration
             *
             * @return the next element in the iteration
             * @throws NoSuchElementException if the iteration has no more
             * elements
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer cur = value;
                value = value + step;
                return cur;
            }
        };
    }
}