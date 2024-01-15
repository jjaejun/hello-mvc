package com.sh.mvc.common;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

public class SqlSessionTemplateTest {

    @DisplayName("정상적인 SqlSession객체를 반환한다.")
    @Test
    public void test() {
        // getSqlsession()을 적고 Ctrl(유지)+(Space*2) 이후 Ctrl+1로 static import
        // 이후 Ctrl+Alt+V로 SqlSession sqlSession 변수 선언
        SqlSession sqlSession = getSqlSession();
        assertThat(sqlSession).isNotNull();
    }
}
