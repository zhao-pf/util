package cn.zpf.myutil.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAdapter(int size, final int size2, final int layout, final Cal cal) {
        setLayoutManager(new StaggeredGridLayoutManager(size, 1));
        setAdapter(new Adapter() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(getContext()).inflate(layout, null, false
                )) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                cal.bix(holder.itemView, position);
            }

            @Override
            public int getItemCount() {
                return size2;
            }
        });
    }
}
