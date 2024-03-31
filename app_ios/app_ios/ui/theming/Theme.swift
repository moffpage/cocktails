
import shared
import UIKit

protocol Theme: Equatable {
    var mode: Mode { get }
    var shapes: Shapes { get }
    var colors: ColorPalette { get }
    var typography: Typography { get }
}
