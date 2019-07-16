package ac.kr.ajou.longmethod;

import org.junit.Test;

public class LongMethodTest {
    public static final String INCORRECT_LENGTH_SOCIAL_STRING = "1234567";
    public static final String INCORRECT_SOCIAL_STRING = "abcdef";
    public static final String CORRECT_SOCIAL_STRING = "123456";
    LongMethod longMethod = LongMethod.builder().build();

    @Test(expected = RuntimeException.class)
    public void doSomething_SocialString_Below8Length() {
        this.longMethod.doSomething(INCORRECT_LENGTH_SOCIAL_STRING, "ab", "111111111");
    }

    @Test(expected = RuntimeException.class)
    public void doSomething_SocialString_DigitsErrors_with6Length() {
        this.longMethod.doSomething(INCORRECT_SOCIAL_STRING, "ab", "111111111");
    }

    @Test(expected = RuntimeException.class)
    public void doSomething_SocialString_Name_Is5Length() {
        this.longMethod.doSomething(CORRECT_SOCIAL_STRING, "abcdef", "111111111");
    }

}
