package chistov.pavel.projects.queue;

import java.util.Collection;

public interface Queue<T> {

  void push(T t);

  void push(Collection<T> collection);

  T pop();

  int size();
}
