//
//  Theme.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 10.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

protocol Theme: Equatable {
    var mode: Mode { get }
    var shapes: Shapes { get }
    var colors: ColorPalette { get }
    var typography: Typography { get }
}
