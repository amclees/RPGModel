package test;

import character.Equipable;
import character.ICharacter;
import core.EquipmentRegistry;
import core.ItemRegistry;
import core.MaterialRegistry;
import core.NPCRegistry;
import environment.Grid;
import environment.IGridItem;
import environment.Layer;

public class InventoryTest {
	public static void main(String[] args) {
		MaterialRegistry.init();
		ItemRegistry.init();
		EquipmentRegistry.init();
		NPCRegistry.init();
		
		Grid grid = new Grid(21, 21);
	}
	
	
}
