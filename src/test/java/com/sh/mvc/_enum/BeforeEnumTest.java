package com.sh.mvc._enum;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BeforeEnumTest {
    public static final String TYPE_MEMBER = "M";
    public static final String TYPE_ADMIN = "A";
    public static final String GENDER_MALE = "M";
    public static final String GENDER_FEMALE = "F";
    public class Member {
        String id;
        String type; // M, A
        String gender; // M, F

        public Member(String id, String type, String gender) {
            this.id = id;
            this.type = type;
            this.gender = gender;
        }
    }

    @Test
    public void test1() {
        Member member = new Member("honggd", TYPE_MEMBER, GENDER_MALE);
        // 상수를 바꿨음에도 똑같은 'String'객체이기에 오류가 나지 않음(이런건 문제가 되는 상황 따라서, enum사용)
//        Member member = new Member("honggd", GENDER_MALE, GENDER_MALE);
        assertThat(member.type).isEqualTo(TYPE_MEMBER);
        assertThat(member.gender).isEqualTo(GENDER_MALE);

        Member admin = new Member("sinsa", TYPE_ADMIN, GENDER_FEMALE);
        assertThat(admin.type).isEqualTo(TYPE_ADMIN);
        assertThat(admin.gender).isEqualTo(GENDER_FEMALE);
    }
}
