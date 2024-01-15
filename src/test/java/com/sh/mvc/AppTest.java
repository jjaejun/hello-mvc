package com.sh.mvc;

import com.sh.mvc.App;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JUnit 테스트 구성요소
 * 1. fixture method : 매 테스트 전후작업 해야하는 것
 *  - fixture 테스트대상 또는 테스트에 필요한 객체
 *  - 모든 테스트는 독립적으로 진행(매번 새로운 테스트 객체를 생성 후 진행)
 *  - @BeforeAll, @AfterAll (static)
 *  - @BeforeEach, @AfterEach
 *
 * 2. 단정문 (assertion)
 *  - 코드실행결과는 이것이다~ 단정
 *  - assertThat(...)
 *  - assertNotNull(...)
 *  - ...
 *
 * 3. TestRunner : 테스트 주체
 *
 */
public class AppTest {
    App app = new App();

    // fixture 메소드
    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }
    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll");
    }
    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
        this.app = new App();
    }
    @AfterEach
    public void afterEach() {
        System.out.println("afterEach");
    }

    //무조건 public으로 선언해야한다
    @DisplayName("App#sum메소드 - 두수의 합을 반환하는지 테스느")
    @Test
    public void test() {
        System.out.println("(test1)");
        int result = app.sum(10, 20); // app클래스 생성해 app클래스 내부에 sum 메소드를 작성시켜 사용
        // assertThat(result) That 끝부분에서 알트+엔터로 임포트 필요
        assertThat(result).isEqualTo(30);

        result = app.sum(30, 5);
        assertThat(result).isEqualTo(35);
    }

    @DisplayName("App#random은 주어진 1 ~ 100 사이의 정수를 반환하는지 확인하는 메소드입니다")
    @Test
    public void test2() {
        System.out.println("(test2)");
        int n = app.random();
        System.out.println(n);
        assertThat(n)
                .isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(100);
    }

}
