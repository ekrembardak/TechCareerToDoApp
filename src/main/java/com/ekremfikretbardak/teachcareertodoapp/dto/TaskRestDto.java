package com.ekremfikretbardak.teachcareertodoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TaskRestDto {
    private Long taskID;
    private String definition;
    private String status;

}
