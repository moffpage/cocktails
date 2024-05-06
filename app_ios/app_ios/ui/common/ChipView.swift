
import SwiftUI

struct ChipView: View {
    @EnvironmentObject
    private var theme: AppTheme
    
    private let text: String
    private let imageName: String?
    private var surfaceColor: Color
    private var contentColor: Color
    
    init(
        text: String,
        iconResource: String? = nil,
        surfaceColor: Color,
        contentColor: Color
    ) {
        self.text = text
        self.imageName = iconResource
        self.surfaceColor = surfaceColor
        self.contentColor = contentColor
    }
    
    var body: some View {
        return ZStack(alignment: .center) {
            HStack(
                alignment: .center,
                spacing: 8
            ) {
                if let imageName = imageName {
                    Image(imageName)
                        .foregroundColor(contentColor)
                }

                Text(text)
                    .modifier(theme.typography.h4(color: contentColor))
            }
            .frame(height: 40)
            .padding(.horizontal, 16)
        }
        .background(surfaceColor)
        .clipShape(Capsule())
    }
}
