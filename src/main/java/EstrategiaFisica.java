public class EstrategiaFisica implements ControleStrategy {

    @Override
    public String ligar() {
        return "🔴 [FÍSICO] Botão POWER pressionado → TV ligada.";
    }

    @Override
    public String desligar() {
        return "🔴 [FÍSICO] Botão POWER pressionado → TV desligada.";
    }

    @Override
    public String mudarCanal(int canal) {
        return "🔴 [FÍSICO] Botão CH+" + canal + " pressionado → Canal " + canal + " selecionado.";
    }

    @Override
    public String ajustarVolume(int nivel) {
        return "🔴 [FÍSICO] Botão VOL pressionado " + nivel + "x → Volume em " + nivel + ".";
    }

    @Override
    public String nome() {
        return "Controle Físico (Botões)";
    }
}
