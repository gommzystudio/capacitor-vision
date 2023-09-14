# Capacitor-Vision: OCR Plugin for Capacitor with Apple Vision & Google ML Kit

Unlock powerful Optical Character Recognition (OCR) capabilities in your Capacitor applications. With `capacitor-vision`, tap into Apple Vision for iOS and Google's ML Kit for Android to seamlessly extract text from images.

## Installation

Integrate `capacitor-vision` into your project with just a few commands:

```bash
npm install capacitor-vision
npx cap sync
```

## Usage

### OCR Scan API

Effortlessly extract text from images with the `scan` method.

#### Method Signature:

```typescript
scan(options: { image: string; }) => Promise<{ text: string[]; }>
```

#### Parameters:

- **`options`**: Object containing:
    - **`image`**: A base64 encoded string of the image you wish to process.

#### Returns:

- A promise resolving to an object containing an array of extracted text strings.

#### Example:

Extract text from a base64 formatted image:

```javascript
import { CapacitorVision } from 'capacitor-vision';

const imageBase64 = "YOUR_BASE64_ENCODED_IMAGE_HERE";

CapacitorVision.scan({ image: imageBase64 })
  .then(response => {
    console.log(response.text);  // Logs the extracted text from the image
  })
  .catch(error => {
    console.error("OCR error:", error);
  });
```

### Apps Using `capacitor-vision`

- [**`AiTutor`**](https://apps.apple.com/us/app/aitutor-hausaufgaben-lösen/id1668208873) - A homework helper app that uses `capacitor-vision` to extract text from images of math problems.

Are you using `capacitor-vision` in your app? Submit a PR to add it to the list!
