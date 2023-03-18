//
//  UIColor.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 18.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

extension common.Color_ {
    func toUIColor() -> UIColor {
        return UIColor(hex: UInt32(hexValue))
    }
}

extension common.ColorScheme {
    func toColorPalette() -> ColorPalette {
        return ColorPalette(
            primary: self.primary.toUIColor(),
            onPrimary: self.onPrimary.toUIColor(),
            surface: self.surface.toUIColor(),
            onSurface: self.onSurface.toUIColor(),
            background: self.background.toUIColor(),
            onBackground: self.onBackground.toUIColor(),
            secondary: self.secondary.toUIColor(),
            error: self.error.toUIColor()
        )
    }
}
