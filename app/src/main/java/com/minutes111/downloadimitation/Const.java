package com.minutes111.downloadimitation;

import com.minutes111.downloadimitation.model.Image;

import java.util.ArrayList;

/**
 * Created by barikos on 25.04.16.
 */
public class Const {
    public static final String BROADCAST_ACTION = "com.minutes111.downloadimitation.smth";

    public static final String ATTR_IMAGES = "images";

    public static final int[] IMAGES_BOOK = {
            R.drawable.img_book_morphine,
            R.drawable.img_book_farewell_to_arms,
            R.drawable.img_book_fom_whom,
            R.drawable.img_book_orange,
            R.drawable.img_book_1984,
            R.drawable.img_book_don,
            R.drawable.img_book_game,
            R.drawable.img_book_picnic,
            R.drawable.img_book_hard_to_be,
            R.drawable.img_book_comp};

    public static ArrayList<Image> getData() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < IMAGES_BOOK.length; i++) {
            arrayList.add(new Image().setImage(IMAGES_BOOK[i]));
        }
        return arrayList;
    }
}
