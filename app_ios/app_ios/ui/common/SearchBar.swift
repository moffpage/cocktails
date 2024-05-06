
import SwiftUI

struct SearchBar: View {
    @EnvironmentObject
    private var theme: AppTheme
    
    private let value: String
    private let onValueChange: (String) -> Void
    private let placeholder: String
    private let showTrailingItem: Bool
    private let onTrailingItemClicked: (() -> Void)?
    
    init(
        value: String,
        onValueChange: @escaping (String) -> Void,
        placeholder: String,
        showTrailingContent: Bool,
        onTrailingItemClicked: (() -> Void)?
    ) {
        self.value = value
        self.onValueChange = onValueChange
        self.placeholder = placeholder
        self.showTrailingItem = showTrailingContent
        self.onTrailingItemClicked = onTrailingItemClicked
    }
    
    init(
        value: String,
        onValueChange: @escaping (String) -> Void,
        placeholder: String,
        onTrailingItemClicked: (() -> Void)?
    ) {
        self.value = value
        self.onValueChange = onValueChange
        self.placeholder = placeholder
        self.showTrailingItem = !value.isEmpty
        self.onTrailingItemClicked = onTrailingItemClicked
    }
    
    var body: some View {
        HStack(alignment: .center) {
            Image("magnifyingglass")
                .foregroundColor(theme.colors.onSurface)
            
            TextField(
                "",
                text: Binding(
                    get: { value },
                    set: { text in
                        onValueChange(text)
                    }
                )
            )
            .placeholder(visible: value.isEmpty) {
                Text(placeholder)
                    .modifier(theme.typography.h4())
            }
            .modifier(theme.typography.h4(color: theme.colors.onBackground))
            .accentColor(theme.colors.primary)
            
            if showTrailingItem {
                Image("close")
                    .foregroundColor(theme.colors.onSurface)
                    .onTapGesture {
                        onTrailingItemClicked?()
                    }
            }
        }
        .padding(.all, 8)
        .background(theme.colors.surface)
        .clip(with: theme.shapes.medium)
    }
}
