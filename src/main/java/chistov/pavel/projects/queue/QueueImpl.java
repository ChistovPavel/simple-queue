package chistov.pavel.projects.queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QueueImpl<T> implements Queue<T> {

  private QueueBlock<T> head;
  private QueueBlock<T> tail;
  private int size;

  public QueueImpl() {
  }

  public QueueImpl(Collection<T> collection) {
    this.push(collection);
  }

  @Override
  public void push(T t) {
    size++;
    if (Objects.isNull(head) && Objects.isNull(tail)) {
      head = tail = new QueueBlock<>(t);
    } else if (head == tail) {
      tail = new QueueBlock<>(t);
      head.setPreviousBlock(tail);
    } else {
      QueueBlock<T> newBlock = new QueueBlock<>(t);
      tail.setPreviousBlock(newBlock);
      this.tail = newBlock;
    }
  }

  @Override
  public final void push(Collection<T> collection) {
    collection.forEach(this::push);
  }

  @Override
  public T pop() {
    if (Objects.isNull(head) && Objects.isNull(tail)) {
      return null;
    }
    size--;
    if (head == tail) {
      T returnValue = head.getValue();
      this.head.setPreviousBlock(null);
      this.tail.setPreviousBlock(null);
      this.head = null;
      this.tail = null;
      return returnValue;
    } else {
      QueueBlock<T> returnBlock = head;
      head = head.getPreviousBlock();
      returnBlock.setPreviousBlock(null);
      return returnBlock.getValue();
    }
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public String toString() {
    List<T> values = new ArrayList<>();
    QueueBlock<T> queueBlock = head;
    while (Objects.nonNull(queueBlock)) {
      values.add(queueBlock.getValue());
      queueBlock = queueBlock.getPreviousBlock();
    }
    return values.stream().map(Object::toString).collect(Collectors.joining(","));
  }

  private static class QueueBlock<V> {

    private final V v;
    private QueueBlock<V> previousBlock;

    private QueueBlock(V v) {
      this.v = v;
      this.previousBlock = null;
    }

    public V getValue() {
      return v;
    }

    public void setPreviousBlock(QueueBlock<V> previousBlock) {
      this.previousBlock = previousBlock;
    }

    public QueueBlock<V> getPreviousBlock() {
      return previousBlock;
    }
  }
}
