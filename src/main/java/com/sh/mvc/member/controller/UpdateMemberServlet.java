package com.sh.mvc.member.controller;

import com.sh.mvc.common.HelloMvcUtils;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/updatePassword")
public class UpdateMemberServlet extends HttpServlet {
    private MemberService memberService = new MemberService();
    
    // 비밀번호 변경 페이지 제공
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/member/updatePassword.jsp").forward(req, resp);
    }
    
    // 비밀번호 변경처리

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        String id = loginMember.getId();
        
        String location = req.getContextPath();
        String msg = null;

        // 1. 사용자 입력값 처리 : 기존 비밀번호/신규 비밀번호 암호화 처리 필수
        String oldPassword = HelloMvcUtils.getEncryptedPassword(req.getParameter("oldPassword"), id);
        String newPassword = HelloMvcUtils.getEncryptedPassword(req.getParameter("newPassword"), id);
        
        // 2. 기존 비밀번호 비교 : session의 loginMember객체 이용할 것
        if (oldPassword.equals(loginMember.getPassword())) {

            // 3. 업무로직 : 기존 비밀번호가 일치한 경우만 신규 비밀번호로 업데이트한다.
            loginMember.setPassword(newPassword); // 세션 갱신
            int result = memberService.updatePassword(loginMember);
            msg = "비밀번호를 성공적으로 변경했습니다.";
            location += "/member/memberDetail";
        } else {
            msg = "비밀번호가 일치하지 않습니다.";
            location += "/member/updatePassword";
        }

        // 4. 사용자 경고창 및 리다이렉트 처리
        // 기존비밀번호가 일치하지 않았다면, "비밀번호가 일치하지 않습니다." 안내 & /mvc/member/updatePassword 리다이렉트
        // 기존비밀번호가 일치하고, 신규비밀번호 변경에 성공했다면, "비밀번호를 성공적으로 변경했습니다." 안내 & /mvc/member/memberView 리다이렉트
        session.setAttribute("msg", msg);
        resp.sendRedirect(location);
    }
}