package org.example.miniprojpetadoptionshelter.entity.file;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "file_infos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Comment("원본 파일명")
    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Comment("UUID가 적용된 저장 파일명")
    @Column(name = "stored_name", nullable = false)
    private String storedName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_size")
    private Long fileSize;

    @Comment("서버 내 실제 경로")
    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Builder
    public FileInfo(String originalName, String storedName, String contentType, Long fileSize, String filePath) {
        this.originalName = originalName;
        this.storedName = storedName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

}
