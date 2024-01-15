package com.sh.mvc.common.filter;

import com.sh.mvc.member.model.entity.Member;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * annotation은 속성을 가질 수 있다.
 * - 대표 속성 하나는 value로 alias 사용가능하고, 생략도 가능하다.
 * - @WebFilter 어노테이션은 urlPatterns, value와 동일
 */
@WebFilter(urlPatterns = {
        "/member/memberDetail",
        "/member/memberUpdate",
        "/member/memberDelete",
        "/board/boardCreate",
        "/board/boardUpdate"
})

public class AuthenticationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 인증여부 검사
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null) {
            session.setAttribute("msg", "로그인 후 사용가능합니다.😉");
            response.sendRedirect(request.getContextPath() + "/");
            return; // redirect/forward 이후 코드가 실행되지 않도록 한다
        }
        super.doFilter(request, response, chain);
    }
}
