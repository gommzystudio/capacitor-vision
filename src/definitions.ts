
export interface ocrPlugin {
  scan(options: {image: string}): Promise<{ text: string[] }>;
}
