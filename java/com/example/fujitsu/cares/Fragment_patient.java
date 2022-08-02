package com.example.fujitsu.cares;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.shapes.PathShape;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_patient.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_patient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_patient extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "patientsElement";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private patientsElement Parampatient;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_patient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_patient.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_patient newInstance(String param1, String param2) {
        Fragment_patient fragment = new Fragment_patient();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Parampatient = (patientsElement) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ImageView   icone,lit,montre;
        TextView    nom,descriptif;
        // Inflate the layout for this fragment
        View    rootView= inflater.inflate(R.layout.fragment_fragment_patient, container, false);
        icone=rootView.findViewById(R.id.icone);
        nom=rootView.findViewById(R.id.nom);
        descriptif=rootView.findViewById(R.id.descriptif);
        montre=rootView.findViewById(R.id.montre);
        nom=rootView.findViewById(R.id.nom);
        lit=rootView.findViewById(R.id.lit);
        RelativeLayout fragment=rootView.findViewById(R.id.fragment_pt);

        nom.setText(Parampatient.getPatient().getNom()+" "+Parampatient.getPatient().getPrenom());
        if(Parampatient.getFile().exists()){
            Bitmap  bitmap= BitmapFactory.decodeFile(Parampatient.getFile().getPath());
            icone.setImageBitmap(bitmap);
        }
        descriptif.setText(Parampatient.getPatient().getAge());
        if(Parampatient.getBracelet().getActivité()=="ACTIF"){
            if (Parampatient.getBracelet().getPosition()=="HORS-LIMITE"){
                montre.setImageResource(R.drawable.montre_rouge);
            }
            else if (Parampatient.getBracelet().getPosition()=="LIMITE"){
                montre.setImageResource(R.drawable.montre_bracelet_blanc_fond_bleu);
            }
            else{
                montre.setImageResource(R.drawable.montre_bracelet_blanc_fond_vert);
            }
        }

        else{
            montre.setImageResource(R.drawable.montre_bracelet_noir_fond_tr_2);
        }
        if(Parampatient.getLit().getEtat()=="ACTIF"){
            if (Parampatient.getLit().getFrequence_cardiaque()<25 || Parampatient.getLit().getFrequence_cardiaque()>100 || Parampatient.getLit().getTemperature()<32){
                lit.setImageResource(R.drawable.lit_rouge);
            }
            else if (Parampatient.getLit().getTemperature()>39){
                lit.setImageResource(R.drawable.montre_bracelet_blanc_fond_bleu);
            }
            else{
                montre.setImageResource(R.drawable.montre_bracelet_blanc_fond_vert);
            }
        }

        else{
            montre.setImageResource(R.drawable.montre_bracelet_noir_fond_tr_2);
        }

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Patient_profile.class);
                intent.putExtra("patient", (Serializable) Parampatient);
                startActivity(intent);
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
