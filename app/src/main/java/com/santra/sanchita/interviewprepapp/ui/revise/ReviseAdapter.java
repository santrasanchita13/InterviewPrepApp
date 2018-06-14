package com.santra.sanchita.interviewprepapp.ui.revise;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santra.sanchita.interviewprepapp.R;
import com.santra.sanchita.interviewprepapp.data.db.model.InterviewItem;

import java.util.List;

/**
 * Created by sanchita on 13/6/18.
 */

public class ReviseAdapter extends RecyclerView.Adapter<ReviseAdapter.ViewHolder> {

    private List<InterviewItem> interviewItemList;
    private Context context;
    ReviseMvpPresenter presenter;

    private GalleryViewHolder galleryViewHolder;

    public ReviseAdapter(Context context, List<InterviewItem> interviewItemList, ReviseMvpPresenter presenter) {
        this.interviewItemList = interviewItemList;
        this.context = context;
        this.presenter = presenter;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }

    public class GalleryViewHolder extends ViewHolder {

        TextView questionRevise, answerRevise;
        View itemView;

        public GalleryViewHolder(View view) {
            super(view);
            itemView = view;
            questionRevise = view.findViewById(R.id.questionRevise);
            answerRevise = view.findViewById(R.id.answerRevise);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_revise, parent, false);
        return new GalleryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        galleryViewHolder = (GalleryViewHolder) holder;

        InterviewItem interviewItem = interviewItemList.get(position);

        galleryViewHolder.questionRevise.setText(interviewItem.getQuestion());

        galleryViewHolder.answerRevise.setText(interviewItem.getAnswer());

        galleryViewHolder.questionRevise.setTag(position);

        galleryViewHolder.itemView.setOnClickListener(view -> {
            int pos = (int) view.findViewById(R.id.questionRevise).getTag();
            InterviewItem updateItem = interviewItemList.get(pos);
            updateItem.setSolved(true);
            presenter.updateAnswer(updateItem);
        });
    }

    @Override
    public int getItemCount() {
        return interviewItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }
}