package SkillCalc;
import java.util.Scanner;

import Calculators.ConstructionCalc;

public class Menu {
    Scanner sc;

    public Menu(){
        sc = new Scanner(System.in);
    }

    public void PlayerInit(){
        System.out.println("----- Oldschool Runescape Skill Calculator -----");
        System.out.println("OSRS username: "); 
        String username = sc.nextLine();
        Player player = new Player(username);
        int status = PlayerService.GetPlayerStats(player);
        if(status == 1){
            AppState.SetMenu(CurrentMenu.MainMenu);
        }
    }

    public void MainMenu(){
        Player player = AppState.GetPlayer();
        int option = 0;
        do {
            System.out.println("\n----- Current Player: " + AppState.GetPlayer().GetUsername() + " -----");
            System.out.println("[1] - Print Player Statistics.");
            System.out.println("[2] - Print Activity Statistics.");
            System.out.println("[3] - View Calculators.");
            System.out.println("[4] - Change User.");
            System.out.println("[0] - Quit.");
            System.out.print("Option: ");
            if(sc.hasNextInt()){
                option = sc.nextInt();
                sc.nextLine();
            }

        } while(!(option >= 0 && option < 7));
        System.out.println("");
        switch(option){
            case 1:
                player.PrintPlayerStats();
                System.out.print("\nPress Enter to Continue... ");
                sc.nextLine();
                MainMenu();
                break;
            case 2:
                player.PrintActivityStats();
                break;
            case 3:
                AppState.SetMenu(CurrentMenu.CalcMenu);
                break;
            case 4:
                AppState.SetMenu(CurrentMenu.Initial);
                break;
            case 0:
                System.exit(1);
            default:
                System.out.println("Main Menu: ?");     
        }
    }

    public void CalculatorMenu(){
        int option = 0;
        do{
            System.out.println("\n----- Current Player: " + AppState.GetPlayer().GetUsername() + " -----");
            System.out.println("[1] - Construction");
            System.out.println("[2] - Herblore");
            System.out.println("[3] - Thieving");
            System.out.println("[0] - Return");
            System.out.print("Option: ");
            if(sc.hasNextInt()){
                option = sc.nextInt();
                sc.nextLine();
            }
        } while(!(option >= 0 && option < 4));
        System.out.println("");
        switch(option){
            case 1:
                new ConstructionCalc();
                break;
            case 2:
                System.out.println("Herblore Calculator...");
                break;
            case 3:
                System.out.println("Thieving Calculator...");
                break;
            case 0:
                AppState.SetMenu(CurrentMenu.MainMenu);
                break;
        }
    }
}
