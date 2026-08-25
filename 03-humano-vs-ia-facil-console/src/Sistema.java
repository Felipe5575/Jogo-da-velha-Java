import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    public void mostraTabuleiro(int tabuleiro[]){
        for(int i = 0; i < tabuleiro.length; i++){
            System.out.printf(" %d ", tabuleiro[i]);
            if ((i + 1) % 3 != 0){
                System.out.printf("|");
            }
            if ((i + 1) % 3 == 0) 
                System.out.printf("\n-----------\n");
        }
        System.out.printf("\n");
    }

    public int decisaoRobo(int tabuleiro[]){
        Random random = new Random();
        List<Integer> livres = new ArrayList<>();
        int tamList = 0;

        for(int i = 0; i<tabuleiro.length; i++){
            if(tabuleiro[i] == 0){
                livres.add(i);
            }
        }

        return livres.get(random.nextInt(livres.size()));
    }
    public void AnuncioGanhador(int ganhador){
        if(ganhador == 1){
            System.out.printf("O robo ganhou\n");
        }
        else if(ganhador == -1){
            System.out.printf("O player humano ganhou\n");
        }
        else{
            System.out.printf("O jogo terminou em empate\n");
        }
    }
}
