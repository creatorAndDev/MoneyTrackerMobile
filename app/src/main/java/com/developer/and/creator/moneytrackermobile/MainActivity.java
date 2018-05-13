package com.developer.and.creator.moneytrackermobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String otherPrice;
    /**Результат который заносится в масив для обработки*/
    ArrayList<Float> arr = new ArrayList<Float>();
    /**Первое введенное число*/
    float number1;
    /**Второе введенное число*/
    float number2;

    //список товаров
    static class Item {

        String name;
        int price;

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //определеяем наши элементы из xml main
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText price = (EditText) findViewById(R.id.price);
        final Button add = (Button) findViewById(R.id.add);
        final ListView items = (ListView) findViewById(R.id.items);

//        final TextView view = (TextView) findViewById(R.id.priceOther);

        //создаем адаптер для ListView что бы передавать в него полученные данные
        final ItemsAdapter adapter = new ItemsAdapter();
        items.setAdapter(adapter);

        //метод по обработке цены для вывода ее снизу в общем показатели
//        public void currency(String otherPrice) {
//            this.otherPrice = otherPrice;
//        }

        //слушатель по кнопке
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
//                    if(name.getText().toString() != "") {
//                        adapter.add(new Item(name.getText().toString(), Integer.valueOf(price.getText().toString())));
//                    } else {
//                        name.requestFocus();
//                    }
                    adapter.add(new Item(name.getText().toString(), Integer.valueOf(price.getText().toString())));

                    //запись цены
//                    String otherPrice = price.getText().toString();
//                    int number = Integer.parseInt(otherPrice);

                    //заносим в массив
                    arr.add(Float.parseFloat(price.getText().toString()));

                    number1 = arr.get(0);
                    number2 = arr.get(1);

                    arr.removeAll(arr);
                    arr.add(number1 + number2);

//        view.setText("" + number);
                    ((TextView) findViewById(R.id.priceOther)).setText(String.format("%.0f", arr.get(0)));
//                    view.setText(String.format("%.0f", arr.get(0)));
                    //отпавляем наше преобразованое число в метод и там выводим его результат
//                    display(number);

                    //обнуляем поля
                    name.setText("");
                    price.setText("");

                } catch (Exception e) {
                    //исключение, если поле для денег является пустым
                    price.requestFocus();
                }
            }
        });
    }

//    private void display(int number) {
//        TextView view = (TextView) findViewById(R.id.priceOther);
//
//        //заносим в массив
//        arr.add(Float.parseFloat(price.getText().toString()));
//
//        number1 = arr.get(0);
//        number2 = arr.get(1);
//
////        arr.removeAll(arr);
//        arr.add(number1 + number2);
//
////        view.setText("" + number);
//        view.setText(String.format("%.0f", arr.get(0)));
//
//        //запись цены
////        String otherPrice = price.getText().toString();
//
//        //переобразовываем в инт, счетаем, потом в преобразовываем в стринг и выводим в поле textview нашу сумму
////        int number = Integer.parseInt(otherPrice);
////        int i = number;
////        int result = i + i;
////        String s = String.valueOf(result);
////        ((TextView) findViewById(R.id.priceOther)).setText(s);
//    }

    //
    private class ItemsAdapter extends ArrayAdapter<Item> {
        //передаем контенкст главного активити
        public ItemsAdapter() {
            super(MainActivity.this, R.layout.item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final View view = getLayoutInflater().inflate(R.layout.item, null);
            final Item item = getItem(position);
            ((TextView)view.findViewById(R.id.name)).setText(item.name);
            ((TextView)view.findViewById(R.id.price)).setText(String.valueOf(item.price));

            return view;
        }
    }
}