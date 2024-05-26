package com.entrevistador.generadorfeedback.infrastructure.adapter.client;

import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaPrevioFeedbackDto;
import com.entrevistador.generadorfeedback.domain.model.dto.EntrevistaPruebaDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackComentarioDto;
import com.entrevistador.generadorfeedback.domain.model.dto.FeedbackResponseDto;
import com.entrevistador.generadorfeedback.domain.model.dto.RespuestasPruebaDto;
import com.entrevistador.generadorfeedback.domain.model.enums.EndpointPrepEntrevistaEnum;
import com.entrevistador.generadorfeedback.domain.port.client.PreparadorEntrevistaClient;
import com.entrevistador.generadorfeedback.infrastructure.properties.WebFluxProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PreparadorEntrevistaRestClient implements PreparadorEntrevistaClient {

    private final WebClient webClient;
    private final WebFluxProperties webFluxProperties;

    public PreparadorEntrevistaRestClient(WebClient.Builder webClientBuilder, WebFluxProperties webFluxProperties) {
        this.webClient = webClientBuilder.baseUrl(webFluxProperties.getWebFluxFeedbackPregunta().getUrlBase()).build();
        this.webFluxProperties = webFluxProperties;
    }

    @Override
    public Flux<FeedbackComentarioDto> obtenerFeedbackMuestra(EntrevistaPruebaDto entrevista) {
        return this.webClient.post()
                .uri(webFluxProperties.getWebFluxFeedbackPregunta()
                        .getEndpoints().get(EndpointPrepEntrevistaEnum.FEEDBACK_PREGUNTA.getDescription()))
                .bodyValue(convertToEntrevistaPrevioFeedbackDto(entrevista))
                .retrieve()
                .bodyToFlux(FeedbackComentarioDto.class);
    }

    private static EntrevistaPrevioFeedbackDto convertToEntrevistaPrevioFeedbackDto(EntrevistaPruebaDto entrevistaPruebaDto) {
        List<RespuestasPruebaDto> respuestasPruebaDtos = entrevistaPruebaDto.getProcesoEntrevista().stream()
                .map(PreparadorEntrevistaRestClient::convertToRespuestasPruebaDto)
                .collect(Collectors.toList());

        return EntrevistaPrevioFeedbackDto.builder()
                .perfil(entrevistaPruebaDto.getPerfil())
                .procesoEntrevista(respuestasPruebaDtos)
                .build();
    }

    private static RespuestasPruebaDto convertToRespuestasPruebaDto(FeedbackResponseDto feedbackResponseDto) {
        return new RespuestasPruebaDto(feedbackResponseDto.getIdPregunta(), feedbackResponseDto.getPregunta(), feedbackResponseDto.getRespuesta());
    }
}
