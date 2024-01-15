package com.sh.mvc.admin.controller;

import com.sh.mvc.common.HelloMvcUtils;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 페이징
 * 1. content
 *  - page 현재 페이지를 가리키는 변수
 *  - limit 한 페이지당 표시할 개체수
 *
 * 2. pagebar
 *  - page 현재 페이지
 *  - limit 한 페이지당 표시할 개체수
 *  - totalContent 전체 개체수
 *  - totalPage 전체 페이지 수
 *  - pagebarSize 페이지바의 숫자 갯수
 *  - pageNo 페이지 증감변수
 *  - pagebarStart | pagebarEnd 페이지 증감변수의 범위
 *  - url 요청 url
 *
 */
//@WebServlet("/admin/memberList")
public class _AdminMemberListServlet extends HttpServlet {
    private MemberService memberService = new MemberService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자 입력값 가져오기
        int page = 1; // 기본값
        // 지정해 두어야 페이지 로드시 페이지 값이 없는 상태기에 생기는 오류를 무시한 상태에서 기본값으로 적용이 가능하다
        int limit = 10;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException ignore) {}
        Map<String, Object> param = Map.of("page", page, "limit", limit);
        System.out.println(param);

        // 2. 업무로직
        // a. content 영역 : 전체조회 쿼리 + RowBounds | Top-n 분석 쿼리
        List<Member> members = memberService.findAll(param);
        req.setAttribute("members", members);

        // b. pagebar 영역
        int totalCount = memberService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = HelloMvcUtils.getPagebar(page, limit, totalCount, url);
        req.setAttribute("pagebar", pagebar);

        // 3. view단 처리
        req.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(req, resp);
    }
}