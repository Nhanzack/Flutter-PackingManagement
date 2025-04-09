package com.project.backendProjectFinal.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ParkingSessionsVisitorDTO {
    @JsonProperty("vehicle_visitorID")
    @NotBlank(message = "Bien so xe la bat buoc")
    private String vehicleVisitor;

    @JsonProperty("pic_entry")
    private String picEntry;

    @JsonProperty("pic_exit")
    private String picExit;

    @JsonProperty("total")
    private Long total;

    @Min(value = 0, message = "Total money must be >= 0")
    private int fee;

    @JsonProperty("parking_id")
    private String parkingId;
}
