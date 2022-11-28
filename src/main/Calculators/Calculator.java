package Calculators;
import java.util.Scanner;

import SkillCalc.AppState;
public abstract class Calculator {
    double bonus = 0;
    int targetLevel = 2;
    Scanner sc = new Scanner(System.in);
    

    protected void GetPlayersTargetLevel(int skillIndex){
        System.out.println("Target Level (" + (AppState.GetPlayer().GetSkillLevelByArrayIndex(22) + 1) + "-99): ");
        if(sc.hasNextInt()){
            int input = sc.nextInt();
            if(input > 1 && input < 100){
                targetLevel = input;
                sc.nextLine();
                System.out.println("Target Level Entered: " + targetLevel);
            } else {
                GetPlayersTargetLevel(skillIndex);
            }
        } else {
            sc.nextLine();
            GetPlayersTargetLevel(skillIndex);
        }
    }
}
