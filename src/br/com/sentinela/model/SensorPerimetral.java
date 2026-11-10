
package br.com.sentinela.model;

public class SensorPerimetral extends DispositivoMonitoramento {

    private String setorVigiado;
    private boolean violacaoDetectada;
    private ZonaRestrita zonaVigiada;

    public SensorPerimetral(String codigoIdentificador,
                            String localizacao,
                            boolean emOperacao,
                            String setorVigiado,
                            ZonaRestrita zonaVigiada) {

        super(codigoIdentificador, localizacao, emOperacao);

        this.setorVigiado = setorVigiado;
        this.zonaVigiada = zonaVigiada;
        this.violacaoDetectada = false;
    }

    public void detectarViolacaoCerca() {

        if (isEmOperacao()) {
            this.violacaoDetectada = true;
        }
    }

    public String getSetorVigiado() {
        return setorVigiado;
    }

    public boolean isViolacaoDetectada() {
        return violacaoDetectada;
    }

    public ZonaRestrita getZonaVigiada() {
        return zonaVigiada;
    }
}
