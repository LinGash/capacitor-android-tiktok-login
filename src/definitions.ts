export interface TikTokPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
