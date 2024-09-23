package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevistaResponse;
import com.entrevistador.generadorfeedback.infrastructure.adapter.dto.IdEntrevista;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.domain.port.client.OrquestadorClient;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PruebaEntrevistaServiceTest {
    @InjectMocks
    private PruebaEntrevistaService pruebaEntrevistaService;
    @Mock
    private PruebaEntrevistaDao pruebaEntrevistaDao;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void shouldGetPreguntasWhenValidRequest() {
        PruebaEntrevistaResponse soloPreguntaImp = new PruebaEntrevistaResponse("", "");
        when(this.pruebaEntrevistaDao.getPreguntas(anyString())).thenReturn(Flux.just(soloPreguntaImp));

        Flux<PruebaEntrevistaResponse> publisher = this.pruebaEntrevistaService.getPreguntas("perfil");

        StepVerifier
                .create(publisher)
                .expectNext(soloPreguntaImp)
                .verifyComplete();

        verify(this.pruebaEntrevistaDao, times(1)).getPreguntas(anyString());
    }

}