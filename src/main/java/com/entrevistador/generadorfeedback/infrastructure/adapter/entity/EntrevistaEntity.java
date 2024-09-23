package com.entrevistador.generadorfeedback.infrastructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "entrevistas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntrevistaEntity {
    @Id
    private String id;
    private String nombreEmpresa;
    private String tituloVacante;
    private String descripcionEntrevista;
    private List<PreguntaEntity> preguntas;
    private String rol;
}