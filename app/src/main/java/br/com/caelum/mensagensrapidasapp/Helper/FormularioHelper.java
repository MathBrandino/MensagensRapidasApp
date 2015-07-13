package br.com.caelum.mensagensrapidasapp.Helper;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.com.caelum.mensagensrapidasapp.Activity.FormularioActivity;
import br.com.caelum.mensagensrapidasapp.Modelo.Mensagem;
import br.com.caelum.mensagensrapidasapp.R;

/**
 * Created by matheus on 13/07/15.
 */
public class FormularioHelper {

    private EditText nome;
    private EditText corpo;
    private TextInputLayout layout;

    private Mensagem mensagem;

    public FormularioHelper(FormularioActivity activity) {
        nome = (EditText) activity.findViewById(R.id.nome_formulario);
        corpo = (EditText) activity.findViewById(R.id.corpo_formulario);

        mensagem = new Mensagem();
    }

    public void limpaFormulario(){

        nome.getText().clear();
        corpo.getText().clear();
    }

    public Mensagem pegaMensagem(){
        mensagem.setNome(nome.getText().toString());
        mensagem.setCorpo(corpo.getText().toString());

        return mensagem;
    }

    public void colocaMensagem(Mensagem mensagem){
        nome.setText(mensagem.getNome());
        corpo.setText(mensagem.getCorpo());

        this.mensagem = mensagem;

    }

    private boolean validaNome(){
        if (nome.getText().toString().trim().isEmpty()){
            mostraErroNome();
            return false;
        }else {
            return true;
        }
    }
    private void mostraErroNome(){

        layout = (TextInputLayout) nome.getParent();
        layout.setError("Nome não pode ser vazio");
    }

    private boolean validaCorpo(){
        if (corpo.getText().toString().trim().isEmpty()){
            mostraErroCorpo();
            return false;
        }else {
            return true;


        }
    }
    private void mostraErroCorpo(){
        layout = (TextInputLayout) corpo.getParent();
        layout.setError("Corpo da mensagem não pode ser vazia");
    }

    public boolean validaFormulario(){
        if (validaNome() && validaCorpo()) {
            return true;
        }else {
            return false;
        }
    }
}
