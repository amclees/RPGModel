package test;

import character.Human;
import core.Dice;

public class SimpleHumanTest {

	public static void main(String[] args) {
		Human h1 = new Human("Guts", 60.0d, 90, 40, 30, 25, 21, 65, "Bank of the Hawk");
		Human h2 = new Human("Griffith", 55.0d, 45, 80, 40, 40, 45, 30, "Band of the Hawk");
		//Human h3 = new Human("Killua", 40.0d, 55, 75, 60, 28, 23, 35, "Hunter Association");
		
		while(h1.getHP() > 0 && h2.getHP() > 0) {
			int h1Init = h1.initiativeRoll();
			int h2Init = h2.initiativeRoll();
			System.out.println("George's initiative is " + h1Init);
			System.out.println("Gut's initiative is " + h2Init);
			int h1AC = h1.ACRoll();
			int h2AC = h2.ACRoll();
			int h1Attack = h1.attackRoll();
			int h2Attack = h2.attackRoll();
			System.out.println("Guts rolls an attack of " + h2Attack);
			System.out.println("George rolls an AC of " + h1AC);
			if(h1AC < h2Attack) {
				int dmgRoll = Dice.d6();
				System.out.println("Guts hits George for " + dmgRoll + " damage.");
				h1.setHP(h1.getHP() - dmgRoll);
			} else {
				System.out.println("Guts fails to hit George.");
			}
			System.out.println("George has " + h1.getHP() + "/" + h1.getMaxHP() + " HP");
			System.out.println("George rolls an attack of " + h1Attack);
			System.out.println("Guts rolls an AC of " + h2AC);
			if(h2AC < h1Attack) {
				int dmgRoll = Dice.d6();
				System.out.println("George hits Guts for " + dmgRoll + " damage.");
				h2.setHP(h2.getHP() - dmgRoll);
			} else {
				System.out.println("George fails to hit Guts.");
			}
			System.out.println("Guts has " + h2.getHP() + "/" + h2.getMaxHP() + " HP");
		}
		if(h1.getHP() > h2.getHP()) {
			System.out.print("George ");
		} else {
			System.out.print("Guts ");
		}
		System.out.println(" won.");
	}

}
