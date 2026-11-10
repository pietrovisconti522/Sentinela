
package br.com.sentinela.model;

public class SensorRadiacao extends DispositivoMonitoramento {

    private double leituraAtual;
    private double limiteSeguranca;
    private ZonaRestrita zonaMonitorada;

    public SensorRadiacao(String codigoIdentificador,
                          String localizacao,
                          boolean emOperacao,
                          ZonaRestrita zonaMonitorada) {

        super(codigoIdentificador, localizacao, emOperacao);

        this.zonaMonitorada = zonaMonitorada;
        this.limiteSeguranca = zonaMonitorada.getLimiteRadiacao();
        this.leituraAtual = 0.0;
    }

    public void registrarLeitura(double leitura) {

        if (!isEmOperacao()) {
            throw new IllegalStateException("Sensor fora de operacao!");
        }

        zonaMonitorada.registrarLeituraRadiacao(leitura);
        this.leituraAtual = leitura;
    }

    public double getLeituraAtual() {
        return leituraAtual;
    }

    public double getLimiteSeguranca() {
        return limiteSeguranca;
    }

    public ZonaRestrita getZonaMonitorada() {
        return zonaMonitorada;
    }
}
