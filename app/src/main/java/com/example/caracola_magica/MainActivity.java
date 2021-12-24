package com.example.caracola_magica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.caracola_magica.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Chat_Bot> convertion;
    private Chat_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // se configura el RecyclerList
        binding.chatRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Se inicializa la lista
        convertion = new ArrayList<>();

        // Se inicializa el adaptador
        adapter = new Chat_Adapter();

        ini();

        // accion de la imagen/boton
        binding.sendMessageButton.setOnClickListener( v -> {
            chat();
            ini();
        });
    }

    private void chat(){
        // valida que se escriba un mensaje
        if (binding.messageEdit.getText().toString().isEmpty()){
            Toast.makeText(this, "Ingrese una pregunta", Toast.LENGTH_SHORT).show();
        }else {
            // se crea para pintar como mensaje lo introducido
            convertion.add(new Chat_Bot(true, String.valueOf(binding.messageEdit.getText())));
            // se genera la respuesta por Chat_Bot.java
            convertion.add(new Chat_Bot(false, ""));
            // Se pintan los resultados del adapatador
            binding.chatRecycler.setAdapter(adapter);
            adapter.submitList(convertion);
            // se limpia el EditText
            binding.messageEdit.setText("");
            hideKeyboard();
        }
    }

    // esconde el teclado cuando se ejecuta
    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void ini(){
        // scroll para la ultima posicion
        binding.chatRecycler.scrollToPosition(convertion.size()-1);

        // valida el contenido para mostrar el mensaje
        if (convertion.isEmpty()){
            binding.chatEmptyView.setVisibility(View.VISIBLE);
        }else {
            binding.chatEmptyView.setVisibility(View.GONE);
        }
    }
}