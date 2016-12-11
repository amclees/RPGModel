package character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import core.Dice;
import core.EquipmentRegistry;
import core.Material;
import environment.Action;
import environment.AttackAction;
import environment.Grid;
import environment.IGridItem;
import environment.Layer;
import environment.MoveAction;
import inventory.Inventory;

public abstract class Character implements ICharacter {
	protected static final long serialVersionUID = -32193334475144657L;
	protected Size size;
	protected Material material;
	protected double weight;
	protected int speed;
	protected int level;
	protected int xp;
	protected int xpToNextLevel;
	protected int maxHP;
	protected int HP;
	protected String faction;
	
	protected String imagePath;
	protected int strength;
	protected int dexterity;
	protected int constitution;
	protected int wisdom;
	protected int intelligence;
	protected int charisma;
	private Map<String, Object> properties; //Note that properties DO NOT TRANSFER on copy
	protected int x;
	protected int y;
	protected String name;
	private List<int[]> pathtoOldDest;
	private IGridItem oldDest;
	private Inventory inventory;
	
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public void neighborChange(Grid grid) {
		//Code for attack of opportunity and such
	}
	
	public int getXP() {
		return this.xp;
	}
	
	public int getXPToNextLevel() {
		return this.xpToNextLevel;
	}
	
	public Character() {
		properties = new HashMap<String, Object>();
		this.xp = 0;
		this.level = 1;
		this.inventory = new Inventory(this.getName());
		this.getGUID();
	}
	
	public void loseHP(int HP) {
		this.HP -= HP;
	}
	
	public String getFaction() {
		return this.faction;
	}
	
	public Action getCombatAction(Grid grid) {
		if(this.getHP() < 1) {
			//Move into this.losehp once grid ref is inside of characrer
			grid.setElement(this.x, this.y, Layer.CORPSE, this);
			grid.removeElement(this.x, this.y, Layer.CHARACTER);
			return null;
		}
		
		Action action;
		List<ICharacter> charactersByDistance = new LinkedList<ICharacter>();
		Map<ICharacter, Integer> characterToDistance = new HashMap<ICharacter, Integer>();
		
		for(ICharacter c : grid.getCharacters()) {
			if(c.equals(this)) continue;
			if(c.getHP() < 1) continue;
			if(c.getFaction().equals(this.getFaction())) continue;
		
			int distance = Math.abs(this.getX() - c.getX()) + Math.abs(this.getY() - c.getY());
			characterToDistance.put(c, distance);
			int index = 0;
			for(ICharacter r : charactersByDistance) {
				if(characterToDistance.get(r) < distance) index++;
			}
			charactersByDistance.add(index, c);
		}
		if(charactersByDistance.size() == 0) return null;
		
		ICharacter target = null;
		
		for(ICharacter c : charactersByDistance) {
			List<int[]> neighbors = new ArrayList<int[]>(8);
			int[] current = { c.getX(), c.getY() };
			for(int i = -1; i <= 1; i++) {
				for(int j = -1; j <= 1; j++) {
					if(j == 0 || i == 0){
						int[] neighbor = {i + current[0], j + current[1]};
						try {
							if(grid.getElement(neighbor[0], neighbor[1], Layer.CHARACTER) == null) neighbors.add(neighbor);
						} catch(ArrayIndexOutOfBoundsException e) {}
					}			
				}
			}
			if(neighbors.size() != 0) {
				target = c;
				break;
			}
		}
		if(target == null) return null;
		
		Equipable weapon = EquipmentRegistry.getEquipment("fist");
		
		for(Equipable e : this.getEquipment()) {
			int[] hereRange = {this.x, this.y};
			int[] targRange = {target.getX(), target.getY()};
			if(dist(hereRange, targRange) <= e.getRange()) {
				if(e.getAttackDice() * e.getDiceSides() >= weapon.getAttackDice() * weapon.getDiceSides()) {
					weapon = e;
				}
			}
		}
		
		
		if(Math.abs(target.getX() - this.getX()) + Math.abs(target.getY() - this.getY()) <= weapon.getRange()) {
			//System.out.println(this.getName() + " is targeting " + target.getName());
			action = new AttackAction(this, target, grid, weapon.getEquipmentSlot());
		} else {
			//System.out.println(this.getName() + " is moving towards " + target.getName());
			List<int[]> steps = this.getSteps(target, grid, weapon.getRange());
			action = new MoveAction(this, grid, steps);
		}
		
		return action;
	}
	
