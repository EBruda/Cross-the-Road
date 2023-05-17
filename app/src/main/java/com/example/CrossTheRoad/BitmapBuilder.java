package com.example.CrossTheRoad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

public class BitmapBuilder {
    private static BitmapBuilder singleInstance = null;

    // Declaring a variable of type HashMap
    private final HashMap<ObjectType, Bitmap> bitmapSet;

    // Creating a private constructor restricted to this class itself
    private BitmapBuilder(Context context) {
        bitmapSet = new HashMap<>();

        Bitmap princess = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.princesstrans), 125, 100, false);
        bitmapSet.put(ObjectType.PRINCESS, princess);
        Bitmap king = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.kingtrans), 125, 100, false);
        bitmapSet.put(ObjectType.KING, king);
        Bitmap jester = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.jestertrans), 125, 100, false);
        bitmapSet.put(ObjectType.JESTER, jester);
        Bitmap executioner = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.executionertrans), 125, 100, false);
        bitmapSet.put(ObjectType.EXECUTIONER, executioner);
        Bitmap wizard = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.wizardtrans), 125, 100, false);
        bitmapSet.put(ObjectType.WIZARD, wizard);
        Bitmap knight = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(),
                R.mipmap.knighttrans), 125, 100, false);
        bitmapSet.put(ObjectType.KNIGHT, knight);
        bitmapSet.put(ObjectType.ROAD, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.road));
        bitmapSet.put(ObjectType.WATER, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.water));
        bitmapSet.put(ObjectType.WATER2, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.water2));
        bitmapSet.put(ObjectType.PAVEMENT, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.pavement));
        bitmapSet.put(ObjectType.GRASS, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.grass));
        bitmapSet.put(ObjectType.LIFE, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.life2));
        bitmapSet.put(ObjectType.GOAL, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher_round));
        bitmapSet.put(ObjectType.HORSE, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.horse));
        bitmapSet.put(ObjectType.CARRIAGE, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.carriage));
        bitmapSet.put(ObjectType.WAGON, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.wagon));
        bitmapSet.put(ObjectType.LOSTLIFE, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.lostlife));
        bitmapSet.put(ObjectType.SHORTLOG, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.barrel));
        bitmapSet.put(ObjectType.MEDIUMLOG, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.log));
        bitmapSet.put(ObjectType.LONGLOG, BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.dragon));
    }

    // Static method to create instance of Singleton class
    public static BitmapBuilder getInstance(Context context) {
        if (singleInstance == null) {
            singleInstance = new BitmapBuilder(context);
        }

        return singleInstance;
    }

    public HashMap<ObjectType, Bitmap> getBitmapSet() {
        return bitmapSet;
    }
}
