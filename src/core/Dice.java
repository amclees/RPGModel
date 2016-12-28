package core;

public class Dice {
  public static int dx(int sides, int dice) {
    int sum = 0;
    for (int i = 0; i < dice; i++) {
      sum += (int) Math.floor(1 + (Math.random() * sides));
    }
    return sum;
  }

  public static int dx(int sides) {
    return dx(sides, 1);
  }

  public static int d20(int dice) {
    return dx(20, dice);
  }

  public static int d20() {
    return d20(1);
  }

  public static int d12(int dice) {
    return dx(12, dice);
  }

  public static int d12() {
    return d12(1);
  }

  public static int d10(int dice) {
    return dx(10, dice);
  }

  public static int d10() {
    return d10(1);
  }

  public static int d8(int dice) {
    return dx(8, dice);
  }

  public static int d8() {
    return d8(1);
  }

  public static int d6(int dice) {
    return dx(6, dice);
  }

  public static int d6() {
    return d6(1);
  }

  public static int d4(int dice) {
    return dx(4, dice);
  }

  public static int d4() {
    return d4(1);
  }

}
