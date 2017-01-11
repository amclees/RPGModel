package environment;

public final class Material {
  private String name;
  private Double density; // g/cm3
  private Double hardness;
  private Double durability;
  private int averageValue; // silver/g

  public Material(String name, Double density, Double hardness, Double durability, int averageValue) {
    this.name = name;
    this.density = density;
    this.hardness = hardness;
    this.durability = durability;
    this.averageValue = averageValue;
  }

  public String getName() {
    return name;
  }

  public Double getDensity() {
    return density;
  }

  public Double getHardness() {
    return hardness;
  }

  public Double getDurability() {
    return durability;
  }

  public int getAverageValue() {
    return averageValue;
  }
}
