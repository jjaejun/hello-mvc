package com.sh.mvc.board.model.service;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.vo.BoardVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

// 트랜잭션 관리가 주 목적이다
public class BoardServiceTest {
    static final int limit = 10;
    BoardService boardService;

    @BeforeEach
    public void beforeEach() { this.boardService = new BoardService(); }

    // 게시글 전체조회
    @DisplayName("게시글 전체조회")
    @Test
    public void test1() {
        // given
        // when
        List<BoardVO> boards = boardService.findAll();

        // then
        // allsatisfies : 요소가 모든 단정문을 충족하는지 확인
        assertThat(boards)
                .isNotNull()
                .allSatisfy((board -> {
                    // pk, 필수값 확인
                    assertThat(board.getId()).isNotZero();
                    assertThat(board.getTitle()).isNotNull();
                    assertThat(board.getContent()).isNotNull();
                    assertThat(board.getRegDate()).isNotNull();
                }));
    }

    // 게시글 한 건 조회
    @DisplayName("게시글 한건 조회")
    @Test
    public void test2() {
        // given
        int n = (int) (Math.random() * 60) + 1;
        // when
        BoardVO board = boardService.findById(n);

        // then
        // satisfies : 요소가 모든 단정문을 충족하는지 확인
        assertThat(board)
                .isNotNull()
                .satisfies((_board) -> {
                    // pk, 필수값 확인
                    assertThat(_board.getId()).isNotZero();
                    assertThat(_board.getTitle()).isNotNull();
                    assertThat(_board.getContent()).isNotNull();
                    assertThat(_board.getRegDate()).isNotNull();
                });
    }
    @DisplayName("존재하지 않는 게시글 한건 조회")
    @ParameterizedTest
    @ValueSource(longs = {100000000L, 9999999L})
    void test2_2(long id) {
        // given
        // when
        BoardVO board = boardService.findById(id);
        // then
        assertThat(board).isNull();
    }

    // 게시글 등록
    @Disabled
    @DisplayName("게시글 등록")
    @Test
    public void test3() {
        // given
        // when
        // pk : seq_board_id를 통해 채번
        // read_count : 기본값 처리
        // reg_date :  기본값 처리
        String title = "안녕하세요, 이재준입니다.";
        String memberId = "admin";
        String content = "반갑습니다";
        BoardVO board = new BoardVO();
        board.setTitle(title);
        board.setMemberId(memberId);
        board.setContent(content);
        int result = boardService.insertBoard(board);
        // then
        assertThat(result).isGreaterThan(0);
    }

    // 게시글 수정
    @Disabled
    @DisplayName("게시글 수정")
    @ParameterizedTest
    @MethodSource("boardIdProvider") // boardIdProvider메소드가 반환하는 stream객체의 요소별로 테스트실행
    public void test4(long id) {
        // given
        BoardVO board = boardService.findById(id);
        assertThat(board).isNotNull();
        // when
        String newTitle = "새 제목";
        String newContent = "새 내용";
        board.setTitle(newTitle);
        board.setContent(newContent);
        int result = boardService.updateBoard(board);
        // then
        assertThat(result).isGreaterThan(0);
        Board boardUpdated = boardService.findById(id);
        assertThat(boardUpdated).satisfies((b) -> {
            assertThat(b.getTitle()).isEqualTo(newTitle);
            assertThat(b.getContent()).isEqualTo(newContent);
        });
    }
    // 게시글 삭제
    @Disabled
    @DisplayName("게시글 삭제")
    @ParameterizedTest
    @MethodSource("boardIdProvider")
    public void test5(long id) {
        // given
        BoardVO board = boardService.findById(id);
        assertThat(board).isNotNull();
        // when
        int result = boardService.deleteBoard(id);
        // then
        assertThat(result).isGreaterThan(0);
        Board boardDeleted = boardService.findById(id);
        assertThat(boardDeleted).isNull();
    }

    // 전체게시글 수 조회
    @DisplayName("전체게시글 수 조회")
    @Test
    public void test6() {
        // given
        // when
        int result = boardService.getTotalCount();
        // then
        assertThat(result).isNotNegative(); // 음수가 아니어야 한다. (0 이상)
    }

    // 게시글 페이징 조회
    @DisplayName("게시글 페이징 조회")
    @ParameterizedTest
    @MethodSource("pageNoProvider")
    public void test7(int page) {
        Map<String, Object> param = Map.of("page", page, "limit", limit);
        List<BoardVO> boards = boardService.findAll(param);
        assertThat(boards)
                .isNotNull()
                .isNotEmpty()
                .size().isLessThanOrEqualTo(limit);
    }

    /**
     * test(int, String, boolean) 같은 매개변수를 포함한 함수를 테스트 할 시 사용
     * Stream<Arguments>
     *  - Arguments.arguments(1, "hello", true)
     *  - Arguments.arguments(2, "bye", false)
     *  - Arguments.arguments(3, "ㅋㅋㅋ", true)
     */
    public static Stream<Integer> pageNoProvider() {
        BoardService boardService = new BoardService();
//        SqlSession session =
        int totalCount = boardService.getTotalCount();
        int totalPage = (int) Math.ceil((double) totalCount / limit);
        return IntStream.range(1, totalPage).boxed(); // 1 부터 total페이지까지를 요소로 하는 Stream생성
    }
    public static Stream<Arguments> boardIdProvider() {
        BoardService boardService = new BoardService(); // non-static fixture를 사용할 수 없다.
        List<BoardVO> boards = boardService.findAll();
        return Stream.of(Arguments.arguments(boards.get(0).getId()));
    }
}