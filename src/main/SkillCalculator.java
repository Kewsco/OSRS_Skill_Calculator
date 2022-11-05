import java.util.Scanner;

import Skills.*;
import Skills.Skill;

class SkillCalculator {
    public static void main(String[] args) {
        UserIntro();
    }

    public static void UserIntro(){
        System.out.println("----- Oldschool Runescape Skill Calculator -----");
        System.out.println("Enter your username: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        sc.close();
        Player player = new Player(username);
        player.PrintPlayerStats();
    }
}