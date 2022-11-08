import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.synth.SynthToolBarUI;

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
    private double combatLevel;

    public Skill[] skills = new Skill[23];

    public Player(String username){
        this.username = username;
        PopulateSkillList();
        AppState.SetPlayer(this);
    }
    
    public int GetPlayerStats(){
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
            this.combatLevel = CalculateCombatLevel();
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

    public void PrintPlayerStats(){
        //TODO:- Format output so it looks neat in terminal.
        System.out.println(toString());
        for (Skill skill : skills) {
            try{
                System.out.println("[" + skill.GetName() + "]" + 
                                   " - Rank: " + skill.GetFormattedRank() + 
                                   " - Level: " + skill.GetCurrentLevel() + 
                                   " - XP: " + skill.GetFormattedXP());
            } catch(NullPointerException ex){
                // Temporary whilst not all skills are present.
            }
        }
    }

    public void PrintActivityStats(){
        System.out.println("Activity Stats not yet implemented.");
    }

    public String toString(){
        String xpFormat = String.format("%,d", totalXP);
        String rankingFormat = String.format("%,d", overallRank);
        String totalFormat = String.format("%,d", totalLevel);
        String cmbFormat = String.format("%.3f", combatLevel);
        return "Username: " + this.username + "\n" + 
               "Ranking: " + rankingFormat + " - Total Level: " + totalFormat + " - Combat Level: " + cmbFormat + " - Total Experience: " + xpFormat;
    }

    // Creates a valid URL with the username provided.
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

    private double CalculateCombatLevel(){
        // TODO:- Calculate the players total level based on their combat skills.
        double pray = Math.floor(skills[5].GetCurrentLevel() / 2);
        double defHP = skills[1].GetCurrentLevel() + skills[3].GetCurrentLevel();
        double baseCmb = (pray + defHP) / 4;
        double strAtt = (skills[0].GetCurrentLevel() + skills[2].GetCurrentLevel()) * 0.325;
        double baseMelee = (baseCmb + strAtt);
        return baseMelee;
    }
    
    private void PopulateSkillList(){
        skills[0] = new Attack();
        skills[1] = new Defence();
        skills[2] = new Strength();
        skills[3] = new Hitpoints();
        skills[4] = new Ranged();
        skills[5] = new Prayer();
        skills[6] = new Magic();
        // skills[7] = new ();
        // skills[8] = new ();
        // skills[9] = new ();
        // skills[10] = new ();
        // skills[11] = new ();
        // skills[12] = new ();
        // skills[13] = new ();
        // skills[14] = new ();
        // skills[15] = new ();
        // skills[16] = new ();
        // skills[17] = new ();
        // skills[18] = new ();
        // skills[19] = new ();
        // skills[20] = new ();
        // skills[21] = new ();
        // skills[22] = new ();
    }

    public String GetUsername(){ return this.username; }
}