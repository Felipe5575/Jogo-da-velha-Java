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
                botao[i].addActionListener(e -> {
                    if (this.jogoFinalizado || tabuleiro[posicao] != 0) 
                        return;
                
                    botao[posicao].setText("x");
                    botao[posicao].setEnabled(false);
                    tabuleiro[posicao] = -1;
                
                    if (checkWin(tabuleiro, painelControle))
                        return;
                
                    decisaoRobo(tabuleiro, botao);
                    if (checkWin(tabuleiro, painelControle)) 
                        return;
                        
                });
                painelJogo.add(botao[i]);
            }

            painelPrincipal.add(painelJogo, BorderLayout.CENTER);
            painelPrincipal.add(painelControle, BorderLayout.SOUTH);
            
            janela.add(painelPrincipal);
            janela.setVisible(true);
        }
    }

    public void decisaoRobo(int tabuleiro[], JButton botao[]){
        Random random = new Random();
        List<Integer> livres = new ArrayList<>();
        int tamList = 0;

        for(int i = 0; i<tabuleiro.length; i++){
            if(tabuleiro[i] == 0){
                livres.add(i);
            }
        }

        int escolhaRobo = livres.get(random.nextInt(livres.size()));
        tabuleiro[escolhaRobo] = 1; 
        botao[escolhaRobo].setText("o");
        botao[escolhaRobo].setEnabled(false);    
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
            JLabel LabelGanhador = new JLabel("O robo ganhou");
            painelControle.add(LabelGanhador);
    
            painelControle.revalidate();
            painelControle.repaint();
        }
        else if(ganhador == -1){
            JLabel LabelGanhador = new JLabel("O player humano ganhou");
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
