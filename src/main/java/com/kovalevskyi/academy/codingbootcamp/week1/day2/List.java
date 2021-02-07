package com.kovalevskyi.academy.codingbootcamp.week1.day2;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * w1d2 task.
 *
 * @param <T> type of inner variable
 */
public class List<T> {

  private List<T> prev;
  private List<T> next;
  private final T value;

  private List(List<T> prev, List<T> next, T value) {
    this.prev = prev;
    this.next = next;
    this.value = value;
  }

  public static <T> List<T> createOne(T value) {
    return new List<>(null, null, value);
  }

  /**
   * Add to the end to some list.
   *
   * @param list     list
   * @param newValue value
   * @param <T>      type
   * @return list
   */
  public static <T> List<T> addToEnd(List<T> list, T newValue) {
    var tmp = Stream
        .iterate(list, Objects::nonNull, entry -> entry.next)
        .dropWhile(entry -> Objects.nonNull(entry.next))
        .findFirst()
        .orElseThrow();
    tmp.next = new List<>(tmp, null, newValue);
    return tmp.next;
  }

  /**
   * Add to the start to some list.
   *
   * @param list     list
   * @param newValue value
   * @param <T>      type
   * @return list
   */
  public static <T> List<T> addToStart(List<T> list, T newValue) {
    var tmp = Stream
        .iterate(list, Objects::nonNull, entry -> entry.prev)
        .dropWhile(entry -> Objects.nonNull(entry.prev))
        .findFirst()
        .orElseThrow();
    tmp.prev = new List<>(null, tmp, newValue);
    return tmp.prev;
  }

  /**
   * Check value existing on some list.
   *
   * @param list  list
   * @param value value
   * @param <T>   type
   * @return true if value is contains on the list
   */
  public static <T> boolean contains(List<T> list, T value) {
    var executorService = Executors.newFixedThreadPool(2);
    var searchNext = executorService.submit(() -> Stream
        .iterate(list, Objects::nonNull, entry -> entry.next)
        .anyMatch(entry -> value.equals(entry.value)));
    var searchPrev = executorService.submit(() -> Stream
        .iterate(list, Objects::nonNull, entry -> entry.prev)
        .anyMatch(entry -> value.equals(entry.value)));
    executorService.shutdown();
    try {
      if (searchPrev.get() || searchNext.get()) {
        return true;
      }
    } catch (InterruptedException | ExecutionException exception) {
      exception.printStackTrace();
    }
    return false;
  }

  /**
   * Create the new list which is based on the input list and apply some function to each value.
   *
   * @param list        list
   * @param mapFunction function
   * @param <T>         type of input list
   * @param <R>         type of output list
   * @return new list with new type
   */
  public static <T, R> List<R> map(List<T> list, Function<T, R> mapFunction) {
    var newList = createOne(mapFunction.apply(list.value));
    var executorService = Executors.newFixedThreadPool(2);
    executorService.execute(() -> {
      var tmpList = list;
      var tmpNewList = newList;
      while (Objects.nonNull(tmpList.next)) {
        tmpList = tmpList.next;
        tmpNewList.next = new List<>(tmpNewList, null, mapFunction.apply(tmpList.value));
        tmpNewList = tmpNewList.next;
      }
    });
    executorService.execute(() -> {
      var tmpList = list;
      var tmpNewList = newList;
      while (Objects.nonNull(tmpList.prev)) {
        tmpList = tmpList.prev;
        tmpNewList.prev = new List<>(null, tmpNewList, mapFunction.apply(tmpList.value));
        tmpNewList = tmpNewList.prev;
      }
    });
    executorService.shutdown();
    while (true) {
      if (executorService.isTerminated()) {
        break;
      }
    }
    return newList;
  }

  /**
   * Get list length.
   *
   * @return length of list
   */
  public int length() {
    var executorService = Executors.newFixedThreadPool(2);
    var searchNext = executorService.submit(() -> (int) Stream
        .iterate(this, entry -> Objects.nonNull(entry.next), entry -> entry.next)
        .count());
    var searchPrev = executorService.submit(() -> (int) Stream
        .iterate(this, entry -> Objects.nonNull(entry.prev), entry -> entry.prev)
        .count());
    executorService.shutdown();
    try {
      return searchPrev.get() + searchNext.get() + 1;
    } catch (InterruptedException | ExecutionException exception) {
      exception.printStackTrace();
    }
    return 0;
  }

  /**
   * Put new value on the list.
   *
   * @param newValue value
   * @return new item of list
   */
  public List<T> insertAfter(T newValue) {
    var inserted = (List<T>) null;
    if (Objects.isNull(next)) {
      inserted = new List<>(this, null, newValue);
    } else {
      inserted = new List<>(this, next, newValue);
      inserted.next.prev = inserted;
    }
    next = inserted;
    return inserted;
  }

  /**
   * Put new values on the list.
   *
   * @param newValues values
   */
  public void insertAfter(T[] newValues) {
    for (T value : newValues) {
      this.insertAfter(value);
    }
  }

  /**
   * Delete value on the list.
   */
  public void delete() {
    if (Objects.isNull(prev) && Objects.nonNull(next)) {
      next.prev = null;
    } else if (Objects.isNull(next) && Objects.nonNull(prev)) {
      prev.next = null;
    } else {
      prev.connect(next);
    }
  }

  /**
   * Connect value on tne list.
   *
   * @param to value
   */
  public void connect(List<T> to) {
    this.next = to;
    to.prev = this;
  }

  public void setPrev(List<T> prev) {
    this.prev = prev;
  }

  public void setNext(List<T> next) {
    this.next = next;
  }

  public List<T> getNext() {
    return next;
  }

  public List<T> getPrev() {
    return prev;
  }

  public T getValue() {
    return value;
  }
}
