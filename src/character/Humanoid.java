package character;

import java.util.LinkedList;
import java.util.List;

import environment.Material;

public abstract class Humanoid extends Character {
  private static final long serialVersionUID = -4243610666877934543L;
  private Equipable feet;
  private Equipable legs;
  private Equipable chest;
  private Equipable head;
  private Equipable neck;
  private Equipable leftHand;
  private Equipable rightHand;
  private List<Equipable> noneEquipable;

  public Humanoid() {
    super();
    noneEquipable = new LinkedList<Equipable>();
    this.material = new Material("flesh", 0.985, 0.05, 0.1, 0);
  }

  public Humanoid(Template template) {
    super(template);
    noneEquipable = new LinkedList<Equipable>();
    this.material = new Material("flesh", 0.985, 0.05, 0.1, 0);
  }

  public void equip(Equipable toEquip, EquipmentSlot slot) {
    if (this.getEquipmentInSlot(slot) == null && toEquip.getEquipmentSlot() == slot) {
      switch (slot) {
        case LEGS:
          legs = toEquip;
          break;
        case FEET:
          feet = toEquip;
          break;
        case CHEST:
          chest = toEquip;
          break;
        case HEAD:
          head = toEquip;
          break;
        case NECK:
          neck = toEquip;
          break;
        case LEFTHAND:
          leftHand = toEquip;
          break;
        case RIGHTHAND:
          rightHand = toEquip;
          break;
        case NONE:
          noneEquipable.add(toEquip);
          break;
      }
    }
  }

  public void unequip(EquipmentSlot slot) {
    Equipable e = this.getEquipmentInSlot(slot);
    e = null;
  }

  public Equipable getEquipmentInSlot(EquipmentSlot slot) {
    switch (slot) {
      case LEGS:
        return legs;
      case FEET:
        return feet;
      case CHEST:
        return chest;
      case HEAD:
        return head;
      case NECK:
        return neck;
      case LEFTHAND:
        return leftHand;
      case RIGHTHAND:
        return rightHand;
    }
    return null;
  }

  public List<Equipable> getEquipment() {
    List<Equipable> list = new LinkedList<Equipable>();
    if (feet != null) list.add(feet);
    if (legs != null) list.add(legs);
    if (chest != null) list.add(chest);
    if (head != null) list.add(head);
    if (neck != null) list.add(neck);
    if (rightHand != null) list.add(rightHand);
    if (leftHand != null) list.add(leftHand);
    for (Equipable e : noneEquipable) {
      if (e != null) list.add(e);
    }
    return list;
  }
}
