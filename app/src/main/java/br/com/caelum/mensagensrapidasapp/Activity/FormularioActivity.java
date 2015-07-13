package br.com.caelum.mensagensrapidasapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.caelum.mensagensrapidasapp.Dao.MensagemDao;
import br.com.caelum.mensagensrapidasapp.Helper.FormularioHelper;
import br.com.caelum.mensagensrapidasapp.Modelo.Mensagem;
import br.com.caelum.mensagensrapidasapp.R;

/**
 * Created by matheus on 13/07/15.
 */
public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();

        if (intent.hasExtra("mensagem")){
            Mensagem mensagem = (Mensagem) intent.getSerializableExtra("mensagem");

            helper.colocaMensagem(mensagem);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.botao_salvar_formulario :
                MensagemDao dao = new MensagemDao(this);

                Mensagem mensagem =  helper.pegaMensagem();
                if (helper.validaFormulario()) {
                    if (mensagem.getId() != null) {
                        dao.altera(mensagem);

                    }else {
                        dao.salva(mensagem);
                    }
                    finish();
                }

                return true;

            case R.id.botao_limpar_formulario :
                Toast.makeText(this,"limpando formulario", Toast.LENGTH_SHORT).show();
                helper.limpaFormulario();
                return true;



        }

        return super.onOptionsItemSelected(item);
    }
}
