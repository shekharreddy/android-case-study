package com.target.dealbrowserpoc.dealbrowser.imagedownloader;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;


import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Common Image Manager to load all app specific images/thumbnails
 */
public class GlideImageDownloader {
    private static final String TAG = "GlideImageDownloader";
    private final static int crossFadeDuration = 700;

    /**
     * To load a ImageView without fallback title text.
     *
     * @param context          - Should be activity context, so that Image request will be cancelled when Activity/Fragment is stopped/destroyed.
     * @param imageUrl         - Image URL to get Poster
     * @param imageView        - To Show Poster/Thumbnail
     */
    public static void loadImage(Context context, String imageUrl, int errorResource, ImageView imageView, String id) {
        Log.d(TAG, "imageUrl  " + imageUrl);
        try {
            GlideApp.with(context)
                    .load(imageUrl)
                    .dontTransform()
                    .error(errorResource)
                    .placeholder(errorResource)
                    .disallowHardwareConfig()
                    .transition(withCrossFade(crossFadeDuration))
                    .skipMemoryCache(false)
                    .signature(new ObjectKey(id))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        } catch (Exception ex) {
            Log.e(TAG, "Glide-tag not found for Image URL " + imageUrl);
        }
    }


}
