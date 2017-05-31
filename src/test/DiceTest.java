package test;

import core.Dice;

public class DiceTest {

  public static void main(String[] args) {
    int times = 50000000;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    while (times > 0) {
      times--;
      int roll = Dice.d12();
      if (roll < min) {
        min = roll;
      }
      if (roll > max) {
        max = roll;
      }
    }
    System.out.println("Minimum roll was " + min);
    System.out.println("Maximum roll was " + max);
  }

}
