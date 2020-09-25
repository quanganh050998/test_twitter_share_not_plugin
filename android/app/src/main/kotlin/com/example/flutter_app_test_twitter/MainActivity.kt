package com.example.flutter_app_test_twitter

//import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.annotation.NonNull
//import android.os.Build
//import android.util.Log
//import androidx.core.content.FileProvider
import io.flutter.embedding.android.FlutterActivity
//import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
//import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
//import io.flutter.plugin.common.PluginRegistry.Registrar
//import java.io.File

import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.share.Sharer;
//import com.facebook.share.model.ShareLinkContent;
//import com.facebook.share.model.SharePhoto;
//import com.facebook.share.model.SharePhotoContent;
//import com.facebook.share.widget.ShareDialog;
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant


class MainActivity: FlutterActivity() {
//    private val INSTAGRAM_PACKAGE_NAME = "com.instagram.android"
//    private val FACEBOOK_PACKAGE_NAME = "com.facebook.katana"
    private val TWITTER_PACKAGE_NAME = "com.twitter.android"

    private val TWITTER_REQUEST_CODE = 0xc0ce
    private val INSTAGRAM_REQUEST_CODE = 0xc0c3

//    private var activity: Activity? = null
    private var channel: MethodChannel? = null
    private var callbackManager: CallbackManager? = null

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        Log.d("Cho nay vao toDOShare" , "Da vao")
        GeneratedPluginRegistrant.registerWith(flutterEngine)
//        channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "social_share_plugin")
//        channel?.setMethodCallHandler { call, result ->
//           run {
//               toDoShare(call, result)
//
//           }
//
//        }
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "social_share_technixo").setMethodCallHandler { call, result ->
            run {
                toDoShare(call, result)

            }

        }

    }


    private  fun toDoShare(call: MethodCall, result: MethodChannel.Result){

        val pm = this.activity!!.packageManager

        if ( call.method ==  "shareToTwitterLink"){
            try {
                pm.getPackageInfo(TWITTER_PACKAGE_NAME, PackageManager.GET_ACTIVITIES)
                twitterShareLink(call.argument<String>("text"), call.argument<String>("url"))
                result.success(true)
            } catch (e: PackageManager.NameNotFoundException) {
                openPlayStore(TWITTER_PACKAGE_NAME)
                result.success(false)
            }

        }

    }

//    fun SocialSharePlugin() {
//
//        callbackManager = CallbackManager.Factory.create()
//
//    }
//
//    fun onAttachedToEngine(binding: FlutterPluginBinding) {
//
//        channel!!.setMethodCallHandler(this)
//    }
//
//    fun onDetachedFromEngine(binding: FlutterPluginBinding) {}
//
//    fun onAttachedToActivity(binding: ActivityPluginBinding) {
//        binding.addActivityResultListener(this)
//        activity = binding.activity
//    }
//
//    fun onDetachedFromActivityForConfigChanges() {}
//
//    fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
//        binding.removeActivityResultListener(this)
//        binding.addActivityResultListener(this)
//    }
//
//    fun onDetachedFromActivity() {}

    /**
     * Plugin registration.
     */
//    fun registerWith(registrar: Registrar) {
//        val channel = MethodChannel(registrar.messenger(), "social_share_plugin")
//        val plugin = SocialSharePlugin()
//        plugin.channel = channel
//        plugin.activity = registrar.activity()
//        channel.setMethodCallHandler(plugin)
//    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
//        if (requestCode == TWITTER_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                Log.d("SocialSharePlugin", "Twitter share done.")
//                channel!!.invokeMethod("onSuccess", null)
//            } else if (resultCode == RESULT_CANCELED) {
//                Log.d("SocialSharePlugin", "Twitter cancelled.")
//                channel!!.invokeMethod("onCancel", null)
//            }
//            return true
//        }
//        if (requestCode == INSTAGRAM_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                Log.d("SocialSharePlugin", "Instagram share done.")
//                channel!!.invokeMethod("onSuccess", null)
//            } else {
//                Log.d("SocialSharePlugin", "Instagram share failed.")
//                channel!!.invokeMethod("onCancel", null)
//            }
//            return true
//        }
//        return callbackManager.onActivityResult(requestCode, resultCode, data)
//    }
//

