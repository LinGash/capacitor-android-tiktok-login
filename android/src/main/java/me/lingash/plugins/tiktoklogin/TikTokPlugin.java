package me.lingash.plugins.tiktoklogin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.tiktok.open.sdk.auth.AuthApi;
import com.tiktok.open.sdk.auth.AuthRequest;
import com.tiktok.open.sdk.auth.AuthResponse;

import com.getcapacitor.Plugin;
import com.getcapacitor.JSObject;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.PluginCall;
@CapacitorPlugin(name = "TikTokPlugin")
public class TikTokPlugin extends Plugin {

    private AuthApi authApi;
    private PluginCall savedCall;

    @Override
    public void load() {
        super.load();
        authApi = new AuthApi(getActivity());  // Initialize AuthApi
    }

    @PluginMethod
    public void login(PluginCall call) {
        String clientKey = call.getString("clientKey");
        String redirectUri = call.getString("redirectUri");
        String codeVerifier = call.getString("codeVerifier");

        if (clientKey == null || redirectUri == null || codeVerifier == null) {
            call.reject("Missing required parameters");
            return;
        }

        this.savedCall = call; // Save the call to resolve later

        // Create and configure the AuthRequest
        AuthRequest request = new AuthRequest(clientKey, "user.info.basic", redirectUri, codeVerifier, false, null,null);

        // Start the TikTok authorization process
        authApi.authorize(request, AuthApi.AuthMethod.TikTokApp);
    }

    @Override
    protected void handleOnNewIntent(Intent intent) {
        super.handleOnNewIntent(intent);

        if (savedCall == null) {
            return;
        }

        String redirectUri = savedCall.getString("redirectUri");
        Uri data = intent.getData();

        if (data != null && data.toString().startsWith(redirectUri)) {
            // Handle the authorization response
            AuthResponse response = authApi.getAuthResponseFromIntent(intent, redirectUri);
    
            if (response != null) {
                JSObject ret = new JSObject();
                ret.put("authCode", response.getAuthCode());
                ret.put("grantedPermissions", response.getGrantedPermissions());
                ret.put("authError", response.getAuthError());
                ret.put("authErrorDescription", response.getAuthErrorDescription());
                savedCall.resolve(ret);
            } else {
                savedCall.reject("Failed to retrieve TikTok auth response");
            }
        } else {
            if (savedCall != null) {
                savedCall.reject("Invalid redirect URI");
                savedCall = null;
            }
        }
    }
}