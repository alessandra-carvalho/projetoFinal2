package com.mobile.projetofinal2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.projetofinal2.R;
import com.mobile.projetofinal2.model.Produto;
import com.mobile.projetofinal2.view.ActivityProduto;
import com.mobile.projetofinal2.viewholder.viewHolderProduto;

import java.util.ArrayList;

public class AdapterProdutos extends RecyclerView.Adapter<viewHolderProduto> {

    //para instanciar o adapter
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Produto> listaProdutos;

    //construtor adapter
    public AdapterProdutos(Context context,ArrayList<Produto> produtos){
        this.context=context;
        this.layoutInflater = LayoutInflater.from(this.context);
        this.listaProdutos = produtos;

    }

    //instacia a viewHolder que corresponde o item da recyclerview
    @NonNull
    @Override
    public viewHolderProduto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //infla um novo xml e o atribui p/ um objeto view
        View view = this.layoutInflater.inflate(R.layout.activity_produto_item,parent,false);
        return new viewHolderProduto(view);
    }

    //linca os dados com viewHolder que foi criada e position identifica os dados dentro da lista
    @Override
    public void onBindViewHolder(@NonNull viewHolderProduto holder, int position) {
         Produto p = this.listaProdutos.get(position);

        //linca dos dados das listas nas views/produtos e quantidades
        holder.textview_nomeProduto.setText(p.getNomeProduto());
        holder.textview_quantProduto.setText(" " + String.format("%.2f",p.getQuantProduto()));

        holder.linearLayoutProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //criação da intent
                Intent intent = new Intent(context, ActivityProduto.class);
                //inserção do objeto referente ao item selecionado na recycler view
                intent.putExtra("produto",p);
                //inicialiação da próxima activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        //retorna o tamanho da lista
        return this.listaProdutos.size();
    }

    //faz a atualização do adaptar conforme a inserção de produtos
    public void atualizaAdapter(ArrayList<Produto> produtos){
        //limpa a lista adapter
        this.listaProdutos.clear();
        //add na lista
        this.listaProdutos.addAll(produtos);
        //notifica o adapter que novos itens foram add
        notifyDataSetChanged();

    }
}
