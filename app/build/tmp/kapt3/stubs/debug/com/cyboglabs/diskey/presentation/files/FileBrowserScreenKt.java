package com.cyboglabs.diskey.presentation.files;

import androidx.compose.foundation.ExperimentalFoundationApi;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import com.cyboglabs.diskey.domain.model.AudioFile;
import timber.log.Timber;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aR\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a4\u0010\f\u001a\u00020\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007\u001a\u0010\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u000fH\u0003\u001a2\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001e\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001f"}, d2 = {"AudioFileCard", "", "file", "Lcom/cyboglabs/diskey/domain/model/AudioFile;", "isSelected", "", "isDownloading", "isDownloaded", "onCardClick", "Lkotlin/Function0;", "onLongClick", "onActionClick", "FileBrowserScreen", "onPlayFile", "Lkotlin/Function1;", "", "onBack", "viewModel", "Lcom/cyboglabs/diskey/presentation/files/FileBrowserViewModel;", "MetaChip", "text", "SectionHeader", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "title", "count", "", "iconTint", "Landroidx/compose/ui/graphics/Color;", "SectionHeader-g2O1Hgs", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;IJ)V", "app_debug"})
public final class FileBrowserScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.foundation.ExperimentalFoundationApi.class})
    @androidx.compose.runtime.Composable()
    public static final void FileBrowserScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onPlayFile, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBack, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.presentation.files.FileBrowserViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.foundation.ExperimentalFoundationApi.class})
    @androidx.compose.runtime.Composable()
    private static final void AudioFileCard(com.cyboglabs.diskey.domain.model.AudioFile file, boolean isSelected, boolean isDownloading, boolean isDownloaded, kotlin.jvm.functions.Function0<kotlin.Unit> onCardClick, kotlin.jvm.functions.Function0<kotlin.Unit> onLongClick, kotlin.jvm.functions.Function0<kotlin.Unit> onActionClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MetaChip(java.lang.String text) {
    }
}