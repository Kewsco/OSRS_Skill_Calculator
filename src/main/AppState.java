// Application state class that will contain the relevant information regarding the current session.
// Singleton class.
enum CurrentMenu {Initial, MainMenu}
public final class AppState {
    private static AppState INSTANCE;
    private static CurrentMenu menu;
    private static Player player;


    private AppState(){
        menu = CurrentMenu.Initial;
        player = null;
    }

    public static AppState GetInstance(){
        if(INSTANCE == null)
            INSTANCE = new AppState();
        return INSTANCE;
    }

    public static void SetPlayer(Player p){ player = p; }
    public static void SetMenu(CurrentMenu m){ menu = m; }

    public static Player GetPlayer(){ return player; }
    public static int GetCurrentMenu(){ return menu.ordinal(); }
}
