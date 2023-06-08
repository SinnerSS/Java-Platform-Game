package main.java.com.group.platformgame.utils;

public class IntRange {
  private int start;
  private int end;

  public IntRange(int start, int end) {
      this.start = start;
      this.end = end;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getEnd() {
    return end;
  }

  public void setEnd(int end) {
    this.end = end;
  }

  public boolean isInRange(int value) {
    return value >= start && value < end;
  }

  public int getOffSet(int value) {
    return value - start;
  }
}