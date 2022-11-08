import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SkillCalculatorTest {
    
    @Test
    public void CreateValidPlayerTest(){
        Player validPlayer = new Player("Marsh Kebab");
        int code = PlayerService.GetPlayerStats(validPlayer);
        assertEquals(code, 1);
    }

    @Test
    public void CreateInvalidPlayer(){
        Player invalidPlayer = new Player("alskjfnbaslifnskljdfn");
        int code = PlayerService.GetPlayerStats(invalidPlayer);
        assertEquals(code, 0);
    }

    @Test
    public void NoUserNameTest(){
        Player invalidPlayer = new Player("");
        int code = PlayerService.GetPlayerStats(invalidPlayer);
        assertEquals(code, 0);
    }
}
