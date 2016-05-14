package toulouse.insa.smartcontrol.viewRules;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import toulouse.insa.smartcontrol.R;

/**
 * Created by gautierenaud on 27/04/16.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private List<RuleToDisplay> mRuleList;

    public MyRecyclerAdapter(List<RuleToDisplay> ruleList){
        this.mRuleList = ruleList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int position){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_frameself, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        RuleToDisplay tmpRule = mRuleList.get(position);

        holder.titleView.setText(tmpRule.getTitle());
        holder.categoryView.setText(tmpRule.getRuleType().toString());
    }

    @Override
    public int getItemCount() {
        return mRuleList.size();
    }
}
