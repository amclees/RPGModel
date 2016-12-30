package inventory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LootTable {
  private List<IItem> allItems;
  private Map<IItem, int[]> quantityRanges;
  private Map<IItem, Double> likelihoodOfAppearence;

  public LootTable(List<IItem> allItems, Map<IItem, int[]> quantityRanges, Map<IItem, Double> likelihoodOfAppearence) {
    this.allItems = allItems;
    this.quantityRanges = quantityRanges;
    this.likelihoodOfAppearence = likelihoodOfAppearence;
  }
  
  public LootTable() {
    this.allItems = new LinkedList<IItem>();
    this.quantityRanges = new HashMap<IItem, int[]>();
    this.likelihoodOfAppearence = new HashMap<IItem, Double>();
  }

  public List<IItem> getAllItems() {
    return allItems;
  }
  
  public int[] getQuantityRange(IItem item) {
    return this.quantityRanges.get(item);
  }
  
  public double getLikelihood(IItem item) {
    return this.likelihoodOfAppearence.get(item);
  }

  public Map<IItem, int[]> getQuantityRangesMap() {
    return quantityRanges;
  }

  public Map<IItem, Double> getLikelihoodOfAppearenceMap() {
    return likelihoodOfAppearence;
  }
  
  public void addItem(IItem item, int[] quantityRanges, double likeliHoodOfAppearence) {
    this.allItems.add(item);
    this.quantityRanges.put(item, quantityRanges);
    this.likelihoodOfAppearence.put(item, likeliHoodOfAppearence);
  }

}
