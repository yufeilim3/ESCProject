package com.example.escproject;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class UploadFileActivity extends AppCompatActivity {
	StorageReference storageRef;
	Button upload;
	EditText filename;
	Uri downloadUrl;
	private FirebaseAuth auth;
	private FirebaseUser firebaseUser;
	private DatabaseReference databaseReference;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_file);
		
		auth = FirebaseAuth.getInstance();
		if ((firebaseUser = auth.getCurrentUser())==null){
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}
		databaseReference = FirebaseDatabase.getInstance().getReference();
		
		upload = findViewById(R.id.btn_upload);
		filename = findViewById(R.id.file_name);
		
		storageRef = FirebaseStorage.getInstance().getReference();
		
		upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				final String path = filename.getText().toString().trim();
				try{
					Uri file = Uri.fromFile(new File(path));
					StorageReference riversRef = storageRef.child(file.getLastPathSegment());
					UploadTask uploadTask = riversRef.putFile(file);
					
					uploadTask.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception exception) {
							// Handle unsuccessful uploads
							Toast.makeText(view.getContext(),"Upload Fail", Toast.LENGTH_LONG).show();
						}
					}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
						@Override
						public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
							// taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
							downloadUrl = taskSnapshot.getDownloadUrl();
							Toast.makeText(view.getContext(),"Upload Successful", Toast.LENGTH_LONG).show();
							String filenode = path.split(".")[0]+path.split(".")[1];
							databaseReference.child("Courses")
									.child(InstrCourseActivity.state.courID)
									.child("Slide")
									.child(filenode)
									.setValue(new onlineFile(path.split(".")[0],path.split(".")[1], downloadUrl.toString()));
						}
					});
				}catch (Exception ex) {
					Toast.makeText(view.getContext(),"No such File", Toast.LENGTH_LONG).show();
				}
				Intent intent = new Intent(view.getContext(), InstrViewSlideActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
	}
}

