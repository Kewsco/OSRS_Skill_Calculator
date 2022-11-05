import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Skills.Skill;
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

    public List<Skill> skills;

    public Player(String username){
        this.username = username;
        GetPlayerStats();
    }

    private void GetPlayerStats(){
        try {
            URL url = new URL(CreatePlayerURL());
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = reader.readLine();
            int[] statLine = ParseStat(line);
            this.overallRank = statLine[0];
            this.totalLevel = statLine[1];
            this.totalXP = statLine[2];
            reader.close();
        } catch (MalformedURLException ex){
            ex.printStackTrace();
        } catch (IOException ex){
            System.out.println("Player not found...");
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
}