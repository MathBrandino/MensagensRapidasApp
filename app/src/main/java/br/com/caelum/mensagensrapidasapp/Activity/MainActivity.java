package br.com.caelum.mensagensrapidasapp.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.caelum.mensagensrapidasapp.Adapter.ListaAdapter;
import br.com.caelum.mensagensrapidasapp.Dao.MensagemDao;
import br.com.caelum.mensagensrapidasapp.Modelo.Mensagem;
import br.com.caelum.mensagensrapidasapp.R;


public class MainActivity extends AppCompatActivity {

    private ListView listaMensagens;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainActivity that = this;

        listaMensagens = (ListView) findViewById(R.id.lista_main);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(that, FormularioActivity.class);
                startActivity(intent);

            }
        });


        listaMensagens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Mensagem mensagem = (Mensagem) parent.getItemAtPosition(position);

                new AlertDialog.Builder(that).setMessage("O que deseja fazer com a mensagem : " + mensagem.getNome() + " ? ")
                        .setTitle(" Atencao ")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                MensagemDao dao = new MensagemDao(that);
                                dao.deleta(mensagem);
                                onResume();
                            }
                        })
                        .setNegativeButton("Utilizar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent compartilhar = new Intent(Intent.ACTION_SEND);
                                compartilhar.setType("text/*");
                                compartilhar.putExtra(Intent.EXTRA_TEXT, mensagem.getCorpo());

                                startActivity(Intent.createChooser(compartilhar, "Escolha aonde mandar sua mensagem"));
                            }
                        })
                        .setNeutralButton("Cancelar", null)
                        .show();


                return true;
            }
        });


        listaMensagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mensagem mensagem = (Mensagem) parent.getItemAtPosition(position);

                Intent editar = new Intent(that, FormularioActivity.class);
                editar.putExtra("mensagem", mensagem);
                startActivity(editar);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MensagemDao dao = new MensagemDao(this);


        List<Mensagem> mensagens = dao.buscaLista();


        ListaAdapter adapter = new ListaAdapter(mensagens, this);

        listaMensagens.setAdapter(adapter);


    }
}
