package com.example.dokyudashprototype;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.BarcodeView;

public class QrFragment extends Fragment {

    private BarcodeView barcodeView;
    private TextView qrCodeResult;
    private static final int CAMERA_REQUEST_CODE = 101;

    public QrFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr, container, false);

        // Initialize views
        barcodeView = view.findViewById(R.id.barcode_scanner);


        // Check for camera permission before starting the scanner
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Request the camera permission if not granted
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            startCamera();
        }

        return view;
    }

    // Handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startCamera();
        } else {
            qrCodeResult.setText("Camera permission denied");
        }
    }

    private void startCamera() {
        // Start scanning automatically inside the smaller frame
        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                // Display the scanned result
                qrCodeResult.setText(result.getText());
            }

            @Override
            public void possibleResultPoints(java.util.List<com.google.zxing.ResultPoint> resultPoints) {
                // Handle possible result points (if needed)
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Resume camera preview when fragment is resumed
        barcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Pause camera preview when fragment is paused
        barcodeView.pause();
    }
}
