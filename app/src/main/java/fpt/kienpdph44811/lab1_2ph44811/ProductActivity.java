package fpt.kienpdph44811.lab1_2ph44811;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fpt.kienpdph44811.Adapter.ProductAdapter;
import fpt.kienpdph44811.DAO.ProductDAO;
import fpt.kienpdph44811.Model.ProductDTO;

public class ProductActivity extends AppCompatActivity {

    private EditText edtProductName, edtProductPrice, edtProductQuantity;
    private Button btnAdd, btnUpdate, btnDelete;
    private ListView lvProducts;
    private ProductDAO productDAO;
    private ProductAdapter productAdapter;
    private ArrayList<ProductDTO> productList;
    private ProductDTO selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        edtProductName = findViewById(R.id.edt_product_name);
        edtProductPrice = findViewById(R.id.edt_product_price);
        edtProductQuantity = findViewById(R.id.edt_product_quantity);
        btnAdd = findViewById(R.id.btn_add_product);
        btnUpdate = findViewById(R.id.btn_update_product);
        btnDelete = findViewById(R.id.btn_delete_product);
        lvProducts = findViewById(R.id.lv_products);

        productDAO = new ProductDAO(this);
        loadProductList();

        btnAdd.setOnClickListener(v -> addProduct());
        btnUpdate.setOnClickListener(v -> updateProduct());
        btnDelete.setOnClickListener(v -> deleteProduct());

        lvProducts.setOnItemClickListener((parent, view, position, id) -> {
            selectedProduct = productList.get(position);
            edtProductName.setText(selectedProduct.getName());
            edtProductPrice.setText(String.valueOf(selectedProduct.getPrice()));
            edtProductQuantity.setText(String.valueOf(selectedProduct.getQuantity()));
        });
    }

    private void loadProductList() {
        productList = productDAO.getAllProducts();
        productAdapter = new ProductAdapter(this, productList);
        lvProducts.setAdapter(productAdapter);
    }

    private void addProduct() {
        String name = edtProductName.getText().toString();
        String priceStr = edtProductPrice.getText().toString();
        String quantityStr = edtProductQuantity.getText().toString();

        if (name.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        int quantity = Integer.parseInt(quantityStr);

        ProductDTO product = new ProductDTO(0, name, price, quantity);
        long result = productDAO.insertProduct(product);

        if (result > 0) {
            Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
            loadProductList();
            clearFields();
        } else {
            Toast.makeText(this, "Failed to add product", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateProduct() {
        if (selectedProduct == null) {
            Toast.makeText(this, "Please select a product to update", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = edtProductName.getText().toString();
        String priceStr = edtProductPrice.getText().toString();
        String quantityStr = edtProductQuantity.getText().toString();

        if (name.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        int quantity = Integer.parseInt(quantityStr);

        selectedProduct.setName(name);
        selectedProduct.setPrice(price);
        selectedProduct.setQuantity(quantity);

        int result = productDAO.updateProduct(selectedProduct);

        if (result > 0) {
            Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show();
            loadProductList();
            clearFields();
            selectedProduct = null;
        } else {
            Toast.makeText(this, "Failed to update product", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteProduct() {
        if (selectedProduct == null) {
            Toast.makeText(this, "Please select a product to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        int result = productDAO.deleteProduct(selectedProduct.getId());

        if (result > 0) {
            Toast.makeText(this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
            loadProductList();
            clearFields();
            selectedProduct = null;
        } else {
            Toast.makeText(this, "Failed to delete product", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        edtProductName.setText("");
        edtProductPrice.setText("");
        edtProductQuantity.setText("");
    }
}
