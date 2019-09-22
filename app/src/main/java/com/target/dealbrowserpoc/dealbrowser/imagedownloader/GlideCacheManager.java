package com.target.dealbrowserpoc.dealbrowser.imagedownloader;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import androidx.annotation.NonNull;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Set configurations to Glide library for loading Images.
 */
@GlideModule
public class GlideCacheManager  extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888));
        final int CACHE_DISK_SIZE = 10 * 1024 * 1024; // set external cache size - 10Mb
        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, CACHE_DISK_SIZE));

        final int CACHE_MEMORY_SIZE = 10 * 1024 * 1024; // set memory cache size - 10Mb
        builder.setMemoryCache(new LruResourceCache(CACHE_MEMORY_SIZE));
    }


        @Override
        public void registerComponents(Context context, Glide glide, Registry registry) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS);

            OkHttpClient okHttpClient = builder.build();

            registry.replace(GlideUrl.class, InputStream.class,
                    new OkHttpUrlLoader.Factory(okHttpClient));
        }
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

}