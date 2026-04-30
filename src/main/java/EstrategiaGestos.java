public class EstrategiaGestos implements ControleStrategy {

    public enum Sensibilidade { BAIXA, MEDIA, ALTA }

    private final Sensibilidade sensibilidade;

    public EstrategiaGestos() {
        this(Sensibilidade.MEDIA);
    }

    public EstrategiaGestos(Sensibilidade sensibilidade) {
        this.sensibilidade = sensibilidade;
    }

    private String prefixo() {
        return "🤚 [GESTO/" + sensibilidade + "]";
    }

    @Override
    public String ligar() {
        return prefixo() + " Gesto 'punho fechado' detectado → TV ligada.";
    }

    @Override
    public String desligar() {
        return prefixo() + " Gesto 'palma aberta' detectado → TV desligada.";
    }

    @Override
    public String mudarCanal(int canal) {
        return prefixo() + " Gesto 'swipe direita' x" + canal + " detectado → Canal " + canal + " selecionado.";
    }

    @Override
    public String ajustarVolume(int nivel) {
        return prefixo() + " Gesto 'polegar para cima/baixo' detectado → Volume em " + nivel + ".";
    }

    @Override
    public String nome() {
        return "Controle por Gestos (sensibilidade=" + sensibilidade + ")";
    }

    public Sensibilidade getSensibilidade() {
        return sensibilidade;
    }
}
