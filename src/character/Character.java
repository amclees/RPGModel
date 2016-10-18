package character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.Dice;
import core.EquipmentRegistry;
import core.Material;
import environment.Action;
import environment.AttackAction;
import environment.Grid;
import environment.GridItem;
import environment.Layer;
import environment.MoveAction;

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
	
	protected int strength;
	protected int dexterity;
	protected int constitution;
	protected int wisdom;
	protected int intelligence;
	protected int charisma;
	private Map<String, Object> properties;
	protected int x;
	protected int y;
	protected String name;
	
	
	public Character() {
		properties = new HashMap<String, Object>();
		this.xp = 0;
		this.level = 1;
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
		
		Equipable weapon = EquipmentRegistry.getEquipment("fist");
		
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
		
		
		for(Equipable e : this.getEquipment()) {
			if(Math.abs(target.getX() - this.getX()) + Math.abs(target.getY() - this.getY()) <= weapon.getRange()) {
				if(e.getAttackDice() * e.getDiceSides() > weapon.getAttackDice() * weapon.getDiceSides()) {
					weapon = e;
				}
			}
		}
		
		
		if(Math.abs(target.getX() - this.getX()) + Math.abs(target.getY() - this.getY()) <= weapon.getRange()) {
			System.out.println(this.getName() + " is targeting " + target.getName());
			action = new AttackAction(this, target, grid, EquipmentSlot.NONE);
		} else {
			System.out.println(this.getName() + " is moving towards " + target.getName());
			List<int[]> steps = this.getSteps(target, grid, weapon.getRange());
			action = new MoveAction(this, grid, steps);
		}
		
		return action;
	}
	
	/* Ideas to improve this:
	 * Limits the maxNodes to a number below number of nodes in the grid
	 * Store the previously found path until the destination changes, so that you don't recalculate every time
	 * Use concurrent maps and such to multithread this (Will takes quite a while, wait until after other methods)
	 */
	private List<int[]> getSteps(GridItem destination, Grid grid, int range) {
		int destX = destination.getX();
		int destY = destination.getY();
		int[] dest = {destX, destY};
		int[] here = {this.getX(), this.getY()};
		System.out.println("Pathing from here " + here[0] + ", " + here[1] + " to dest " + dest[0] + ", " + dest[1]);

		
		List<int[]> uncheckedCoords = new LinkedList<int[]>();
		List<int[]> checkedCoords = new LinkedList<int[]>();
		Map<int[], int[]> bestApproach = new HashMap<int[], int[]>();
		Map<int[], Integer> costToCoord = new HashMap<int[], Integer>();
		Map<int[], Integer> totalEstimatedPassingCost = new HashMap<int[], Integer>();
		uncheckedCoords.add(here);
		costToCoord.put(here, 0);
		totalEstimatedPassingCost.put(here, dist(here, dest));
		int[] current = null;
		int maxNodes = grid.getHeight() * grid.getWidth();
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
		
		List<int[]> path = new LinkedList<int[]>();
		path.add(current);
		while(bestApproach.containsKey(current)) {
			current = bestApproach.get(current);
			path.add(0, current);
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
	
	public void move(int xChange, int yChange) {
		x += xChange;
		y += yChange;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public int getChallengeRating() {
		int rating = 0;
		rating += speed * 5;
		rating += level * 10;
		rating += HP;
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
		roll += strength / 2;
		roll += dexterity / 6;
		return roll;
	}
	
	public int ACRoll() {
		int roll = Dice.d20();
		roll += dexterity / 2;
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
		xp += xp;
		if(xp >= xpToNextLevel) {
			xp -= xpToNextLevel;
			xpToNextLevel = (int)(xpToNextLevel * 1.2d);
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
		return (int)Math.floor(Math.random() * 2);
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
	
	
	
}
