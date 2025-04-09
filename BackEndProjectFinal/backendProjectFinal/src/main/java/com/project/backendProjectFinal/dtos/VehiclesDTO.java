package com.project.backendProjectFinal.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class VehiclesDTO {
    @JsonProperty("vehicle_id")
    private String id;

    private String type;

    @JsonProperty("user_id")
    private Long usersId;
}
