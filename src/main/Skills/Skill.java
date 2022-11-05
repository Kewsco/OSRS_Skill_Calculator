package Skills;
enum Category {Production, Resource, Combat, Development}
public abstract class Skill {
    protected int id;
    protected String name;
    protected Category category;
    protected int currentRank;
    protected int currentXP;
    protected int currentLevel;

    public  String GetName(){ return this.name; }
    public Category GetCategory(){ return this.category; }
    public int GetCurrentXP(){ return this.currentXP; }
    public int GetCurrentLevel(){ return this.currentLevel; }
    public int GetCurrentRank(){ return this.currentRank; }
    public String GetFormattedXP(){ return String.format("%,d", currentXP); }
    public String GetFormattedRank(){ return String.format("%,d", currentRank); }
    public void SetCurrentRank(int rank){ this.currentRank = rank; }
    public void SetCurrentXP(int xp){ this.currentXP = xp; }
    public void SetCurrentLevel(int level){ this.currentLevel = level; }

    @Override
    public String toString(){
        return "Skill: " + this.name + "\n" + 
               "Category: " + this.category.name();
    }
}
