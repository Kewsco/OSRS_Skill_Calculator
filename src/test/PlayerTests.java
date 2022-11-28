import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import SkillCalc.Player;
import Skills.Skill;

public class PlayerTests {
    
    @Test
    public void AllSKillsPopulated(){
        Player p = new Player("Name");        
        assertTrue(Arrays.asList(p.GetAllSkills()) instanceof List<Skill>);
    }
}
