package org.writeo.dao.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolumesDTO {
    private Long volumeId;
    //private Long novelId;
    private Integer volumeNumber;
    private Integer startingChapter;
    private Integer endingChapter;
    private String volumeName;
}