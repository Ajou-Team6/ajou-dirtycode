package ac.kr.ajou.refactoring;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class LoginServiceTest {

    LoginService loginService = new LoginService();

    @Test
    public void nullOrEmptyUserIdTest() {
        assertThat(loginService.login(null, "pwd"), is(false));
        assertThat(loginService.login("","pwd"),is(false));
    }

    @Test
    public void userExistsTest() {
        int users = 10;
        for(int loop = 0; loop < users; loop++)
        {
            assertThat(loginService.login("test1","test1"),is(true));
        }
        assertThat(loginService.login("test11","test11"),is(false));
    }

    @Test
    public void wrongPasswordTest() {
        String wrongPassword = "pwd";
        assertThat(loginService.login("test1", wrongPassword), is(false));
    }

    @Test
    public void rightUserNameTest() {
        int users = 10;
        for(int loop = 0; loop < users; loop++)
        {
            assertThat(loginService.getUserNameById("test" + loop),is("testUserName" + loop));
        }
    }

    @Test
    public void rightUserAgeTest() {
        int users = 10;
        for (int loop = 0; loop < users; loop++) {
            assertThat(loginService.getAgeById("test" + loop), is(10 + loop));
        }
    }
}
