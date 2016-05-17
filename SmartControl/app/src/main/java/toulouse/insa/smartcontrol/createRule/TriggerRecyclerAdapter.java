package toulouse.insa.smartcontrol.createRule;

import android.content.Context;
import android.os.Handler;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import toulouse.insa.smartcontrol.R;

/**
 * Created by gautierenaud on 14/05/16.
 */

public class TriggerRecyclerAdapter extends RecyclerView.Adapter<TriggerViewHolder> {

    private Context mContext;
    private CreateRule mParent;

    private int spinnerCounter = 0;

    public int getSpinnerCounter() {
        return spinnerCounter;
    }

    private ArrayList<String> possibleChoices;

    public TriggerRecyclerAdapter(Context context, CreateRule parent){
        this.mContext = context;
        this.mParent = parent;
    }

    @Override
    public TriggerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_trigger_item, parent, false);

        return new TriggerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TriggerViewHolder holder, int position) {
        this.possibleChoices = mParent.getPossibleTriggerChoices(holder.getAdapterPosition());
        printPossibleValues(holder.getAdapterPosition());

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(mContext, R.layout.custom_spinner_item, this.possibleChoices);
        holder.triggerSpinner.setAdapter(spinnerArrayAdapter);

        String selectedValue = mParent.getTriggerSelection(holder.getAdapterPosition());
        System.out.println("Value selected by " + position + " : " + selectedValue);
        if (!selectedValue.equals("")) {
            int positionInSpinner = spinnerArrayAdapter.getPosition(selectedValue);
            holder.triggerSpinner.setSelection(positionInSpinner);
        }else{
            mParent.updateTriggerSelectionChange(new Pair<>(holder.getAdapterPosition(), possibleChoices.get(0)));
            Handler handler = new Handler();

            final Runnable r = new Runnable() {
                public void run() {
                    notifyDataSetChanged();
                }
            };

            handler.post(r);
        }

        // configure the action to do on selection
        holder.triggerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("onItemSelected " + holder.getAdapterPosition());
                if (holder.getAdapterPosition() >= 0) {
                    printPossibleValues(holder.getAdapterPosition());
                    // add to selection <holderPosition, selectedValue>
                    ArrayList<String> tmpPossibilities = mParent.getPossibleTriggerChoices(holder.getAdapterPosition());
                    if (tmpPossibilities.size() > position) {
                        String newSelection = tmpPossibilities.get(position);
                        String actualSelection = mParent.getTriggerSelection(holder.getAdapterPosition());
                        if (!newSelection.equals(actualSelection)) {
                            mParent.updateTriggerSelectionChange(new Pair<>(holder.getAdapterPosition(), newSelection));
                            notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button = holder.deleteButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAt(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.spinnerCounter;
    }

    public void removeAt(int position){
        this.spinnerCounter--;
        this.mParent.updateTriggerSelectionDelete(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, this.spinnerCounter);
    }

    public void addSpinner(){
        this.spinnerCounter++;
        notifyItemInserted(this.spinnerCounter - 1);
        notifyItemRangeChanged(0, this.spinnerCounter);
    }

    public void printPossibleValues(int position){
        System.out.println("possible triggers for " + position);
        ArrayList<String> tmpList = mParent.getPossibleTriggerChoices(position);
        for (String p : tmpList) {
            System.out.println("\n " + p);
        }
    }
}
