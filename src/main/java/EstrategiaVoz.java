public class EstrategiaVoz implements ControleStrategy {

    private final double confianca;

    public EstrategiaVoz() {
        this(0.95);
    }

    public EstrategiaVoz(double confianca) {
        if (confianca < 0.0 || confianca > 1.0)
            throw new IllegalArgumentException("Confiança deve ser entre 0.0 e 1.0");
        this.confianca = confianca;
    }

    private String prefixo() {
        return String.format("🎙️ [VOZ %.0f%%]", confianca * 100);
    }

    @Override
    public String ligar() {
        return prefixo() + " Comando reconhecido: 'ligar televisão' → TV ligada.";
    }

    @Override
    public String desligar() {
        return prefixo() + " Comando reconhecido: 'desligar televisão' → TV desligada.";
    }

    @Override
    public String mudarCanal(int canal) {
        return prefixo() + " Comando reconhecido: 'canal " + canal + "' → Canal " + canal + " selecionado.";
    }

    @Override
    public String ajustarVolume(int nivel) {
        return prefixo() + " Comando reconhecido: 'volume " + nivel + "' → Volume em " + nivel + ".";
    }

    @Override
    public String nome() {
        return String.format("Controle por Voz (confiança=%.0f%%)", confianca * 100);
    }

    public double getConfianca() {
        return confianca;
    }
}
