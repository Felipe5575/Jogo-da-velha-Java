import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Sistema extends JFrame{
    private boolean jogoFinalizado = false;
    public void mostraTabuleiro(int tabuleiro[], boolean criado){
        if(!criado){
            JFrame janela = new JFrame("JogoDaVelha");
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setSize(400, 450);

            JPanel painelPrincipal = new JPanel(new BorderLayout()); //ele vai organizar os dois abaixo
            JPanel painelJogo = new JPanel(new GridLayout(3,3)); //grade do jogo
            JPanel painelControle = new JPanel(); //separada para reiniciar o jogo
            
            JButton[] botao = new JButton[9];
            for (int i = 0; i < 9; i++) {
                final int posicao = i;

                botao[i] = new JButton("");
                painelJogo.add(botao[i]);
            }

            painelPrincipal.add(painelJogo, BorderLayout.CENTER);
            painelPrincipal.add(painelControle, BorderLayout.SOUTH);
            
            janela.add(painelPrincipal);
            janela.setVisible(true);
            
            while(true){
                decisaoRoboV1(tabuleiro, botao);
                if (checkWin(tabuleiro, painelControle))
                    return;
            
                decisaoRoboV2(tabuleiro, botao);
                if (checkWin(tabuleiro, painelControle)) 
                    return;
            }
        }
    }

    public void decisaoRoboV1(int tabuleiro[], JButton botao[]){
        int escolhaRobo = -1;
        Random random = new Random();
        int[] tabuleiroPossivel = Arrays.copyOf(tabuleiro, tabuleiro.length);

        //verificar vitoria possivel
        for(int i = 0; i<9; i++){
            if(tabuleiroPossivel[i] == 0){
                tabuleiroPossivel[i] = -1;
                if(vitoriaHipotetica(tabuleiroPossivel)){
                    escolhaRobo = i;
                    System.out.printf("%d\n", i);
                    tabuleiroPossivel[i] = 0;
                    break;
                }
                tabuleiroPossivel[i] = 0;
            }

            if(tabuleiroPossivel[i] == 0){
                tabuleiroPossivel[i] = 1; 
                if(vitoriaHipotetica(tabuleiroPossivel)){
                    escolhaRobo = i;
                    System.out.printf("%d\n", i);
                    tabuleiroPossivel[i] = 0;
                    break;
                }
                tabuleiroPossivel[i] = 0;
            }
        }
        
        if (escolhaRobo == -1 && tabuleiroPossivel[4] == 0) { //caso se o centro estiver disponivel
            escolhaRobo = 4;
        }

        if (escolhaRobo == -1) { //caso se as diagonais estiverem ocupadas
            if ((tabuleiroPossivel[0] == -1 && tabuleiroPossivel[8] == -1) || (tabuleiroPossivel[2] == -1 && tabuleiroPossivel[6] == -1)) {
                // Tente pegar uma lateral para evitar o fork (duas condicoes de vitoria ao mesmo tempo)
                if (tabuleiroPossivel[1] == 0) escolhaRobo = 1;
                else if (tabuleiroPossivel[3] == 0) escolhaRobo = 3;
                else if (tabuleiroPossivel[5] == 0) escolhaRobo = 5;
                else if (tabuleiroPossivel[7] == 0) escolhaRobo = 7;
            }
        }

        if (escolhaRobo == -1) { //caso todas as coisas anteriores nao estiverem disponiveis, va atras de uma lateral
            int[] cantos = {0, 2, 6, 8};
            for (int c : cantos) {
                if (tabuleiroPossivel[c] == 0) {
                    escolhaRobo = c;
                    break;
                }
            }
        }

        if (escolhaRobo == -1) {
            int[] laterais = {1, 3, 5, 7};
            for (int l : laterais) {
                if (tabuleiroPossivel[l] == 0) {
                    escolhaRobo = 1;
                    break;
                }
            }
        }

        tabuleiro[escolhaRobo] = -1; 
        botao[escolhaRobo].setText("x");
        botao[escolhaRobo].setEnabled(false);  
    }

    public void decisaoRoboV2(int tabuleiro[], JButton botao[]){
        int escolhaRobo = -1;
        Random random = new Random();
        int[] tabuleiroPossivel = Arrays.copyOf(tabuleiro, tabuleiro.length);

        //verificar vitoria possivel
        for(int i = 0; i<9; i++){
            if(tabuleiroPossivel[i] == 0){
                tabuleiroPossivel[i] = 1; //simula jogada robo que ganhe
                if(vitoriaHipotetica(tabuleiroPossivel)){//se retornar true, a jogada que foi encontrada é considerada vencedora
                    escolhaRobo = i;
                    System.out.printf("%d\n", i);
                    tabuleiroPossivel[i] = 0;
                    break;
                }
                tabuleiroPossivel[i] = 0;//nao deu em nada
            }

            if(tabuleiroPossivel[i] == 0){
                tabuleiroPossivel[i] = -1; //simula uma jogada do robo que impessa vitoria
                if(vitoriaHipotetica(tabuleiroPossivel)){//se retornar true, a jogada que foi é considerada uma que não perca
                    escolhaRobo = i;
                    System.out.printf("%d\n", i);
                    tabuleiroPossivel[i] = 0;
                    break;
                }
                tabuleiroPossivel[i] = 0;//nao deu em nada
            }
        }

        if (escolhaRobo == -1 && tabuleiroPossivel[4] == 0) { //caso se o centro estiver disponivel
            escolhaRobo = 4;
        }

        if (escolhaRobo == -1) { //caso se as diagonais estiverem ocupadas
            if ((tabuleiroPossivel[0] == -1 && tabuleiroPossivel[8] == -1) || (tabuleiroPossivel[2] == -1 && tabuleiroPossivel[6] == -1)) {
                // Tente pegar uma lateral para evitar o fork (duas condicoes de vitoria ao mesmo tempo)
                if (tabuleiroPossivel[1] == 0) escolhaRobo = 1;
                else if (tabuleiroPossivel[3] == 0) escolhaRobo = 3;
                else if (tabuleiroPossivel[5] == 0) escolhaRobo = 5;
                else if (tabuleiroPossivel[7] == 0) escolhaRobo = 7;
            }
        }

        if (escolhaRobo == -1) { //caso todas as coisas anteriores nao estiverem disponiveis, va atras de uma lateral
            int[] cantos = {0, 2, 6, 8};
            for (int c : cantos) {
                if (tabuleiroPossivel[c] == 0) {
                    escolhaRobo = c;
                    break;
                }
            }
        }

        if (escolhaRobo == -1) {
            int[] laterais = {1, 3, 5, 7};
            for (int l : laterais) {
                if (tabuleiroPossivel[l] == 0) {
                    escolhaRobo = l;
                    break;
                }
            }
        }

        tabuleiro[escolhaRobo] = 1; 
        botao[escolhaRobo].setText("o");
        botao[escolhaRobo].setEnabled(false);    
    }

    public boolean vitoriaHipotetica(int tabuleiro[]){
        int[][] linhas = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Linhas
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Colunas
            {0, 4, 8}, {2, 4, 6}             // Diagonais
        };
    
        for (int[] linha : linhas) {
            int soma = tabuleiro[linha[0]] + tabuleiro[linha[1]] + tabuleiro[linha[2]];
            if (soma == 3 || soma == -3) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWin(int tabuleiro[], JPanel painel){
        for(int i = 0; i<9; i+=3){
            int SomaLinha = tabuleiro[i] + tabuleiro[i+1] + tabuleiro[i+2];
            if(SomaLinha == 3){
                AnuncioGanhador(1, painel);
                this.jogoFinalizado = true;
                return true;
            }
            if(SomaLinha == -3){
                AnuncioGanhador(-1, painel);
                this.jogoFinalizado = true;
                return true;
            }
        }

        for(int i = 0; i<3; i++){
            int SomaColuna = tabuleiro[i] + tabuleiro[i+3] + tabuleiro[i+6];
            if(SomaColuna == 3){
                AnuncioGanhador(1, painel);
                this.jogoFinalizado = true;
                return true;
            }
            if(SomaColuna == -3){
                AnuncioGanhador(-1, painel);
                this.jogoFinalizado = true;
                return true;
            }
        }

        int somaDiag1 = tabuleiro[0] + tabuleiro[4] + tabuleiro[8];
        if(somaDiag1 == 3){
            AnuncioGanhador(1, painel);
            this.jogoFinalizado = true;
            return true;
        }
        if(somaDiag1 == -3){
            AnuncioGanhador(-1, painel);
            this.jogoFinalizado = true;
            return true;
        }    


        int somaDiag2 = tabuleiro[2] + tabuleiro[4] + tabuleiro[6];
        if(somaDiag2 == 3){
            AnuncioGanhador(1, painel);
            this.jogoFinalizado = true;
            return true;
        }

        if(somaDiag2 == -3){
            AnuncioGanhador(-1, painel);
            this.jogoFinalizado = true;
            return true;
        }

        boolean empate = true;
        for(int i = 0; i<tabuleiro.length; i++){
            if(tabuleiro[i] == 0){
                empate = false;
                break;
            }
        }
        if(empate){
            AnuncioGanhador(0, painel);
            this.jogoFinalizado = true;
            return true;
        }

        return false;
    }

    public void AnuncioGanhador(int ganhador, JPanel painelControle){
        if(ganhador == 1){
            JLabel LabelGanhador = new JLabel("O roboV2 ganhou");
            painelControle.add(LabelGanhador);
    
            painelControle.revalidate();
            painelControle.repaint();
        }
        else if(ganhador == -1){
            JLabel LabelGanhador = new JLabel("O roboV1 ganhou");
            painelControle.add(LabelGanhador);
    
            painelControle.revalidate();
            painelControle.repaint();
        }
        else{
            JLabel LabelGanhador = new JLabel("O jogo terminou em empate");
            painelControle.add(LabelGanhador);
    
            painelControle.revalidate();
            painelControle.repaint();
        }
        JButton botaoReiniciar = new JButton("Reiniciar");
        painelControle.add(botaoReiniciar);

        botaoReiniciar.addActionListener(e -> {
            Window janelaAtual = SwingUtilities.getWindowAncestor(botaoReiniciar);
            if (janelaAtual != null) {
                janelaAtual.dispose(); // Fecha a janela atual
            }             
            int[] novoTabuleiro = new int[9];
            this.jogoFinalizado = false; 
            mostraTabuleiro(novoTabuleiro, jogoFinalizado);
        });

        painelControle.revalidate();
        painelControle.repaint();
    }
}
