import { WebPlugin } from '@capacitor/core';

import type { TikTokPluginPlugin } from './definitions';

export class TikTokPluginWeb extends WebPlugin implements TikTokPluginPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
