package Skills;
enum PlankType {Plank, Oak, Teak, Mahogany}
public class Construction extends Skill{
    PlankType plankType;
    public Construction(){
        this.name = "Construction";
        this.category = Category.Production;
    }

    public void SetPlankType(PlankType type){
        this.plankType = type;
    }
}
