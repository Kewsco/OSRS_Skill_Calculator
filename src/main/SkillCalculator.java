class SkillCalculator {
    static AppState state;
    static Menu menu;
    public static void main(String[] args) {
        state = AppState.GetInstance();
        menu = new Menu();
        while(true)
            MenuHandler();
    }

    public static void MenuHandler(){
        switch(AppState.GetCurrentMenu()){
            case 0:
                menu.PlayerInit();
                break;
            case 1:
                menu.MainMenu();
                break;
        }
    }
}