package com.entrevistador.generadorfeedback.infrastructure.adapter.dao;

import com.entrevistador.generadorfeedback.domain.model.PruebaEntrevista;
import com.entrevistador.generadorfeedback.infrastructure.adapter.entity.EntrevistaEntity;
import com.entrevistador.generadorfeedback.infrastructure.adapter.mapper.out.PruebaEntrevistaMapper;
import com.entrevistador.generadorfeedback.infrastructure.repositoy.EntrevistaRepository;
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
class PruebaEntrevistaDBDaoTest {
    @InjectMocks
    private PruebaEntrevistaDBDao pruebaEntrevistaDBDao;
    @Mock
    private EntrevistaRepository entrevistaRepository;
    @Mock
    private PruebaEntrevistaMapper pruebaEntrevistaMapper;

    @Test
    void testGetPreguntas() {
        PruebaEntrevista pruebaEntrevista = PruebaEntrevista.builder().build();

        when(this.entrevistaRepository.findAllByRol(anyString()))
                .thenReturn(Flux.just(EntrevistaEntity.builder().build()));
        when(this.pruebaEntrevistaMapper.mapOutEntrevistaEntityToPruebaEntrevista(any()))
                .thenReturn(pruebaEntrevista);

        Flux<PruebaEntrevista> publisher = this.pruebaEntrevistaDBDao.getPreguntas("any");

        StepVerifier
                .create(publisher)
                .expectNext(pruebaEntrevista)
                .verifyComplete();

        verify(this.entrevistaRepository, times(1)).findAllByRol(anyString());
        verify(this.pruebaEntrevistaMapper, times(1)).mapOutEntrevistaEntityToPruebaEntrevista(any());
    }

    @Test
    void testGuardarEntrevista() {
        PruebaEntrevista pruebaEntrevista = PruebaEntrevista.builder().build();

        when(this.pruebaEntrevistaMapper.mapInPruebaEntrevistaToEntrevistaEntity(any()))
                .thenReturn(EntrevistaEntity.builder().build());
        when(this.entrevistaRepository.save(any()))
                .thenReturn(Mono.just(EntrevistaEntity.builder().build()));
        when(this.pruebaEntrevistaMapper.mapOutEntrevistaEntityToPruebaEntrevista(any()))
                .thenReturn(pruebaEntrevista);

        StepVerifier
                .create(this.pruebaEntrevistaDBDao.guardarEntrevista(pruebaEntrevista))
                .expectNext(pruebaEntrevista)
                .verifyComplete();

        verify(this.pruebaEntrevistaMapper, times(1)).mapInPruebaEntrevistaToEntrevistaEntity(any());
        verify(this.entrevistaRepository, times(1)).save(any());
        verify(this.pruebaEntrevistaMapper, times(1)).mapOutEntrevistaEntityToPruebaEntrevista(any());
    }
}