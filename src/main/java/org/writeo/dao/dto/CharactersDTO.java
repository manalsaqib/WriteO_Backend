package org.writeo.dao.dto;

import lombok.*;

import jakarta.persistence.*;
import org.writeo.dao.model.Mentions;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // kind of default constructors

public class CharactersDTO {

    private Long characterId;

    private String name;

    private String description;

    private List<Mentions> mentions;
}
