package alyona.mikhaylova.model;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class GetPointsRequest implements Serializable {
    @NotNull
    @DecimalMax(value = "5", inclusive = false)
    @DecimalMin(value = "0", inclusive = false)
    private Double r;
}
