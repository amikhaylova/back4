package alyona.mikhaylova.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;


@Getter
@Setter
public class PointObject implements Serializable {

    @NotNull
    @DecimalMax(value = "8", inclusive = false)
    @DecimalMin(value = "-8", inclusive = false)
    private Double x;

    @NotNull
    @DecimalMax(value = "8", inclusive = false)
    @DecimalMin(value = "-8", inclusive = false)
    private Double y;

    @NotNull
    @DecimalMax(value = "5", inclusive = false)
    @DecimalMin(value = "-5", inclusive = false)
    private Double r;

    @Null
    private String hit;

}