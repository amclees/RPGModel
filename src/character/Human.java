package character;

import core.Material;

public class Human extends Humanoid {

  public Human(String name, double weight, int strength, int dexterity, int wisdom, int intelligence, int charisma,
      int constitution, String faction) {
    super();
    this.name = name;
    this.size = Size.MEDIUM;
    this.weight = weight;
    speed = 5;

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
    else
      this.faction = faction;
  }

}
