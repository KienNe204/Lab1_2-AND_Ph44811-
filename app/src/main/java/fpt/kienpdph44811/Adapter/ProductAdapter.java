package fpt.kienpdph44811.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import fpt.kienpdph44811.Model.ProductDTO;
import fpt.kienpdph44811.lab1_2ph44811.R;

public class ProductAdapter extends ArrayAdapter<ProductDTO> {

    private Context context;
    private ArrayList<ProductDTO> productList;

    public ProductAdapter(@NonNull Context context, ArrayList<ProductDTO> productList) {
        super(context, 0, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        ProductDTO product = productList.get(position);

        TextView tvProductName = view.findViewById(R.id.tv_product_name);
        TextView tvProductPrice = view.findViewById(R.id.tv_product_price);
        TextView tvProductQuantity = view.findViewById(R.id.tv_product_quantity);

        tvProductName.setText(product.getName());
        tvProductPrice.setText("Price: " + product.getPrice());
        tvProductQuantity.setText("Quantity: " + product.getQuantity());

        return view;
    }
}
