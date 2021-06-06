package com.info.kisileruygulamasi;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class KisilerAdapter extends RecyclerView.Adapter<KisilerAdapter.CardTutucu>{

    private Context context;
    private List<Kisiler> kisilerList;
    private Veritabani veritabani;

    public KisilerAdapter(Context context, List<Kisiler> kisilerList, Veritabani veritabani) {
        this.context = context;
        this.kisilerList = kisilerList;
        this.veritabani = veritabani;
    }

    @NonNull
    @Override
    public CardTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim, parent, false);
        return new CardTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTutucu holder, int position) {
        Kisiler k = kisilerList.get(position);

        holder.textView.setText(k.getKisi_ad() + " - " + k.getKisi_tel());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.imageView);
                popupMenu.getMenuInflater().inflate(R.menu.card_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_delete:
                                Snackbar.make(holder.imageView,"Kişi silinsin mi?", Snackbar.LENGTH_LONG).setAction("Evet", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        new KisilerDao().kisiSil(veritabani, k.getKisi_id());

                                        kisilerList = new KisilerDao().tumKisiler(veritabani);

                                        notifyDataSetChanged();
                                    }
                                }).show();
                                return true;
                            case R.id.action_edit:
                                alertGoster(k);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return kisilerList.size();
    }

    public class CardTutucu extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public CardTutucu(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public void alertGoster(Kisiler kisi) {

        LayoutInflater layout = LayoutInflater.from(context);
        View tasarim = layout.inflate(R.layout.alert_tasarim, null);

        EditText editTextAd = tasarim.findViewById(R.id.editTextAd);
        EditText editTextTel = tasarim.findViewById(R.id.editTextTel);

        editTextAd.setText(kisi.getKisi_ad());
        editTextTel.setText(kisi.getKisi_tel());

        AlertDialog.Builder ad = new AlertDialog.Builder(context);

        ad.setTitle("Kişi Güncelle");
        ad.setView(tasarim);
        ad.setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String kisiAd = editTextAd.getText().toString().trim();
                String kisiTel = editTextTel.getText().toString().trim();

                new KisilerDao().kisiGuncelle(veritabani,kisi.getKisi_id(),kisiAd,kisiTel);

                kisilerList = new KisilerDao().tumKisiler(veritabani);

                notifyDataSetChanged();
            }
        });
        ad.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.create().show();
    }
}
