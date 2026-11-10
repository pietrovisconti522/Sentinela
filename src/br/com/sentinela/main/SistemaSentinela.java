
package br.com.sentinela.main;

import br.com.sentinela.model.*;

public class SistemaSentinela {

    public static void main(String[] args) {

        // =====================================
        // CENARIO 1 - ROTINA NORMAL
        // =====================================

        System.out.println("\n==================================");
        System.out.println(" CENARIO 1 - ROTINA NORMAL");
        System.out.println("==================================");

        ZonaRestrita zonaReator1 =
                new ZonaRestrita("ZNR-01", "Casa do Reator", 5.0);

        ZonaRestrita zonaCombustivel1 =
                new ZonaRestrita("ZNR-02", "Patio de Armazenamento", 3.0);

        SensorRadiacao sensorReator1 =
                new SensorRadiacao("RAD-01", "Casa do Reator",
                        true, zonaReator1);

        SensorRadiacao sensorCombustivel1 =
                new SensorRadiacao("RAD-02", "Patio de Armazenamento",
                        true, zonaCombustivel1);

        SensorPerimetral perimetro1 =
                new SensorPerimetral("PER-01", "Setor Norte",
                        true, "Setor Norte", zonaCombustivel1);

        DronePatrulha drone1 =
                new DronePatrulha("DRN-01", "Base de Operacoes",
                        true, "Rota Norte", 95.0);

        sensorReator1.registrarLeitura(0.2);
        sensorCombustivel1.registrarLeitura(0.5);

        System.out.println("Casa do Reator: "
                + zonaReator1.getNivelRadiacaoAtual() + " uSv/h");

        System.out.println("Patio de Armazenamento: "
                + zonaCombustivel1.getNivelRadiacaoAtual() + " uSv/h");

        System.out.println("Perimetro: Integro");
        System.out.println("Drone: Em patrulha");
        System.out.println("Deteccoes: Nenhuma");

        DispositivoMonitoramento[] dispositivos1 = {
                sensorReator1,
                sensorCombustivel1,
                perimetro1,
                drone1
        };

        CentralMonitoramento central1 =
                new CentralMonitoramento(dispositivos1);

        imprimirAlertas(central1.analisarLeituras());


        // =====================================
        // CENARIO 2 - PICO DE RADIACAO
        // =====================================

        System.out.println("\n==================================");
        System.out.println(" CENARIO 2 - PICO DE RADIACAO");
        System.out.println("==================================");

        ZonaRestrita zonaReator2 =
                new ZonaRestrita("ZNR-01", "Casa do Reator", 5.0);

        ZonaRestrita zonaCombustivel2 =
                new ZonaRestrita("ZNR-02", "Patio de Armazenamento", 3.0);

        SensorRadiacao sensorReator2 =
                new SensorRadiacao("RAD-01", "Casa do Reator",
                        true, zonaReator2);

        SensorRadiacao sensorCombustivel2 =
                new SensorRadiacao("RAD-02", "Patio de Armazenamento",
                        true, zonaCombustivel2);

        SensorPerimetral perimetro2 =
                new SensorPerimetral("PER-01", "Setor Norte",
                        true, "Setor Norte", zonaCombustivel2);

        DronePatrulha drone2 =
                new DronePatrulha("DRN-01", "Base de Operacoes",
                        true, "Rota Norte", 90.0);

        sensorReator2.registrarLeitura(6.5);
        sensorCombustivel2.registrarLeitura(0.5);

        System.out.println("Casa do Reator: "
                + zonaReator2.getNivelRadiacaoAtual() + " uSv/h");

        System.out.println("Classificacao: "
                + zonaReator2.classificarRisco());

        DispositivoMonitoramento[] dispositivos2 = {
                sensorReator2,
                sensorCombustivel2,
                perimetro2,
                drone2
        };

        CentralMonitoramento central2 =
                new CentralMonitoramento(dispositivos2);

        imprimirAlertas(central2.analisarLeituras());


        // =====================================
        // CENARIO 3 - ACESSO INDEVIDO
        // =====================================

        System.out.println("\n==================================");
        System.out.println(" CENARIO 3 - ACESSO INDEVIDO");
        System.out.println("==================================");

        ZonaRestrita zonaReator3 =
                new ZonaRestrita("ZNR-01", "Casa do Reator", 5.0);

        ZonaRestrita zonaCombustivel3 =
                new ZonaRestrita("ZNR-02", "Patio de Armazenamento", 3.0);

        SensorRadiacao sensorReator3 =
                new SensorRadiacao("RAD-01", "Casa do Reator",
                        true, zonaReator3);

        SensorRadiacao sensorCombustivel3 =
                new SensorRadiacao("RAD-02", "Patio de Armazenamento",
                        true, zonaCombustivel3);

        SensorPerimetral perimetro3 =
                new SensorPerimetral("PER-01", "Setor Norte",
                        true, "Setor Norte", zonaCombustivel3);

        DronePatrulha drone3 =
                new DronePatrulha("DRN-01", "Base de Operacoes",
                        true, "Rota Norte", 85.0);

        sensorReator3.registrarLeitura(0.2);
        sensorCombustivel3.registrarLeitura(0.5);

        // Simulando uma violacao da cerca
        perimetro3.detectarViolacaoCerca();

        // Simulando a deteccao de um veiculo desconhecido
        drone3.simularDeteccaoVeiculo(true);

        System.out.println("Sensor perimetral: Violacao detectada!");
        System.out.println("Camera: Veiculo nao identificado!");

        DispositivoMonitoramento[] dispositivos3 = {
                sensorReator3,
                sensorCombustivel3,
                perimetro3,
                drone3
        };

        CentralMonitoramento central3 =
                new CentralMonitoramento(dispositivos3);

        imprimirAlertas(central3.analisarLeituras());


        // FINALIZACAO
        System.out.println("\n==================================");
        System.out.println(" FIM DA SIMULACAO SENTINELA");
        System.out.println("==================================");
    }


    // Metodo para imprimir os alertas
    public static void imprimirAlertas(AlertaSeguranca[] alertas) {

        if (alertas.length == 0) {
            System.out.println("\n[ CENTRAL ] Nenhum alerta. Sistema normal.");
            return;
        }

        System.out.println("\n===== ALERTAS DA CENTRAL =====");

        for (AlertaSeguranca alerta : alertas) {

            System.out.println("\nSeveridade: "
                    + alerta.getSeveridade());

            System.out.println("Dispositivo: "
                    + alerta.getDispositivoOrigem()
                            .getCodigoIdentificador());

            System.out.println("Zona afetada: "
                    + alerta.getZonaAfetada().getDescricao());

            System.out.println("Mensagem: "
                    + alerta.getMensagem());
        }
    }
}
