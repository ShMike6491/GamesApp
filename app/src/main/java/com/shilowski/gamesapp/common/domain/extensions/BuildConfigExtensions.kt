package com.shilowski.gamesapp.common.domain.extensions

import android.os.Build

val isApiTiramisuOrNewer: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU // 33