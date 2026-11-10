
package br.com.sentinela.model;

import java.util.Arrays;

public class CentralMonitoramento {

    private DispositivoMonitoramento[] dispositivos;

    public CentralMonitoramento(DispositivoMonitoramento[] dispositivos) {
        this.dispositivos = dispositivos;
    }

    public AlertaSeguranca[] analisarLeituras() {

        AlertaSeguranca[] alertas =
                new AlertaSeguranca[dispositivos.length * 2];

        int quantidadeAlertas = 0;

        boolean violacaoPerimetral = false;
        ZonaRestrita zonaAfetada = null;

        // Analise dos dispositivos
        for (DispositivoMonitoramento dispositivo : dispositivos) {

            if (!dispositivo.isEmOperacao()) {
                continue;
            }

            // Verifica os sensores de radiacao
            if (dispositivo instanceof SensorRadiacao) {

                SensorRadiacao sensor =
                        (SensorRadiacao) dispositivo;

                String risco =
                        sensor.getZonaMonitorada().classificarRisco();

                if (!risco.equals("NORMAL")) {

                    String mensagem = "Radiacao " + risco
                            + " na zona "
                            + sensor.getZonaMonitorada().getDescricao();

                    alertas[quantidadeAlertas] =
                            new AlertaSeguranca(
                                    sensor,
                                    sensor.getZonaMonitorada(),
                                    risco,
                                    mensagem
                            );

                    quantidadeAlertas++;
                }
            }

            // Verifica os sensores perimetrais
            if (dispositivo instanceof SensorPerimetral) {

                SensorPerimetral sensor =
                        (SensorPerimetral) dispositivo;

                if (sensor.isViolacaoDetectada()) {

                    violacaoPerimetral = true;
                    zonaAfetada = sensor.getZonaVigiada();

                    alertas[quantidadeAlertas] =
                            new AlertaSeguranca(
                                    sensor,
                                    zonaAfetada,
                                    "ATENCAO",
                                    "Violacao da cerca detectada no setor "
                                    + sensor.getSetorVigiado()
                            );

                    quantidadeAlertas++;
                }
            }
        }

        // Envia o drone quando existe uma violacao
        if (violacaoPerimetral) {

            for (DispositivoMonitoramento dispositivo : dispositivos) {

                if (dispositivo instanceof DronePatrulha) {

                    DronePatrulha drone =
                            (DronePatrulha) dispositivo;

                    System.out.println(
                            "\n[ CENTRAL ] Drone enviado para verificar a ocorrencia."
                    );

                    boolean veiculoDetectado =
                            drone.iniciarVerificacao();

                    if (drone.isEnviadoParaOcorrencia()) {

                        if (veiculoDetectado) {

                            alertas[quantidadeAlertas] =
                                    new AlertaSeguranca(
                                            drone,
                                            zonaAfetada,
                                            "CRITICO",
                                            "Camera confirmou um veiculo nao identificado."
                                    );

                            quantidadeAlertas++;
                        }

                    } else {

                        System.out.println(
                                "[ CENTRAL ] Drone indisponivel para verificacao."
                        );
                    }

                    break;
                }
            }
        }

        return Arrays.copyOf(alertas, quantidadeAlertas);
    }
}
