package com.developer.and.creator.moneytrackermobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**Результат который заносится в масив для обработки (массив нужен для сравнения чисел полученых ранее с новыми)*/
    ArrayList<Float> arr = new ArrayList<Float>();
    /**Введенное число*/
    float number1;
    /**Ранее полученое число*/
    float number2;

    /**флаг для проверки введеного числа (одно или два)**/
    boolean countFiledNumber = false;

    /**список товаров**/
    static class Item {

        String name;
        float price;

        Item(String name, float price) {
            this.name = name;
            this.price = price;
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        /**определеяем наши элементы из xml main**/
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText price = (EditText) findViewById(R.id.price);
        final Button add = (Button) findViewById(R.id.add);
        final ListView items = (ListView) findViewById(R.id.items);

        /**создаем адаптер для ListView что бы передавать в него полученные данные**/
        final ItemsAdapter adapter = new ItemsAdapter();
        items.setAdapter(adapter);

        /**слушатель по кнопке**/
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    adapter.add(new Item(name.getText().toString(), Float.valueOf(price.getText().toString())));

                    //заносим в массив
                    arr.add(Float.parseFloat(price.getText().toString()));

                    //проверяем флаг на количество введеных раз чисел
                    if (countFiledNumber == false) {
                        ((TextView) findViewById(R.id.priceOther)).setText(arr.get(0).toString());

                        //обнуляем поля
                        name.setText("");
                        price.setText("");
                        countFiledNumber = true;

                        //возвращаем фокус на имя
                        name.requestFocus();
                    } else {
                        //меняем флаг в зависимости если уже раз хотя бы вводили
                        countFiledNumber = true;

                        //заносим наши числа
                        number1 = arr.get(0);
                        number2 = arr.get(1);

                        arr.removeAll(arr);
                        arr.add(number1 + number2);
                        ((TextView) findViewById(R.id.priceOther)).setText(arr.get(0).toString());
                    }

                    //обнуляем поля
                    name.setText("");
                    price.setText("");

                    //возвращаем фокус на имя
                    name.requestFocus();

                } catch (Exception e) {
                    //исключение, если поле для денег является пустым
                    price.requestFocus();
                    //выводим сообщение об ошибке
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Проверьте правильность набора цены", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }

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