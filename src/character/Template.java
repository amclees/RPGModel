package character;

import java.util.Map;

import inventory.LootTable;

public class Template {
  private double statVariation;
  private int[] baseStats;
  private LootTable lootTable;
  private String faction;
  private Map<String, Double> names; //Double indicates weight in random choice
  private int[] weightRange;
  private int[] xpRange;
  
  //Don't need equipment, use auto-equip
  
  public Template(int[] baseStats, double statVariation, LootTable lootTable, Map<String, Double> names, int[] weightRange,
      String faction, int[] xpRange) {
    this.baseStats = baseStats;
    this.statVariation = statVariation;
    this.lootTable = lootTable;
    this.names = names;
    this.weightRange = weightRange;
    this.faction = faction;
    this.xpRange = xpRange;
  }

  public int[] getXpRange() {
    return xpRange;
  }

  public double getVariation() {
    return statVariation;
  }

  public int[] getBaseStats() {
    return baseStats;
  }

  public LootTable getLootTable() {
    return lootTable;
  }

  public String getFaction() {
    return faction;
  }

  public Map<String, Double> getNames() {
    return names;
  }

  public int[] getWeightRange() {
    return weightRange;
  }
  
}
