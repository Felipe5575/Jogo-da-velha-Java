# Jogo da Velha — Java

Implementação do clássico Jogo da Velha desenvolvida 100% em Java, sem bibliotecas ou frameworks externos.

O projeto possui diferentes versões do jogo, com níveis variados de dificuldade para o oponente automático e uma versão executada diretamente pelo console.

## Tecnologias

* Java
* Java Swing
* Java AWT
* Java Standard Library
* Programação Orientada a Objetos

## Modos de Jogo

### 01 — Humano vs IA Difícil

O oponente analisa o estado do tabuleiro e busca sempre o melhor caminho possível para vencer ou garantir o empate.

### 02 — Humano vs IA Fácil

O oponente escolhe suas jogadas aleatoriamente.

### 03 — Humano vs IA Fácil — Console

Versão da dificuldade fácil executada diretamente pelo terminal, sem interface gráfica.

### 04 — Humano vs IA Média

O oponente considera o estado do tabuleiro, mas possui uma estratégia mais relaxada e pode deixar algumas oportunidades passarem.

### 05 — IA Difícil vs IA Difícil

Duas IAs de dificuldade difícil jogam automaticamente entre si.

### 06 — IA Média vs IA Difícil

Uma IA de dificuldade média enfrenta uma IA de dificuldade difícil.

## Lógica do Jogo

O jogo utiliza o estado do tabuleiro e a pontuação das posições para identificar as possíveis condições da partida:

* Vitória
* Derrota
* Empate
* Continuação

Cada nível de dificuldade utiliza uma estratégia diferente para selecionar suas jogadas.

## Interface

As versões gráficas utilizam Java Swing e Java AWT para criação da interface e gerenciamento dos eventos de interação.

A versão de console utiliza apenas entrada e saída pelo terminal.

## Estrutura

```text
jogo-da-velha-java/
│
├── 01-humano-vs-ia-dificil/
├── 02-humano-vs-ia-facil/
├── 03-humano-vs-ia-facil-console/
├── 04-humano-vs-ia-medio/
├── 05-ia-dificil-vs-ia-dificil/
├── 06-ia-medio-vs-ia-dificil/
│
├── README.md
├── LICENSE
└── .gitignore
```

Cada diretório contém uma implementação independente do respectivo modo de jogo.

## Dependências

Nenhuma dependência externa.

O projeto utiliza apenas recursos disponíveis na plataforma Java, incluindo Swing e AWT.

