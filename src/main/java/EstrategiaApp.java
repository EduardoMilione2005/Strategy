public class EstrategiaApp implements ControleStrategy {

    private final String dispositivo;

    public EstrategiaApp() {
        this("Smartphone");
    }

    public EstrategiaApp(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    @Override
    public String ligar() {
        return "📱 [APP/" + dispositivo + "] Toque em 'Power' → TV ligada.";
    }

    @Override
    public String desligar() {
        return "📱 [APP/" + dispositivo + "] Toque em 'Power' → TV desligada.";
    }

    @Override
    public String mudarCanal(int canal) {
        return "📱 [APP/" + dispositivo + "] Swipe para canal " + canal + " → Canal " + canal + " selecionado.";
    }

    @Override
    public String ajustarVolume(int nivel) {
        return "📱 [APP/" + dispositivo + "] Slider ajustado para " + nivel + " → Volume em " + nivel + ".";
    }

    @Override
    public String nome() {
        return "Controle via App (" + dispositivo + ")";
    }

    public String getDispositivo() {
        return dispositivo;
    }
}
j