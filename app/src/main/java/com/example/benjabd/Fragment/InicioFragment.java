package com.example.benjabd.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import com.example.benjabd.Agenda.AdapterA;

import com.example.benjabd.Agenda.BenjaBD;
import com.example.benjabd.Agenda.EditAgenda;
import com.example.benjabd.Agenda.ItemsA;
import com.example.benjabd.Agenda.Registro;
import com.example.benjabd.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class InicioFragment extends Fragment {
    private SearchView searchView;
    private AdapterA adapterA;
    RecyclerView RV2;
    private SwipeRefreshLayout refreshLayout;
    List<ItemsA> productlists=new ArrayList<>();
    private Activity view;
Button btn_Ven,btn_fch;
EditText edt_vff;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);




    }





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


       final BenjaBD benjaBD = new BenjaBD(getActivity());
        btn_Ven = view.findViewById(R.id.btn_Ven);
        btn_fch = view.findViewById(R.id.btn_fch);
        edt_vff = view.findViewById(R.id.edt_vff);
        RV2 = view.findViewById(R.id.RV2);

        RV2.setLayoutManager(new LinearLayoutManager(getActivity()));
        RV2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        RV2.setLayoutManager(linearLayoutManager);
        refreshLayout = view.findViewById(R.id.swipeRefresh);



        adapterA = new AdapterA(productlists, getActivity());




        setHasOptionsMenu(true);
        btn_fch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_vff.getText().toString().trim().equalsIgnoreCase("")){

                    GregorianCalendar objeF = new GregorianCalendar();
                    int mes,dia;

                    mes =objeF.get(Calendar.MONTH);
                    dia = objeF.get(Calendar.DAY_OF_MONTH);

                    String c = mes +"/"+dia;
                   /* productlists = benjaBD.mostrars();
                    final List<ItemsA> filtermodelist= filter(productlists,b);
                    adapterA.setfilter(filtermodelist);
                    RV2.setAdapter(adapterA);*/

                    Toast.makeText(getActivity(), "CLIAU! ACABAS DE BUSCAR LA FECHA DE HOY:"+ c,Toast.LENGTH_LONG).show();

                }else {
                    String b = edt_vff.getText().toString().trim();
                    productlists = benjaBD.mostrars();
                    final List<ItemsA> filtermodelist= filter(productlists,b);
                    adapterA.setfilter(filtermodelist);
                    RV2.setAdapter(adapterA);
                    Toast.makeText(getActivity(), "WEEEEEY ACABAS DE BUSCAR ESTA FECHA:"+" "+b,Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_Ven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "BOTON AVERIADO HASTA EL MOEMNTO JEJEJEJEJE ¡¡SORRY!!",Toast.LENGTH_LONG).show();

            }
        });

        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        productlists = benjaBD.mostrars();
                        adapterA = new AdapterA(productlists, getActivity());
                        RV2.setAdapter(adapterA);
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
                RV2.setAdapter(adapterA);
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
            final  String text = model.getFechaF().toLowerCase();
            if(text.startsWith(query))
            {
                filteredModelist.add(model) ;
            }
        }
        return  filteredModelist;
    }


}
