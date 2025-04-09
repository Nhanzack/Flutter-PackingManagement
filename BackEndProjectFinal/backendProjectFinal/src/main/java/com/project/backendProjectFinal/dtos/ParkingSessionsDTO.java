package com.project.backendProjectFinal.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ParkingSessionsDTO {
    @JsonProperty("pic_entry")
    private String picEntry;

    @JsonProperty("pic_exit")
    private String picExit;

    @Min(value = 0, message = "Total money must be >= 0")
    private int fee;

    @JsonProperty("total")
    private Long total;

    @JsonProperty("parking_id")
    private String parkingId;

    @JsonProperty("vehicle_id")
    private String vehicleId;

}
