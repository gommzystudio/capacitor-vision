export interface ocrPlugin {
  scan( image: string ): Promise<{ text: string }>;
}
