package com.sh.mvc.photo.model.entity;

import java.time.LocalDateTime;

public class Photo {
    private Long id;
    private String memberId;
    private String content;
    private String originalFileName;
    private String renamedFileName;
    private int readCount;
    private LocalDateTime regDate;

    public Photo() {}

    public Photo(Long id, String memberId, String content, String originalFileName, String renamedFileName, int readCount, LocalDateTime regDate) {
        this.id = id;
        this.memberId = memberId;
        this.content = content;
        this.originalFileName = originalFileName;
        this.renamedFileName = renamedFileName;
        this.readCount = readCount;
        this.regDate = regDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getRenamedFileName() {
        return renamedFileName;
    }

    public void setRenamedFileName(String renamedFileName) {
        this.renamedFileName = renamedFileName;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", memberId='" + memberId + '\'' +
                ", content='" + content + '\'' +
                ", originalFileName='" + originalFileName + '\'' +
                ", renamedFileName='" + renamedFileName + '\'' +
                ", readCount=" + readCount +
                ", regDate=" + regDate +
                '}';
    }
}