//    fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
//        val pm = activity!!.packageManager
//        when (call.method) {
//            "getPlatformVersion" -> result.success("Android " + Build.VERSION.RELEASE)
//            "shareToFeedInstagram" -> try {
//                pm.getPackageInfo(INSTAGRAM_PACKAGE_NAME, PackageManager.GET_ACTIVITIES)
//                instagramShare(call.argument<String>("type"), call.argument<String>("path"))
//                result.success(true)
//            } catch (e: PackageManager.NameNotFoundException) {
//                openPlayStore(INSTAGRAM_PACKAGE_NAME)
//                result.success(false)
//            }
//            "shareToFeedFacebook" -> try {
//                pm.getPackageInfo(FACEBOOK_PACKAGE_NAME, PackageManager.GET_ACTIVITIES)
//                facebookShare(call.argument<String>("caption"), call.argument<String>("path"))
//                result.success(true)
//            } catch (e: PackageManager.NameNotFoundException) {
//                openPlayStore(FACEBOOK_PACKAGE_NAME)
//                result.success(false)
//            }
//            "shareToFeedFacebookLink" -> try {
//                pm.getPackageInfo(FACEBOOK_PACKAGE_NAME, PackageManager.GET_ACTIVITIES)
//                facebookShareLink(call.argument<String>("quote"), call.argument<String>("url"))
//                result.success(true)
//            } catch (e: PackageManager.NameNotFoundException) {
//                openPlayStore(FACEBOOK_PACKAGE_NAME)
//                result.success(false)
//            }
//            "shareToTwitterLink" -> try {
//                pm.getPackageInfo(TWITTER_PACKAGE_NAME, PackageManager.GET_ACTIVITIES)
//                twitterShareLink(call.argument<String>("text"), call.argument<String>("url"))
//                result.success(true)
//            } catch (e: PackageManager.NameNotFoundException) {
//                openPlayStore(TWITTER_PACKAGE_NAME)
//                result.success(false)
//            }
//            else -> result.notImplemented()
//        }
//    }

    private fun openPlayStore(packageName: String) {
        try {
            val playStoreUri: Uri = Uri.parse("market://details?id=$packageName")
            val intent = Intent(Intent.ACTION_VIEW, playStoreUri)
            activity!!.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val playStoreUri: Uri = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            val intent = Intent(Intent.ACTION_VIEW, playStoreUri)
            activity!!.startActivity(intent)
        }
    }

//    private fun instagramShare(type: String?, imagePath: String?) {
//        val image = File(imagePath)
//        val uri: Uri = FileProvider.getUriForFile(activity!!, activity!!.packageName + ".social.share.fileprovider",
//                image)
//        val share = Intent(Intent.ACTION_SEND)
//        share.type = type
//        share.putExtra(Intent.EXTRA_STREAM, uri)
//        share.setPackage(INSTAGRAM_PACKAGE_NAME)
//        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        val chooser = Intent.createChooser(share, "Share to")
//        val resInfoList = activity!!.packageManager.queryIntentActivities(chooser,
//                PackageManager.MATCH_DEFAULT_ONLY)
//        for (resolveInfo in resInfoList) {
//            val packageName = resolveInfo.activityInfo.packageName
//            activity!!.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        }
//        activity!!.startActivityForResult(chooser, INSTAGRAM_REQUEST_CODE)
//    }
//
//    private fun facebookShare(caption: String?, mediaPath: String?) {
//        val media = File(mediaPath)
//        val uri: Uri = FileProvider.getUriForFile(activity!!, activity!!.packageName + ".social.share.fileprovider",
//                media)
//        val photo: SharePhoto = Builder().setImageUrl(uri).setCaption(caption).build()
//        val content: SharePhotoContent = Builder().addPhoto(photo).build()
//        val shareDialog = ShareDialog(activity)
//        shareDialog.registerCallback(callbackManager, object : FacebookCallback<Sharer.Result?>() {
//            fun onSuccess(result: Sharer.Result?) {
//                channel!!.invokeMethod("onSuccess", null)
//                Log.d("SocialSharePlugin", "Sharing successfully done.")
//            }
//
//            fun onCancel() {
//                channel!!.invokeMethod("onCancel", null)
//                Log.d("SocialSharePlugin", "Sharing cancelled.")
//            }
//
//            fun onError(error: FacebookException) {
//                channel!!.invokeMethod("onError", error.getMessage())
//                Log.d("SocialSharePlugin", "Sharing error occurred.")
//            }
//        })
//        if (ShareDialog.canShow(SharePhotoContent::class.java)) {
//            shareDialog.show(content)
//        }
//    }
//
//    private fun facebookShareLink(quote: String?, url: String?) {
//        val uri: Uri = Uri.parse(url)
//        val content: ShareLinkContent = Builder().setContentUrl(uri).setQuote(quote).build()
//        val shareDialog = ShareDialog(activity)
//        shareDialog.registerCallback(callbackManager, object : FacebookCallback<Sharer.Result?>() {
//            fun onSuccess(result: Sharer.Result?) {
//                channel!!.invokeMethod("onSuccess", null)
//                Log.d("SocialSharePlugin", "Sharing successfully done.")
//            }
//
//            fun onCancel() {
//                channel!!.invokeMethod("onCancel", null)
//                Log.d("SocialSharePlugin", "Sharing cancelled.")
//            }
//
//            fun onError(error: FacebookException) {
//                channel!!.invokeMethod("onError", error.getMessage())
//                Log.d("SocialSharePlugin", "Sharing error occurred.")
//            }
//        })
//        if (ShareDialog.canShow(ShareLinkContent::class.java)) {
//            shareDialog.show(content)
//        }
//    }

    private fun twitterShareLink(text: String?, url: String?) {
        val tweetUrl = String.format("https://twitter.com/intent/tweet?text=%s&url=%s", text, url)
        val uri: Uri = Uri.parse(tweetUrl)
        activity!!.startActivityForResult(Intent(Intent.ACTION_VIEW, uri), TWITTER_REQUEST_CODE)
    }
}










