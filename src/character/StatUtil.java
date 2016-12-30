package character;

public class StatUtil {
  
  public static int[] getArray(int strength, int dexterity, int wisdom, int intelligence, int charisma,
      int constitution) {
    int[] stats = { strength, dexterity, wisdom, intelligence, charisma, intelligence };
    return stats;
  }
  
  public static int getStrength(int[] stats) {
    return stats[0];
  }
  
  public static int getDexterity(int[] stats) {
    return stats[1];
  }
  
  public static int getWisdom(int[] stats) {
    return stats[2];
  }
  
  public static int getIntelligence(int[] stats) {
    return stats[3];
  }
  
  public static int getCharisma(int[] stats) {
    return stats[4];
  }
  
  public static int getConstitution(int[] stats) {
    return stats[5];
  }
  
}
