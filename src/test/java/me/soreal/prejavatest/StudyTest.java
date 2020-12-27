package me.soreal.prejavatest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    // Ctrl + Shift + T 혹은 Shift 두번으로 test 검색해서 찾기
    // Maven Dependencies 확인 : Junit5
//    org.junit.jupiter:junit-jupiter:5.7.0
//    org.mockito:mockito-junit-jupiter:3.6.0

    @Test
    @DisplayName("스터디 만들기")
    void create_new_study() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }

    @Test
    @Disabled
    void create_new_study_again() {
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        // 무조건 static void
        // 모든 테스트 실행 시 딱 한 번 호출
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        // 무조건 static void
        // 모든 테스트 실행 시 딱 한 번 호출
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each");
    }

    @AfterEach
    void afterEach(){
        System.out.println("After each");
    }





}