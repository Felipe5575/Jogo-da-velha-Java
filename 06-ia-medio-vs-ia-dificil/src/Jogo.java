public class Jogo {
    public void iniciar(Jogador player[]){
        int[] tabuleiro = new int[9];
        Sistema sistemaJogo = new Sistema();
        boolean criado = false;
        sistemaJogo.mostraTabuleiro(tabuleiro, criado);
        criado = true;
    }
}