package com.entrevistador.generadorfeedback.infrastructure.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Cambiar esta Clase a la Clase modelo que maneja el Orquestador y mover al paquete domain.model
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdEntrevista {
    private String id;

}
