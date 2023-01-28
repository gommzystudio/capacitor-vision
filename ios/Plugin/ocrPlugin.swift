import Foundation
import Capacitor
import Vision



/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(ocrPlugin)
public class ocrPlugin: CAPPlugin {
    var detectedText: [String] = []
    
    @objc func scan(_ call: CAPPluginCall) {
        let image = call.getString("image") ?? ""
        DispatchQueue.global(qos: .userInitiated).async {
            guard let stringData = Data(base64Encoded: image),
                  let uiImage = UIImage(data: stringData) else {
                      print("Error: couldn't create UIImage")
                      return
                  }
            
            // Get the CGImage on which to perform requests.
            guard let cgImage = uiImage.cgImage else {
                print("Error: couldn't get cgImage")
                return }

            // Create a new image-request handler.
            let requestHandler = VNImageRequestHandler(cgImage: cgImage)

            do {
                // Perform the text-recognition request.
                try requestHandler.perform([self.textDetectionRequest])
            } catch {
                print("Unable to perform the requests: \(error).")
            }
            
            call.resolve(["text": self.detectedText])
        }
    }
    
    lazy var textDetectionRequest: VNRecognizeTextRequest = {
        // Specifying the image analysis request to perform - text detection here
        let textDetectRequest = VNRecognizeTextRequest(completionHandler: recognizeTextHandler)
        return textDetectRequest
    }()
    
    func recognizeTextHandler(request: VNRequest?, error: Error?) {
        guard let observations = request?.results as? [VNRecognizedTextObservation] else {
            return
        }
        let recognizedStrings = observations.compactMap { observation in
            // Return the string of the top VNRecognizedText instance.
            return observation.topCandidates(1).first?.string as String?
        }
        
        self.detectedText = recognizedStrings;
    }
}
