import { WebPlugin } from '@capacitor/core';

import type { ocrPlugin } from './definitions';

export class ocrWeb extends WebPlugin implements ocrPlugin {
  // @ts-ignore
  async scan(image: string ): Promise<{ text: string }> {
    return {
      text: "not implemented"
    }
  }
}
