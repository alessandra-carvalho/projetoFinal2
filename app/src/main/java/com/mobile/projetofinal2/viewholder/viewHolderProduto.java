package com.mobile.projetofinal2.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.projetofinal2.R;

public class viewHolderProduto extends RecyclerView.ViewHolder {

    //mapeia itens da recyclerView
    public LinearLayout linearLayoutProduto;
    public TextView textview_nomeProduto;
    public TextView textview_quantProduto;
    public ImageView img_editProduto;
    public ImageView img_excluirProduto;


    //construtor da viewHolder
    public viewHolderProduto(@NonNull View itemView) {
        super(itemView);

        //lincando os itens do xml do linear Layout da activity_produto_item
        this.linearLayoutProduto = itemView.findViewById(R.id.linearlayout_produto);
        this.textview_nomeProduto = itemView.findViewById(R.id.textview_nomeProduto);
        this.textview_quantProduto = itemView.findViewById(R.id.textview_quantProduto);
        this.img_editProduto = itemView.findViewById(R.id.img_editProduto);
        this.img_excluirProduto = itemView.findViewById(R.id.img_excluirProduto);
    }
}
