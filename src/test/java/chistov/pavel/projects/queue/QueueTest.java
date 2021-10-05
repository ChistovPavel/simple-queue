package chistov.pavel.projects.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QueueTest {

  private static final List<Integer> TEST_VALUES = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

  @Test
  public void filledQueueTest() {
    QueueImpl<Integer> queue = new QueueImpl<>(TEST_VALUES);
    Assert.assertEquals(TEST_VALUES.size(), queue.size());
    Assert.assertEquals(TEST_VALUES.get(0), queue.pop());
    Assert.assertEquals(TEST_VALUES.size() - 1, queue.size());
    IntStream.range(0, 5).forEach(i -> queue.pop());
    Assert.assertEquals(TEST_VALUES.get(TEST_VALUES.size() - 1), queue.pop());
    Assert.assertEquals(0, queue.size());
  }

  @Test
  public void emptyQueueTest() {
    QueueImpl<Integer> queue = new QueueImpl<>();
    Assert.assertEquals(0, queue.size());
    Assert.assertNull(queue.pop());
    Assert.assertEquals(0, queue.size());
    IntStream.range(0, 5).forEach(i -> queue.pop());
    Assert.assertNull(queue.pop());
    Assert.assertEquals(0, queue.size());
    queue.push(1);
    Assert.assertEquals(1, queue.size());
    Assert.assertEquals(new Integer(1), queue.pop());
    Assert.assertNull(queue.pop());
    Assert.assertEquals(0, queue.size());
  }

  @Test
  public void toStringTest() {
    QueueImpl<Integer> queue = new QueueImpl<>(TEST_VALUES);
    Assert.assertEquals(TEST_VALUES.stream().map(Object::toString).collect(Collectors.joining(",")), queue.toString());
  }
}
