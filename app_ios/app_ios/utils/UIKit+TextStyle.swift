
import shared
import UIKit

extension UILabel {
    convenience init(text: StringResource) {
        self.init(frame: .zero)
        self.text = text.desc().localized()
    }
    
    func setTextStyle(style textStyle: TextStyle) {
        font = textStyle.fontFamily.fonts.first?.resource.uiFont(withSize: textStyle.fontSize)
    }
}

extension UIButton {
    func setTitle(_ title: StringResource, for state: UIControl.State = .normal) {
        setTitle(title.desc().localized(), for: state)
    }
}

extension UITextView {
    func setTextStyle(style textStyle: TextStyle) {
        font = textStyle.fontFamily.fonts.first?.resource.uiFont(withSize: textStyle.fontSize)
    }
}

extension UITextField {
    func setTextStyle(style textStyle: TextStyle) {
        font = textStyle.fontFamily.fonts.first?.resource.uiFont(withSize: textStyle.fontSize)
    }
}

extension String {
    func image(textStyle: TextStyle, size: CGSize? = nil) -> UIImage {
        let nsString = self as NSString
        let attributes: [NSAttributedString.Key: Any] = [
            .font: textStyle.fontFamily.fonts[0].resource.uiFont(withSize: textStyle.fontSize)
        ]
        let size = size ?? nsString.size(withAttributes: attributes)
        return UIGraphicsImageRenderer(size: size).image { _ in
            nsString.draw(in: CGRect(origin: .zero, size: size),
                                    withAttributes: attributes)
        }
    }
}
