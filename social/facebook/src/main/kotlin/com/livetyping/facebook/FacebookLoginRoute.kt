package com.livetyping.facebook

import android.app.Activity
import android.content.Intent
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.livetyping.logincore.SocialLoginError
import com.livetyping.logincore.SocialLoginRoute
import java.util.*


class FacebookLoginRoute : SocialLoginRoute {

    private val callbackManager = CallbackManager.Factory.create()

    override fun login(activity: Activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?,
                                  successBlock: (token: String) -> Unit,
                                  errorBlock: ((error: SocialLoginError) -> Unit)?) {
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult?) {
                result?.let { successBlock.invoke(result.accessToken.token) }
            }

            override fun onCancel() {
                LoginManager.getInstance().unregisterCallback(callbackManager)
            }

            override fun onError(error: FacebookException?) {
                error?.let {
                    errorBlock?.invoke(FacebookLoginError(error))
                }
            }

        })
        callbackManager.onActivityResult(requestCode, resultCode, data)

    }
}