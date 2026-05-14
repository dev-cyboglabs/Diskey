package com.cyboglabs.diskey.presentation.scan;

import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import com.cyboglabs.diskey.domain.model.ConnectionState;
import com.cyboglabs.diskey.domain.model.Device;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a \u0010\b\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007\u00a8\u0006\f"}, d2 = {"DeviceCard", "", "device", "Lcom/cyboglabs/diskey/domain/model/Device;", "isConnecting", "", "onConnect", "Lkotlin/Function0;", "ScanScreen", "onDeviceConnected", "viewModel", "Lcom/cyboglabs/diskey/presentation/scan/ScanViewModel;", "app_debug"})
public final class ScanScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ScanScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeviceConnected, @org.jetbrains.annotations.NotNull()
    com.cyboglabs.diskey.presentation.scan.ScanViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DeviceCard(com.cyboglabs.diskey.domain.model.Device device, boolean isConnecting, kotlin.jvm.functions.Function0<kotlin.Unit> onConnect) {
    }
}