
/**
 * @author      Deni Dias <denidias96@gmail.com>
 * @version     1.0
 */

package deni.ufms.genius;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by deni on 19/08/16.
 */
public class Game extends Activity {

    /* Pontuacao */
    private TextView txtScore;

    /* Botoes do jogo */
    private ImageView btnGreen, btnRed, btnYellow, btnBlue;

    /* Variaveis com as sequencias da maquina e do jogador */
    private ArrayList<Integer> comSequence, playerSequence;

    /* Variavel para armazenar a pontuacao */
    private int score;

    /* Verifica se esta no seu turno */
    private boolean yourTurn;

    /* Movimento que esta sendo executado */
    private int play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        /* Inicializa as variaveis */
        txtScore = (TextView) findViewById(R.id.txtScore);
        score = 0;
        txtScore.setText(String.valueOf(score));

        btnGreen = (ImageView) findViewById(R.id.btnGreen);
        btnRed = (ImageView) findViewById(R.id.btnRed);
        btnYellow = (ImageView) findViewById(R.id.btnYellow);
        btnBlue = (ImageView) findViewById(R.id.btnBlue);

        comSequence = new ArrayList<>();
        playerSequence = new ArrayList<>();

        yourTurn = false;

        playSound(R.raw.welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                playSequence();
            }
        }, 1000);

        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!yourTurn) {
                    return;
                }
                execButton(R.id.btnGreen);
                addPlayerMove(R.id.btnGreen);
            }
        });

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!yourTurn) {
                    return;
                }
                execButton(R.id.btnRed);
                addPlayerMove(R.id.btnRed);
            }
        });

        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!yourTurn) {
                    return;
                }
                execButton(R.id.btnYellow);
                addPlayerMove(R.id.btnYellow);
            }
        });

        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!yourTurn) {
                    return;
                }
                execButton(R.id.btnBlue);
                addPlayerMove(R.id.btnBlue);
            }
        });
    }

    int i;

    /* Executa a sequencia para o jogador repetir */
    private void playSequence() {

        /* Define que a maquina esta mostrando a sequencia */
        yourTurn = false;

        /* Adiciona um movimento */
        addComputerMove();

        execButton(comSequence.get(comSequence.size()-1));

        /* Percorre a lista para mostrar o sequencia */
        /*
        for (int i=0; i < comSequence.size(); i++){
            execButton(comSequence.get(i));
        }


        for (int move : comSequence) {
            play = move;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    execButton(play);
                    System.out.println(comSequence.indexOf(play));
                }
            }, 1000);
        }*/

        /* Permite que o usuario execute sua sequencia */
        yourTurn = true;

    }

    /* Executa a musica passada por parametro */
    private void playSound(int resource) {
        MediaPlayer player = MediaPlayer.create(Game.this, resource);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    /* Executa o botao em questao */
    private void execButton(int button) {
        switch (button) {
            case R.id.btnGreen:
                playSound(R.raw.green);
                btnGreen.setBackgroundResource(R.color.selectedGreen);
                new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        btnGreen.setBackgroundResource(R.color.defaultGreen);

                    }
                }.start();

                break;
            case R.id.btnRed:
                playSound(R.raw.red);
                btnRed.setBackgroundResource(R.color.selectedRed);
                new CountDownTimer(500, 1000) {

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        btnRed.setBackgroundResource(R.color.defaultRed);
                    }
                }.start();

                break;
            case R.id.btnYellow:
                playSound(R.raw.yellow);
                btnYellow.setBackgroundResource(R.color.selectedYellow);
                new CountDownTimer(500, 1000) {

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        btnYellow.setBackgroundResource(R.color.defaultYellow);
                    }
                }.start();

                break;
            case R.id.btnBlue:
                playSound(R.raw.blue);
                btnBlue.setBackgroundResource(R.color.selectedBlue);
                new CountDownTimer(500, 1000) {

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        btnBlue.setBackgroundResource(R.color.defaultBlue);
                    }
                }.start();
                break;
        }
    }

    /* Adiciona uma jogada ao computador */
    private void addComputerMove() {

        /* Randomiza um numero entre 0 e 3 */
        Random random = new Random();
        int randomNum = random.nextInt(4);

        switch (randomNum) {
            case 0:
                comSequence.add(R.id.btnGreen);
                break;
            case 1:
                comSequence.add(R.id.btnRed);
                break;
            case 2:
                comSequence.add(R.id.btnYellow);
                break;
            case 3:
                comSequence.add(R.id.btnBlue);
                break;
        }
    }

    /* Adiciona uma jogada ao jogador */
    private void addPlayerMove(int button){
        /* Adiciona movimento a sequencia de jogadas do jogador */
        playerSequence.add(button);

        /* Captura movimento similar do computador */
        int position = playerSequence.size() - 1;
        int move = comSequence.get(position);

        /* Caso o movimento não seja o mesmo, encerrar o jogo */
        if (move != button){
            playSound(R.raw.error);
            Toast.makeText(Game.this, "ACABOU!! Score: "+score,Toast.LENGTH_SHORT).show();
            this.finish();
            return;
        }

        /* Caso a sequencia foi encerrada e executada com sucesso, computador realiza outra seq. */
        if (playerSequence.size() == comSequence.size()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    playSound(R.raw.right);
                }
            }, 1000);
            playerSequence = new ArrayList<>();
            score++;
            txtScore.setText(String.valueOf(score));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    playSequence();
                }
            }, 2000);
        }

    }
}
