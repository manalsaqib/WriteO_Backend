package org.writeo.dao.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChaptersDTO {
    private Long chapterId;
    private Long novelId;
    private String name;
    private String content;
    private Integer wordCount;

}
