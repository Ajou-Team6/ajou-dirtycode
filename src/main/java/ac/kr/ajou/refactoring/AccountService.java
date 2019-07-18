package ac.kr.ajou.refactoring;

public class AccountService {
    private final LoginService loginService;

    public AccountService(LoginService loginService) {
        this.loginService = loginService;
    }

    public String getUserNameById(String id) {
        if (loginService.getDataBaseService().userExists(id)) {
            Player player = loginService.getDataBaseService().getPlayerById(id);
            return player.getUserName();
        }
        return null;
    }

    public int getAgeById(String id) {
        if (loginService.getDataBaseService().userExists(id)) {
            Player player = loginService.getDataBaseService().getPlayerById(id);
            return player.getAge();
        }
        return -1;
    }
}