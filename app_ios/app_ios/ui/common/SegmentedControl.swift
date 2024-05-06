
import shared
import SwiftUI

struct SegmentedControl: View {
    @EnvironmentObject
    private var theme: AppTheme
    
    private let titles: [String]
    private let selected: (Int) -> Bool
    private let onSegmentClick: (Int) -> Void
    
    init(
        titles: [String],
        selected: @escaping (Int) -> Bool,
        onSegmentClick: @escaping (Int) -> Void
    ) {
        self.titles = titles
        self.selected = selected
        self.onSegmentClick = onSegmentClick
    }
    
    var body: some View {
        ZStack {
            HStack {
                ForEach(
                    Array(titles.enumerated()),
                    id: \.element
                ) { index, title in
                    SegmentView(
                        title: title,
                        isSelected: selected(index)
                    )
                    .clipShape(Capsule())
                    .onTapGesture { onSegmentClick(index) }
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.all, 2)
        }
        .background(theme.colors.surface)
        .clipShape(Capsule())
    }
}

private struct SegmentView: View {
    @EnvironmentObject
    private var theme: AppTheme
    
    private let title: String
    private let isSelected: Bool
    
    init(title: String, isSelected: Bool) {
        self.title = title
        self.isSelected = isSelected
    }
    
    var body: some View {
        ZStack(alignment: .center) {
            Text(title)
                .modifier(
                    theme.typography.h4(
                        color: isSelected ?
                        theme.colors.onPrimary :
                        theme.colors.onSurface
                    )
                )
                .frame(maxWidth: .infinity)
                .padding(.vertical, 8)
                .padding(.horizontal, 10)
                .lineLimit(1)
                .multilineTextAlignment(.center)
        }
        .clipShape(Capsule())
        .background(
            isSelected ?
                theme.colors.primary :
                theme.colors.surface
        )
    }
}
