import { registerPlugin } from '@capacitor/core';

import type { TikTokPluginPlugin } from './definitions';

const TikTokPlugin = registerPlugin<TikTokPluginPlugin>('TikTokPlugin', {
  web: () => import('./web').then((m) => new m.TikTokPluginWeb()),
});

export * from './definitions';
export { TikTokPlugin };
