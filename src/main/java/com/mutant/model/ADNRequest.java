package com.mutant.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@Schema(description = "Modelo para la solicitud de ADN")
public class ADNRequest {

    @Schema(description = "Lista de secuencias de ADN", required = true, example = "[\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]")
    private String[] adn;

}
