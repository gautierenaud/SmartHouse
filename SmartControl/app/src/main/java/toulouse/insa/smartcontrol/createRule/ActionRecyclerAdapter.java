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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import toulouse.insa.smartcontrol.R;

/**
 * Created by gautierenaud on 14/05/16.
 */

public class ActionRecyclerAdapter extends RecyclerView.Adapter<ActionViewHolder> {

    private Context mContext;
    private CreateRule mParent;

    private int spinnerCounter = 0;

    public int getSpinnerCounter() {
        return spinnerCounter;
    }

    private ArrayList<String> possibleChoices;

    public ActionRecyclerAdapter(Context context, CreateRule parent){
        this.mContext = context;
        this.mParent = parent;
    }

    @Override
    public ActionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_action_item, parent, false);

        return new ActionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ActionViewHolder holder, int position) {
        this.possibleChoices = mParent.getPossibleActionChoices(holder.getAdapterPosition());
        printPossibleValues(holder.getAdapterPosition());

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(mContext, R.layout.custom_spinner_item, this.possibleChoices);
        holder.actionSpinner.setAdapter(spinnerArrayAdapter);

        String selectedValue = mParent.getActionSelection(holder.getAdapterPosition());
        System.out.println("Value selected by " + position + " : " + selectedValue);
        if (!selectedValue.equals("")) {
            int positionInSpinner = spinnerArrayAdapter.getPosition(selectedValue);
            holder.actionSpinner.setSelection(positionInSpinner);
        }else{
            mParent.updateActionSelectionChange(new Pair<>(holder.getAdapterPosition(), possibleChoices.get(0)));
            Handler handler = new Handler();

            final Runnable r = new Runnable() {
                public void run() {
                    notifyDataSetChanged();
                }
            };

            handler.post(r);
        }

        // configure the action to do on selection
        holder.actionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("onItemSelected " + holder.getAdapterPosition());
                if (holder.getAdapterPosition() >= 0) {
                    printPossibleValues(holder.getAdapterPosition());
                    // add to selection <holderPosition, selectedValue>
                    ArrayList<String> tmpPossibilities = mParent.getPossibleActionChoices(holder.getAdapterPosition());
                    if (tmpPossibilities.size() > position  ) {
                        RelativeLayout setColors = (RelativeLayout) view.getRootView().findViewById(R.id.setColor_container);
                        LinearLayout setText = (LinearLayout) view.getRootView().findViewById(R.id.setText_container);
                        String newSelection = tmpPossibilities.get(position);
                        if (newSelection.equals("changeColor")){
                            setColors.setVisibility(View.VISIBLE);
                            setText.setVisibility(View.GONE);
                        } else if (newSelection.equals("setText")){
                            setColors.setVisibility(View.GONE);
                            setText.setVisibility(View.VISIBLE);
                        }

                        String actualSelection = mParent.getActionSelection(holder.getAdapterPosition());
                        if (!newSelection.equals(actualSelection)) {
                            mParent.updateActionSelectionChange(new Pair<>(holder.getAdapterPosition(), newSelection));
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
        this.mParent.updateActionSelectionDelete(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, this.spinnerCounter);
    }

    public void addSpinner(){
        this.spinnerCounter++;
        notifyItemInserted(this.spinnerCounter - 1);
        notifyItemRangeChanged(0, this.spinnerCounter);
    }

    public void printPossibleValues(int position){
        System.out.println("possible actions for " + position);
        ArrayList<String> tmpList = mParent.getPossibleActionChoices(position);
        for (String p : tmpList) {
            System.out.println("\n " + p);
        }
    }
}