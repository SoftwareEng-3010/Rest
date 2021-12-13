package UI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.exercise_5.R;
import com.google.zxing.Result;

public class QRCodeActivity extends AppCompatActivity {

    private final String TAG = "QRCodeActivity";
    private CodeScanner qrScanner;
    private CodeScannerView qrScannerView;
    private TextView text;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode_activity);

        qrScannerView = (CodeScannerView)findViewById(R.id.scanner_view);
        text = (TextView)findViewById(R.id.qrTextView);

        qrScanner = new CodeScanner(this, qrScannerView);
        qrScanner.setCamera(CodeScanner.CAMERA_FRONT);

        qrScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(QRCodeActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // TODO: 12/13/2021 Figure out how to start the emulated camera to use startPreview()
//        qrScanner.startPreview();

//        qrScannerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                qrScanner.startPreview();
//            }
//        });
    }


}
