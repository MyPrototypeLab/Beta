package com.prototypelab.crud_android_mssql.main;

import static com.prototypelab.crud_android_mssql.main.constants.sql.DELETE;
import static com.prototypelab.crud_android_mssql.main.constants.sql.MERGE;
import static com.prototypelab.crud_android_mssql.main.constants.sql.SELECT;
import static java.nio.file.Files.delete;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.prototypelab.crud_android_mssql.R;
import com.prototypelab.crud_android_mssql.main.Model.Customer;
import com.prototypelab.crud_android_mssql.main.connecction.ConnectionMSSQL;
import com.prototypelab.crud_android_mssql.main.constants.sql;

import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {

    ConnectionMSSQL conn = new ConnectionMSSQL();
    Button btnSearch, btnDelete, btnSave;
    EditText etId, etName, etAddress, etSearch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        btnSearch = findViewById(R.id.btnSearch);
        btnDelete = findViewById(R.id.btnDelete);
        btnSave = findViewById(R.id.btnSave);
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etSearch = findViewById(R.id.etSearch);

        // Set click listeners for buttons
        btnSearch.setOnClickListener(v -> search());
        btnDelete.setOnClickListener(v -> delete());
        btnSave.setOnClickListener(v -> save());
    }

    private void save() {
        try {
            Customer c = null;
            //Veririfcar que los campos no esten vacios
            if (etId.getText().toString().isEmpty() || etName.getText().toString().isEmpty() || etAddress.getText().toString().isEmpty()) {
                Toast.makeText(this,
                        "Por favor, completa todos los campos",
                        Toast.LENGTH_SHORT).show();
            }else{
                //Obtener los datos del cliente
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                String address = etAddress.getText().toString();
                //Crear nuevo cliente
                c = new Customer(id, name, address);
                String merge = MERGE(c);
                conn.ExecuteSQLQueries(merge);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void search() {
        try{
            if (etSearch.getText().toString().isEmpty()){
                Toast.makeText(this,
                        "Por favor, completa el campo de búsqueda",
                        Toast.LENGTH_SHORT).show();
            }else {
                String search = etSearch.getText().toString();
                String sql = SELECT(search);
                ResultSet rs =  conn.ExecuteSQLSelect(sql);
              if (rs !=  null){
                  etId.setText(rs.getString("id"));
                  etName.setText(rs.getString("name"));
                  etAddress.setText(rs.getString("address"));
              }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void delete() {
        try{
            if (etId.getText().toString().isEmpty()){
                Toast.makeText(this,
                        "Por favor, completa el campo de búsqueda",
                        Toast.LENGTH_SHORT).show();
            }else {
                String id = etId.getText().toString();
                String sql = DELETE(id);
                conn.ExecuteSQLQueries(sql);
                Toast.makeText(this,"Registro eliminado", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}