package com.example.arsalabangash.confirmat;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
    private int numItems;
    final private ListItemClickListener listItemClickListener;

    public interface ListItemClickListener {
        void onListClick(int clickedItemIndex);
    }

    public ReportAdapter(int numberOfItems, ListItemClickListener newListItemClickListener) {
        numItems = numberOfItems;
        listItemClickListener = newListItemClickListener;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     */
    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        ReportViewHolder viewHolder = new ReportViewHolder(view);

        return viewHolder;
    }

    /**
     * Update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position
     */
    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        holder.bind(position);
    }

    //Returns number of items to display
    @Override
    public int getItemCount() {
        return numItems;
    }

    class ReportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView listItemNumberView;

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks
         */
        public ReportViewHolder(View itemView) {
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.item_text);
            itemView.setOnClickListener(this);
        }

        /**
         * Binds the data to the current ViewHolder
         * @param listIndex The index of the question for which data has to be displayed
         */
        void bind(int listIndex) {
            listItemNumberView.setText(ReportData.getReportData().getQuestionReport(listIndex));
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listItemClickListener.onListClick(clickedPosition);
        }
    }
}
