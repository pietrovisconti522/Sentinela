
package br.com.sentinela.model;

public class DronePatrulha extends DispositivoMonitoramento {

    private String rotaPatrulha;
    private double bateriaPercentual;
    private boolean veiculoNaoIdentificadoDetectado;
    private boolean enviadoParaOcorrencia;

    public DronePatrulha(String codigoIdentificador,
                         String localizacao,
                         boolean emOperacao,
                         String rotaPatrulha,
                         double bateriaPercentual) {

        super(codigoIdentificador, localizacao, emOperacao);

        if (bateriaPercentual < 0 || bateriaPercentual > 100) {
            throw new IllegalArgumentException("Bateria invalida!");
        }

        this.rotaPatrulha = rotaPatrulha;
        this.bateriaPercentual = bateriaPercentual;
        this.veiculoNaoIdentificadoDetectado = false;
        this.enviadoParaOcorrencia = false;
    }

    public void simularDeteccaoVeiculo(boolean detectado) {
        this.veiculoNaoIdentificadoDetectado = detectado;
    }

    public boolean iniciarVerificacao() {

        if (!isEmOperacao() || bateriaPercentual <= 0) {
            return false;
        }

        this.enviadoParaOcorrencia = true;

        return veiculoNaoIdentificadoDetectado;
    }

    public String getRotaPatrulha() {
        return rotaPatrulha;
    }

    public double getBateriaPercentual() {
        return bateriaPercentual;
    }

    public boolean isVeiculoNaoIdentificadoDetectado() {
        return veiculoNaoIdentificadoDetectado;
    }

    public boolean isEnviadoParaOcorrencia() {
        return enviadoParaOcorrencia;
    }
}
