import { registerPlugin } from '@capacitor/core';

import type { ocrPlugin } from './definitions';

const ocr = registerPlugin<ocrPlugin>('ocr', {
  web: () => import('./web').then(m => new m.ocrWeb()),
});

export * from './definitions';
export { ocr };
