import Skills.Attack;
import Skills.Defence;
import Skills.Hitpoints;
import Skills.Magic;
import Skills.Prayer;
import Skills.Ranged;
import Skills.Skill;
import Skills.Strength;

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
    public Skill GetSkill(int index) { return skills[index];}
    public Skill[] GetAllSkills(){ return skills; }

    public void SetTotalLevel(int lvl){ this.totalLevel = lvl;}
    public void SetOverallRank(int rank){ this.overallRank = rank;}
    public void SetTotalXP(int xp){ this.totalXP = xp;}
    public void SetCombatLevel(double cmb) {this.combatLevel = cmb;}
}