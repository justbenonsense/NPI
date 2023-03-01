package Database;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventupdiseo.EditarEvento;
import com.example.eventupdiseo.Grado;
import com.example.eventupdiseo.MenuActivity;
import com.example.eventupdiseo.MisEventos;
import com.example.eventupdiseo.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.MyViewHolder> {

    Context context;
    ArrayList<Evento> eventos;
    String user_usuario, user_nombre, user_grado, user_foto;
    FragmentActivity activity;

    public EventoAdapter(Context c, ArrayList<Evento> p, FragmentActivity a){
        context = c;
        eventos = p;
        activity = a;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i){
        myViewHolder.eventonombre.setText(eventos.get(i).getNombre());
        myViewHolder.eventoubi.setText(eventos.get(i).getUbicacion());
        myViewHolder.eventofecha.setText(eventos.get(i).getFecha());
        myViewHolder.eventohora.setText(eventos.get(i).getHora());

        final String getEventoNombre = eventos.get(i).getNombre();
        final String getEventoFecha = eventos.get(i).getFecha();
        final String getEventoHora = eventos.get(i).getHora();
        final String getEventoUbicacion = eventos.get(i).getUbicacion();
        final String getEventoGrado = eventos.get(i).getGrado();
        final String getEventoAforo = String.valueOf(eventos.get(i).getAforo());
        final String getEventoIdioma = eventos.get(i).getIdioma();

        myViewHolder.button_como_llegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if ( ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                        ActivityCompat.requestPermissions( activity, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  }, 0);
                        return;
                    }
                    if ( ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
                        ActivityCompat.requestPermissions( activity, new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION  }, 1);
                        return;
                    }
                    String uri = "http://maps.google.com/maps?daddr=" + getEventoUbicacion;
                    Uri gmmIntentUri = Uri.parse(uri);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(mapIntent);
                    }

            }
        });

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent edit = new Intent(context, EditarEvento.class);
                Intent intent = ((MenuActivity)v.getContext()).getIntent();

                // Cogemos la información del usuario
                user_usuario = intent.getStringExtra("usuario");
                user_nombre = intent.getStringExtra("nombre");
                user_foto = intent.getStringExtra("foto");
                user_grado = intent.getStringExtra("grado");

                // Enviamos la información del usuario y del evento
                edit.putExtra("eventonombre",getEventoNombre);
                edit.putExtra("eventofecha",getEventoFecha);
                edit.putExtra("eventohora",getEventoHora);
                edit.putExtra("eventoubicacion",getEventoUbicacion);
                edit.putExtra("eventogrado",getEventoGrado);
                edit.putExtra("eventoaforo",getEventoAforo);
                edit.putExtra("eventoidioma",getEventoIdioma);
                edit.putExtra("usuario",user_usuario);
                edit.putExtra("nombre",user_nombre);
                edit.putExtra("foto",user_foto);
                edit.putExtra("grado",user_grado);

                context.startActivity(edit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eventonombre, eventoubi, eventofecha, eventohora;
        Button button_como_llegar;
        Button boton_editar;

        public MyViewHolder(@NonNull View itemview){
            super(itemview);
            eventonombre = (TextView) itemview.findViewById(R.id.title_evento);
            eventoubi = (TextView) itemview.findViewById(R.id.evento_ubi);
            eventofecha = (TextView) itemview.findViewById(R.id.fecha_evento);
            eventohora = (TextView) itemview.findViewById(R.id.hora_evento);
            button_como_llegar = (Button) itemview.findViewById(R.id.button_como_llegar);
        }
    }
}
