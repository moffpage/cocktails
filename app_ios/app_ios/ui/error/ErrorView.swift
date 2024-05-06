
import shared
import SwiftUI

struct ErrorView: View {
    @EnvironmentObject
    private var theme: AppTheme
    
    private let onRetry: () -> Void
    private let errorText: String
    
    init(onRetry: @escaping () -> Void, errorText: String) {
        self.onRetry = onRetry
        self.errorText = errorText
    }
    
    var body: some View {
        ZStack(alignment: .center) {
            VStack(alignment: .center, spacing: 16) {
                Image("error")
                    .frame(width: 48, height: 48)
                    .foregroundColor(theme.colors.error)
                
                Text(errorText)
                    .modifier(theme.typography.h3())
                
                Button {
                    onRetry()
                } label: {
                    Text(UiComponentsStrings.shared.retry
                        .desc().localized())
                        .modifier(theme.typography.body1(color: theme.colors.onPrimary))
                        .padding(.vertical, 8)
                        .padding(.horizontal, 65.5)
                }
                .background(theme.colors.primary)
                .clip(with: RoundedCornerShape(cornerRadius: 22))
            }
            .padding(32)
        }
        .frame(maxWidth: .infinity)
        .background(theme.colors.surface)
        .clip(with: theme.shapes.large)
    }
}
