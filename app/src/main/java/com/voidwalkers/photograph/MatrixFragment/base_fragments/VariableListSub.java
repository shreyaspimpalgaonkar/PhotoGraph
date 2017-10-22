package com.voidwalkers.photograph.MatrixFragment.base_fragments;



import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.voidwalkers.photograph.MatrixFragment.Matrix;
import com.voidwalkers.photograph.MatrixFragment.MatrixAdapter;
import com.voidwalkers.photograph.R;
import com.voidwalkers.photograph.GlobalValues;



public class VariableListSub extends ListFragment {

    MatrixAdapter adapter;
    int Row;
    int Col;
    int ClickNo;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new MatrixAdapter(getContext(),R.layout.list_layout_fragment,((GlobalValues)getActivity().getApplication()).GetCompleteList());
        getListView().setDividerHeight(1);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Matrix m = ((GlobalValues) getActivity().getApplication()).GetCompleteList().get(position);
        if (ClickNo == 0) {
            Row =m.GetRow();
            Col = m.GetCol();
            AddToQueue(m);
            ClickNo++;
        }
        else{
            if(m.GetRow() == Row && m.GetCol() ==Col){
                AddToQueue(m);
            }
            else
            {
                Toast.makeText(getContext(),"You can only select " + String.valueOf(Row) +" x "+ String.valueOf(Col) + " Matrix ",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void AddToQueue(Matrix click){
        try {
            @SuppressWarnings("ConstantConditions") //to suppress the null pointer exception of the  textview
                    TextView textView = (TextView) getParentFragment().getView().findViewById(R.id.AdditionStatus);
            String Initial = textView.getText().toString();
            if(Initial.isEmpty()){
                textView.setText(click.GetName());
                ((GlobalValues)getActivity().getApplication()).MatrixQueue.add(click);
            }
            else {
                String Complete = Initial +  " - " + click.GetName();
                textView.setText(Complete);
                ((GlobalValues)getActivity().getApplication()).MatrixQueue.add(click);
            }
        }catch  (NullPointerException e){
            Log.d("AddToQueue","Exception raised, cannot get textview from parent fragment");
            e.printStackTrace();
        }

    }
    public void UpdateInfo(){
        ClickNo = 0;
    }

}