package com.doctarhyf.myapplication.frags;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctarhyf.myapplication.R;
import com.doctarhyf.myapplication.frags.FragmentInsecHistory.OnListFragmentInteractionListener;
import com.doctarhyf.myapplication.frags.dummy.DummyContent.InsecSignal;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link InsecSignal} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class AdapterInsecSignal extends RecyclerView.Adapter<AdapterInsecSignal.ViewHolder> {

    private final List<InsecSignal> mInsecSignalList;
    private final OnListFragmentInteractionListener mListener;

    public AdapterInsecSignal(List<InsecSignal> items, OnListFragmentInteractionListener listener) {
        mInsecSignalList = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_insec_signal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mInsecSignalList.get(position);
        holder.mIdView.setText(mInsecSignalList.get(position).id);
        holder.mContentView.setText(mInsecSignalList.get(position).content);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onInsecItemClicked(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInsecSignalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public InsecSignal mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
