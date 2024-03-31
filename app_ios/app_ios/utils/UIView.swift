
import shared
import UIKit

extension UIView {
    func clip(to shape: RoundedCornerShape) {
        clipsToBounds = true
        layer.cornerRadius = shape.cornerRadius
    }
    
    func clipOval() {
        clipsToBounds = true
        layer.cornerRadius = bounds.height / 2
    }
}
