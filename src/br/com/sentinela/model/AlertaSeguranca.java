
package br.com.sentinela.model;

public class AlertaSeguranca {

    private DispositivoMonitoramento dispositivoOrigem;
    private ZonaRestrita zonaAfetada;
    private String severidade;
    private String mensagem;

    public AlertaSeguranca(DispositivoMonitoramento dispositivoOrigem,
                           ZonaRestrita zonaAfetada,
                           String severidade,
                           String mensagem) {

        this.dispositivoOrigem = dispositivoOrigem;
        this.zonaAfetada = zonaAfetada;
        this.severidade = severidade;
        this.mensagem = mensagem;
    }

    public DispositivoMonitoramento getDispositivoOrigem() {
        return dispositivoOrigem;
    }

    public ZonaRestrita getZonaAfetada() {
        return zonaAfetada;
    }

    public String getSeveridade() {
        return severidade;
    }

    public String getMensagem() {
        return mensagem;
    }
}
