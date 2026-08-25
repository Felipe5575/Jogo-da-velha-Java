import java.util.InputMismatchException;
import java.util.Scanner;

public class Jogo {
    public void iniciar(Jogador player[]){
        Scanner in = new Scanner(System.in);
        int[] tabuleiro = new int[9];
        Sistema sistemaJogo = new Sistema();
        boolean acabou = false;
        while(!acabou){
            jogada(player[0], sistemaJogo, tabuleiro, in);
            acabou = checkWin(tabuleiro, sistemaJogo);
            if(acabou) break;
            jogada(player[1], sistemaJogo, tabuleiro, in);
            acabou = checkWin(tabuleiro, sistemaJogo);
        }
        sistemaJogo.mostraTabuleiro(tabuleiro);
    }

    public void jogada(Jogador player, Sistema sistemaJogo, int tabuleiro[], Scanner in){
        if(!player.robo){
            sistemaJogo.mostraTabuleiro(tabuleiro);
            System.out.printf("Em qual espaço deseja jogar: \n");

            int espaçoEscolhido = -1;

            while(true){
                try{
                    espaçoEscolhido = in.nextInt();
                    in.nextLine();

                    if(espaçoEscolhido < 0 || espaçoEscolhido >= tabuleiro.length){
                        System.out.printf("Espaço fora do tabuleiro\n");
                    }

                    else{
                        if(tabuleiro[espaçoEscolhido] == 0){
                            tabuleiro[espaçoEscolhido] = -1; //fora escolhido pelo humano
                            return;
                        }
                        else{
                            System.out.printf("Espaco ja ocupado, escolha outro\n");
                        }
                    }

                }catch(InputMismatchException e){
                    System.out.printf("Entrada invalida, digite um numero\n");
                }
            }
        }
        else{
            int escolhaRobo = sistemaJogo.decisaoRobo(tabuleiro);
            tabuleiro[escolhaRobo] = 1;
        }
    }

    public boolean checkWin(int tabuleiro[], Sistema sistemaJogo){
        for(int i = 0; i<9; i+=3){
            int SomaLinha = tabuleiro[i] + tabuleiro[i+1] + tabuleiro[i+2];
            if(SomaLinha == 3){
                sistemaJogo.AnuncioGanhador(1);
                return true;
            }
            if(SomaLinha == -3){
                sistemaJogo.AnuncioGanhador(-1);
                return true;
            }
        }

        for(int i = 0; i<3; i++){
            int SomaColuna = tabuleiro[i] + tabuleiro[i+3] + tabuleiro[i+6];
            if(SomaColuna == 3){
                sistemaJogo.AnuncioGanhador(1);
                return true;
            }
            if(SomaColuna == -3){
                sistemaJogo.AnuncioGanhador(-1);
                return true;
            }
        }

        int somaDiag1 = tabuleiro[0] + tabuleiro[4] + tabuleiro[8];
        if(somaDiag1 == 3){
            sistemaJogo.AnuncioGanhador(1);
            return true;
        }
        if(somaDiag1 == -3){
            sistemaJogo.AnuncioGanhador(-1);
            return true;
        }    


        int somaDiag2 = tabuleiro[2] + tabuleiro[4] + tabuleiro[6];
        if(somaDiag2 == 3){
            sistemaJogo.AnuncioGanhador(1);
            return true;
        }

        if(somaDiag2 == -3){
            sistemaJogo.AnuncioGanhador(-1);
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
            sistemaJogo.AnuncioGanhador(0);
            return true;
        }

        return false;
    }
}