package SkillCalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Skills.Skill;

public final class PlayerService {
    public static int GetPlayerStats(Player player) {
        try {
            URL url = new URL(CreatePlayerURL(player.GetUsername()));
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            int[] statLine = ParseStat(reader.readLine());
            player.SetOverallRank(statLine[0]);
            player.SetTotalLevel(statLine[1]);;
            player.SetTotalXP(statLine[2]);
            for(int i = 0; i < 23; i++){ 
                Skill sk = player.GetSkill(i);
                statLine = ParseStat(reader.readLine());
                sk.SetCurrentRank(statLine[0]);
                sk.SetCurrentLevel(statLine[1]);
                sk.SetCurrentXP(statLine[2]);
            }
            //TODO:- Read ALL skill data and create the required objects.
            reader.close();
            player.SetCombatLevel(CalculateCombatLevel(player));
            return 1;
        } catch (MalformedURLException ex){
            //ex.printStackTrace();
            System.out.println("Invalid URL...\n");
        } catch (IOException ex){
            //ex.printStackTrace();
            System.out.println("Player not found...\n");
        }
        return 0;
    }
    
    private static int[] ParseStat(String statLine){
        List<String> statList = Arrays.asList(statLine.split(","));
        List<Integer> integerStats = new ArrayList<Integer>();
        for (String strStat : statList) {
            integerStats.add(Integer.parseInt(strStat));
        }
        int[] intArr = integerStats.stream().mapToInt(i->i).toArray();
        return intArr;
    }

    // Creates a valid URL with the username provided.
    private static String CreatePlayerURL(String username){
        StringBuilder sb = new StringBuilder("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=");
        sb.append((username.replaceAll("\\s+", "%20")));
        return sb.toString();
    }

    private static  double CalculateCombatLevel(Player player){
        Skill[] skills = player.GetAllSkills();
        double pray = Math.floor(skills[5].GetCurrentLevel() / 2);
        double defHP = skills[1].GetCurrentLevel() + skills[3].GetCurrentLevel();
        double baseCmb = (pray + defHP) / 4;
        double strAtt = (skills[0].GetCurrentLevel() + skills[2].GetCurrentLevel()) * 0.325;
        double baseMelee = (baseCmb + strAtt);
        return baseMelee;
        
        // TODO:- Calculate the players total level, factoring in their ranged and magic level (not just melee).
    }
}
