package com.project.backendProjectFinal.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ParkingLotsDTO {
    @JsonProperty("parking_id")
    @NotNull(message = "ID cannot be blank")
    private String id;

    @JsonProperty("fullname")
    private String fullName;

    private String location;

    @NotNull(message = "Capacity is required")
    private int capacity;

    @NotBlank(message = "status is required")
    private String status;
}
