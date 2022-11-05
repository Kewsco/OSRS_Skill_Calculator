package Skills;
enum Category {Production, Resource, Combat, Development}
public abstract class Skill {
    protected String name;
    protected Category category;
    protected int currentXP;
    protected int currentLevel;

    public  String GetName(){ return this.name; }
    public Category GetCategory(){ return this.category; }
    public int GetCurrentXP(){ return this.currentXP; }
    public int GetCurrentLevel(){ return this.currentLevel; }
    public String GetFormattedXP(){return String.format("%,d", currentXP);}
    @Override
    public String toString(){
        return "Skill: " + this.name + "\n" + 
               "Category: " + this.category.name();
    }
}