	/* Ideas to improve this:
	 * Limits the maxNodes to a better number
	 * Store the previously found path until the destination changes, so that you don't recalculate every time
	 * Use concurrent maps and such to multithread this (Will takes quite a while, wait until after other methods)
	 * 
	 * There is an issue with pathing. Sometimes NPCs with same target and faction next to each other don't move
	 */
	private List<int[]> getSteps(IGridItem destination, Grid grid, int range) {
		List<int[]> path;
		if(oldDest != destination) {
			int destX = destination.getX();
			int destY = destination.getY();
			int[] dest = {destX, destY};
			int[] here = {this.getX(), this.getY()};
			//System.out.println("Pathing from here " + here[0] + ", " + here[1] + " to dest " + dest[0] + ", " + dest[1]);
	
			
			List<int[]> uncheckedCoords = new LinkedList<int[]>();
			List<int[]> checkedCoords = new LinkedList<int[]>();
			Map<int[], int[]> bestApproach = new HashMap<int[], int[]>();
			Map<int[], Integer> costToCoord = new HashMap<int[], Integer>();
			Map<int[], Integer> totalEstimatedPassingCost = new HashMap<int[], Integer>();
			uncheckedCoords.add(here);
			costToCoord.put(here, 0);
			totalEstimatedPassingCost.put(here, dist(here, dest));
			int[] current = null;
			int maxNodes = dist(dest, here) * 3;
			while(uncheckedCoords.size() != 0) {
				if(maxNodes < 0) break;
				maxNodes--;
				current = null;
				int currentCost = Integer.MAX_VALUE;
				for(int[] c : uncheckedCoords) {
					if(totalEstimatedPassingCost.get(c) < currentCost) {
						current = c;
						currentCost = totalEstimatedPassingCost.get(c);
					}
				}
				//System.out.println("Current is " + current[0] + ", " + current[1] + " with est. total cost of " + currentCost);
				if(dist(dest, current) == 1) {
					break;
				}
				
				uncheckedCoords.remove(current);
				checkedCoords.add(current);
				
				List<int[]> neighbors = new ArrayList<int[]>(8);
				for(int i = -1; i <= 1; i++) {
					for(int j = -1; j <= 1; j++) {
						if(!(i == 0 && j == 0)){
							int[] neighbor = {i + current[0], j + current[1]};
							try {
								if(grid.getElement(neighbor[0], neighbor[1], Layer.CHARACTER) == null) neighbors.add(neighbor);
							} catch(ArrayIndexOutOfBoundsException e) {}
						}			
					}
				}
				
				
				for(int[] neighbor : neighbors) {
					//System.out.println(neighbor[0] + ", " + neighbor[1]);
					if(checkedCoords.contains(neighbor)) continue;
					int costToNeighbor = dist(current, neighbor) + costToCoord.get(current);
					int oldCostToNeighbor = costToCoord.containsKey(neighbor) ? costToCoord.get(neighbor) : Integer.MAX_VALUE;
					//System.out.println("For neighbor at " + neighbor[0] + ", " + neighbor[1] + ": " + costToNeighbor + " is cost to neighbor. " + oldCostToNeighbor + " is previous cost.");
					if(!uncheckedCoords.contains(neighbor)) uncheckedCoords.add(neighbor);
					else if(costToNeighbor >= oldCostToNeighbor) continue;
					
					bestApproach.put(neighbor, current);
					costToCoord.put(neighbor, costToNeighbor);
					totalEstimatedPassingCost.put(neighbor, costToCoord.get(neighbor) + estimateCost(neighbor, dest));
					
				}
				
	 		} 
			
			path = new LinkedList<int[]>();
			path.add(current);
			while(bestApproach.containsKey(current)) {
				current = bestApproach.get(current);
				path.add(0, current);
			}
			this.oldDest = destination;
			this.pathtoOldDest = path;
		} else {
			path = this.pathtoOldDest;
		}
		
		List<int[]> steps = new ArrayList<int[]>(this.getSpeed());
		
		for(int i = 0; i < this.getSpeed() && i < path.size(); i++) {
			steps.add(path.get(i));
		}
		
		return steps;
		
	}
	
	public static int estimateCost(int[] neighbor, int[] dest) {
		return (int)Math.round(1.5d * (double)dist(neighbor, dest));
	}
	
	public static int dist(int[] point1, int[] point2) {
		return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
	}
	
	public String getName() {
		return this.name;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	//How much XP to get from killing this
	public int getChallengeRating() {
		int rating = 0;
		rating += speed;
		rating += strength * 2;
		rating += dexterity * 2;
		rating += wisdom * 2;
		rating += intelligence * 2;
		rating += constitution * 2;
		rating += charisma * 2;
		return rating;
	}
	
	public void setProperty(String key, Object value) {
		properties.remove(key);
		properties.put(key, value);
	}
	
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	public int attackRoll() {
		int roll = Dice.d20();
		roll += strength / 3;
		roll += dexterity / 9;
		return roll;
	}
	
	public int ACRoll() {
		int roll = Dice.d20();
		roll += dexterity / 6;
		roll += strength / 9;
		for(Equipable e : this.getEquipment()) {
			roll -= e.getDexPenalty() / 2;
		}
		return roll;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
	
	public int getHP() {
		return HP;
	}
	public void setHP(int HP) {
		this.HP = HP;
	}
	public void addXP(int xp) {
		this.xp += xp;
		if(this.xp >= xpToNextLevel) {
			this.xp -= xpToNextLevel;
			xpToNextLevel = (int)(xpToNextLevel * 1.35d);
			level++;
			strength = levelStat(strength);
			dexterity = levelStat(dexterity);
			constitution = levelStat(constitution);
			wisdom = levelStat(wisdom);
			intelligence = levelStat(intelligence);
			charisma = levelStat(intelligence);
			maxHP += constitution / 5;
			HP = maxHP;
			addXP(0);
		}
	}
	
	private int levelStat(int stat) {
		return stat + (int)Math.round(stat * Math.random() / 12d);
		//return stat + (int)Math.floor(Math.random() * 2);
	}
	
	public Size getSize() {
		return size;
	}
	public int getSpeed() {
		return speed;
	}
	public int initiativeRoll() {
		int roll = Dice.d20();
		roll += dexterity / 6;
		roll += wisdom;
		for(Equipable e : this.getEquipment()) {
			roll -= e.getDexPenalty() / 6;
		}
		return roll;
	}
	public int getLevel() {
		return level;
	}
	public int getXp() {
		return xp;
	}
	public int getStrength() {
		return strength;
	}
	public int getDexterity() {
		return dexterity;
	}
	public int getConstitution() {
		return constitution;
	}
	public int getWisdom() {
		return wisdom;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public int getCharisma() {
		return charisma;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
}
