package org.writeo.dao.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NovelsDTO {
    private Long id;
    private String name;
    private String description;
    private Integer totalVolumes;
    // private String titlePic;
    //private byte[] titlePic;
    private String genre;
    private String startDate;
    private String endDate;
    private String completionStatus;
    //private Long authorId;
}
