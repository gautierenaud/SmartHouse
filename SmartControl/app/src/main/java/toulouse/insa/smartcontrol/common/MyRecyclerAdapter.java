package toulouse.insa.smartcontrol.common;

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
    private List<FrameselfObject> mRuleList;

    public MyRecyclerAdapter(List<FrameselfObject> ruleList){
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
        FrameselfObject tmpRule = mRuleList.get(position);

        holder.titleView.setText(tmpRule.getmTitle());

        holder.triggerView.setText(tmpRule.getmTrigger());

        holder.actionView.setText(tmpRule.getmAction());
    }

    @Override
    public int getItemCount() {
        return mRuleList.size();
    }
}
