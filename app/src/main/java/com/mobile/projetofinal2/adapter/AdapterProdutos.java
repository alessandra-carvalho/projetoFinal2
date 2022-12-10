package com.mobile.projetofinal2.adapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.projetofinal2.R;
import com.mobile.projetofinal2.model.Produto;
import com.mobile.projetofinal2.repositorio.ProdutoDAO;
import com.mobile.projetofinal2.view.ActivityProduto;
import com.mobile.projetofinal2.view.ActivityProdutoMain;
import com.mobile.projetofinal2.viewholder.viewHolderProduto;

import java.util.ArrayList;

public class AdapterProdutos extends RecyclerView.Adapter<viewHolderProduto> {

    //para instanciar o adapter
    //private Context context;
    private Activity activity;
    private LayoutInflater layoutInflater;
    private ArrayList<Produto> listaProdutos;

    //construtor adapter
    //public AdapterProdutos(Context context,ArrayList<Produto> produtos){
    public AdapterProdutos(Activity activity,ArrayList<Produto> produtos){
        //this.context=context;
        this.activity = activity;
        //this.layoutInflater = LayoutInflater.from(this.context);
        this.layoutInflater = LayoutInflater.from(this.activity.getBaseContext());
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

    //link para os dados com viewHolder que foi criada e position identifica os dados dentro da lista
    @Override
    public void onBindViewHolder(@NonNull viewHolderProduto holder, int position) {
         final Produto p = this.listaProdutos.get(position);

        //link dados com listas nas views/produtos e quantidades
        holder.textview_nomeProduto.setText(p.getNomeProduto());
        holder.textview_quantProduto.setText(" " + String.format("%.0f",p.getQuantProduto()));

        //chama a activity quando clica no nome do produto
//        holder.textview_nomeProduto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //criação da intent
//                //Intent intent = new Intent(context, ActivityProduto.class);
//                Intent intent = new Intent(activity.getBaseContext(), ActivityProduto.class);
//                //inserção do objeto referente ao item selecionado na recycler view
//                intent.putExtra("produto",p);
//                //inicialiação da próxima activity
//                //context.startActivity(intent);
//                activity.getBaseContext().startActivity(intent);
//            }
//        });
        //cria o evento de click no icone editar
        holder.img_editProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Códido implementado, mas sem funcionar (problema nas activitys)", Toast.LENGTH_SHORT).show();
                //chamada da activity de alteração
                //Intent intent = new Intent(Context.ActivityProduto.class);
//                Intent intent = new Intent(activity.getBaseContext(),ActivityProduto.class);
//                //parametros para a intenção de passar para a prox activity o produto clicado
//                intent.putExtra("produto",p);
//                intent.putExtra("editar","editar");
//                activity.getBaseContext().startActivity(intent);
//                //retira a activity da pilha
//                activity.finish();
            }
        });
        //cria o evento de click nos icone excluir
        holder.img_excluirProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity.getBaseContext(), "excluir produto", Toast.LENGTH_SHORT).show();

                ProdutoDAO objProdutoDAO = new ProdutoDAO();
                //objProdutoDAO.apagarProduto(p, context);
               //objProdutoDAO.apagarProduto(p, activity.getBaseContext());
                objProdutoDAO.apagarProduto(p, activity);
                //exclui um objeto na lista
                listaProdutos.remove(p);
                //notifica o adapter que ocorreu uma mudança na lista
                notifyDataSetChanged();
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
