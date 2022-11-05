import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Skills.Attack;
import Skills.Defence;
import Skills.Hitpoints;
import Skills.Magic;
import Skills.Prayer;
import Skills.Ranged;
import Skills.Skill;
import Skills.Strength;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Player {
    
    private String username;
    private int overallRank;
    private int totalLevel;
    private int totalXP;
    private int combatLevel;

    public Skill[] skills = new Skill[23];

    public Player(String username){
        this.username = username;
        PopulateSkillList();
        GetPlayerStats();
    }

    private void GetPlayerStats(){
        try {
            URL url = new URL(CreatePlayerURL());
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            int[] statLine = ParseStat(reader.readLine());
            this.overallRank = statLine[0];
            this.totalLevel = statLine[1];
            this.totalXP = statLine[2];
            for(int i = 0; i < 7; i++){ // Change to 23 when ready for all skills.
                statLine = ParseStat(reader.readLine());
                skills[i].SetCurrentRank(statLine[0]);
                skills[i].SetCurrentLevel(statLine[1]);
                skills[i].SetCurrentXP(statLine[2]);
            }
            //TODO:- Read ALL skill data and create the required objects.
            reader.close();
        } catch (MalformedURLException ex){
            ex.printStackTrace();
        } catch (IOException ex){
            System.out.println("Player not found...");
        }
    }

    public void PrintPlayerStats(){
        //TODO:- Format output so it looks neat in terminal.
        for (Skill skill : skills) {
            try{
                System.out.println("[" + skill.GetName() + "]" + 
                                   " - Rank: " + skill.GetFormattedRank() + 
                                   " - Level: " + skill.GetCurrentLevel() + 
                                   " - XP: " + skill.GetFormattedXP());
            } catch(NullPointerException ex){
                // Temporary whilst not all skills are instantiated.
            }
        }
    }

    public String toString(){
        String xpFormat = String.format("%,d", totalXP);
        String rankingFormat = String.format("%,d", overallRank);
        String totalFormat = String.format("%,d", totalLevel);
        return "Username: " + this.username + "\n" + 
               "Ranking: " + rankingFormat + " - Total Level: " + totalFormat + " - Total Experience: " + xpFormat;
    }

    private String CreatePlayerURL(){
        StringBuilder sb = new StringBuilder("https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=");
        sb.append((username.replaceAll("\\s+", "%20")));
        return sb.toString();
    }

    private int[] ParseStat(String statLine){
        List<String> statList = Arrays.asList(statLine.split(","));
        List<Integer> integerStats = new ArrayList<Integer>();
        for (String strStat : statList) {
            integerStats.add(Integer.parseInt(strStat));
        }
        int[] intArr = integerStats.stream().mapToInt(i->i).toArray();
        return intArr;
    }

    private void CalculateCombatLevel(){
        // TODO:- Calculate the players total level based on their combat skills.
        this.combatLevel = 3;
    }

    private void PopulateSkillList(){
        skills[0] = new Attack();
        skills[1] = new Defence();
        skills[2] = new Strength();
        skills[3] = new Hitpoints();
        skills[4] = new Ranged();
        skills[5] = new Prayer();
        skills[6] = new Magic();
    }
}