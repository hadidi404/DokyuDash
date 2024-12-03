package com.example.dokyudashprototype;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.io.File;
import java.util.List;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.FileViewHolder> {

    private List<File> fileList;
    private Context context;

    public FilesAdapter(Context context, List<File> fileList) {
        this.context = context;
        this.fileList = fileList;
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        File file = fileList.get(position);
        holder.fileName.setText(file.getName());

        // Optionally set an image thumbnail for images, videos, etc.
        if (file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg") || file.getName().endsWith(".png")) {
            Glide.with(context)
                    .load(file)
                    .into(holder.fileImage); // Load image with Glide
        } else {
            holder.fileImage.setImageResource(R.drawable.dict);  // Default icon for non-image files
        }
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {
        TextView fileName;
        ImageView fileImage;

        public FileViewHolder(View itemView) {
            super(itemView);
            fileName = itemView.findViewById(R.id.fileName);
            fileImage = itemView.findViewById(R.id.fileImage);  // ImageView for thumbnail
        }
    }
}
