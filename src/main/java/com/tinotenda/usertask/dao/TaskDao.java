package com.tinotenda.usertask.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDao {

    @JsonProperty(required = true)
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;
    @JsonProperty(required = true, value = "date_time")
    @NotNull(message = "DateTime is mandatory")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
}
