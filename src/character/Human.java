package character;

public class Human extends Humanoid {
  private static final long serialVersionUID = 5209803077405543449L;

  public Human(String name, double weight, int strength, int dexterity, int wisdom, int intelligence, int charisma,
      int constitution, String faction) {
    super();
    this.name = name;
    this.size = Size.MEDIUM;
    this.weight = weight;
    this.speed = 5;

    this.strength = strength;
    this.dexterity = dexterity;
    this.wisdom = wisdom;
    this.intelligence = intelligence;
    this.charisma = charisma;
    this.constitution = constitution;

    this.HP = constitution * 2;
    this.maxHP = HP;

    this.xpToNextLevel = 1000;

    if (faction == null)
      this.faction = "human";
    else this.faction = faction;
  }

  public Human(Template template) {
    super(template);

    this.size = Size.MEDIUM;
    this.speed = 5;
    this.xpToNextLevel = 1000;

    this.loadStandardTemplate(template);

    this.HP = constitution * 2;
    this.maxHP = HP;
    if (this.faction == null) this.faction = "human";
  }

}
