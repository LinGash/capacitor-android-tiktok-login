export interface TikTokPlugin {
    login(options: { clientKey: string; redirectUri: string; codeVerifier: string }): Promise<{ authCode?: string; grantedPermissions?: string[]; authError?: string; authErrorDescription?: string }>;
}