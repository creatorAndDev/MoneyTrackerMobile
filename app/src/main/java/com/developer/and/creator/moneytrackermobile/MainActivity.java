package com.developer.and.creator.moneytrackermobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //список товаров
    static class Item {

        String name;
        int price;

        Item(String name, int price) {
            this.name = name;
            this.price = price;
        };
    }
    //

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //определеяем нажи элементы из xml main
        final EditText name = (EditText) findViewById(R.id.name);
        final EditText price = (EditText) findViewById(R.id.price);
        final Button add = (Button) findViewById(R.id.add);
        final ListView items = findViewById(R.id.items);

        //
        //animations
//        final AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
//        alphaAnimation.setDuration(1000);
//
//        final AnimationSet setAnimation = new AnimationSet(true);
//        setAnimation.addAnimation(alphaAnimation);
//
//        setupAnimation(add, alphaAnimation, R.anim.alpha_anim);

        //

        //создаем адаптер для ListView что бы передавать в него полученные данные
        final ItemsAdapter adapter = new ItemsAdapter();
        items.setAdapter(adapter);

        //слушатель по кнопке
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    adapter.add(new Item(name.getText().toString(), Integer.valueOf(price.getText().toString())));
                    name.setText("");
                    price.setText("");
                } catch (Exception e) {
                    //исключение, если поле является пустым
                }
            }
        });
    }

    //animations
//    private void setupAnimation(View view, final Animation animation, final int animationID) {
//        view.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                AnimationUtils.loadAnimation(ViewAnimations.this.animationID) : animation);
////                v.startAnimation(mChrckBox.isChecked) ?
////                        AnimationUtils.loadAnimation(ViewAnimations.this.animationID) : animation);
//            }
//        });
//    }

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