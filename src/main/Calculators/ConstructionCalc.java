package Calculators;

import java.util.List;
import java.util.Scanner;

public final class ConstructionCalc extends Calculator{
    
    Scanner sc;

    public ConstructionCalc(){
        sc = new Scanner(System.in);
        super.GetPlayersTargetLevel(22);
        GetOutfitBonus();
    }

    private void GetOutfitBonus(){
        System.out.println("\n--------------------\n");
        System.out.println("Carpenter Outfit:");
        System.out.println("[1] Full Carpenters \n[2] Partial Carpenters \n[3] No Carpenters");
        if(sc.hasNextInt()){
            switch(sc.nextInt()){
                case 1:
                    super.bonus = 2.5;
                    break;
                case 2:
                    GetCarpenterPieces();
                    break;
                case 3:
                    break;
                default:
                    GetOutfitBonus();
            }
        } else {
            sc.nextLine();
            GetOutfitBonus();
        }
    }

    private void GetCarpenterPieces(){
        String[] pieces = {"Boots", "Legs", "Body", "Head"};
        double[] pieceValues = {0.2, 0.6, 0.8, 0.4};
        int totalPieces = 0;
        for(int i = 0; i < 4; i++){
            boolean correct = false;
            while(!correct){
                System.out.println("\nDo you own carpenter " + pieces[i] + "?\n[1] Yes\n[2] No");  
                if(sc.hasNextInt()){
                    switch(sc.nextInt()){
                        case 1:
                            totalPieces++;
                            super.bonus += pieceValues[i];
                            correct = true;
                            break;
                        case 2:
                            correct = true;
                            break;
                    }
                } else {
                    sc.nextLine();
                }
            }
        }
        if(totalPieces > 3){
            super.bonus += 0.5;
        }
        System.out.println("Total Bonus XP: " + super.bonus + "%");
    }
}
