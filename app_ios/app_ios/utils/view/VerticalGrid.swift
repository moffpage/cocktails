
import SwiftUI

struct VerticalGrid<Content: View, Item: Hashable>: View {
    private var items: [[Item]] = []
    
    private let columns: Int
    private let content: (Item) -> Content
    private let itemSpacing: CGFloat
    private let contentPadding: CGFloat
    
    init(
        items: [Item],
        columns: Int,
        itemSpacing: CGFloat,
        contentPadding: CGFloat,
        @ViewBuilder content: @escaping (Item) -> Content
    ) {
        self.columns = columns
        self.content = content
        self.itemSpacing = itemSpacing
        self.contentPadding = contentPadding
        self.setupItems(items)
    }
    
    var body: some View {
        VStack {
            ForEach(0 ..< items.count, id: \.self) { row in
                HStack {
                    let itemSpacing = (itemSpacing * CGFloat(columns - 1)) / CGFloat(columns)
                    let contentPadding = contentPadding * 2
                    let itemWidth = (UIScreen.main.bounds.width - itemSpacing - contentPadding) / CGFloat(columns)
                    
                    ForEach(items[row], id: \.self) { item in
                        self.content(item)
                            .frame(width: itemWidth)
                    }
                    
                    if items[row].count == 1 {
                        Spacer()
                            .frame(width: itemWidth)
                    }
                }
            }
        }
    }
    
    private mutating func setupItems(_ items: [Item]) {
        var column = 0
        var columnIndex = 0
        
        for item in items {
            if columnIndex < self.columns {
                if columnIndex == 0 {
                    self.items.insert([item], at: column)
                    columnIndex += 1
                } else {
                    self.items[column].append(item)
                    columnIndex += 1
                }
            } else {
                column += 1
                self.items.insert([item], at: column)
                columnIndex = 1
            }
        }
    }
}
