import ch.trinitreesoft.ListUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumeratedListTest {
    List<String> testList = Arrays.asList("one","two","three");
    @Test
    void standardEnumerationShouldStartAtZero() {
        int count = 0;
        for (ListUtils.EnumeratedItem<String> item : ListUtils.enumerate(testList)){
            assertEquals(item.getIndex(), count);
            assertEquals(item.get(), testList.get(count));
            count++;
        }
    }

    @Test
    void enumerationStartCanBeAltered() {
        int count = 5;
        int countStart = count;
        for (ListUtils.EnumeratedItem<String> item : ListUtils.enumerate(testList,count)){
            assertEquals(item.getIndex(), count);
            assertEquals(item.get(), testList.get(count-countStart));
            count++;
        }
    }

}
