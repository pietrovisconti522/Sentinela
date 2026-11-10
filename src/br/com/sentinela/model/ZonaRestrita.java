
package br.com.sentinela.model;

public class ZonaRestrita {

    private String identificador;
    private String descricao;
    private double nivelRadiacaoAtual;
    private double limiteRadiacao;

    public ZonaRestrita(String identificador, String descricao, double limiteRadiacao) {

        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("Identificador invalido!");
        }

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descricao invalida!");
        }

        if (limiteRadiacao <= 0 || limiteRadiacao > 1000000) {
            throw new IllegalArgumentException("Limite invalido!");
        }

        this.identificador = identificador;
        this.descricao = descricao;
        this.limiteRadiacao = limiteRadiacao;
        this.nivelRadiacaoAtual = 0.0;
    }

    public void registrarLeituraRadiacao(double leitura) {

        if (leitura < 0 || leitura > 1000000 ||
            Double.isNaN(leitura) || Double.isInfinite(leitura)) {

            throw new IllegalArgumentException("Leitura invalida!");
        }

        this.nivelRadiacaoAtual = leitura;
    }

    public String classificarRisco() {

        if (nivelRadiacaoAtual >= limiteRadiacao) {
            return "CRITICO";
        } else if (nivelRadiacaoAtual >= limiteRadiacao * 0.8) {
            return "ATENCAO";
        } else {
            return "NORMAL";
        }
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getNivelRadiacaoAtual() {
        return nivelRadiacaoAtual;
    }

    public double getLimiteRadiacao() {
        return limiteRadiacao;
    }
}
