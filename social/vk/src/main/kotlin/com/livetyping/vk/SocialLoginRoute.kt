package com.livetyping.vk

import android.app.Activity
import android.content.Intent
import com.livetyping.logincore.SocialLoginError


interface SocialLoginRoute {

    fun login(activity: Activity)

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?,
                         successBlock: (token: String) -> Unit,
                         errorBlock: ((error: SocialLoginError) -> Unit)? = null)
}