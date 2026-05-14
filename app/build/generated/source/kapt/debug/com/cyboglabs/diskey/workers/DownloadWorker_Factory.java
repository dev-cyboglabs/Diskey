package com.cyboglabs.diskey.workers;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DownloadWorker_Factory {
  public DownloadWorker_Factory() {
  }

  public DownloadWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams);
  }

  public static DownloadWorker_Factory create() {
    return new DownloadWorker_Factory();
  }

  public static DownloadWorker newInstance(Context context, WorkerParameters workerParams) {
    return new DownloadWorker(context, workerParams);
  }
}
