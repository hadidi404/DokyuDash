package com.example.dokyudashprototype;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DocumentsActivity extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;
    private Uri fileUri;
    private RecyclerView recyclerView;
    private FilesAdapter adapter;
    private List<File> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        recyclerView = findViewById(R.id.filesRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3 columns in the grid

        fileList = new ArrayList<>();
        adapter = new FilesAdapter(this, fileList);
        recyclerView.setAdapter(adapter);

        Button uploadButton = findViewById(R.id.add_docs);
        uploadButton.setOnClickListener(v -> openFileChooser());
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("*/*"); // Allows any type of file to be selected
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileUri = data.getData();
            saveFileLocally(fileUri);
        }
    }

    private void saveFileLocally(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            File directory = new File(getFilesDir(), "uploaded_files");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File outputFile = new File(directory, System.currentTimeMillis() + "." + getFileExtension(uri));
            OutputStream outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();

            fileList.add(outputFile);  // Add the file to the list
            adapter.notifyDataSetChanged();  // Refresh the RecyclerView

            Toast.makeText(this, "File saved locally at: " + outputFile.getAbsolutePath(), Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "File saving failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        String extension = "";
        String mimeType = getContentResolver().getType(uri);
        if (mimeType != null) {
            extension = mimeType.split("/")[1];
        }
        return extension.isEmpty() ? "file" : extension;
    }
}
