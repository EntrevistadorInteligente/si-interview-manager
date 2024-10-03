package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PruebaEntrevistaServiceTest {
    @InjectMocks
    private PruebaEntrevistaService pruebaEntrevistaService;
    @Mock
    private PruebaEntrevistaDao pruebaEntrevistaDao;

    @Test
    void shouldGetPreguntasWhenValidRequest() {
        PruebaEntrevista soloPreguntaImp = PruebaEntrevista.builder().build();
        when(this.pruebaEntrevistaDao.getPreguntas(anyString())).thenReturn(Flux.just(soloPreguntaImp));

        Flux<PruebaEntrevista> publisher = this.pruebaEntrevistaService.getPreguntas("perfil");

        StepVerifier
                .create(publisher)
                .expectNext(soloPreguntaImp)
                .verifyComplete();

        verify(this.pruebaEntrevistaDao, times(1)).getPreguntas(anyString());
    }

    @Test
    void testGuardarEntrevista() {
        PruebaEntrevista pruebaEntrevista = PruebaEntrevista.builder().build();
        when(this.pruebaEntrevistaDao.guardarEntrevista(any())).thenReturn(Mono.just(pruebaEntrevista));

        Mono<PruebaEntrevista> publisher = this.pruebaEntrevistaService.guardarEntrevista(pruebaEntrevista);

        StepVerifier
                .create(publisher)
                .expectNext(pruebaEntrevista)
                .verifyComplete();

        verify(this.pruebaEntrevistaDao, times(1)).guardarEntrevista(any());
    }

}