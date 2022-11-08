import Skills.*;

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
        skills[7] = new Cooking();
        skills[8] = new Woodcutting();
        skills[9] = new Fletching();
        skills[10] = new Fishing();
        skills[11] = new Firemaking();
        skills[12] = new Crafting();
        skills[13] = new Smithing();
        skills[14] = new Mining();
        skills[15] = new Herblore();
        skills[16] = new Agility();
        skills[17] = new Thieving();
        skills[18] = new Slayer();
        skills[19] = new Farming();
        skills[20] = new Runecrafting();
        skills[21] = new Hunter();
        skills[22] = new Construction();
    }

    public String GetUsername(){ return this.username; }
    public Skill GetSkill(int index) { return skills[index];}
    public Skill[] GetAllSkills(){ return skills; }

    public void SetTotalLevel(int lvl){ this.totalLevel = lvl;}
    public void SetOverallRank(int rank){ this.overallRank = rank;}
    public void SetTotalXP(int xp){ this.totalXP = xp;}
    public void SetCombatLevel(double cmb) {this.combatLevel = cmb;}
}