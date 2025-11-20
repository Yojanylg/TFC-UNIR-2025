package com.myweddingplanner.back.dto.wedding;

import com.myweddingplanner.back.model.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyWeddingEventDTO {

    private Long idEvent;
    private EventType type;
    private String description;
    private LocalDateTime time;

}
