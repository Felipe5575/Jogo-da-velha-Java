
public class JogoDaVelha {
    public static void main(String[] args){
        Jogo jogo = new Jogo();
        Jogador[] players;
        players = new Jogador[2];

        players[0] = new JogadorRobo();
        players[1] = new JogadorHumano();

        jogo.iniciar(players);        
    }
}