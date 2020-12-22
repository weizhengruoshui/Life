package com.stephen.combination.common.manager.fragment

import android.net.Uri

data class FragmentToken(val tag: String, val isBackStack: Boolean = false, val uri: Uri? = null)