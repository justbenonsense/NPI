package Database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventupdiseo.EditarEvento;
import com.example.eventupdiseo.MainActivity;
import com.example.eventupdiseo.MenuActivity;
import com.example.eventupdiseo.MisEventos;
import com.example.eventupdiseo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AllEventsAdapter extends RecyclerView.Adapter<AllEventsAdapter.MyViewHolderEU> {

    Context context;
    ArrayList<Evento> eventos;
    String user_usuario, user_nombre, user_grado, user_foto;

    public AllEventsAdapter(Context c, ArrayList<Evento> p){
        context = c;
        eventos = p;
    }

    @NonNull
    @Override
    public MyViewHolderEU onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        return new MyViewHolderEU(LayoutInflater.from(context).inflate(R.layout.item_eventup,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderEU myViewHolder, int i){
        myViewHolder.eventonombre.setText(eventos.get(i).getNombre());
        myViewHolder.eventoubi.setText(eventos.get(i).getUbicacion());
        myViewHolder.eventofecha.setText(eventos.get(i).getFecha());
        myViewHolder.eventohora.setText(eventos.get(i).getHora());
        myViewHolder.eventogrado.setText("Grado: "+eventos.get(i).getGrado());
        myViewHolder.eventoaforo.setText("Aforo: "+eventos.get(i).getAforo());
        myViewHolder.eventoidioma.setText("Idioma: "+eventos.get(i).getIdioma());

        final String getEventoNombre = eventos.get(i).getNombre();

        myViewHolder.button_apuntarse_evento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = ((MenuActivity)v.getContext()).getIntent();

                // Cogemos la información del usuario
                user_usuario = intent.getStringExtra("usuario");

                // Creamos un objeto de la clase DBHelper
                DBHelper admin = new DBHelper(context);

                // Abrimos la base de datos como escritura
                SQLiteDatabase db = admin.getWritableDatabase();

                admin.asignarEvento(user_usuario,getEventoNombre);

                eventos.remove(myViewHolder.getAdapterPosition());
                notifyItemRemoved(myViewHolder.getAdapterPosition());

                db.close();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    class MyViewHolderEU extends RecyclerView.ViewHolder {

        TextView eventonombre, eventoubi, eventofecha, eventohora, eventogrado, eventoidioma, eventoaforo;
        Button button_apuntarse_evento;

        public MyViewHolderEU(@NonNull View itemview){
            super(itemview);
            eventonombre = (TextView) itemview.findViewById(R.id.title_eventup);
            eventoubi = (TextView) itemview.findViewById(R.id.eventup_ubi);
            eventofecha = (TextView) itemview.findViewById(R.id.fecha_eventup);
            eventohora = (TextView) itemview.findViewById(R.id.hora_eventup);
            eventogrado = (TextView) itemview.findViewById(R.id.eventup_grado);
            eventoidioma = (TextView) itemview.findViewById(R.id.eventup_idioma);
            eventoaforo = (TextView) itemview.findViewById(R.id.eventup_aforo_total);
            button_apuntarse_evento = (Button) itemview.findViewById(R.id.button_apuntarse_evento);
        }

    }

}
