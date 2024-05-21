package com.entrevistador.generadorfeedback.application.service;

import com.entrevistador.generadorfeedback.domain.model.dto.SoloPreguntaImp;
import com.entrevistador.generadorfeedback.domain.port.PruebaEntrevistaDao;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyInt;
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
        WebFluxProperties entrevistaPruebaPorperties = mock(WebFluxProperties.class);
        when(entrevistaPruebaPorperties.getLimitPreguntas()).thenReturn(10);
        ReflectionTestUtils.setField(pruebaEntrevistaService, "webFluxProperties", entrevistaPruebaPorperties);

    }

    @Test
    void shouldGetPreguntasWhenValidRequest() {
        SoloPreguntaImp soloPreguntaImp = new SoloPreguntaImp("");
        when(this.pruebaEntrevistaDao.getPreguntas(anyString(), anyInt())).thenReturn(Flux.just(soloPreguntaImp));

        Flux<SoloPreguntaImp> publisher = this.pruebaEntrevistaService.getPreguntas("perfil");

        StepVerifier
                .create(publisher)
                .expectNext(soloPreguntaImp)
                .verifyComplete();

        verify(this.pruebaEntrevistaDao, times(1)).getPreguntas(anyString(), anyInt());
    }


}