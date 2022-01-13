package com.example.benjabd.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.benjabd.Agenda.AdapterA;
import com.example.benjabd.Agenda.BenjaBD;
import com.example.benjabd.Agenda.ItemsA;
import com.example.benjabd.Agenda.Registro;
import com.example.benjabd.R;

import java.util.ArrayList;
import java.util.List;

public class AgendaFragment extends Fragment {
    Button btn_Agr;
    private Activity view;
private RecyclerView RV;
    private SwipeRefreshLayout refreshLayout;
private AdapterA adapterA;
    List<ItemsA> productlists=new ArrayList<>();
    SearchView searchView;
    static final int DURACION =   1000; // 3 segundos de carga
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agenda, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


      final   BenjaBD benjaBD = new BenjaBD(getActivity());
        btn_Agr = view.findViewById(R.id.btn_Agr);
        RV = view.findViewById(R.id.RV);
        refreshLayout = view.findViewById(R.id.swipeRefresh);

        RV.setLayoutManager(new LinearLayoutManager(getActivity()));
        RV.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        RV.setLayoutManager(linearLayoutManager);
        productlists = benjaBD.mostrars();
        adapterA = new AdapterA(productlists, getActivity());


        RV.setAdapter(adapterA);
        setHasOptionsMenu(true);

        btn_Agr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              //  getFragmentManager().beginTransaction().replace(R.id.container,
                //        new ListaGuias()).addToBackStack(null).commit();
                Intent intent = new Intent(getActivity(), Registro.class);
                getActivity().startActivity(intent);

            }
        });

// Iniciar la tarea asíncrona al revelar el indicador
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        productlists = benjaBD.mostrars();
                        adapterA = new AdapterA(productlists, getActivity());
                        RV.setAdapter(adapterA);
                        refreshLayout.setRefreshing(false);
                    }
                }
        );



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home_i, menu);

        final MenuItem myActionMenuItem=menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.colorPrimary));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<ItemsA> filtermodelist= filter(productlists,newText);
                adapterA.setfilter(filtermodelist);
                return true;
            }


        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private List<ItemsA> filter(List<ItemsA> pl,String query)
    {
        query=query.toLowerCase();
        final List<ItemsA> filteredModelist=new ArrayList<>();
        for(ItemsA model:pl)
        {
            final  String text = model.getApellido().toLowerCase();
            if(text.startsWith(query))
            {
                filteredModelist.add(model) ;
            }
        }
        return  filteredModelist;
    }

}
