
import shared
import Lottie
import SwiftUI

struct LoadingView: View {
    @EnvironmentObject
    private var theme: AppTheme
    
    private let alpha: Double
    private let enableInput: Bool
    private let backgroundColor: SwiftUI.Color
    
    init(
        alpha: Double = 0.5,
        enableInput: Bool = false,
        backgroundColor: SwiftUI.Color
    ) {
        self.alpha = alpha
        self.enableInput = enableInput
        self.backgroundColor = backgroundColor
    }
    
    var body: some View {
        ZStack(alignment: .center) {
            LottieView(
                animation: LottieAnimation.filepath(
                    Animations.shared.cocktail.path
                )
            )
            .looping()
            .resizable()
            .aspectRatio(contentMode: .fit)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(backgroundColor.opacity(alpha))
        .allowsHitTesting(enableInput)
    }
}
