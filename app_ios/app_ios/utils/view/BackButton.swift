
import SwiftUI

struct BackButton: View {
    @EnvironmentObject
    private var theme: AppTheme
    
    private let action: () -> Void
    
    init(action: @escaping () -> Void) {
        self.action = action
    }
    
    var body: some View {
        ZStack(alignment: .center) {
            Image("back")
                .foregroundColor(theme.colors.onPrimary)
                .padding(8)
        }
        .background(theme.colors.primary.opacity(0.5))
        .clipShape(Circle())
        .onTapGesture(perform: action)
    }
}
