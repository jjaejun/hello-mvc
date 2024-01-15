package com.sh.mvc.board.model.vo;

import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.entity.BoardComment;
import com.sh.mvc.member.model.entity.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * vo클래스 value object
 * - immutable한 value객체
 * - entity클래스를 확장한 객체
 */
public class BoardVO extends Board {
    private Member member;
    private int attachCount; // 첨부파일 개수
    private List<Attachment> attachments = new ArrayList<>();
    private List<Long> delFiles = new ArrayList<>();
    private List<BoardComment> comments;
    private int commentCount;

    public BoardVO() {}

    public int getAttachCount() {
        return attachCount;
    }

    public void setAttachCount(int attachCount) {
        this.attachCount = attachCount;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Long> getDelFiles() {
        return delFiles;
    }

    public void setDelFiles(List<Long> delFiles) {
        this.delFiles = delFiles;
    }

    public List<BoardComment> getComments() {
        return comments;
    }

    public void setComments(List<BoardComment> comments) {
        this.comments = comments;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "member=" + member +
                ", attachCount=" + attachCount +
                ", attachments=" + attachments +
                ", delFiles=" + delFiles +
                ", comments=" + comments +
                ", commentCount=" + commentCount +
                "} " + super.toString();
    }

    public void setValue(String name, String value) {
        switch (name) {
            case "id" : this.setId(Long.parseLong(value)); break;
            case "title" : this.setTitle(value); break;
            case "memberId" : this.setMemberId(value); break;
            case "content" : this.setContent(value); break;
            case "delFile" : this.delFiles.add(Long.parseLong(value)); break;
            default: throw new RuntimeException("부적절한 name값 : " + name);
        }
    }
}
